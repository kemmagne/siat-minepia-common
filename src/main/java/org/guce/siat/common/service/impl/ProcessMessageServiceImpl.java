package org.guce.siat.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import javax.xml.soap.SOAPException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.util.JAXBUtil;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.doc.DOCUMENT;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.service.DocumentReciever;
import org.guce.siat.common.service.ProcessMessageService;
import org.guce.siat.common.utils.EbxmlUtils;
import org.guce.siat.common.utils.ParamsConstants;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.guce.siat.common.utils.ebms.ESBConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ProcessMessageServiceImpl.class);

    @Autowired
    private PropertiesLoader propertiesLoader;

    @Autowired
    private ParamsDao paramsDao;

    @Override
    public byte[] process(byte[] message, DocumentReciever documentReciever) {

        OrchestraEbxmlMessage response = null;

        Map<String, Object> messageMap = null;
        try {
            messageMap = EbxmlUtils.ebxmlToMap(message);
            byte[] xmlBytes = (byte[]) messageMap.get(ESBConstants.FLOW);
            if (xmlBytes != null) {
                try {
                    DOCUMENT document = JAXBUtil.unmarshall(xmlBytes, DOCUMENT.class);
                    LOG.info(MessageFormat.format("Trying to integrate message {0} from file {1} of type {2} from partner {3}", document.getMESSAGE().getNUMEROMESSAGE(), document.getREFERENCEDOSSIER().getNUMERODOSSIER(), document.getTYPEDOCUMENT(), document.getROUTAGE().getEMETTEUR()));
                    backup(propertiesLoader, paramsDao, xmlBytes, document);
                } catch (Exception ex) {
                    LOG.error("Couldn't get infos from xml : " + ex.getMessage(), ex);
                }
            }
            Map<String, Object> result = documentReciever.uploadEbxmlFile(messageMap);
            if (MapUtils.isNotEmpty(result)) {
                response = EbxmlUtils.mapToEbxml(result);
            }
        } catch (Exception e) {
            LOG.error("####Process Recieved exception : " + e.getMessage(), e);
            try {
                Map<String, Object> exceptionResult = documentReciever.generateAperakCFile(messageMap, e.getMessage());
                response = EbxmlUtils.mapToEbxml(exceptionResult);
            } catch (Exception ex) {
                LOG.error("####Process Recieved exception : " + ex.getMessage(), ex);
            }
        }

        if (response == null) {
            try {
                Map<String, Object> exceptionResult = documentReciever.generateAperakCFile(messageMap, "The response is null");
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

        if (response != null) {
            return response.getData();
        }

        return null;
    }

    public static String backup(PropertiesLoader propertiesLoader, ParamsDao paramsDao, byte[] ebxmlData, DOCUMENT document) {

        Params params = paramsDao.findParamsByName(ParamsConstants.BACKUP_RECEVED_MESSAGES.getParamsName());
        boolean backup = params != null && BooleanUtils.toBoolean(params.getValue());
        if (!backup) {
            return null;
        }

        try {
            String ebxmlFolder = propertiesLoader.getProperty(PropertiesConstants.EBXML_FOLDER);
            StringBuilder builder = new StringBuilder(ebxmlFolder);
            if (!ebxmlFolder.endsWith("/")) {
                builder.append("/");
            }
            builder.append("backups").append(new SimpleDateFormat("/yyyy/MM/dd").format(Calendar.getInstance().getTime()));
            String backupFileName = String.format("%s_%s_%s.ebxml",
                    document.getREFERENCEDOSSIER().getNUMERODOSSIER(),
                    document.getTYPEDOCUMENT(),
                    document.getMESSAGE().getNUMEROMESSAGE());
            File backupFile = new File(builder.toString(), backupFileName);
            FileUtils.writeByteArrayToFile(backupFile, ebxmlData);
            return backupFile.getPath();
        } catch (IOException ex) {
            LOG.warn("####Unable to create backup : " + ex.getMessage(), ex);
            return null;
        }
    }

}
