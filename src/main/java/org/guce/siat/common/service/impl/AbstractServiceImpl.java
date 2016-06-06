package org.guce.siat.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.service.AbstractService;
import org.guce.siat.common.service.annotations.Audit;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AbstractServiceImpl.
 *
 * @param <T>
 *           the generic type
 */
public abstract class AbstractServiceImpl<T extends Serializable> implements AbstractService<T>
{

	/**
	 * Gets the jpa dao.
	 *
	 * @return the jpa dao
	 */
	public abstract AbstractJpaDao<T> getJpaDao();

	/**
	 * Sets the jpa dao.
	 *
	 * @param jpaDao
	 *           the new jpa dao
	 */
	public abstract void setJpaDao(AbstractJpaDao<T> jpaDao);


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#find(java.lang.Long)
	 */
	@Override
	public T find(final Long id)
	{
		return getJpaDao().find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#find(java.lang.String)
	 */
	@Override
	public T find(final String id)
	{
		return getJpaDao().find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#findAll()
	 */
	@Override
	public List<T> findAll()
	{
		return getJpaDao().findAll();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#findActiveItems()
	 */
	@Override
	public List<T> findActiveItems()
	{
		return getJpaDao().findActiveItems();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#save(java.io.Serializable)
	 */
	@Transactional(readOnly = false)
	@Audit(operationType = "SAVE")
	@Override
	public T save(final T entity)
	{
		return getJpaDao().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#update(java.io.Serializable)
	 */
	@Transactional(readOnly = false)
	@Audit(operationType = "UPDATE")
	@Override
	public void update(final T entity)
	{
		getJpaDao().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#delete(java.io.Serializable)
	 */
	@Transactional(readOnly = false)
	@Audit(operationType = "DELETE")
	@Override
	public void delete(final T entity)
	{
		getJpaDao().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractService#deleteById(java.lang.Long)
	 */
	@Transactional(readOnly = false)
	@Audit(operationType = "DELETE")
	@Override
	public void deleteById(final Long entityId)
	{
		getJpaDao().deleteById(entityId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractService#saveOrUpdateList(java.util.List)
	 */
	@Transactional(readOnly = false)
	@Audit(operationType = "SAVE")
	@Override
	public List<T> saveOrUpdateList(final List<T> entitiesList)
	{
		return getJpaDao().saveOrUpdateList(entitiesList);
	}

	/**
	 * Delete list.
	 *
	 * @param entitiesList
	 *           the entities list
	 */
	@Transactional(readOnly = false)
	@Audit(operationType = "DELETE")
	@Override
	public void deleteList(final List<T> entitiesList)
	{
		getJpaDao().deleteList(entitiesList);
	}

}
