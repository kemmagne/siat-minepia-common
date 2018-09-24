package org.guce.siat.common.service;

import java.util.Map;
import org.guce.siat.common.model.ItemFlow;

/**
 * The Interface FileProducer.
 */
public interface FileProducer {

    /**
     * Send file.
     *
     * @param file the file
     */
    public void sendFile(Map<String, Object> file);

    /**
     *
     * @param itemFlow
     * @throws Exception
     */
    void resendFile(ItemFlow itemFlow) throws Exception;

    /**
     *
     * @param itemFlow
     * @throws Exception
     */
    void resendAcknowledgment(ItemFlow itemFlow) throws Exception;

    /**
     * allow to create the backup of aperak in the right directory so that the
     * file could
     *
     * @param data
     * @throws Exception
     */
    void createAperakBackup(Map<String, Object> data) throws Exception;

}

