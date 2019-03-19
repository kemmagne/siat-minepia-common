package org.guce.siat.common.service.impl;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.soap.SOAPException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.core.OrchestraEbxmlMessageFactory;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.mail.MailConstants;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;

import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.service.MailService;
import org.guce.siat.common.utils.EbxmlUtils;
import org.guce.siat.common.utils.SecurityUtils;
import org.guce.siat.common.utils.XmlXPathUtils;
import org.guce.siat.common.utils.ebms.ESBConstants;
import org.guce.siat.common.utils.enums.AperakType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;

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
     * The rest template.
     */
    private RestTemplate restTemplate;
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

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        restTemplate.setRequestFactory(requestFactory);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#sendFile(java.util.Map)
     */
    @Override
    public boolean sendFile(final Map<String, Object> data) {

        LOG.info("######## Start sending Message");
        try {
            final OrchestraEbxmlMessage ebxml = EbxmlUtils.mapToEbxml(data);
            // save the message id to item flows
            final List<ItemFlow> itemFlows = (List<ItemFlow>) data.get(ESBConstants.ITEM_FLOWS);
            if (CollectionUtils.isNotEmpty(itemFlows)) {
                final int nbIfs = itemFlows.size();
                for (int index = 0; index < nbIfs; index++) {
                    itemFlows.get(index).setMessageId(ebxml.getMessageId());
                }
                itemFlowDao.saveOrUpdateList(itemFlows);
            }
            sendViaRest(ebxml);

            LOG.info("######## Message Sent Successfully");
            return Boolean.TRUE;
        } catch (Exception ex) {
            LOG.error("######## Couldn't send Message", ex);
            return Boolean.FALSE;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#sendViaRest(byte[])
     */
    public void sendViaRest(final OrchestraEbxmlMessage ebxml) throws Exception {
        try {
            final byte[] ebxmlData = ebxml.getData();
            final InputStream in = new ByteArrayInputStream(ebxmlData);
            final RequestCallback requestCallback = new RequestCallback() {
                @Override
                public void doWithRequest(ClientHttpRequest request) throws IOException {
                    request.getHeaders().add("Content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
                    request.getHeaders().add("Authorization", SecurityUtils.getBasicAuth(LOGIN, PASSWORD));
                    IOUtils.copy(in, request.getBody());
                }
            };

            final HttpMessageConverterExtractor<String> responseExtractor = new HttpMessageConverterExtractor<>(String.class, restTemplate.getMessageConverters());

            restTemplate.execute(webserviceUrl, HttpMethod.POST, requestCallback, responseExtractor);
            backupNotSentMsg(ebxml, Boolean.TRUE);
        } catch (Exception ex) {
            backupNotSentMsg(ebxml, Boolean.FALSE);
            throw ex;
        }
    }

    private void backupNotSentMsg(final OrchestraEbxmlMessage ebxml, boolean sent) throws SOAPException, IOException {
        final String messageID = ebxml.getMessageId();
        final String backupFileName = String.format("%s.ebxml", messageID);
        java.io.File backupFile = new java.io.File(messagesFolder, backupFileName);
        backupFile.getParentFile().mkdirs();
        if (!backupFile.exists()) {
            IOUtils.write(ebxml.getData(), new FileOutputStream(backupFile));
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

    @Override
    public boolean resendDecision(final ItemFlow itemFlow) {
        try {
            final String messageID = itemFlow.getMessageId();
            final String backupFileName = String.format("%s.ebxml", messageID);
            java.io.File backupFile = new java.io.File(messagesFolder, backupFileName);
            OrchestraEbxmlMessage ebxml = OrchestraEbxmlMessageFactory.getInstance().createFromFile(backupFile.getAbsolutePath());
            sendViaRest(ebxml);
            return Boolean.TRUE;
        } catch (Exception ex) {
            LOG.error("######## Couldn't resend Message", ex);
            return Boolean.FALSE;
        }
    }

}

