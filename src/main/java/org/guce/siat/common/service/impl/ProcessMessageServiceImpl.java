package org.guce.siat.common.service.impl;

import java.text.MessageFormat;
import java.util.Map;
import javax.xml.soap.SOAPException;
import org.apache.commons.collections.MapUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.util.JAXBUtil;
import org.guce.siat.common.doc.DOCUMENT;
import org.guce.siat.common.service.DocumentReciever;
import org.guce.siat.common.service.ProcessMessageService;
import org.guce.siat.common.utils.EbxmlUtils;
import org.guce.siat.common.utils.ebms.ESBConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ProcessMessageServiceImpl.class);

    @Override
    public byte[] process(final byte[] message, final DocumentReciever documentReciever) {

        OrchestraEbxmlMessage response = null;

        Map<String, Object> messageMap = null;
        try {
            messageMap = EbxmlUtils.ebxmlToMap(message);
            byte[] xmlBytes = (byte[]) messageMap.get(ESBConstants.FLOW);
            if (xmlBytes != null) {
                DOCUMENT document = JAXBUtil.unmarshall(xmlBytes, DOCUMENT.class);
                try {
                    LOG.info(MessageFormat.format("Trying to integrate message {0} from file {1} of type {2} from partner {3}", document.getMESSAGE().getNUMEROMESSAGE(), document.getREFERENCEDOSSIER().getNUMERODOSSIER(), document.getTYPEDOCUMENT(), document.getROUTAGE().getEMETTEUR()));
                } catch (Exception ex) {
                    LOG.error("Couldn't get infos from xml", ex);
                }
            } else {
                LOG.info("Couldn't get xml bytes");
            }
            final Map<String, Object> result = documentReciever.uploadEbxmlFile(messageMap);
            if (MapUtils.isNotEmpty(result)) {
                response = EbxmlUtils.mapToEbxml(result);
            }
        } catch (final Exception e) {
            LOG.error("####Process Recieved exception : " + e.getMessage(), e);
            try {
                final Map<String, Object> exceptionResult = documentReciever.generateAperakCFile(messageMap, e.getMessage());
                response = EbxmlUtils.mapToEbxml(exceptionResult);
            } catch (Exception ex) {
                LOG.error("####Process Recieved exception : " + ex.getMessage(), ex);
            }
        }

        if (response == null) {
            try {
                final Map<String, Object> exceptionResult = documentReciever.generateAperakCFile(messageMap, "The response is null");
                response = EbxmlUtils.mapToEbxml(exceptionResult);
            } catch (Exception ex) {
                LOG.error(null, ex);
                try {
                    response = new OrchestraEbxmlMessage();
                } catch (SOAPException ex1) {
                    LOG.error(null, ex1);
                }
            }
        }

        return response.getData();
    }

}
