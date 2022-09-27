package org.guce.siat.common.job;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.core.OrchestraEbxmlMessageFactory;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.service.FileProducer;
import org.guce.siat.common.service.MessageToSendService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author lissouck
 */
@Component("taskResenderForMessageToResend")
public class TaskResendMessageToResend {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TaskResendMessageToResend.class);

    private static final OrchestraEbxmlMessageFactory FACTORY = OrchestraEbxmlMessageFactory.getInstance();

    @Autowired
    private PropertiesLoader propertiesLoader;

    @Autowired
    private FileProducer fileProducer;

    @Autowired
    private MessageToSendService messageToSendService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * The max retry time number for reprocess messages job.
     */
    private String maxRetryTimeNumber;

    /**
     * The number of minute to exceed between two retry for reprocess messages
     * job.
     */
    private String numberMinuteExceedBetweenTwoRetry;

    /**
     * The maximum life time in minute
     */
    private String maxLifeTime;

    @PostConstruct
    public void init() {
        maxRetryTimeNumber = propertiesLoader.getProperty(PropertiesConstants.MAX_RETRY_TIME_NUMBER);
        numberMinuteExceedBetweenTwoRetry = propertiesLoader.getProperty(PropertiesConstants.NUMBER_MINUTE_EXCEEDED_BETWEEN_TWO_RETRY);
        maxLifeTime = propertiesLoader.getProperty(PropertiesConstants.MAX_LIFE_TIME_IN_MINUTE);
    }

    public void resendMessageNotAlreadySended() {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            LOG.info("Starting job to resend message not already send");
            int maxRetryTimeNumberInt = 10;
            if (maxRetryTimeNumber != null && StringUtils.isNotBlank(maxRetryTimeNumber)) {
                maxRetryTimeNumberInt = Integer.parseInt(maxRetryTimeNumber);
            }
            int numberMinuteExceedBetweenTwoRetryInt = 3;
            if (numberMinuteExceedBetweenTwoRetry != null && StringUtils.isNotBlank(numberMinuteExceedBetweenTwoRetry)) {
                numberMinuteExceedBetweenTwoRetryInt = Integer.parseInt(numberMinuteExceedBetweenTwoRetry);
            }
            List<MessageToSend> messageToSendList = messageToSendService.getMessagesToResend(maxRetryTimeNumberInt, numberMinuteExceedBetweenTwoRetryInt);
            for (MessageToSend messageToSend : messageToSendList) {
                synchronized (messageToSend) {
                    try {
                        LOG.info("----------------- Trying to send message ID = {0} ------------", messageToSend.getMessageId());
                        final OrchestraEbxmlMessage ebxml = FACTORY.createFromByte(messageToSend.getEbxml());
                        String response = null;
                        try {
                            response = fileProducer.sendEbxmlViaRest(ebxml);
                        } catch (Exception ex) {
                            LOG.error(ex.getMessage(), ex);
                        }
                        messageToSendService.saveOrDeleteNotSendedMessageAsMessageToResend(messageToSend, response);
                    } catch (Exception ex) {
                        LOG.error(ex.getMessage(), ex);
                    }
                }

            }
            TransactionStatus tsCommit = transactionStatus;
            transactionStatus = null;
            transactionManager.commit(tsCommit);
        } catch (Exception ex) {
            LOG.error(null, ex);
        } finally {
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("####ROLLBACK Transaction####");
                }
            }
        }
        LOG.info("Ending job to resend message not already send");
    }

    public void purgeAllMessagesExceededMaxNumberOfRetry() {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            LOG.info("Starting job to purge message not already send");
            int maxRetryTimeNumberInt = 10;
            if (maxRetryTimeNumber != null && StringUtils.isNotBlank(maxRetryTimeNumber)) {
                maxRetryTimeNumberInt = Integer.parseInt(maxRetryTimeNumber);
            }
            int maxLifeTimeInt = 10;
            if (maxLifeTime != null && StringUtils.isNotBlank(maxLifeTime)) {
                maxLifeTimeInt = Integer.parseInt(maxLifeTime);
            }
//            messageToSendService.deleteMessagesExceededMaxNumberRetrySendAndMaxLifeTime(maxRetryTimeNumberInt, maxLifeTimeInt);
            List<MessageToSend> messageToSendList = messageToSendService.findMessagesExceededMaxNumberRetrySendAndMaxLifeTime(maxRetryTimeNumberInt, maxLifeTimeInt);
            for (MessageToSend messageToSend : messageToSendList) {
                synchronized (messageToSend) {
                    try {
                        //emailSenderService.send(....)
                        messageToSendService.delete(messageToSend);

                    } catch (Exception ex) {
                        LOG.error(ex.getMessage(), ex);
                    }
                }

            }
            TransactionStatus tsCommit = transactionStatus;
            transactionStatus = null;
            transactionManager.commit(tsCommit);
        } catch (Exception ex) {
            LOG.error(null, ex);
        } finally {
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("####ROLLBACK Transaction####");
                }
            }
        }
        LOG.info("Ending job to purge message not already send");
    }
}
