package org.guce.siat.common.service.impl;

import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.service.EbxmlPropertiesService;

import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.utils.SiatUtils;
import org.guce.siat.common.utils.ebms.ESBConstants;
import org.guce.siat.common.utils.filter.ExtentionFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * The Class FileProducerImpl.
 *
 */
@Service("fileProducer")
public class FileProducerImpl implements FileProducer {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileProducerImpl.class);

    private static final String ATTACHMENTS_FOLDER_NAME = "attachments";
    private static final String DATE_PATTERN = "yyyy/MM/dd";

    /**
     * The jms template.
     */
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;
    @Autowired
    private EbxmlPropertiesService ebxmlPropertiesService;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#sendFile(java.util.Map)
     */
    @Override
    public void sendFile(final Map<String, Object> data) {

        if (data.get(ESBConstants.CURRENT_FLOW) != null) {
            LOG.info("######## Start creation of backup");

            try {
                createMessageBackup(data, false);
            } catch (Exception ex) {
                LOG.error("Cannot not create backup", ex);
            }

            LOG.info("######## End creation of backup");
        }

        LOG.info("######## Start sending Message");
        if (jmsTemplate != null) {
            jmsTemplate.convertAndSend(data);
            LOG.info("######## Message Sent Successfully");
        }
    }

    @Override
    public void resendFile(final ItemFlow itemFlow) throws Exception {
        resendFile(itemFlow, false);
    }

    @Override
    public void resendAcknowledgment(final ItemFlow itemFlow) throws Exception {
        resendFile(itemFlow, true);
    }

    @Override
    public void createAperakBackup(final Map<String, Object> data) throws Exception {
        createMessageBackup(data, true);
    }

    private void resendFile(final ItemFlow itemFlow, final boolean aperak) throws Exception {
        // folder : rootFolder/fileType/dateCreationDossier/eguceNb/flow/ifIds
        // att : folder/attachments
        // xml : folder/service_eguceNb_typeDoc.xml

        final Map<String, Object> data = new HashMap<>();

        final String rootFolder = ebxmlPropertiesService.getEbxmlFolder();

        final DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        final File file = itemFlow.getFileItem().getFile();
        java.io.File folder = new java.io.File(String.format("%s/%s/%s/%s/%s",
                rootFolder, file.getFileTypeGuce(), df.format(file.getCreatedDate()),
                file.getNumeroDossier(), itemFlow.getFlow().getCode()));
        if (!folder.exists()) {
            throw new Exception(String.format("Cannot resent the file : folder %s doesn't exist",
                    folder.getAbsolutePath()));
        }

        final String itemFlowId = itemFlow.getId().toString();
        boolean ok = false;
        for (final java.io.File f : folder.listFiles()) {
            final String fName = f.getName();
            if (fName.equals(itemFlowId) || fName.startsWith(itemFlowId.concat("_"))
                    || fName.contains("_".concat(itemFlowId).concat("_"))
                    || fName.endsWith("_".concat(itemFlowId))) {
                folder = f;
                ok = true;
                break;
            }
        }
        if (!ok) {
            throw new Exception(String.format("Cannot find folder by item flow id %s", itemFlowId));
        }

        final FilenameFilter filter = new ExtentionFileFilter(ESBConstants.XML_FILE_EXTENSION);
        final java.io.File[] xmlFiles = folder.listFiles(filter);
        if (xmlFiles.length == 0) {
            throw new Exception("Cannot find xml file");
        }

        final java.io.File xmlFile = xmlFiles[0];
        Path path = Paths.get(xmlFile.getAbsolutePath());
        final byte[] xmlBytes = Files.readAllBytes(path);
        data.put(ESBConstants.FLOW, xmlBytes);

        if (!aperak) {
            folder = new java.io.File(String.format("%s/%s", folder.getAbsolutePath(), ATTACHMENTS_FOLDER_NAME));
            if (folder.exists()) {
                Map<String, byte[]> attachments = new HashMap<>();
                for (final java.io.File attachment : folder.listFiles()) {
                    final String attName = attachment.getName();
                    path = Paths.get(attachment.getAbsolutePath());
                    final byte[] attBytes = Files.readAllBytes(path);
                    attachments.put(attName, attBytes);
                }
                data.put(ESBConstants.ATTACHMENT, attachments);
            }
        }

        final String xmlFileName = xmlFile.getName().substring(0,
                xmlFile.getName().lastIndexOf(ESBConstants.XML_FILE_EXTENSION));
        final String[] parts = xmlFileName.split("_");
        final String service = parts[0];
        final String documentType = parts[2];
        data.put(ESBConstants.SERVICE, service);
        data.put(ESBConstants.TYPE_DOCUMENT, documentType);
        data.put(ESBConstants.MESSAGE, null);
        data.put(ESBConstants.EBXML_TYPE, !aperak ? "STANDARD" : ESBConstants.APERAK);
        data.put(ESBConstants.TO_PARTY_ID, ebxmlPropertiesService.getToPartyId());
        data.put(ESBConstants.DEAD, "0");

        LOG.info("######## Start resending Message");
        if (jmsTemplate != null) {
            jmsTemplate.convertAndSend(data);
            LOG.info("######## Message resent Successfully");
        }
    }

    private void createMessageBackup(final Map<String, Object> data, final boolean aperak) throws Exception {
        // folder : rootFolder/fileType/dateCreationDossier/eguceNb/flow/ifIds
        // att : folder/attachments
        // xml : folder/service_eguceNb_typeDoc.xml

        // some informations could be empty or null
        final byte[] xmlBytes = (byte[]) data.get(ESBConstants.FLOW);
        final File file = (File) data.get(ESBConstants.FILE);

        if (org.apache.commons.lang.StringUtils.isBlank((String) data.get(ESBConstants.SERVICE))) {
            final String service = file.getFileTypeGuce();
            data.put(ESBConstants.SERVICE, service);
        }

        if (org.apache.commons.lang.StringUtils.isBlank((String) data.get(ESBConstants.TYPE_DOCUMENT))) {
            final String documentType = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/TYPE_DOCUMENT");
            data.put(ESBConstants.TYPE_DOCUMENT, documentType);
        }

        final String rootFolder = ebxmlPropertiesService.getEbxmlFolder();
        final DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        final String ifIds = StringUtils
                .collectionToDelimitedString((List) data.get(ESBConstants.ITEM_FLOW_IDS), "_");
        final java.io.File folder = new java.io.File(String.format("%s/%s/%s/%s/%s/%s",
                rootFolder, file.getFileTypeGuce(), df.format(file.getCreatedDate()),
                file.getNumeroDossier(), data.get(ESBConstants.CURRENT_FLOW), ifIds));
        folder.mkdirs();

        final String xmlFilePath = String.format("%s/%s_%s_%s%s",
                folder.getAbsolutePath(), data.get(ESBConstants.SERVICE),
                file.getNumeroDossier(), data.get(ESBConstants.TYPE_DOCUMENT),
                ESBConstants.XML_FILE_EXTENSION);
        try (final FileOutputStream os = new FileOutputStream(xmlFilePath)) {
            os.write(xmlBytes);
        }

        if (!aperak) {
            final Map<String, byte[]> attachments = (Map<String, byte[]>) data.get(ESBConstants.ATTACHMENT);
            if (MapUtils.isNotEmpty(attachments)) {
                final java.io.File attachmentsFolder = new java.io.File(String.format("%s/%s",
                        folder.getAbsolutePath(), ATTACHMENTS_FOLDER_NAME));
                attachmentsFolder.mkdirs();
                for (Map.Entry<String, byte[]> att : attachments.entrySet()) {
                    String attName = att.getKey();
                    byte[] attBytes = att.getValue();
                    try (final FileOutputStream os = new FileOutputStream(String.format("%s/%s",
                            attachmentsFolder.getAbsolutePath(), attName))) {
                        os.write(attBytes);
                    }
                }
            }
        }

        //
        reset(data);
    }

    private void reset(final Map<String, Object> data) {
        data.remove(ESBConstants.FILE);
        data.remove(ESBConstants.CURRENT_FLOW);
        data.remove(ESBConstants.ITEM_FLOW_IDS);
    }

}

