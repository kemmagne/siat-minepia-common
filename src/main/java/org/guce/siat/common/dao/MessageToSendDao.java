package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.MessageToSend;

/**
 * The Interface MessageToSendDao.
 */
public interface MessageToSendDao extends AbstractJpaDao<MessageToSend> {

    /**
     * Find by message Id.
     *
     * @param messageId the message Id
     * @return the MessageToSend
     */
    MessageToSend findByMessageId(String messageId);

    List<MessageToSend> getMessagesToResend(int maxRetrySendTimeNumber, int numberMinuteExcededBetweenTwoSend);
    
    List<MessageToSend> getMessagesThatExceededMaxNumberRetryTime(int maxRetrySendTimeNumber);
    
    List<MessageToSend> findMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime);
    
    void deleteMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime);

}
