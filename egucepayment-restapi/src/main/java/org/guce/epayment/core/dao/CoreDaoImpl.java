package org.guce.epayment.core.dao;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CoreDaoImpl implements CoreDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreDaoImpl.class);

    private static final int UNIQUE_VALUE_LENGTH = 10;

    @PersistenceContext
    private EntityManager em;

    @Override
    public <T> List<T> findRange(Class<T> entityClass, int start, int end) {

        final CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);

        cq.select(cq.from(entityClass));

        final TypedQuery<T> q = em.createQuery(cq);

        if (end > start) {

            q.setMaxResults(end - start);
            q.setFirstResult(start);
        }

        return q.getResultList();
    }

    @Override
    public <T> Optional<T> findById(final Object id, final Class<T> clazz) {

        TypedQuery<T> query = em.createQuery("SELECT e FROM " + clazz.getName() + " e WHERE e.id = :id", clazz);

        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception ex) {
            LOGGER.info(String.format("problem occured when try to find in %s by id : %s", clazz, id), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String generateUniqueValue(String sequence, String prefix) {

        final long i = findNextVal(sequence);

        return new DecimalFormat(prefix + StringUtils.repeat("0", UNIQUE_VALUE_LENGTH)).format(i);
    }

    private long findNextVal(String sequence) {

        try {

            final Query q = em.createNativeQuery("Select " + sequence + ".NEXTVAL from dual");

            return ((Number) q.getSingleResult()).longValue();
        } catch (Exception e) {
            return 0;
        }
    }

}
