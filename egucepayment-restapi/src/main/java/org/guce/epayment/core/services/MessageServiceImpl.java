package org.guce.epayment.core.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import generated.AperakDocument;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.JAXBException;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.dao.CoreDao;
import org.guce.epayment.core.documents.PAY602DOCUMENT;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.CoreUtils;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.core.utils.MailConstants;
import org.guce.epayment.core.utils.MessageUtils;
import org.guce.epayment.core.utils.enums.AperakType;
import org.guce.util.JAXBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

@Transactional
@Service
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Value("${mailRemand.folder}")
    private String mailRemandFolder;
    @Value("${mailSender.from}")
    private String from;
    @Value("${mailSender.replyTo}")
    private String replyTo;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration freeMakerConfig;
    @Autowired
    private ApplicationService appService;
    @Autowired
    private CoreDao coreDao;

    @Override
    public void sendMail(final Map<String, Object> props) throws MessagingException {

        try {

            final Template template = freeMakerConfig.getTemplate(props.get(MailConstants.FTL).toString());

            final String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, props);

            MimeMessagePreparator preparator = (MimeMessage mimeMessage) -> {

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setFrom(from);
                message.setReplyTo(replyTo);
                message.setSubject(props.get(MailConstants.SUBJECT).toString());
                message.setSentDate(DateUtils.asDate(LocalDateTime.now()));
                message.setText(html, true);
                message.setTo((String[]) props.get(MailConstants.TO));
                final Object bcc = props.get(MailConstants.BCC);
                if (bcc != null) {
                    message.setBcc((String[]) bcc);
                }
                final Object cc = props.get(MailConstants.CC);
                if (cc != null) {
                    message.setCc((String[]) cc);
                }
            };

            mailSender.send(preparator);
        } catch (Exception ex) {
            LOGGER.error("Problem occured when trying to send mail", ex);
            // on sauvegarde les informations d'envoi de mail dans les fichers json
        }
    }

    @Override
    public void confirmPayment(PaymentInvoiceVersion piv) {

        final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(dateFormat);
        final String now = LocalDateTime.now().format(dtFormatter);
        final PAY602DOCUMENT document = new PAY602DOCUMENT();
        final User signator = piv.getPayment().getSignatures().get(0).getUser();

        document.setTYPEDOCUMENT(Constants.GUCE_PAYMENT_RESPONSE_DOC_TYPE);

        document.setROUTAGE(new PAY602DOCUMENT.ROUTAGE());
        document.getROUTAGE().setEMETTEUR(Constants.E_GUCE_PARTNER_CODE);
        document.getROUTAGE().setDESTINATAIRE(Constants.E_GUCE_GUCE_PARTNER_CODE);

        document.setREFERENCEDOSSIER(new PAY602DOCUMENT.REFERENCEDOSSIER());
        document.getREFERENCEDOSSIER().setDATECREATION(now);
        document.getREFERENCEDOSSIER().setREFERENCEGUCE(piv.getInvoiceVersion().getEGuceReference());
        document.getREFERENCEDOSSIER().setSERVICE(Constants.GUCE_PAYMENT_SERVICE);

        document.setCONTENT(new PAY602DOCUMENT.CONTENT());
        document.getCONTENT().setPAIEMENT(new PAY602DOCUMENT.CONTENT.PAIEMENT());
        document.getCONTENT().getPAIEMENT().setENCAISSEMENT(new PAY602DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT());
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().setMONTANT(piv.getAmount().toString());
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().setCANALENCAISSEMENT(Constants.E_GUCE_PARTNER_CODE);
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().setDATEENCAISSEMENT(piv.getInvoiceVersion().getPaymentDate().format(dtFormatter));
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().setFORMATDATEENCAISSEMENT(dateFormat);
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().setNATURE(piv.getPayment().getMode().getLabel());
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().setNUMERORECU(piv.getPayment().getReference());
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().setBANQUE(new PAY602DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT.BANQUE());
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().getBANQUE().setCODE(piv.getPayment().getBankGateway().getCode());
        document.getCONTENT().getPAIEMENT().getENCAISSEMENT().getBANQUE().setLIBELLE(piv.getPayment().getBankGateway().getName());

        document.getCONTENT().getPAIEMENT().setSIGNATAIRE(new PAY602DOCUMENT.CONTENT.PAIEMENT.SIGNATAIRE());
        document.getCONTENT().getPAIEMENT().getSIGNATAIRE().setDATE(piv.getPayment().getSignatures().get(0).getCreationDate().format(dtFormatter));
        document.getCONTENT().getPAIEMENT().getSIGNATAIRE().setCODE(signator.getLogin());
        document.getCONTENT().getPAIEMENT().getSIGNATAIRE().setNOM(signator.getLastName()
                + (signator.getFirstName() != null ? " " + signator.getFirstName() : ""));
        document.getCONTENT().getPAIEMENT().getSIGNATAIRE().setSOCIETE(piv.getPayment().getBankGateway().getName());

        final List<InvoiceLine> invoicesLines = piv.getInvoiceVersion().getInvoiceLines();

        document.getCONTENT().getPAIEMENT().setREPARTITION(new PAY602DOCUMENT.CONTENT.PAIEMENT.REPARTITION());

        invoicesLines.stream().map((invoiceLine) -> {

            final PAY602DOCUMENT.CONTENT.PAIEMENT.REPARTITION.BENEFICIAIRE beneficiaire = new PAY602DOCUMENT.CONTENT.PAIEMENT.REPARTITION.BENEFICIAIRE();

            beneficiaire.setCODE(invoiceLine.getBeneficiary().getCode());
            beneficiaire.setLIBELLE(invoiceLine.getBeneficiary().getName());
            beneficiaire.setMONTANT(invoiceLine.getAmountToPay());

            return beneficiaire;
        }).forEachOrdered((beneficiaire) -> {
            document.getCONTENT().getPAIEMENT().getREPARTITION().getBENEFICIAIRE().add(beneficiaire);
        });

        try {

            final String body = CoreUtils.getToSendMessage(JAXBUtil.marshall(document, true), "payment");
            final RestTemplate restTemplate = new RestTemplate();
            final String url = CoreUtils.getProperty("payment.rest.api.url");

            restTemplate.postForEntity(url, body, String.class);

            final Map<String, BigDecimal> ids = new HashMap<>();
            final Map<String, LocalDateTime> map = new HashMap<>();

            ids.put("ID", piv.getInvoiceVersion().getId());
            map.put("PAY_CONFIRM_DATE", LocalDateTime.now());

            coreDao.updateEntity(InvoiceVersion.class, ids, map);
        } catch (JAXBException ex) {
            LOGGER.error(null, ex);
        }
    }

    @Override
    public void sendAperak(AperakType aperakType, String fileNumber, String guceReference, String service, String errorCode, String aperakErreur) {

        final AperakDocument aperakDocument = new AperakDocument();
        final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        final String now = LocalDateTime.now().format(dtFormatter);

        aperakDocument.setTYPEDOCUMENT(aperakType.name());

        aperakDocument.setMESSAGE(new AperakDocument.MESSAGE());
        aperakDocument.getMESSAGE().setDATEEMISSION(now);
        aperakDocument.getMESSAGE().setETAT(StringUtils.EMPTY);
        aperakDocument.getMESSAGE().setNUMEROMESSAGE(MessageUtils.generateMessageID());
        aperakDocument.getMESSAGE().setTYPEMESSAGE(StringUtils.EMPTY);

        aperakDocument.setREFERENCEDOSSIER(new AperakDocument.REFERENCEDOSSIER());
        aperakDocument.getREFERENCEDOSSIER().setDATECREATION(now);
        aperakDocument.getREFERENCEDOSSIER().setNUMERODOSSIER(fileNumber);
        aperakDocument.getREFERENCEDOSSIER().setREFERENCEGUCE(guceReference);
        aperakDocument.getREFERENCEDOSSIER().setSERVICE(service);

        aperakDocument.setROUTAGE(new AperakDocument.ROUTAGE());
        aperakDocument.getROUTAGE().setEMETTEUR(Constants.E_GUCE_PARTNER_CODE);
        aperakDocument.getROUTAGE().setDESTINATAIRE(Constants.E_GUCE_GUCE_PARTNER_CODE);

        if (AperakType.APERAK_C.equals(aperakType)) {
            aperakDocument.setERREURS(new AperakDocument.ERREURS());
            aperakDocument.getERREURS().setERREUR(new AperakDocument.ERREURS.ERREUR());
            aperakDocument.getERREURS().getERREUR().setCODEERREUR(errorCode);
            aperakDocument.getERREURS().getERREUR().setCODEERREUR(aperakErreur);
        }

        try {
            final String body = CoreUtils.getToSendMessage(JAXBUtil.marshall(aperakDocument, true), service);
            final RestTemplate restTemplate = new RestTemplate();
            final String url = CoreUtils.getProperty(service + ".rest.api.url");
            restTemplate.postForEntity(url, body, String.class);
        } catch (JAXBException ex) {
            LOGGER.error(null, ex);
        }
    }

}

/*

<div>
   Your email address is <a href="mailto:${user.emailAddress}">${user.emailAddress}</a>.
</div>

 */
