package org.guce.siat.common.mail.bo;

import java.io.File;
import java.util.Map;

/**
 * The Interface EmailSenderService.
 */
public interface EmailSenderService {

    /**
     * Send.
     *
     * @param subject the subject
     * @param from the from
     * @param emailBodyPath the email body path
     * @param hTemplateVariables the h template variables
     * @param mailReceiver the mail receiver
     * @param files the files
     */
    void send(String subject, String from, String emailBodyPath, Map<String, Object> hTemplateVariables, String[] mailReceiver, File... files);

    /**
     * Send mail
     *
     * @param params
     */
    void send(final Map<String, Object> params);
}

