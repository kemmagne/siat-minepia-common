package org.guce.epayment.core.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.guce.epayment.core.entities.Partner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
@Repository
public class PartnerDaoImpl implements PartnerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Partner> findByTypes(String partnerTypes, boolean justActive, int start, int end) {
        final TypedQuery<Partner> query;
        if (justActive) {
            query = em.createQuery("SELECT DISTINCT p FROM Partner p JOIN p.types t WHERE p.deleted = :deleted AND p.active = :active AND t.code IN :partnerTypes ORDER BY p.id DESC",
                    Partner.class);
        } else {
            query = em.createQuery("SELECT DISTINCT p FROM Partner p JOIN p.types t WHERE p.deleted = :deleted AND t.code IN :partnerTypes ORDER BY p.id DESC",
                    Partner.class);
        }

        query.setParameter("partnerTypes", StringUtils.commaDelimitedListToSet(partnerTypes));
        query.setParameter("deleted", false);
        if (justActive) {
            query.setParameter("active", true);
        }

        if (start > -1) {
            query.setFirstResult(start);
            query.setMaxResults(end - start);
        }
        return query.getResultList();
    }

    @Override
    public long countByTypes(String partnerTypes, boolean justActive) {
        final Query query;
        if (justActive) {
            query = em.createQuery("SELECT DISTINCT COUNT(p) FROM Partner p JOIN p.types t WHERE p.deleted = :deleted AND p.active = :active AND t.code IN :partnerTypes");
        } else {
            query = em.createQuery("SELECT DISTINCT COUNT(p) FROM Partner p JOIN p.types t WHERE p.deleted = :deleted AND t.code IN :partnerTypes");
        }

        query.setParameter("deleted", false);
        if (justActive) {
            query.setParameter("active", true);
        }
        query.setParameter("partnerTypes", StringUtils.commaDelimitedListToSet(partnerTypes));

        return ((Number) query.getSingleResult()).longValue();
    }

}
