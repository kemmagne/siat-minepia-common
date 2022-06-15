package org.guce.siat.common.dao.impl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AbstractJpaDao.
 *
 * @param <T> the generic type
 */
public abstract class AbstractJpaDaoImpl<T extends Serializable> implements AbstractJpaDao<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The classe.
     */
    private Class<T> classe;

    /**
     * The entity manager.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the entity manager.
     *
     * @param entityManager the new entity manager
     */
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#setClasse(java.lang.Class)
     */
    @Override
    public final void setClasse(final Class<T> classeToSet) {
        this.classe = classeToSet;
    }

    public Class<T> getClasse() {
        return classe;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#find(java.lang.Long)
     */
    @Transactional(readOnly = true)
    @Override
    public T find(final Long id) {
        return this.entityManager.find(this.classe, id);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#find(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public T find(final String id) {
        return this.entityManager.find(this.classe, id);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#findAll()
     */
    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(getClasse());
        Root<Params> root = cq.from(getClasse());
        return entityManager.createQuery(cq.select(root)).getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#findActiveItems()
     */
    @Transactional(readOnly = true)
    @Override
    public List<T> findActiveItems() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(getClasse());
        Root<T> root = cq.from(getClasse());
        cq.where(builder.equal(root.get("deleted"), false));
        TypedQuery<T> query = entityManager.createQuery(cq.select(root));
        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#save(java.io.Serializable)
     */
    @Transactional(readOnly = false)
    @Override
    public T save(final T entity) {
        this.entityManager.persist(entity);
        this.entityManager.flush();
        return entity;
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#update(java.io.Serializable)
     */
    @Transactional(readOnly = false)
    @Override
    public void update(final T entity) {
        this.entityManager.merge(entity);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#delete(java.io.Serializable)
     */
    @Transactional(readOnly = false)
    @Override
    public void delete(final T entity) {
        this.entityManager.remove(this.entityManager.merge(entity));
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#deleteById(java.lang.Long)
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteById(final Long entityId) {
        final T entity = this.find(entityId);
        this.delete(entity);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#saveOrUpdateList(java.util.List)
     */
    @Transactional(readOnly = false)
    @Override
    public List<T> saveOrUpdateList(final List<T> entitiesList) {
        for (int i = 1; i <= entitiesList.size(); i++) {
            T entity = this.entityManager.merge(entitiesList.get(i - 1));
            entitiesList.set(i - 1, entity);
        }
        return entitiesList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#saveList(java.util.List)
     */
    @Transactional(readOnly = false)
    @Override
    public List<T> saveList(final List<T> entitiesList) {
        for (int i = 1; i <= entitiesList.size(); i++) {
            T entity = entitiesList.get(i - 1);
            this.entityManager.persist(entity);
            entitiesList.set(i - 1, entity);
        }
        return entitiesList;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#deleteList(java.util.List)
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteList(final List<T> entitiesList) {
        if (CollectionUtils.isNotEmpty(entitiesList)) {
            for (T entity : entitiesList) {
                this.entityManager.remove(this.entityManager.merge(entity));
            }
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AbstractJpaDao#clear()
     */
    @Transactional(readOnly = false)
    @Override
    public void clear() {
        this.entityManager.flush();
        this.entityManager.clear();
    }

}
