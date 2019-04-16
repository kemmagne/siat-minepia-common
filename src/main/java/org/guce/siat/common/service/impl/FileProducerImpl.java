package org.guce.siat.common.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.soap.SOAPException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.core.OrchestraEbxmlMessageFactory;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.model.ItemFlow;

import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.utils.EbxmlUtils;
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
    @PropertySource("classpath:global-config.properties")})
public class FileProducerImpl implements FileProducer {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileProducerImpl.class);

    private static final String LOGIN = "@4wWYa3!9fhMS@dqMlKY";
    private static final String PASSWORD = "ek5zD]hKv4@WuD$5";

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

    private String backupFolder;

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
    private void sendViaRest(final OrchestraEbxmlMessage ebxml, final org.guce.siat.common.model.File file) throws Exception {
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
            backupNotSentMsg(ebxml, Boolean.TRUE, file);
        } catch (Exception ex) {
            backupNotSentMsg(ebxml, Boolean.FALSE, file);
            throw ex;
        }
    }

    private void backupNotSentMsg(final OrchestraEbxmlMessage ebxml, boolean sent, org.guce.siat.common.model.File file) throws SOAPException, IOException {
        final String messageID = ebxml.getMessageId();
        final String fileName = String.format("%s.ebxml", messageID);
        java.io.File notSentFile = new java.io.File(messagesFolder, fileName);
        notSentFile.getParentFile().mkdirs();
        final byte[] ebxmlData = ebxml.getData();
        if (!notSentFile.exists()) {
            IOUtils.write(ebxmlData, new FileOutputStream(notSentFile));
        } else {
            if (sent) {
                StringBuilder builder = new StringBuilder(messagesFolder);
                if (!messagesFolder.endsWith("/")) {
                    builder.append("/");
                }
                builder.append("backups")
                        .append(new SimpleDateFormat("/yyyy/MM/dd").format(Calendar.getInstance().getTime()));
                File backupFile = new File(builder.toString());
                backupFile.mkdirs();
                final String backupFileName = String.format("%s_%s_%s.ebxml", file.getNumeroDossier(), ebxml.getAction(), ebxml.getMessageId());
                backupFile = new File(backupFile, backupFileName);
                IOUtils.write(ebxmlData, new FileOutputStream(backupFile));
                notSentFile.delete();
            }
        }
    }

    @Override
    public boolean resendDecision(final ItemFlow itemFlow) {
        try {
            final String messageID = itemFlow.getMessageId();
            final String backupFileName = String.format("%s.ebxml", messageID);
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

