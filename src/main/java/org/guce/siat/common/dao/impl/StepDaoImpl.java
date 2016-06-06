/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.StepDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Step;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class StepDaoImpl.
 */
@Repository("stepDao")
@Transactional(propagation = Propagation.REQUIRED)
public class StepDaoImpl extends AbstractJpaDaoImpl<Step> implements StepDao
{

	/**
	 * Instantiates a new step dao impl.
	 */
	public StepDaoImpl()
	{
		super();
		setClasse(Step.class);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.common.dao.StepDao#findByAuthorotiesId(org.guce.siat.common.model.Authority)
	 */
	@Override
	public List<Step> findByAuthority(final Authority authority)
	{
		if (authority != null)
		{
			final String hqlString = "SELECT s FROM Step s INNER JOIN s.roleList rl WHERE rl.id = :authorityId";
			final TypedQuery<Step> query = super.entityManager.createQuery(hqlString, Step.class);
			query.setParameter("authorityId", authority.getId());
			return query.getResultList();
		}
		return Collections.emptyList();
	}

}
