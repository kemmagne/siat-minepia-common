package org.guce.siat.common.service;

import java.util.List;
import java.util.Map;

import org.guce.siat.common.model.MessageToSend;

/**
 * The Interface MessageToSendService.
 */
public interface MessageToSendService extends AbstractService<MessageToSend> {

    /**
     * Find by message Id.
     *
     * @param messageId the message Id
     * @return the MessageToSend
     */
    MessageToSend findByMessageId(String messageId);

    List<MessageToSend> getMessagesToResend(int maxRetrySendTimeNumber, int numberMinuteExcededBetweenTwoSend);
    
    List<MessageToSend> getMessagesThatExceededMaxNumberRetryTime(int maxRetrySendTimeNumber);
    
    void saveOrUpadateNotSendedMessageAsMessageToResend(Map data);
    void deleteNotSendedMessageIfExistsAsMessageToResend(Map data);
    void saveOrDeleteNotSendedMessageAsMessageToResend(MessageToSend messageToSend, String response);
    List<MessageToSend> findMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime);
    
    void deleteMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime);

}
