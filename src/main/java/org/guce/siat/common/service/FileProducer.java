package org.guce.siat.common.service;

import java.util.Map;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.siat.common.model.ItemFlow;

/**
 * The Interface FileProducer.
 */
public interface FileProducer {

    /**
     * Send file.
     *
     * @param file the file
     * @return
     */
    boolean sendFile(Map<String, Object> file);

    /**
     * resend decision.
     *
     * @param itemFlow
     * @return
     */
    boolean resendDecision(ItemFlow itemFlow);

    void sendViaRest(final OrchestraEbxmlMessage ebxml, final org.guce.siat.common.model.File file) throws Exception;

}

