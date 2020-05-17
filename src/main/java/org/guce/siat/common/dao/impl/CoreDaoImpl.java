package org.guce.siat.common.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.guce.siat.common.dao.CoreDao;
import org.guce.siat.common.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ht
 */
@Repository("dao")
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

}
