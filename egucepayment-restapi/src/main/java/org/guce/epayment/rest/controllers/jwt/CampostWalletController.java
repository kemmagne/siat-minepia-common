package org.guce.epayment.rest.controllers.jwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.guce.epayment.campost.exceptions.NoCampostAccountException;
import org.guce.epayment.campost.services.CampostService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.CampostMobileFirstReq;
import org.guce.epayment.rest.dto.CampostMobileFirstRes;
import org.guce.epayment.rest.dto.CampostMobileSecondReq;
import org.guce.epayment.rest.dto.DefaultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@Transactional
@RestController
@RequestMapping("jwt/public/payment/campost/wallet/request")
public class CampostWalletController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampostWalletController.class);

    @Autowired
    @Qualifier("campostWalletService")
    private CampostService campostService;

    @ResponseBody
    @RequestMapping(path = "first", method = RequestMethod.POST)
    public ResponseEntity sendFirstRequest(@RequestBody CampostMobileFirstReq campostMobileFirstReq) {

        final List<Map<String, Object>> invoices = RestUtils.getInvoicesMap(campostMobileFirstReq.getInvoices());
        final Map<String, String> requestParams = new HashMap<>();

        requestParams.put("p_mobile_no", campostMobileFirstReq.getMobile());

        final Map<String, String> mapResult;
        try {
            mapResult = campostService.first(invoices, requestParams, campostMobileFirstReq.getBeneficiaryCode());
        } catch (NoCampostAccountException ex) {
            LOGGER.error(null, ex);
            return ResponseEntity.ok(DefaultDto.of("0"));
        }

        final Integer responseCode = Integer.valueOf(mapResult.get("code"));
        final CampostMobileFirstRes response = new CampostMobileFirstRes();

        if (Objects.equals(Constants.ONE, responseCode)) {
            // success
            response.setOrderNumber(mapResult.get("reference"));
            response.setPacCodeOneLabel(mapResult.get("p_pac_code_one_label"));
            response.setPacCodeTwoLabel(mapResult.get("p_pac_code_two_label"));
            response.setAmount(mapResult.get("p_amount"));
            response.setCardNumberPart(mapResult.get("p_card_number_part"));
        } else if (Objects.equals(Constants.ZERO, responseCode)) {
            // error
        }
        response.setResponseCode(responseCode);

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping(path = "second", method = RequestMethod.POST)
    public ResponseEntity<DefaultDto> sendSecondRequest(@RequestBody CampostMobileSecondReq campostMobileSecondReq) {

        final String pacCodeOne = campostMobileSecondReq.getPacCodeOne();
        final String pacCodeTwo = campostMobileSecondReq.getPacCodeTwo();
        final Map<String, String> infos = new HashMap<>();

        infos.put("p_pac_code_one", pacCodeOne);
        infos.put("p_pac_code_two", pacCodeTwo);

        Map<String, String> mapResult = campostService.second(infos, campostMobileSecondReq.getPaymentReference(),
                campostMobileSecondReq.isCancel());

        return ResponseEntity.ok(DefaultDto.of(mapResult.get("code")));
    }

}
