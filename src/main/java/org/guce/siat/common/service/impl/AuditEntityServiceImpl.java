package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AuditDao;
import org.guce.siat.common.model.AuditEntity;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.AuditEntityService;
import org.guce.siat.common.utils.filter.AuditFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AuditEntityServiceImpl.
 */
@Service("auditEntityService")
@Transactional(readOnly = true)
public class AuditEntityServiceImpl extends AbstractServiceImpl<AuditEntity> implements AuditEntityService {

    /**
     * The audit dao.
     */
    @Autowired
    private AuditDao auditDao;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<AuditEntity> getJpaDao() {
        return auditDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<AuditEntity> jpaDao) {
        this.auditDao = (AuditDao) jpaDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AuditEntityService#findByFilter(org.guce.siat.common.utils.AuditFilter)
     */
    @Override
    public List<AuditEntity> findByFilter(final AuditFilter filter) {
        return auditDao.findByFilter(filter);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AuditEntityService#findByFilterAndModel(org.guce.siat.common.utils.AuditFilter,
	 * java.lang.String)
     */
    @Override
    public List<AuditEntity> findByFilterAndModel(final AuditFilter filter, final String alertName) {
        return auditDao.findByFilterAndModel(filter, alertName);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AuditEntityService#findByModel(java.lang.String)
     */
    @Override
    public List<AuditEntity> findByModel(final String model) {
        return auditDao.findByModel(model);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AuditEntityService#FindByAdmin(org.guce.siat.common.model.User)
     */
    @Override
    public List<AuditEntity> findByAdmin(final User admin) {
        return auditDao.findByAdmin(admin);
    }
}
