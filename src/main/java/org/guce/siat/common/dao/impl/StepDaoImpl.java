/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.StepDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.StepCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class StepDaoImpl.
 */
@Repository("stepDao")
@Transactional(propagation = Propagation.REQUIRED)
public class StepDaoImpl extends AbstractJpaDaoImpl<Step> implements StepDao {

    /**
     * Instantiates a new step dao impl.
     */
    public StepDaoImpl() {
        super();
        setClasse(Step.class);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.common.dao.StepDao#findByAuthorotiesId(org.guce.siat.common.model.Authority)
     */
    @Override
    public List<Step> findByAuthority(final Authority authority) {
        if (authority != null) {
            final String hqlString = "SELECT s FROM Step s INNER JOIN s.roleList rl WHERE rl.id = :authorityId";
            final TypedQuery<Step> query = super.entityManager.createQuery(hqlString, Step.class);
            query.setParameter("authorityId", authority.getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    @Override
    public Step findByStepCode(StepCode stepCode) {

        TypedQuery<Step> query = super.entityManager.createQuery("SELECT s FROM Step s WHERE s.stepCode = :stepCode", Step.class);

        query.setParameter("stepCode", stepCode);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            if (logger.isDebugEnabled()) {
                logger.debug("There's no step with code {}", nre);
            }
            return null;
        }
    }

}
