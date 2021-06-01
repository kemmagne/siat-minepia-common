/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;
import javax.persistence.Query;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.MessageToSendDao;
import org.guce.siat.common.model.MessageToSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MessageToSendDaoImpl.
 */
@Repository("messageToSendDao")
@Transactional(propagation = Propagation.REQUIRED)
public class MessageToSendDaoImpl extends AbstractJpaDaoImpl<MessageToSend> implements MessageToSendDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MessageToSendDaoImpl.class);

    /**
     * Instantiates a new messageToSend dao impl.
     */
    public MessageToSendDaoImpl() {
        super();
        setClasse(MessageToSend.class);
    }

    

    @Override
    public MessageToSend findByMessageId(String messageId) {
            final String hqlString = "FROM MessageToSend m WHERE m.messageId=:messageId";
            final TypedQuery<MessageToSend> query = super.entityManager.createQuery(hqlString, MessageToSend.class);
            query.setParameter("messageId", messageId);
            List results = query.getResultList();
            return results != null && !results.isEmpty() ? (MessageToSend)results.get(0) : null;
        
    }

    @Override
    public List<MessageToSend> getMessagesToResend(int maxRetrySendTimeNumber, int numberMinuteExcededBetweenTwoSend) {
        String strQuery = "SELECT m.* FROM MESSAGE_TO_SEND m \n" +
                        "WHERE m.RESEND_RETRY_NUMBER < ? \n" +
                        "AND (EXTRACT(DAY FROM (SYSTIMESTAMP - m.LAST_RETRY_SEND_TIME)*24*60)+ EXTRACT(HOUR FROM (SYSTIMESTAMP - m.LAST_RETRY_SEND_TIME)*60)+ EXTRACT(MINUTE FROM (SYSTIMESTAMP - m.LAST_RETRY_SEND_TIME))+\n" +
                        "EXTRACT(SECOND FROM (SYSTIMESTAMP - m.LAST_RETRY_SEND_TIME)/60)) > ?";
        Query q = super.entityManager.createNativeQuery(strQuery, MessageToSend.class);
        q.setParameter(1, maxRetrySendTimeNumber);
        q.setParameter(2, numberMinuteExcededBetweenTwoSend);
        return q.getResultList();
    }

    @Override
    public List<MessageToSend> getMessagesThatExceededMaxNumberRetryTime(int maxRetrySendTimeNumber) {
        Query query = super.entityManager.createQuery("Select m from MessageToSend m WHERE m.resendRetryNumber > :maxRetrySendTimeNumber", MessageToSend.class);
        query.setParameter("maxRetrySendTimeNumber", maxRetrySendTimeNumber);
        return query.getResultList();
    }

    @Override
    public List<MessageToSend> findMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime) {
        String strQuery = "SELECT m2.*\n"
                + "FROM MESSAGE_TO_SEND m2\n"
                + "WHERE m2.RESEND_RETRY_NUMBER > ? OR (EXTRACT(DAY FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME)*24*60)+ EXTRACT(HOUR FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME)*60)+ EXTRACT(MINUTE FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME))+ EXTRACT(SECOND FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME)/60)) > ?";
        Query q = super.entityManager.createNativeQuery(strQuery, MessageToSend.class);
        q.setParameter(1, maxRetrySendTimeNumber);
        q.setParameter(2, maxLifeTime);
        return q.getResultList();
    }

    @Override
    public void deleteMessagesExceededMaxNumberRetrySendAndMaxLifeTime(int maxRetrySendTimeNumber, int maxLifeTime) {
        String strQuery = "DELETE FROM MESSAGE_TO_SEND m2\n"
                + "WHERE m2.RESEND_RETRY_NUMBER > ? OR (EXTRACT(DAY FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME)*24*60)+ EXTRACT(HOUR FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME)*60)+ EXTRACT(MINUTE FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME))+ EXTRACT(SECOND FROM (SYSTIMESTAMP - m2.LAST_RETRY_SEND_TIME)/60)) > ?";
        Query q = super.entityManager.createNativeQuery(strQuery);
        q.setParameter(1, maxRetrySendTimeNumber);
        q.setParameter(2, maxLifeTime);
        q.executeUpdate();
    }
}
