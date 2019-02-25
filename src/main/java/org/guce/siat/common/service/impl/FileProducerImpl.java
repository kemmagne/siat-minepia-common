package org.guce.siat.common.service.impl;

import hk.hku.cecid.ebms.pkg.EbxmlMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.mail.MailConstants;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;

import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.service.MailService;
import org.guce.siat.common.utils.EbxmlUtils;
import org.guce.siat.common.utils.HttpUtils;
import org.guce.siat.common.utils.XmlXPathUtils;
import org.guce.siat.common.utils.ebms.ESBConstants;
import org.guce.siat.common.utils.enums.AperakType;
import org.guce.siat.common.utils.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * The Class FileProducerImpl.
 *
 */
@Service("fileProducer")
@PropertySources({
    @PropertySource("classpath:global-config.properties")})
public class FileProducerImpl implements FileProducer {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileProducerImpl.class);

    private static final String LOGIN = "@4wWYa3!9fhMS@dqMlKY";
    private static final String PASSWORD = "ek5zD]hKv4@WuD$5";

    /**
     * The Constant NEGATIVE_APERAK_MAIL.
     */
    private static final String NEGATIVE_APERAK_MAIL = "negatifAperakReceived.vm";

    /**
     * The file dao.
     */
    @Autowired
    private FileDao fileDao;

    /**
     * The item flow dao.
     */
    @Autowired
    private ItemFlowDao itemFlowDao;
    /**
     * The file item.
     */
    @Autowired
    private FileItemDao fileItemDao;

    /**
     * The mail service.
     */
    @Autowired
    private MailService mailService;

    /**
     * The jms template.
     */
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;

    /**
     * The rest template.
     */
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    /**
     * use jms ?
     */
    @Value("${use.jms}")
    private boolean useJms;
    /**
     * the messages folder
     */
    @Value("${messages.folder}")
    private String messagesFolder;
    /**
     * the messages folder
     */
    @Value("${webservice.url}")
    private String webserviceUrl;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#sendFile(java.util.Map)
     */
    @Override
    public void sendFile(final Map<String, Object> data) {

        LOG.info("######## Start sending Message");
        if (!useJms) {
            try {
                final byte[] ebxml = EbxmlUtils.mapToEbxml(data);
                sendViaRest(ebxml);
            } catch (SOAPException | IOException | TransformerException ex) {
                LOG.error(null, ex);
            }
        } else {
            jmsTemplate.convertAndSend(data);
        }
        LOG.info("######## Message Sent Successfully");
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#sendViaRest(byte[])
     */
    @Override
    public void sendViaRest(final byte[] ebxml) throws SOAPException, IOException, TransformerException {
        final HttpEntity<byte[]> requestEntity = new HttpEntity<>(ebxml, HttpUtils.createHeaders(LOGIN, PASSWORD));
        final ResponseEntity responseEntity = restTemplate.exchange(webserviceUrl, HttpMethod.POST, requestEntity, Object.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            byte[] responseBytes = (byte[]) responseEntity.getBody();
            Map<String, Object> responseMap = EbxmlUtils.ebxmlToMap(responseBytes);
            final byte[] xmlBytes = (byte[]) responseMap.get(ESBConstants.FLOW);
            final String xmlContent = new String(xmlBytes);
            try {
                final Element rootElement = XmlXPathUtils.stringToXMLDOM(xmlContent).getDocumentElement();
                processReceivedAperak(rootElement);
            } catch (SAXException | ParserConfigurationException ex) {
                LOG.error(null, ex);
            }
            backupNotSentMsg(ebxml, Boolean.TRUE);
        } else {
            backupNotSentMsg(ebxml, Boolean.FALSE);
        }
    }

    private void backupNotSentMsg(final byte[] ebxml, boolean sent) throws SOAPException, IOException {
        final EbxmlMessage ebxmlMessage = new EbxmlMessage(new ByteArrayInputStream(ebxml));
        final String messageID = ebxmlMessage.getMessageId();
        final String backupFileName = String.format("%s.ebxml", messageID);
        java.io.File backupFile = new java.io.File(messagesFolder, backupFileName);
        backupFile.getParentFile().mkdirs();
        if (!backupFile.exists()) {
            IOUtils.writeBytesToFile(backupFile, ebxml);
        } else {
            if (sent) {
                backupFile.delete();
            }
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#processReceivedAperak(org.w3c.dom.Element)
     */
    @Override
    public boolean processReceivedAperak(Element rootElement) {
        final String documentTypeExpression = "/DOCUMENT/TYPE_DOCUMENT";

        final String referenceSiatExpression = "/DOCUMENT/REFERENCE_DOSSIER/REFERENCE_SIAT";

        final String documentType = XmlXPathUtils.findSingleValue(documentTypeExpression, rootElement);
        final String referenceSiat = XmlXPathUtils.findSingleValue(referenceSiatExpression, rootElement);
        if (org.apache.commons.lang.StringUtils.isNotBlank(documentType) && org.apache.commons.lang.StringUtils.isNotBlank(referenceSiat)) {
            final org.guce.siat.common.model.File siatFile = fileDao.findByRefSiat(referenceSiat);
            final List<FileItem> fileItems = siatFile.getFileItemsList();

            if (AperakType.APERAK_C.name().equals(documentType) || AperakType.APERAK_J.name().equals(documentType)) {
                //rollback the last decision
                String senderMail = null;
                for (final FileItem fileItem : fileItems) {
                    final ItemFlow itemflow = itemFlowDao.findLastOutgoingItemFlowByFileItem(fileItem);
                    senderMail = itemflow.getSender().getEmail();
                    itemflow.setReceived(AperakType.APERAK_C.getCharCode());
                    itemflow.setSent(Boolean.FALSE);
                    itemFlowDao.update(itemflow);

                    fileItem.setDraft(Boolean.TRUE);
                    fileItem.setStep(itemflow.getFlow().getFromStep());
                    fileItemDao.update(fileItem);
                }
                final String templateFileName = NEGATIVE_APERAK_MAIL;
                final Map<String, String> map = new HashMap<>();
                map.put(MailConstants.SUBJECT, "SIAT : Dossier non envoyé");
                map.put(MailConstants.FROM, mailService.getFromValue());
                map.put(MailConstants.EMAIL, senderMail);
                map.put("refSiat", siatFile.getReferenceSiat());
                map.put(MailConstants.VMF, templateFileName);
                mailService.sendMail(map);
                return true;
            } else if (AperakType.APERAK_D.name().equals(documentType) || AperakType.APERAK_F.name().equals(documentType)) {
                for (final FileItem fileItem : fileItems) {
                    final ItemFlow itemFlow = itemFlowDao.findLastOutgoingItemFlowByFileItem(fileItem);
                    if (itemFlow != null) {
                        itemFlow.setReceived(AperakType.APERAK_D.getCharCode());
                        itemFlowDao.update(itemFlow);
                    }
                }
                return true;
            }
        }
        return false;
    }

}

