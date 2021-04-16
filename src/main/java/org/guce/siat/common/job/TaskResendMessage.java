package org.guce.siat.common.job;

import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.core.OrchestraEbxmlMessageFactory;
import org.guce.siat.common.mail.bo.EmailSenderService;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.service.MessageToSendService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author tadzotsa
 */
@PropertySource("classpath:global-config.properties")
@Component("taskMessageResender")
public class TaskResendMessage {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TaskResendMessage.class);

    private static final OrchestraEbxmlMessageFactory FACTORY = OrchestraEbxmlMessageFactory.getInstance();

    @Autowired
    private PropertiesLoader propertiesLoader;

    @Autowired
    private FileProducer fileProducer;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private MessageToSendService messageToSendService;

    /**
     * the messages folder
     */
    @Value("${messages.folder}")
    private String messagesFolder;

    @PostConstruct
    public void init() {
        messagesFolder = propertiesLoader.getProperty(PropertiesConstants.MESSAGES_FOLDER);
    }

    public void resendMessage() {
        File messagesFolderFile = new File(messagesFolder);
        messagesFolderFile.mkdirs();
        Collection<File> filesCollections = FileUtils.listFiles(messagesFolderFile, new String[]{"ebxml"}, false);
        if (CollectionUtils.isNotEmpty(filesCollections)) {
            for (final File file : filesCollections) {
                synchronized (file) {
                    try {
                        final OrchestraEbxmlMessage ebxml = FACTORY.createFromFile(file.getAbsolutePath());
                        fileProducer.sendViaRest(ebxml, null);
                    } catch (Exception ex) {
                        LOG.error(null, ex);
                    }
                }
            }
        }
    }

    public void resendMessageNotAlreadySended() {
        try {
            LOG.info("Starting job to resend message not already send");
            List<MessageToSend> messageToSendList = messageToSendService.findAll();
            for (MessageToSend messageToSend : messageToSendList) {
                synchronized (messageToSend) {
                    try {
                        if (messageToSend.getResendRetryNumber() < 5) {
                            final OrchestraEbxmlMessage ebxml = FACTORY.createFromByte(messageToSend.getEbxml());
                            fileProducer.sendViaRest(ebxml, null);
                        } else {
                            //emailSenderService.send(....)
                            messageToSendService.delete(messageToSend);
                        }
                    } catch (Exception ex) {
                        LOG.error(null, ex);
                    }
                }

            }
        } catch (Exception ex) {
            LOG.error(null, ex);
        }
        LOG.info("Ending job to resend message not already send");
    }

}
