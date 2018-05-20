package org.guce.epayment.core.services;

import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import org.guce.epayment.core.utils.MailConstants;
import org.guce.epayment.core.mail.config.MailConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author tadzotsa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MailConfig.class})
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Ignore
    @Test
    public void testSend() throws MessagingException {

        final Map<String, Object> props = new HashMap<>();

        props.put(MailConstants.FTL, "resetPassword_fr.ftl");
        props.put(MailConstants.SUBJECT, "Réinitialisation du mot de passe");
        props.put(MailConstants.TO, new String[]{"romuald.tadzotsa@guichetunique.org"});

        // template elements
        props.put("title", "M.");
        props.put("lastName", "TADZOTSA");
        props.put("firstName", "Hyacinthe");
        props.put("password", "p@55w0rd");

        emailService.send(props);
    }

}
