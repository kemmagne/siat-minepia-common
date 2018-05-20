package org.guce.epayment.core.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.time.LocalDateTime;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.core.utils.MailConstants;
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
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${mailRemand.folder}")
    private String mailRemandFolder;
    @Value("${mailSender.from}")
    private String from;
    @Value("${mailSender.replyTo}")
    private String replyTo;

    private final JavaMailSender mailSender;
    private final Configuration freeMakerConfig;
    private final ApplicationService appService;

    @Autowired
    public EmailServiceImpl(final JavaMailSender emailSender, final Configuration freeMakerConfig,
            final ApplicationService applicationService) {
        this.mailSender = emailSender;
        this.freeMakerConfig = freeMakerConfig;
        this.appService = applicationService;
    }

    @Override
    public void send(final Map<String, Object> props) throws MessagingException {

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

}

/*

<div>
   Your email address is <a href="mailto:${user.emailAddress}">${user.emailAddress}</a>.
</div>

 */
