package org.guce.epayment.campost.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.guce.epayment.campost.documents.CARDDATA;
import org.guce.epayment.campost.documents.RESPONSEREASON;
import org.guce.epayment.campost.documents.TRANSNET;
import org.guce.epayment.campost.entities.CampostAccount;
import org.guce.epayment.campost.entities.CampostPayment;
import org.guce.epayment.campost.exceptions.NoCampostAccountException;
import org.guce.epayment.campost.repositories.CampostAccountRepository;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.Params;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.repositories.ParamsRepository;
import org.guce.epayment.core.repositories.PartnerRepository;
import org.guce.epayment.core.repositories.PaymentModeRepository;
import org.guce.epayment.core.repositories.PaymentRepository;
import org.guce.epayment.core.services.PaymentService;
import org.guce.epayment.core.util.parser.Parser;
import org.guce.epayment.core.util.parser.ParserConstants;
import org.guce.epayment.core.util.parser.ParserFactory;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.CoreUtils;
import org.guce.epayment.security.core.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractCampostService implements CampostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCampostService.class);

    protected static final String PM_CAMPOST_WALLET = "CAMPOST_WALLET".intern();
    protected static final String CAMPOST_SEQ = "CAMPOST_PAYMENT_SEQ".intern();
    protected static final String CAMPOST_PREFIX = "CM".intern();
    protected static final String CAMPOST_CODE = "CAMPOST_CODE".intern();
    protected static final String CAMPOST_BASE_URL = "CAMPOST_BASE_URL".intern();

    @Autowired
    private CampostAccountRepository campostAccountRepository;
    @Autowired
    private ParamsRepository paramsRepository;
    @Autowired
    private PaymentModeRepository paymentModeRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    private void putAddInfosForFirstReq(Map<String, String> requestParams, CampostAccount account, CampostPayment payment) {

        requestParams.put("p_card_no", "");
        requestParams.put("p_trans_cur", "XAF");
        requestParams.put("p_order_no", payment.getReference());
        requestParams.put("p_remark", "test");
        requestParams.put("p_merchant_id", account.getMerchantid());
        requestParams.put("p_local_curr_code", "XAF");
        requestParams.put("p_country_name", "Cameroon");
        requestParams.put("p_act_id", "PLWAL");
        requestParams.put("p_amount", payment.getAmount().toString());
    }

    protected abstract void fillMapResForFirstReq(Map<String, String> result, TRANSNET transnet);

    @Override
    public Map<String, String> first(final List<Map<String, Object>> invoices, final Map<String, String> requestParams,
            final String beneficiaryCode) throws NoCampostAccountException {

        final CampostAccount account = campostAccountRepository.findByBeneficiary(beneficiaryCode).orElseThrow(() -> new NoCampostAccountException(""));
        final PaymentMode paymentMode = paymentModeRepository.findByCode(PM_CAMPOST_WALLET).get();
        final Params param = paramsRepository.getOne(CAMPOST_CODE);
        final Partner campost = partnerRepository.findByCode(param.getValue()).get();
        final CampostPayment payment = (CampostPayment) paymentService.init(invoices, paymentMode, null, null, campost, Constants.HUNDRED, false);

        payment.setBeneficiary(partnerRepository.findByCode(beneficiaryCode).get());
        payment.setSessionId("...");
        payment.setLang("FR");
        payment.setCurrency("XAF");
        paymentService.save(payment, CAMPOST_SEQ, CAMPOST_PREFIX);

        putAddInfosForFirstReq(requestParams, account, payment);

        final TRANSNET transnet = sendRequestAndGetResponse(requestParams, account).get();
        final RESPONSEREASON responsereason = transnet.getRESPONSEREASON();
        final Map<String, String> result = new HashMap<>();
        if (null != responsereason.getSUCCESS()) {

            result.put("reference", payment.getReference());
            fillMapResForFirstReq(result, transnet);
            final CARDDATA carddata = transnet.getCATALOG().getCARDDATA();
            final String sessionId = carddata.getTRANSSESSIONID();
            payment.setSessionId(sessionId);
            payment.setPartnerReference(sessionId);
        } else {

            paymentService.updateInvoices(payment, Invoice.INVOICE_PAYMENT_REJECTED);
            paymentService.setDecision(payment, Payment.PAYMENT_REJECTED);

            result.put("code", "0");
        }

        paymentService.update(payment);

        return result;
    }

    @Override
    public Map<String, String> second(Map<String, String> infos, String paymentReference, boolean cancel) {

        final Map<String, String> mapResult = new HashMap();
        final CampostPayment payment = (CampostPayment) paymentRepository.findByReference(paymentReference).get();
        if (cancel) {
            this.paymentService.updateInvoices(payment, Invoice.INVOICE_PAYMENT_CANCELED);
            paymentService.setDecision(payment, Payment.PAYMENT_CANCELED);
            this.paymentService.update(payment);
            mapResult.put("code", "2");
            return mapResult;
        }

        final CampostAccount account = campostAccountRepository.findByBeneficiary(payment.getBeneficiary().getCode()).get();

        infos.put("p_trans_session_id", payment.getSessionId());
        infos.put("p_merchant_id", account.getMerchantid());
        infos.put("p_local_curr_code", "XAF");
        infos.put("p_country_name", "Cameroon");
        infos.put("p_act_id", "PWPAY");

        final TRANSNET transnet = sendRequestAndGetResponse(infos, account).get();
        final RESPONSEREASON responsereason = transnet.getRESPONSEREASON();

        if (null != responsereason.getSUCCESS()) {

            paymentService.setDecision(payment, Payment.PAYMENT_VALIDATED);
            paymentService.updateInvoices(payment, Invoice.INVOICE_PAID);

            mapResult.put("code", "1");
        } else {

            paymentService.updateInvoices(payment, Invoice.INVOICE_PAYMENT_REJECTED);
            paymentService.setDecision(payment, Payment.PAYMENT_REJECTED);

            mapResult.put("code", "0");
        }

        paymentService.update(payment);

        return mapResult;
    }

    private Optional<TRANSNET> sendRequestAndGetResponse(Map<String, String> requestParams, CampostAccount account) {

        final String requestString = CoreUtils.mapToString(requestParams, "&");
        final String enc = SecurityUtils.symetricEncrypt(requestString, account.getSecretKey(), account.getInitVector());
        final String izenid = account.getIzenid();
        final Params campostUrlParam = paramsRepository.getOne(CAMPOST_BASE_URL);
        final String postUrl = String.format("%s?izenid=%s&enc=%s", campostUrlParam.getValue(), izenid, enc);
        String response;

        try {

            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

            response = restTemplate.postForObject(postUrl, request, String.class, new HashMap<>());
        } catch (Exception ex) {
            LOGGER.error(null, ex);

            return Optional.empty();
        }

        final String dec = SecurityUtils.symetricDecrypt(response.trim(), account.getSecretKey(), account.getInitVector());
        final Parser<TRANSNET> parser = ParserFactory.getParser(ParserConstants.PARSER_XML, TRANSNET.class).get();

        return Optional.of(parser.parse(dec));
    }

}
