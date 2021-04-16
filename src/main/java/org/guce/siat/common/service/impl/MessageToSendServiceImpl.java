package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.CarDao;
import org.guce.siat.common.dao.MessageToSendDao;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.MessageToSend;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.service.CarService;
import org.guce.siat.common.service.MessageToSendService;
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
    public List<MessageToSend> findByRetryNumberLessThanFive() {
        return messageToSendDao.findByRetryNumberLessThanFive();
    }

}
