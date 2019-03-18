package org.guce.siat.common.service;

import java.util.Map;

/**
 * The Interface MailService.
 */
public interface MailService {

    /**
     * Send mail.
     *
     * @param map the map
     */
    void sendMail(Map<String, String> map);

    /**
     * Gets the from value.
     *
     * @return the from value
     */
    String getFromValue();

    /**
     * Gets the reply to value.
     *
     * @return the reply to value
     */
    String getReplyToValue();
}

