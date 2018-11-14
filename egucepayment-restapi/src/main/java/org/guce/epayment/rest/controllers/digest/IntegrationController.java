package org.guce.epayment.rest.controllers.digest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;
import org.apache.commons.collections4.CollectionUtils;
import org.guce.epayment.core.documents.DouaneAlerteDocument;
import org.guce.epayment.core.documents.PAY601DOCUMENT;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerType;
import org.guce.epayment.core.entities.enums.InvoiceStatus;
import org.guce.epayment.core.entities.enums.PartnerTypeCode;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.InvoiceService;
import org.guce.epayment.core.services.MessageService;
import org.guce.epayment.core.services.PaymentService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.CoreUtils;
import org.guce.epayment.core.utils.enums.AperakType;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.IncomingMessageDto;
import org.guce.util.JAXBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity integrateInvoice(@RequestBody PAY601DOCUMENT document) {

        final String invoiceNumber = document.getCONTENT().getPAIEMENT().getFACTURE().getREFERENCEFACTURE();
        final String eGuceReference = document.getREFERENCEDOSSIER().getREFERENCEGUCE();

        try {
            final String invoiceTypeCode = document.getCONTENT().getPAIEMENT().getFACTURE().getTYPEFACTURE();
            final InvoiceType invoiceType = coreService
                    .findByUniqueKey(Constants.UK_CODE, invoiceTypeCode, InvoiceType.class)
                    .get();
            final BigDecimal versionAmount = new BigDecimal(document.getCONTENT().getPAIEMENT().getFACTURE().getMONTANTTTC());
            final Invoice invoice = invoiceService.findByNumberAndType(invoiceNumber, invoiceType.getId())
                    .orElse(new Invoice());

            final InvoiceVersion invoiceVersion = new InvoiceVersion();
            invoiceVersion.setVersionAmount(versionAmount);
            invoiceVersion.setEGuceReference(eGuceReference);
            invoiceVersion.setInvoiceLines(getInvoiceLines(document, invoiceType, invoice));

            if (invoice.getId() == null) {
                final String taxPayerNumber = document.getCONTENT().getPAIEMENT().getCHARGEUR().getNUMEROCONTRIBUABLE();
                final Partner taxPayer = coreService
                        .findByUniqueKey(Constants.UK_CODE, taxPayerNumber, Partner.class).orElse(new Partner());
                if (taxPayer.getId() == null) {
                    taxPayer.setCode(taxPayerNumber);
                    taxPayer.setName(document.getCONTENT().getPAIEMENT().getCHARGEUR().getRAISONSOCIALE());
                    taxPayer.setActive(Boolean.TRUE);
                    taxPayer.setTypes(Collections.singletonList(coreService
                            .findByUniqueKey(Constants.UK_CODE, PartnerTypeCode.PRINCIPAL.name(),
                                    PartnerType.class).get()));
                    coreService.save(taxPayer, Partner.class);
                }
                invoice.setOwner(taxPayer);
                invoice.setAmount(versionAmount);
                final String subTypeCode = document.getCONTENT().getPAIEMENT().getFACTURE().getSOUSTYPEFACTURE();
                invoice.setSubType(coreService.findByUniqueKey(Constants.UK_CODE, subTypeCode, InvoiceType.class).orElse(null));

                invoiceVersion.setBalanceAmount(versionAmount);
                invoiceVersion.setNumber(0);

                invoice.setInvoiceVersions(Collections.singletonList(invoiceVersion));
            } else {
                invoiceVersion.setInvoice(invoice);
                invoiceVersion.setNumber(invoice.getLastVersionNumber() + 1);
                final Properties ivtProps = CoreUtils.getParams(Optional.ofNullable(invoiceType.getParameters()));
                final boolean balance = ivtProps != null && Boolean.parseBoolean(ivtProps.getProperty("balance", "false"));
                if (!balance) {
                    invoiceVersion.setBalanceAmount(versionAmount.subtract(invoice.getAmount()));
                    invoice.setAmount(versionAmount);
                } else {
                    invoice.setAmount(invoice.getAmount().add(versionAmount));
                    invoiceVersion.setBalanceAmount(versionAmount);
                }

                invoice.setStatus(InvoiceStatus.UNPAID);

                coreService.save(invoiceVersion, InvoiceVersion.class);
            }

            coreService.save(invoice, Invoice.class);

            messageService.sendAperak(AperakType.APERAK_K, invoiceNumber, eGuceReference,
                    Constants.GUCE_PAYMENT_SERVICE, null, null);

            return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
            messageService.sendAperak(AperakType.APERAK_C, invoiceNumber, eGuceReference,
                    Constants.GUCE_PAYMENT_SERVICE, AperakType.APERAK_C.name(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @RequestMapping(path = "customs/receipts", method = RequestMethod.POST)
    public ResponseEntity integrateCustomsReceipt(@RequestBody IncomingMessageDto messageDto) throws JAXBException {

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

    private List<InvoiceLine> getInvoiceLines(final PAY601DOCUMENT document, final InvoiceType invoiceType,
            final Invoice invoice) {
        List<InvoiceLine> invoiceLines;
        final String invoiceNumber = document.getCONTENT().getPAIEMENT().getFACTURE().getREFERENCEFACTURE();
        final BigDecimal versionAmount = new BigDecimal(document.getCONTENT().getPAIEMENT().getFACTURE().getMONTANTTTC());
        final List<PAY601DOCUMENT.CONTENT.PAIEMENT.REPARTITIONS.REPARTITION> repartitions = document.getCONTENT()
                .getPAIEMENT().getREPARTITIONS().getREPARTITION();

        final Properties ivtProps = CoreUtils.getParams(Optional.ofNullable(invoiceType.getParameters()));
        final boolean balance = ivtProps != null && Boolean.parseBoolean(ivtProps.getProperty("balance", "false"));

        // on doit gérer l'intégration des lignes de facture en cas de redressement
        if (CollectionUtils.isNotEmpty(repartitions)) {
            invoiceLines = repartitions.stream().map(repartition -> {

                final InvoiceLine invoiceLine = new InvoiceLine();

                invoiceLine.setAmount(repartition.getMONTANT());

                final Partner beneficiary = coreService.findByUniqueKey(Constants.UK_CODE, repartition.getCODEBENIF(), Partner.class).get();
                final InvoiceType invoiceLineType = coreService.findByUniqueKey(Constants.UK_CODE, repartition.getTYPEDOSSIER(), InvoiceType.class).get();

                if (CollectionUtils.isEmpty(invoice.getInvoiceVersions()) || balance) {
                    invoiceLine.setAmountToPay(repartition.getMONTANT());
                } else {
                    final List<InvoiceLine> lines = invoiceService.find(invoiceLineType, beneficiary, invoice);
                    invoiceLine.setAmountToPay(repartition.getMONTANT().subtract(lines.get(0).getAmount()));
                }

                invoiceLine.setBenefReference(repartition.getDOCUMENTORIGINE());
                invoiceLine.setBeneficiary(beneficiary);
                invoiceLine.setType(invoiceLineType);

                return invoiceLine;
            }).collect(Collectors.toList());
        } else {

            final InvoiceLine invoiceLine = new InvoiceLine();

            invoiceLine.setAmount(versionAmount);
            if (CollectionUtils.isEmpty(invoice.getInvoiceVersions()) || balance) {// pas de redressement ou reliquat
                invoiceLine.setAmountToPay(versionAmount);
            } else {// redressement et pas reliquat
                invoiceLine.setAmountToPay(versionAmount.subtract(invoice.getAmount()));
            }
            invoiceLine.setBenefReference(invoiceNumber);
            invoiceLine.setBeneficiary(coreService.findByUniqueKey(Constants.UK_CODE, document.getCONTENT().getPAIEMENT().getBENEFICIAIRE().getCODE(), Partner.class).get());
            invoiceLine.setType(invoiceType);

            invoiceLines = Collections.singletonList(invoiceLine);
        }

        return invoiceLines;
    }

}

