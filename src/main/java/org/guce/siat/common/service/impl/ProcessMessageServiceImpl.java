package org.guce.siat.common.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import javax.persistence.PersistenceException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.guce.siat.common.service.DocumentReciever;
import org.guce.siat.common.service.ProcessMessageService;
import org.guce.siat.common.utils.EbxmlUtils;
import org.guce.siat.common.utils.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

@Transactional
@Service("processMessageService")
public class ProcessMessageServiceImpl implements ProcessMessageService {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ProcessMessageServiceImpl.class);

    @Override
    public byte[] process(final byte[] message, final DocumentReciever documentReciever) {

        byte[] response = null;

        Map<String, Object> messageMap = null;
        try {
            messageMap = EbxmlUtils.ebxmlToMap(message);
            final Map<String, Object> result = documentReciever.uploadEbxmlFile(messageMap);
            if (result != null) {
                response = EbxmlUtils.mapToEbxml(result);
            }
        } catch (final ValidationException | ParseException | TransformerException | SOAPException | SAXException
                | ParserConfigurationException | JAXBException | XPathExpressionException | IndexOutOfBoundsException
                | PersistenceException | NullPointerException | IOException e) {
            LOG.error("####Process Recieved exception : " + e.getMessage(), e);
            try {
                final Map<String, Object> exectionResult = documentReciever.generateAperakCFile(messageMap, e.getMessage());
                response = EbxmlUtils.mapToEbxml(exectionResult);
            } catch (Exception ex) {
                LOG.error("####Process Recieved exception : " + ex.getMessage(), ex);
            }
        }

        return response;
    }

}
