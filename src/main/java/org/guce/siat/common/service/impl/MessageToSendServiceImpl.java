package org.guce.siat.common.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.guce.orchestra.core.OrchestraEbxmlMessage;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.MessageToSendDao;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.service.MessageToSendService;
import org.guce.siat.common.utils.EbxmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MessageToSendServiceImpl.
 */
@Service("messageToSendService")
@Transactional(readOnly = true)
public class MessageToSendServiceImpl extends AbstractServiceImpl<MessageToSend> implements MessageToSendService {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MessageToSendServiceImpl.class);
    /**
     * The messageToSend dao.
     */
    @Autowired
    private MessageToSendDao messageToSendDao;

    /**
     * Instantiates a new messageToSend service impl.
     */
    public MessageToSendServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<MessageToSend> getJpaDao() {
        return messageToSendDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<MessageToSend> jpaDao) {
        this.messageToSendDao = (MessageToSendDao) jpaDao;
    }

    @Override
    public MessageToSend findByMessageId(String messageId) {
        return this.messageToSendDao.findByMessageId(messageId);
    }

    @Override
    public void saveOrUpadateNotSendedMessageAsMessageToResend(Map data) {
        try {
            if (data != null) {
                OrchestraEbxmlMessage ebxml = EbxmlUtils.mapToEbxml(data);
                if (ebxml == null) {
                    return;
                }
                LOG.info("---------------------------------------------Sauvegarde du message à renvoyer plutard - DEBUT ----------------------------------------------");
                LOG.info(ebxml.toString());
                MessageToSend messageToSendExist = messageToSendDao.findByMessageId(ebxml.getMessageId());
                if (messageToSendExist == null) {
                    MessageToSend messageToSend = new MessageToSend();
                    messageToSend.setMessageId(ebxml.getMessageId());
                    messageToSend.setEbxml(ebxml.getData());
                    messageToSend.setResendRetryNumber(0);
                    messageToSend.setLastRetrySendTime(Calendar.getInstance().getTime());
                    messageToSendDao.save(messageToSend);
                } else {
                    messageToSendExist.setResendRetryNumber(messageToSendExist.getResendRetryNumber() + 1);
                    messageToSendDao.update(messageToSendExist);
                }
            }
            LOG.info("---------------------------------------------Sauvegarde du message à renvoyer plutard - FIN ----------------------------------------------");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteNotSendedMessageIfExistsAsMessageToResend(Map data) {
        try {
            if (data != null) {
                OrchestraEbxmlMessage ebxml = EbxmlUtils.mapToEbxml(data);
                if (ebxml == null) {
                    return;
                }
                LOG.info("-----------------------------------------Suppression du message à renvoyer plutard - DEBUT ----------------------------------------------");
                LOG.info(ebxml.toString());
                MessageToSend messageToSendExist = messageToSendDao.findByMessageId(ebxml.getMessageId());
                if (messageToSendExist != null) {
                    messageToSendDao.delete(messageToSendExist);
                }
            }
            LOG.info("---------------------------------------------Suppression du message à renvoyer plutard - FIN ----------------------------------------------");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void saveOrDeleteNotSendedMessageAsMessageToResend(MessageToSend messageToSend, String response) {
        if (messageToSend != null) {
            MessageToSend messageToSendExist = messageToSendDao.findByMessageId(messageToSend.getMessageId());
            if (response == null || !"SIAT".equals(response)) {
                if (messageToSendExist == null) {
                    messageToSend.setResendRetryNumber(0);
                    messageToSend.setLastRetrySendTime(Calendar.getInstance().getTime());
                    messageToSendDao.save(messageToSend);
                } else {
                    messageToSend.setResendRetryNumber(messageToSend.getResendRetryNumber() + 1);
                    messageToSend.setLastRetrySendTime(Calendar.getInstance().getTime());
                    messageToSendDao.update(messageToSend);
                }
            } else if ("SIAT".equals(response)) {
                if (messageToSendExist != null) {
                    messageToSendDao.delete(messageToSend);
                }
            }
        }
    }

    @Override
    public List<MessageToSend> getMessagesToResend(int maxRetrySendTimeNumber, int numberMinuteExcededBetweenTwoSend) {
        return messageToSendDao.getMessagesToResend(maxRetrySendTimeNumber, numberMinuteExcededBetweenTwoSend);
    }

    @Override
    public List<MessageToSend> getMessagesThatExceededMaxNumberRetryTime(int maxRetrySendTimeNumber) {
        return messageToSendDao.getMessagesThatExceededMaxNumberRetryTime(maxRetrySendTimeNumber);
    }

    @Override
    public List<MessageToSend> findMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime) {
        return messageToSendDao.findMessagesExceededMaxNumberRetrySendAndMaxLifeTime(maxRetrySendTimeNumber, maxLifeTime);
    }

    @Override
    public void deleteMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime) {
        messageToSendDao.deleteMessagesExceededMaxNumberRetrySendAndMaxLifeTime(maxRetrySendTimeNumber, maxLifeTime);
    }

}
