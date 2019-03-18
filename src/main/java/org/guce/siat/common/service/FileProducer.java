package org.guce.siat.common.service;

import java.util.Map;
import org.guce.siat.common.model.ItemFlow;
import org.w3c.dom.Element;

/**
 * The Interface FileProducer.
 */
public interface FileProducer {

    /**
     * Send file.
     *
     * @param file the file
     */
    void sendFile(Map<String, Object> file);

    /**
     * resend decision.
     *
     * @param itemFlow
     */
    boolean resendDecision(ItemFlow itemFlow);

    /**
     * Process received APERAK.
     *
     * @param rootElement the root ELEMENT
     * @return true, if successful
     */
    boolean processReceivedAperak(final Element rootElement);

}

