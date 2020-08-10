package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.collections.map.HashedMap;
import org.guce.siat.common.dao.AuditDao;
import org.guce.siat.common.model.AuditEntity;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.DateUtils;
import org.guce.siat.common.utils.SiatUtils;
import org.guce.siat.common.utils.enums.AuthorityConstants;
import org.guce.siat.common.utils.enums.PositionType;
import org.guce.siat.common.utils.filter.AuditFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AuditDaoImpl.
 */
@Repository
@Transactional(readOnly = false)
public class AuditDaoImpl extends AbstractJpaDaoImpl<AuditEntity> implements AuditDao {

    /**
     * Instantiates a new audit dao impl.
     */
    public AuditDaoImpl() {
        setClasse(AuditEntity.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AuditDao#findByFilter(org.guce.siat.common.utils.AuditFilter)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AuditEntity> findByFilter(final AuditFilter filter) {
        final StringBuilder hqlBuilder = new StringBuilder();
        final Map<String, Object> params = new HashedMap();
        Boolean firstConditionAdded = false;
        hqlBuilder.append("SELECT * FROM History ");

        if (filter.getBeginDate() != null && filter.getEndDate() == null && !firstConditionAdded) {
            hqlBuilder.append(" WHERE auditDate >= TO_DATE(:beginDate,'");
            hqlBuilder.append(DateUtils.PATTERN_YYYY_MM_DD_HH24_MI_SS);
            hqlBuilder.append("')");
            params.put("beginDate", DateUtils.formatSimpleDateForOracle(filter.getBeginDate()));
            firstConditionAdded = true;
        }

        if (filter.getBeginDate() == null && filter.getEndDate() != null) {
            if (!firstConditionAdded) {
                hqlBuilder.append(" WHERE auditDate <:endDate");
                firstConditionAdded = true;
            } else {
                hqlBuilder.append(" AND auditDate <:endDate");
            }
            params.put("endDate", DateUtils.addDays(filter.getEndDate(), 1));
        }

        if (filter.getBeginDate() != null && filter.getEndDate() != null) {
            if (!firstConditionAdded) {
                hqlBuilder.append(" WHERE auditDate >=:beginDate");
                hqlBuilder.append(" AND auditDate< :endDate");
            } else {
                hqlBuilder.append(" AND auditDate >=:beginDate");
                hqlBuilder.append(" AND auditDate< :endDate");
            }
            params.put("beginDate", filter.getBeginDate());
            params.put("endDate", DateUtils.addDays(filter.getEndDate(), 1));
        }

        final Query query = super.entityManager.createNativeQuery(hqlBuilder.toString(), AuditEntity.class);
        for (final Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AuditEntity> findByFilterAndModel(final AuditFilter filter, final String model) {
        final StringBuilder hqlBuilder = new StringBuilder();
        final Map<String, Object> params = new HashedMap();
        hqlBuilder.append("SELECT * FROM HISTORY WHERE MODEL = :model");
        params.put("model", model);
        if (filter.getBeginDate() != null && filter.getEndDate() == null) {
            hqlBuilder.append(" AND AUDITDATE >= TO_DATE(:beginDate,'");
            hqlBuilder.append(DateUtils.PATTERN_YYYY_MM_DD_HH24_MI_SS);
            hqlBuilder.append("')");
            params.put("beginDate", DateUtils.formatSimpleDateForOracle(filter.getBeginDate()));
        }

        if (filter.getBeginDate() == null && filter.getEndDate() != null) {
            hqlBuilder.append(" AND AUDITDATE <:endDate");
            params.put("endDate", DateUtils.addDays(filter.getEndDate(), 1));
        }

        if (filter.getBeginDate() != null && filter.getEndDate() != null) {
            hqlBuilder.append(" AND AUDITDATE >=:beginDate");
            hqlBuilder.append(" AND AUDITDATE <:endDate");

            params.put("beginDate", filter.getBeginDate());
            params.put("endDate", DateUtils.addDays(filter.getEndDate(), 1));
        }

        final Query query = super.entityManager.createNativeQuery(hqlBuilder.toString(), AuditEntity.class);
        for (final Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public List<AuditEntity> findByUsername(final String username) {
        final TypedQuery<AuditEntity> query = super.entityManager
                .createQuery("SELECT a FROM AuditEntity a WHERE a.username = :username ORDER BY a.id DESC", AuditEntity.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    @Override
    public List<AuditEntity> findByModel(final String model) {
        final TypedQuery<AuditEntity> query = super.entityManager
                .createQuery("SELECT a FROM AuditEntity a WHERE a.model = :model ORDER BY a.id DESC", AuditEntity.class);
        query.setParameter("model", model);
        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AuditDao#FindByAdmin(org.guce.siat.common.model.User)
     */
    @Override
    public List<AuditEntity> findByAdmin(final User admin) {
        if (PositionType.ADMINISTRATEUR.equals(admin.getPosition())) {
            if (admin.getAuthoritiesList().contains(AuthorityConstants.ROOT.getCode())) {
                return findAll();
            } else if (admin.getAuthoritiesList().contains(AuthorityConstants.ADMIN_MINISTERE.getCode())
                    || admin.getAuthoritiesList().contains(AuthorityConstants.ADMIN_ORGANISME.getCode())) {
                final String hqlString = "SELECT a FROM AuditEntity a WHERE a.username IN (SELECT u.login FROM User u WHERE u.administration IN (:administrationList))";
                final TypedQuery<AuditEntity> query = super.entityManager.createQuery(hqlString, AuditEntity.class);
                query.setParameter("administrationList",
                        SiatUtils.getRecursiveSubAdministations(Collections.singletonList(admin.getAdministration())));
                return query.getResultList();
            }
        }
        return null;
    }
}
