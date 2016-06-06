package org.guce.siat.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.utils.Constants;


/**
 * The Class AbstractJpaDao.
 *
 * @param <T>
 *           the generic type
 */
public abstract class AbstractJpaDaoImpl<T extends Serializable> implements AbstractJpaDao<T>
{

	/** The classe. */
	private Class<T> classe;

	/** The entity manager. */
	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager
	 *           the new entity manager
	 */
	public void setEntityManager(final EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#setClasse(java.lang.Class)
	 */
	@Override
	public final void setClasse(final Class<T> classeToSet)
	{
		this.classe = classeToSet;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#find(java.lang.Long)
	 */
	@Override
	public T find(final Long id)
	{
		return this.entityManager.find(this.classe, id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#find(java.lang.String)
	 */
	@Override
	public T find(final String id)
	{
		return this.entityManager.find(this.classe, id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll()
	{
		return this.entityManager.createQuery("FROM " + this.classe.getName()).getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#findActiveItems()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findActiveItems()
	{
		return this.entityManager.createQuery("FROM " + this.classe.getSimpleName() + " WHERE deleted = false").getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#save(java.io.Serializable)
	 */
	@Override
	public T save(final T entity)
	{
		this.entityManager.persist(entity);
		this.entityManager.flush();
		return entity;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#update(java.io.Serializable)
	 */
	@Override
	public void update(final T entity)
	{
		this.entityManager.merge(entity);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#delete(java.io.Serializable)
	 */
	@Override
	public void delete(final T entity)
	{
		this.entityManager.remove(this.entityManager.merge(entity));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(final Long entityId)
	{
		final T entity = this.find(entityId);
		this.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#saveOrUpdateList(java.util.List)
	 */
	@Override
	public List<T> saveOrUpdateList(final List<T> entitiesList)
	{
		for (int i = 1; i <= entitiesList.size(); i++)
		{
			final T entity = this.entityManager.merge(entitiesList.get(i - 1));
			if ((i % Constants.TEN) == 0)
			{
				this.entityManager.flush();
				this.entityManager.clear();
			}
			entitiesList.set(i - 1, entity);
		}
		return entitiesList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#saveList(java.util.List)
	 */
	@Override
	public List<T> saveList(final List<T> entitiesList)
	{
		for (int i = 1; i <= entitiesList.size(); i++)
		{
			final T entity = entitiesList.get(i - 1);

			this.entityManager.persist(entity);
			if ((i % Constants.TEN) == 0)
			{
				this.entityManager.flush();
				this.entityManager.clear();
			}
			entitiesList.set(i - 1, entity);
		}
		return entitiesList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#deleteList(java.util.List)
	 */
	@Override
	public void deleteList(final List<T> entitiesList)
	{
		int i = 0;
		if (CollectionUtils.isNotEmpty(entitiesList))
		{
			for (final T entity : entitiesList)
			{
				if (++i % Constants.TEN == 0)
				{
					this.entityManager.flush();
					this.entityManager.clear();
				}
				this.entityManager.remove(this.entityManager.merge(entity));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#clear()
	 */
	@Override
	public void clear()
	{
		this.entityManager.flush();
		this.entityManager.clear();
	}

}
