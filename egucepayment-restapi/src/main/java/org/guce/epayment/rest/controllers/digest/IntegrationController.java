package org.guce.epayment.rest.controllers.digest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;
import org.guce.epayment.core.documents.DouaneAlerteDocument;
import org.guce.epayment.core.documents.PAY601DOCUMENT;
import org.guce.epayment.core.documents.QuittanceDouaneDocument;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerType;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.Receipt;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.InvoiceService;
import org.guce.epayment.core.services.MessageService;
import org.guce.epayment.core.services.PaymentService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.CoreUtils;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.IncomingMessageDto;
import org.guce.epayment.rest.dto.InvoiceVersionDto;
import org.guce.util.JAXBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("digest/integration")
public class IntegrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationController.class);

    @Autowired
    private CoreService coreService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private MessageService messageService;

    @ResponseBody
    @RequestMapping(path = "invoices", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity integrateInvoice(@RequestBody IncomingMessageDto messageDto)
            throws JAXBException {

        System.out.println(messageDto);

        final ResponseEntity response = getResponse(messageDto, "payment");
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            return response;
        }

        final byte[] original = (byte[]) response.getBody();
        final PAY601DOCUMENT document = JAXBUtil.unmarshall(original, PAY601DOCUMENT.class);
        final InvoiceVersionDto invoiceVersionDto = new InvoiceVersionDto();

        invoiceVersionDto.setAmount(new BigDecimal(document.getCONTENT().getPAIEMENT().getFACTURE().getMONTANTTTC()));
        invoiceVersionDto.setGuceReference(document.getREFERENCEDOSSIER().getREFERENCEGUCE());
        invoiceVersionDto.setInvoiceNumber(document.getCONTENT().getPAIEMENT().getFACTURE().getREFERENCEFACTURE());
        invoiceVersionDto.setInvoiceTypeCode(document.getCONTENT().getPAIEMENT().getFACTURE().getTYPEFACTURE());
        invoiceVersionDto.setSubTypeCode(document.getCONTENT().getPAIEMENT().getFACTURE().getSOUSTYPEFACTURE());
        invoiceVersionDto.setTaxPayerName(document.getCONTENT().getPAIEMENT().getCHARGEUR().getRAISONSOCIALE());
        invoiceVersionDto.setTaxPayerNumber(document.getCONTENT().getPAIEMENT().getCHARGEUR().getNUMEROCONTRIBUABLE());
        Integer version = document.getCONTENT().getPAIEMENT().getFACTURE().getVERSION();
        if (version == null) {
            version = 0;
        }
        invoiceVersionDto.setVersion(version);

        final List<PAY601DOCUMENT.CONTENT.PAIEMENT.REPARTITIONS.REPARTITION> reparttions = document.getCONTENT()
                .getPAIEMENT().getREPARTITIONS().getREPARTITION();
        if (!CollectionUtils.isEmpty(reparttions)) {
            invoiceVersionDto.setSubInvoices(reparttions.stream().map(
                    repartition -> InvoiceVersionDto.of(repartition.getMONTANT(), repartition.getCODEBENIF(),
                            repartition.getDOCUMENTORIGINE()))
                    .collect(Collectors.toList()));
        } else {
            invoiceVersionDto.setSubInvoices(Collections
                    .singletonList(InvoiceVersionDto.of(
                            new BigDecimal(document.getCONTENT().getPAIEMENT().getFACTURE().getMONTANTTTC()),
                            document.getCONTENT().getPAIEMENT().getBENEFICIAIRE().getCODE(), null)));
        }

        integrateInvoice(invoiceVersionDto);

//        messageService.sendAperak(AperakType.APERAK_K, invoiceVersionDto.getInvoiceNumber(),
//                invoiceVersionDto.getGuceReference(), Constants.GUCE_PAYMENT_SERVICE, null, null);
        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "customs/receipts", method = RequestMethod.POST)
    public ResponseEntity integrateCustomsReceipt(@RequestBody IncomingMessageDto messageDto) throws JAXBException {

        System.out.println(messageDto);

        final ResponseEntity response = getResponse(messageDto, "cusdec");
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            return response;
        }

        final byte[] original = (byte[]) response.getBody();
        final QuittanceDouaneDocument document = JAXBUtil.unmarshall(original, QuittanceDouaneDocument.class);
        final String bureau = document.getKEYCUO();
        final String invoiceNumber = document.getNUMEROLIQUIDATION() + bureau + document.getDATELIQUIDATION();
        final Receipt receipt = new Receipt();

        receipt.setNumber(document.getNUMEROQUITTANCE());
        receipt.setReceiptDate(LocalDate.parse(document.getDATEQUITTANCE(), DateTimeFormatter.ofPattern("yyyyMMdd")));
        receipt.setBeneficiary(coreService.findByUniqueKey(Constants.UK_CODE, bureau, Partner.class).get());

        // le numéro dossier parent correspond au numéro dossier partenaire
        // le beneficiaire est le bureau de douane
        final Optional<Invoice> invoiceParentOp = invoiceService
                .findByNumberAndType(invoiceNumber, InvoiceType.INVOICE_TYPE_CUSDEC);
        if (!invoiceParentOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(DefaultDto.of("There is not a paid invoice for this receipt"));
        }
        final Invoice invoiceParent = invoiceParentOp.get();
        final Invoice subInvoice = invoiceParent.getSubInvoices()
                .stream().filter(subInv -> bureau.equals(subInv.getBeneficiary().getCode())).findFirst().get();
        final InvoiceVersion invoiceVersion = invoiceService
                .findByInvoiceAndNumber(subInvoice.getId(),
                        Integer.parseInt(document.getVERSIONLIQUIDATION())).get();

        receipt.setInvoiceVersion(invoiceVersion);

        Optional<Payment> paymentOp = paymentService.findPaymentForInvoiceVersion(invoiceVersion);
        if (!paymentOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(DefaultDto.of("There is not a payment for this receipt"));
        }
        receipt.setPayment(paymentOp.get());

        final StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s=%s\n", "dateliquidation", document.getDATELIQUIDATION()))
                .append(String.format("%s=%s\n", "serieliquidation", document.getSERIELIQUIDATION()))
                .append(String.format("%s=%s\n", "numeroliquidation", document.getNUMEROLIQUIDATION()))
                .append(String.format("%s=%s\n", "versionliquidation", document.getVERSIONLIQUIDATION()))
                .append(String.format("%s=%s\n", "montantliquidation", document.getMONTANTLIQUIDATION()))
                .append(String.format("%s=%s\n", "keycuo", document.getKEYCUO()))
                .append(String.format("%s=%s\n", "bureauquittance", document.getBUREAUQUITTANCE()))
                .append(String.format("%s=%s", "seriequittance", document.getSERIEQUITTANCE()));

        receipt.setMetadata(builder.toString());

        coreService.save(receipt, Receipt.class);

        final Map<String, BigDecimal> ids = new HashMap<>();
        final Map<String, LocalDateTime> map = new HashMap<>();

        ids.put("ID", invoiceVersion.getId());
        map.put("ACKNOWLEDGMENT_DATE", LocalDateTime.now());

        coreService.updateEntity(InvoiceVersion.class, ids, map);

