package org.guce.siat.common.service;

import java.io.IOException;
import java.util.Map;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
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
     * send message to rest api
     *
     * @param ebxml
     * @throws SOAPException
     * @throws IOException
     * @throws TransformerException
     */
    void sendViaRest(byte[] ebxml) throws SOAPException, IOException, TransformerException;

    /**
     * Process received APERAK.
     *
     * @param rootElement the root ELEMENT
     * @return true, if successful
     */
    boolean processReceivedAperak(final Element rootElement);

}

