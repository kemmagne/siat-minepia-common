package org.guce.siat.common.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.CoreDao;
import org.guce.siat.common.model.Container;
import org.guce.siat.common.model.File;
import org.guce.siat.common.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ht
 */
@Repository("coreDao")
@Transactional
public class CoreDaoImpl implements CoreDao {

    /**
     * The entity manager.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public <T> T save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public <T> void save(List<T> entities) {

        for (int i = 1; i <= entities.size(); i++) {

            T entity = entities.get(i - 1);
            entityManager.persist(entity);

            if ((i % Constants.TEN) == 0) {
                entityManager.flush();
                entityManager.clear();
            }

            entities.set(i - 1, entity);
        }

//        return entities;
    }

    @Override
    public <T> void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public <T> List<T> update(List<T> entities) {

        for (int i = 1; i <= entities.size(); i++) {

            T entity = entityManager.merge(entities.get(i - 1));

            if ((i % Constants.TEN) == 0) {
                entityManager.flush();
                entityManager.clear();
            }

            entities.set(i - 1, entity);
        }

        return entities;
    }

    @Override
    public <T> void delete(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    @Override
    public <T> void delete(List<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Container findContainerByFileAndNumber(File file, String contNumber) {

        TypedQuery<Container> query = entityManager.createQuery("SELECT c FROM Container c WHERE c.file.id = :fileId AND c.contNumber = :contNumber", Container.class);

        query.setParameter("fileId", file.getId());
        query.setParameter("contNumber", contNumber);

        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
