package org.guce.siat.common.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.core.OrchestraEbxmlMessageFactory;
import org.guce.siat.common.mail.bo.EmailSenderService;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.service.MessageToSendService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author tadzotsa
 */
public class MessageSenderJob extends QuartzJobBean {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderJob.class);

    private static final OrchestraEbxmlMessageFactory FACTORY = OrchestraEbxmlMessageFactory.getInstance();


    @Autowired
    private FileProducer fileProducer;


    @Autowired
    private MessageToSendService messageToSendService;

    /**
     * This method is just for test purpose
     *
     * @throws JobExecutionException
     */
    public void executeInternal1() throws JobExecutionException {
        executeInternal(null);
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

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

    public FileProducer getFileProducer() {
        return fileProducer;
    }

    public void setFileProducer(FileProducer fileProducer) {
        this.fileProducer = fileProducer;
    }

    public MessageToSendService getMessageToSendService() {
        return messageToSendService;
    }

    public void setMessageToSendService(MessageToSendService messageToSendService) {
        this.messageToSendService = messageToSendService;
    }
    
    

}
