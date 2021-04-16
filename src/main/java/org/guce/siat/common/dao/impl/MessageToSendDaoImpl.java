/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.CarDao;
import org.guce.siat.common.dao.MessageToSendDao;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.model.Organism;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MessageToSendDaoImpl.
 */
@Repository("MessageToSendDao")
@Transactional(propagation = Propagation.REQUIRED)
public class MessageToSendDaoImpl extends AbstractJpaDaoImpl<MessageToSend> implements MessageToSendDao {

    /**
     * Instantiates a new messageToSend dao impl.
     */
    public MessageToSendDaoImpl() {
        super();
        setClasse(MessageToSend.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.MessageToSendDao#findByRetryNumberLessThanFive()
     */
    @Override
    public List<MessageToSend> findByRetryNumberLessThanFive() {
        final String hqlString = "SELECT m FROM MessageToSend m WHERE m.resendRetryNumber < 5";
        final TypedQuery<MessageToSend> query = super.entityManager.createQuery(hqlString, MessageToSend.class);
        return query.getResultList();
    }

}
