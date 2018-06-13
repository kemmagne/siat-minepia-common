package org.guce.epayment.core.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import generated.AperakDocument;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.documents.PAY602DOCUMENT;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.core.utils.MailConstants;
import org.guce.epayment.core.utils.MessageUtils;
import org.guce.epayment.core.utils.enums.AperakType;
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
    public void confirmPayment(PaymentInvoiceVersion paymentInvoiceVersion) {

        final PAY602DOCUMENT document = new PAY602DOCUMENT();

    }

    @Override
    public void sendAperak(AperakType aperakType, String fileNumber, String guceReference, String service, String errorCode, String aperakErreur) {

        final AperakDocument aperakDocument = new AperakDocument();
        final String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS"));

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
        aperakDocument.getROUTAGE().setEMETTEUR(Constants.PARTNER_CODE);
        aperakDocument.getROUTAGE().setDESTINATAIRE(Constants.GUCE_PARTNER_CODE);

        if (AperakType.APERAK_C.equals(aperakType)) {
            aperakDocument.setERREURS(new AperakDocument.ERREURS());
            aperakDocument.getERREURS().setERREUR(new AperakDocument.ERREURS.ERREUR());
            aperakDocument.getERREURS().getERREUR().setCODEERREUR(errorCode);
            aperakDocument.getERREURS().getERREUR().setCODEERREUR(aperakErreur);
        }
    }

}

/*

<div>
   Your email address is <a href="mailto:${user.emailAddress}">${user.emailAddress}</a>.
</div>

 */
