package org.guce.siat.common.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.soap.SOAPException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.core.OrchestraEbxmlMessageFactory;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.service.MessageToSendService;
import org.guce.siat.common.utils.EbxmlUtils;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.guce.siat.common.utils.SecurityUtils;
import org.guce.siat.common.utils.ebms.ESBConstants;
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

/**
 * The Class FileProducerImpl.
 *
 */
@Service("fileProducer")
@PropertySources({
    @PropertySource("classpath:global-config.properties")
})
public class FileProducerImpl implements FileProducer {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileProducerImpl.class);

    private static final String LOGIN = "@4wWYa3!9fhMS@dqMlKY";
    private static final String PASSWORD = "ek5zD]hKv4@WuD$5";

    @Autowired
    private PropertiesLoader propertiesLoader;

    /**
     * The item flow dao.
     */
    @Autowired
    private ItemFlowDao itemFlowDao;

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
        messagesFolder = propertiesLoader.getProperty(PropertiesConstants.MESSAGES_FOLDER);
        webserviceUrl = propertiesLoader.getProperty(PropertiesConstants.WEBSERVICE_URL);

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
            OrchestraEbxmlMessage ebxml = EbxmlUtils.mapToEbxml(data);
            // save the message id to item flows
            List<ItemFlow> itemFlows = (List<ItemFlow>) data.get(ESBConstants.ITEM_FLOWS);
            if (CollectionUtils.isNotEmpty(itemFlows)) {
                int nbIfs = itemFlows.size();
                for (int index = 0; index < nbIfs; index++) {
                    itemFlows.get(index).setMessageId(ebxml.getMessageId());
                }
                itemFlowDao.saveOrUpdateList(itemFlows);
            }
            sendViaRest(ebxml, itemFlows.get(0).getFileItem().getFile());

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
    @Override
    public void sendViaRest(final OrchestraEbxmlMessage ebxml, final org.guce.siat.common.model.File file) throws Exception {
        try {
            byte[] ebxmlData = ebxml.getData();
            final InputStream in = new ByteArrayInputStream(ebxmlData);
            RequestCallback requestCallback = new RequestCallback() {
                @Override
                public void doWithRequest(ClientHttpRequest request) throws IOException {
                    request.getHeaders().add("Content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
                    request.getHeaders().add("Authorization", SecurityUtils.getBasicAuth(LOGIN, PASSWORD));
                    IOUtils.copy(in, request.getBody());
                    IOUtils.closeQuietly(in);
                }
            };

            HttpMessageConverterExtractor<String> responseExtractor = new HttpMessageConverterExtractor<>(String.class, restTemplate.getMessageConverters());

            String response = restTemplate.execute(webserviceUrl, HttpMethod.POST, requestCallback, responseExtractor);
            backupNotSentMsg(ebxml, Boolean.TRUE, file);
        } catch (Exception ex) {
            backupNotSentMsg(ebxml, Boolean.FALSE, file);
            throw ex;
        }
    }
    
    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#sendEbxmlViaRest(byte[])
     */
    @Override
    public String sendEbxmlViaRest(final OrchestraEbxmlMessage ebxml) throws Exception {
        String RESPONSE ="";
        try {
            byte[] ebxmlData = ebxml.getData();
            final InputStream in = new ByteArrayInputStream(ebxmlData);
            RequestCallback requestCallback = new RequestCallback() {
                @Override
                public void doWithRequest(ClientHttpRequest request) throws IOException {
                    request.getHeaders().add("Content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
                    request.getHeaders().add("Authorization", SecurityUtils.getBasicAuth(LOGIN, PASSWORD));
                    IOUtils.copy(in, request.getBody());
                    IOUtils.closeQuietly(in);
                }
            };

            HttpMessageConverterExtractor<String> responseExtractor = new HttpMessageConverterExtractor<>(String.class, restTemplate.getMessageConverters());

            RESPONSE = restTemplate.execute(webserviceUrl, HttpMethod.POST, requestCallback, responseExtractor);
        } catch (Exception ex) {
            throw ex;
        }
        return RESPONSE;
    }

    private void backupNotSentMsg(final OrchestraEbxmlMessage ebxml, final boolean sent, org.guce.siat.common.model.File file) throws SOAPException, IOException {
        String messageID = ebxml.getMessageId();
        if (file == null) {
            ItemFlow itemFlow = itemFlowDao.findByMessageId(messageID);
            if (itemFlow != null) {
                file = itemFlow.getFileItem().getFile();
            }
        }
        String fileName = String.format("%s.ebxml", messageID);
        java.io.File notSentFile = new java.io.File(messagesFolder, fileName);
        notSentFile.getParentFile().mkdirs();
        byte[] ebxmlData = ebxml.getData();
        if (!notSentFile.exists()) {
            FileUtils.writeByteArrayToFile(notSentFile, ebxmlData);
        } else if (sent) {
            if (file != null) {
                StringBuilder builder = new StringBuilder(messagesFolder);
                if (!messagesFolder.endsWith("/")) {
                    builder.append("/");
                }
                builder.append("backups").append(new SimpleDateFormat("/yyyy/MM/dd").format(Calendar.getInstance().getTime()));
                String backupFileName = String.format("%s_%s_%s.ebxml", file.getNumeroDossier(), ebxml.getAction(), ebxml.getMessageId());
                File backupFile = new File(builder.toString(), backupFileName);
                FileUtils.writeByteArrayToFile(backupFile, ebxmlData);
            }
            notSentFile.delete();
        }
    }

    @Override
    public boolean resendDecision(final ItemFlow itemFlow) {
        try {
            String messageID = itemFlow.getMessageId();
            String backupFileName = String.format("%s.ebxml", messageID);
            java.io.File backupFile = new java.io.File(messagesFolder, backupFileName);
            OrchestraEbxmlMessage ebxml = OrchestraEbxmlMessageFactory.getInstance().createFromFile(backupFile.getAbsolutePath());
            sendViaRest(ebxml, itemFlow.getFileItem().getFile());
            return Boolean.TRUE;
        } catch (Exception ex) {
            LOG.error("######## Couldn't resend Message", ex);
            return Boolean.FALSE;
        }
    }

}
