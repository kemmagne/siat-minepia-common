package org.guce.siat.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.service.AbstractService;
import org.guce.siat.common.service.annotations.Audit;
import org.guce.siat.common.utils.RequestPage;
import org.guce.siat.common.utils.enums.AuditConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AbstractServiceImpl.
 *
 * @param <T> the generic type
 */
public abstract class AbstractServiceImpl<T extends Serializable> implements AbstractService<T> {

    /**
     * Gets the jpa dao.
     *
     * @return the jpa dao
     */
    public abstract AbstractJpaDao<T> getJpaDao();

    /**
     * Sets the jpa dao.
     *
     * @param jpaDao the new jpa dao
     */
    public abstract void setJpaDao(AbstractJpaDao<T> jpaDao);

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#find(java.lang.Long)
     */
    @Override
    public T find(final Long id) {
        return getJpaDao().find(id);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#find(java.lang.String)
     */
    @Override
    public T find(final String id) {
        return getJpaDao().find(id);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#findAll()
     */
    @Override
    public List<T> findAll() {
        return getJpaDao().findAll();
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#findActiveItems()
     */
    @Override
    public List<T> findActiveItems() {
        return getJpaDao().findActiveItems();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#save(java.io.Serializable)
     */
    @Transactional(readOnly = false)
    @Audit(operationType = AuditConstants.SAVE)
    @Override
    public T save(final T entity) {
        return getJpaDao().save(entity);
    }

    @Transactional(readOnly = false)
    @Override
    public T saveHistory(final T entity) {
        return getJpaDao().save(entity);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#update(java.io.Serializable)
     */
    @Transactional(readOnly = false)
    @Audit(operationType = AuditConstants.UPDATE)
    @Override
    public void update(final T entity) {
        getJpaDao().update(entity);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#delete(java.io.Serializable)
     */
    @Transactional(readOnly = false)
    @Audit(operationType = AuditConstants.DELETE)
    @Override
    public void delete(final T entity) {
        getJpaDao().delete(entity);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#deleteById(java.lang.Long)
     */
    @Transactional(readOnly = false)
    @Audit(operationType = AuditConstants.DELETE)
    @Override
    public void deleteById(final Long entityId) {
        getJpaDao().deleteById(entityId);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#saveOrUpdateList(java.util.List)
     */
    @Transactional(readOnly = false)
    @Audit(operationType = AuditConstants.SAVE)
    @Override
    public List<T> saveOrUpdateList(final List<T> entitiesList) {
        return getJpaDao().saveOrUpdateList(entitiesList);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#saveList(java.util.List)
     */
    @Transactional(readOnly = false)
    @Audit(operationType = AuditConstants.SAVE)
    @Override
    public List<T> saveList(final List<T> entitiesList) {
        return getJpaDao().saveList(entitiesList);
    }

    /**
     * Delete list.
     *
     * @param entitiesList the entities list
     */
    @Transactional(readOnly = false)
    @Audit(operationType = AuditConstants.DELETE)
    @Override
    public void deleteList(final List<T> entitiesList) {
        getJpaDao().deleteList(entitiesList);
    }

    @Override
    public int count() {
        return getJpaDao().count();
    }

    @Override
    public List<T> findPage(final RequestPage requestPage) {
        return getJpaDao().findPage(requestPage);
    }

    @Override
    public List<T> paginate(Map<String, ? extends Object> filters, String sortField, String sortOrder, int first, int limit, int matchMode, Map<String, Integer> matchModes) {
        return getJpaDao().paginate(filters, sortField, sortOrder, first, limit, matchMode, matchModes);
    }

    @Override
    public int paginateCount(Map<String, ? extends Object> filters, int matchMode, Map<String, Integer> matchModes) {
        return getJpaDao().paginateCount(filters, matchMode, matchModes);
    }

}
