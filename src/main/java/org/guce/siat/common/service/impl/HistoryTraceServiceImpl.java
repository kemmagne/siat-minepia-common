package org.guce.siat.common.service.impl;

import java.util.List;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.HistoryTraceDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.HistoryTrace;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.HistoryTraceService;
import org.guce.siat.common.utils.FilterTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class HistoryTraceServiceImpl.
 */
@Service("historyTraceService")
@Transactional(readOnly = true)
public class HistoryTraceServiceImpl extends AbstractServiceImpl<HistoryTrace> implements HistoryTraceService {

    /**
     * The bureau dao.
     */
    @Autowired
    private HistoryTraceDao historyTraceDao;

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Instantiates a new bureau service impl.
     */
    public HistoryTraceServiceImpl() {
        super();
    }


    
    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<HistoryTrace> getJpaDao() {
        return historyTraceDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<HistoryTrace> jpaDao) {
        this.historyTraceDao = (HistoryTraceDao) jpaDao;

    }

    @Override
    public <T> void makeHistory(T entity, String operation, User user) {
       this.historyTraceDao.makeHistory(entity, operation, user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <C, S extends C> void makeHistory(S entity, String operation, User user, Class<C> clazz) {
        this.historyTraceDao.makeHistory(entity, operation, user, clazz);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HistoryTrace> findTraceByUser(User coreUser) {
        return this.historyTraceDao.findTraceByUser(coreUser); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HistoryTrace> findTraceByFilter(FilterTrace filterTrace) {
       return this.historyTraceDao.findTraceByFilter(filterTrace); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> findModelTrace() {
        return this.historyTraceDao.findModelTrace(); //To change body of generated methods, choose Tools | Templates.
    }


}
