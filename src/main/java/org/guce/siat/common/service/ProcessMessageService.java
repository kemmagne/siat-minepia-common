package org.guce.siat.common.service;

/**
 *
 * @author tadzotsa
 */
public interface ProcessMessageService {

    byte[] process(byte[] message, DocumentReciever documentReciever);

}
