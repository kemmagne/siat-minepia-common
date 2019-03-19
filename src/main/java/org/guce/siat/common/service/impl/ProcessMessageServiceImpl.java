package org.guce.siat.common.service.impl;

import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.siat.common.service.DocumentReciever;
import org.guce.siat.common.service.ProcessMessageService;
import org.guce.siat.common.utils.EbxmlUtils;
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

        return response != null ? response.getData() : null;
    }

}
