package org.guce.siat.common.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.Pageable;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.utils.RequestPage;
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

    @Override
    public int count() {

        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(getClasse())));
        Long singleResult = entityManager.createQuery(cq).getSingleResult();
        return singleResult.intValue();
    }

    @Override
    public List<T> findPage(RequestPage requestPage) {
        final int page = requestPage.getPage();
        final int pageSize = requestPage.getPageSize();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery(getClasse());
        Root<Params> root = cq.from(getClasse());
        return entityManager.createQuery(cq.select(root))
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

    }

    @Override
    public List<T> paginate(Map<String, ? extends Object> filters, String sortField, String sortOrder, int first, int limit, int matchMode, Map<String, Integer> matchModes) {
        StringBuilder sb = new StringBuilder();
        String s = "SELECT ent FROM " + this.classe.getSimpleName() + " ent ";
        sb.append(s);

        Query query = this.buildQuery("ent", sb, filters, sortField, sortOrder, first, limit, matchMode, matchModes);

        return query.getResultList();
    }

    @Override
    public int paginateCount(Map<String, ? extends Object> filters, int matchMode, Map<String, Integer> matchModes) {
        StringBuilder sb = new StringBuilder();
        String s = "SELECT COUNT(ent) FROM " + this.classe.getSimpleName() + " ent ";
        sb.append(s);
        Query query = this.buildQuery("ent", sb, filters, null, null, null, null, matchMode, matchModes);
        return ((Long) query.getSingleResult()).intValue();
    }

    public Query buildQuery(String filtersPrefix, StringBuilder query, Map<String, ? extends Object> filters, String sortField, String sortOrder, Integer first, Integer limit, int matchMode, Map<String, Integer> matchModes) {
        String s;
        if (filters.size() > 0) {
            if (!query.toString().toLowerCase().contains("where")) {
                query.append(" WHERE 1=1 ");
            }
        }
        int i = 0;
        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
            i++;
            String filterProperty = it.next();
            int match = matchMode;
            if (matchModes != null) {
                Integer val = matchModes.get(filterProperty);
                if (val != null) {
                    match = val;
                }
            }
            int index = filterProperty.indexOf("##");
            if (index != -1) {
                filterProperty = filterProperty.substring(0, index);
            }
            if (filtersPrefix != null) {
                filterProperty = filtersPrefix + "." + filterProperty;
            }
            switch (match) {
                case Pageable.MATCH_MODE_CONTAINS:
                case Pageable.MATCH_MODE_END_WITH:
                case Pageable.MATCH_MODE_START_WITH:
                    s = " AND UPPER(" + filterProperty + ") LIKE :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_NOT_CONTAINS:
                case Pageable.MATCH_MODE_NOT_END_WITH:
                case Pageable.MATCH_MODE_NOT_START_WITH:
                    s = " AND UPPER(" + filterProperty + ") NOT LIKE :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_EQUAL:
                    s = " AND " + filterProperty + " = :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_NOT_EQUAL:
                    s = " AND " + filterProperty + " != :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_LESS_THAN:
                    s = " AND " + filterProperty + " < :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_MORE_THAN:
                    s = " AND " + filterProperty + " > :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_LESS_OR_EQUAL_THAN:
                    s = " AND " + filterProperty + " <= :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_MORE_OR_EQUAL_THAN:
                    s = " AND " + filterProperty + " >= :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_IN:
                    s = " AND " + filterProperty + " IN :param" + i + " ";
                    break;
                case Pageable.MATCH_MODE_NOT_IN:
                    s = " AND " + filterProperty + " NOT IN :param" + i + " ";
                    break;
                default:
                    s = " AND UPPER " + filterProperty + " = :param" + i + " ";
                    break;
            }
            query.append(s);
        }
        //sort
        if (sortField != null && !sortField.trim().isEmpty()) {
            String sortField0 = sortField;
            if (filtersPrefix != null) {
                sortField0 = filtersPrefix + "." + sortField0;
            }
            String sort = "";
            //System.out.println("Sort : "+sortOrder);
            if (sortOrder != null) {
                if (sortOrder.equalsIgnoreCase("ASC") || sortOrder.equalsIgnoreCase("DESC")) {
                    sort = sortOrder;
                }
            }
            //System.out.println("Sort : "+sort);
            s = " ORDER BY " + sortField0 + " " + sort;
            query.append(s);
        }
        Query q = this.getEntityManager().createQuery(query.toString());
        //System.out.println("query "+query);
        //System.out.println("query 2 "+sb.toString());
        if (limit != null && limit > 0) {
            q.setMaxResults(limit + first);
        }
        if (first != null && first >= 0) {
            q.setFirstResult(first);
        }
        i = 0;
        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
            i++;
            String filterProperty = it.next();
            Object filterValue = filters.get(filterProperty);
            Object value;
            int match = matchMode;
            if (matchModes != null) {
                Integer val = matchModes.get(filterProperty);
                if (val != null) {
                    match = val;
                }
            }
            switch (match) {
                case Pageable.MATCH_MODE_START_WITH:
                    value = "" + filterValue.toString().toUpperCase() + "%";
                    break;
                case Pageable.MATCH_MODE_END_WITH:
                    value = "%" + filterValue.toString().toUpperCase() + "";
                    break;
                case Pageable.MATCH_MODE_CONTAINS:
                    value = "%" + filterValue.toString().toUpperCase() + "%";
                    break;
                case Pageable.MATCH_MODE_NOT_START_WITH:
                    value = "" + filterValue.toString().toUpperCase() + "%";
                    break;
                case Pageable.MATCH_MODE_NOT_END_WITH:
                    value = "%" + filterValue.toString().toUpperCase() + "";
                    break;
                case Pageable.MATCH_MODE_NOT_CONTAINS:
                    value = "%" + filterValue.toString().toUpperCase() + "%";
                    break;
                case Pageable.MATCH_MODE_EQUAL:
                    value = filterValue;
                    break;
                default:
                    value = filterValue;
                    break;
            }
            String param = "param" + i;
            q.setParameter(param, value);
        }
        return q;
    }
}
