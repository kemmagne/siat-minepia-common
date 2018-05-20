package org.guce.epayment.core.services;

import java.util.Map;
import javax.mail.MessagingException;

/**
 *
 * @author tadzotsa
 */
public interface EmailService {

    void send(Map<String, Object> props) throws MessagingException;

}