//        messageService.sendAperak(AperakType.APERAK_K, invoiceNumber, invoiceVersion.getGuceReference(),
//                Constants.GUCE_CUSDEC_SERVICE, null, null);
        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "customs/alerts", method = RequestMethod.POST)
    public ResponseEntity integrateCustomsAlert(@RequestBody IncomingMessageDto messageDto) throws JAXBException {

        System.out.println(messageDto);

        final ResponseEntity response = getResponse(messageDto, "cusdec");
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            return response;
        }

        final byte[] original = (byte[]) response.getBody();
        final DouaneAlerteDocument document = JAXBUtil.unmarshall(original, DouaneAlerteDocument.class);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    private void integrateInvoice(InvoiceVersionDto invVersDto) {

        final String invoiceNumber = invVersDto.getInvoiceNumber().toUpperCase();
        final String invoiceTypeCode = invVersDto.getInvoiceTypeCode().toUpperCase();
        final InvoiceType invoiceType = coreService.findByUniqueKey(Constants.UK_CODE, invoiceTypeCode,
                InvoiceType.class).get();
        final Invoice principalInvoice = invoiceService.findByNumberAndType(invoiceNumber, invoiceType.getId())
                .orElse(new Invoice());
        final BigDecimal versionAmount = invVersDto.getAmount();
        final InvoiceVersion invoiceVersion = new InvoiceVersion();

        invoiceVersion.setVersionAmount(versionAmount);
        invoiceVersion.setEGuceReference(invVersDto.getGuceReference());
        invoiceVersion.setNumber(invVersDto.getVersion());

        if (principalInvoice.getId() == null) {

            // save tax payer if doesn't exist in bd
            final Partner taxPayer = coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER,
                    invVersDto.getTaxPayerNumber(), Partner.class).orElse(new Partner());

            if (taxPayer.getId() == null) {

                taxPayer.setCode(invVersDto.getTaxPayerNumber());
                taxPayer.setTaxPayerNumber(invVersDto.getTaxPayerNumber());
                taxPayer.setName(invVersDto.getTaxPayerName());
                taxPayer.setTypes(Collections.singletonList(coreService.findByUniqueKey(Constants.UK_CODE,
                        PartnerType.PARTNER_TYPE_PRINCIPAL, PartnerType.class).get()));

                coreService.save(taxPayer, Partner.class);
            }

            principalInvoice.setAmount(versionAmount);
            principalInvoice.setNumber(invoiceNumber);
            principalInvoice.setType(invoiceType);
            principalInvoice.setOwner(taxPayer);
            principalInvoice.setSubType(coreService.findByUniqueKey(Constants.UK_CODE,
                    invVersDto.getSubTypeCode(), InvoiceType.class).get());

            invoiceVersion.setInvoice(principalInvoice);
            invoiceVersion.setBalanceAmount(versionAmount);

            // sub invoices ???
            principalInvoice.setSubInvoices(invVersDto.getSubInvoices().stream()
                    .map(subInvDto -> getSubInvoice(invVersDto, subInvDto, true, 0))
                    .collect(Collectors.toList())
            );

            principalInvoice.setInvoiceVersions(Collections.singletonList(invoiceVersion));
        } else {

            invoiceVersion.setInvoice(principalInvoice);

            final Properties ivtProps = CoreUtils.getParams(Optional.ofNullable(invoiceType.getParameters()));
            final boolean balance = null != ivtProps && Boolean.parseBoolean(ivtProps.getProperty("balance", "false"));

            if (balance) {

                principalInvoice.setAmount(principalInvoice.getAmount().add(versionAmount));
                invoiceVersion.setBalanceAmount(versionAmount);
            } else {

                final BigDecimal currInvAmount = principalInvoice.getAmount();

                invoiceVersion.setBalanceAmount(versionAmount.subtract(currInvAmount));
                principalInvoice.setAmount(versionAmount);
            }

            principalInvoice.setStatus(Invoice.INVOICE_UNPAID);
            principalInvoice.setLastVersionDate(LocalDateTime.now());
            principalInvoice.setLastVersionNumber(invoiceVersion.getNumber());

            // sub invoices
            invVersDto.getSubInvoices().forEach(subInvDto -> {

                final Invoice subInvoice = getSubInvoice(invVersDto, subInvDto, balance, invoiceVersion.getNumber());
                final int index = principalInvoice.getSubInvoices().indexOf(subInvoice);

                if (index > -1) {
                    principalInvoice.getSubInvoices().set(index, subInvoice);
                } else {
                    principalInvoice.getSubInvoices().add(subInvoice);
                }
            });

            coreService.save(invoiceVersion, InvoiceVersion.class);
        }

        coreService.save(principalInvoice, Invoice.class);
    }

    private Invoice getSubInvoice(InvoiceVersionDto parentInvVersDto, InvoiceVersionDto subInvVersDto,
            boolean balance, int versionNumber) {

        final String subInvBenefCode;
        if (subInvVersDto == null) {
            subInvBenefCode = parentInvVersDto.getBeneficiaryCode();
        } else {
            subInvBenefCode = subInvVersDto.getBeneficiaryCode();
        }
        final String parentInvTypeCode = parentInvVersDto.getInvoiceTypeCode();
        final String subInvNumber = getSubInvoiceNumber(parentInvVersDto.getInvoiceNumber(),
                parentInvTypeCode, subInvBenefCode);
        final InvoiceType subInvType = getSubInvoiceType(parentInvTypeCode, subInvBenefCode);
        final Invoice subInvoice = invoiceService.findByNumberAndType(subInvNumber, subInvType.getId())
                .orElse(new Invoice());

        final BigDecimal amount;
        final String benefReference;
        if (subInvVersDto == null) {

            amount = parentInvVersDto.getAmount();
            benefReference = parentInvVersDto.getBenefReference();
        } else {

            amount = subInvVersDto.getAmount();
            benefReference = subInvVersDto.getBenefReference();
        }

        final InvoiceVersion subInvVersion = new InvoiceVersion();
        subInvVersion.setVersionAmount(amount);

        if (subInvoice.getId() == null) {

            subInvoice.setBenefReference(benefReference);
            subInvoice.setNumber(subInvNumber);
            subInvoice.setType(subInvType);
            subInvoice.setBeneficiary(coreService.findByUniqueKey(Constants.UK_CODE, subInvBenefCode,
                    Partner.class).get());
            subInvoice.setOwner(coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER,
                    parentInvVersDto.getTaxPayerNumber(), Partner.class).get());
            subInvoice.setAmount(amount);
            //
            subInvVersion.setBalanceAmount(amount);
            subInvVersion.setInvoice(subInvoice);
            subInvVersion.setNumber(1);
            subInvVersion.setInvoice(subInvoice);
            //
            subInvoice.setInvoiceVersions(Collections.singletonList(subInvVersion));
        } else {

            subInvVersion.setInvoice(subInvoice);

            if (balance) {

                subInvVersion.setBalanceAmount(amount);
                subInvoice.setAmount(subInvoice.getAmount().add(amount));
            } else {

                final BigDecimal currSubInvAmount = subInvoice.getAmount();

                subInvVersion.setBalanceAmount(amount.subtract(currSubInvAmount));
                subInvoice.setAmount(amount);
            }

            subInvoice.setStatus(Invoice.INVOICE_UNPAID);
            subInvoice.setLastVersionDate(LocalDateTime.now());
            subInvoice.setLastVersionNumber(versionNumber);

            coreService.save(subInvVersion, InvoiceVersion.class);
        }

        return subInvoice;
    }

    private InvoiceType getSubInvoiceType(String parentInvTypeCode, String subInvBenefCode) {

        final String invoiceTypeCode = String.format(RestConstants.SUB_INVOICE_TYPE_CODE_FORMAT,
                parentInvTypeCode, subInvBenefCode);
        final Optional<InvoiceType> invoiceTypeOp = coreService.findByUniqueKey(Constants.UK_CODE,
                invoiceTypeCode, InvoiceType.class);

        if (!invoiceTypeOp.isPresent()) {

            final Partner benef = coreService.findByUniqueKey(Constants.UK_CODE, subInvBenefCode, Partner.class).get();
            final InvoiceType invoiceType = new InvoiceType();

            invoiceType.setCode(invoiceTypeCode);
            invoiceType.setLabel(RestConstants.SUB_INVOICE_TYPE_NAME_PREFIX + benef.getName());

            return coreService.save(invoiceType, InvoiceType.class);
        } else {
            return invoiceTypeOp.get();
        }
    }

    private String getSubInvoiceNumber(String parentInvoiceNumber, String parentInvoiceTypeCode,
            String subInvoiceBenefCode) {
        return String.format(RestConstants.SUB_INVOICE_NUMBER_FORMAT,
                parentInvoiceNumber, parentInvoiceTypeCode, subInvoiceBenefCode);
    }

    private ResponseEntity getResponse(final IncomingMessageDto messageDto, final String senderPrefix) {

        final byte[] bytes = RestUtils.getOriginalMessage(messageDto, senderPrefix);

        if (bytes == null) {
            // exception
            System.out.println("exception");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The was an exception during message parsing");
        }

        if (bytes.length == 0) {
            // authenticité non confirmée
            System.out.println("authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sender authentication failed");
        }

        return ResponseEntity.ok(bytes);
    }

}
