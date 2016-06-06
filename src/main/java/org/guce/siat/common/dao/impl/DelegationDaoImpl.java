/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.DelegationDao;
import org.guce.siat.common.model.Delegation;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.SiatUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class DelegationDaoImpl.
 */
@Repository("delegationDao")
@Transactional(propagation = Propagation.REQUIRED)
public class DelegationDaoImpl extends AbstractJpaDaoImpl<Delegation> implements
		DelegationDao {

	/**
	 * Instantiates a new Delegation dao impl.
	 */
	public DelegationDaoImpl() {
		super();
		setClasse(Delegation.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.dao.DelegationDao#findDelegationByDateByFromUsers
	 * (org.guce.siat.common.model.User, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Delegation> findDelegationByDateByFromUsers(final User user,
			final Date bDate, final Date eDate) {
		final StringBuilder hqlBuilder = new StringBuilder();

		hqlBuilder.append("SELECT d FROM Delegation d ");
		hqlBuilder.append("WHERE d.fromUser.id = :user ");
		hqlBuilder.append("AND ");
		hqlBuilder.append('(');
		hqlBuilder.append("(:bDate BETWEEN d.beginDate AND d.endDate) ");
		hqlBuilder.append("OR (:eDate BETWEEN d.beginDate AND d.endDate) ");
		hqlBuilder.append("OR (d.beginDate BETWEEN :bDate AND :eDate) ");
		hqlBuilder.append("OR (d.endDate BETWEEN :bDate AND :eDate) ");
		hqlBuilder.append(") ");
		hqlBuilder.append("AND d.enabled = true ");
		hqlBuilder.append("AND d.deleted = false ");

		final TypedQuery<Delegation> query = super.entityManager.createQuery(
				hqlBuilder.toString(), Delegation.class);

		query.setParameter("bDate", bDate);
		query.setParameter("eDate", eDate);
		query.setParameter("user", user.getId());

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.dao.DelegationDao#findDelegationByDateByToUsers(
	 * org.guce.siat.common.model.User, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Delegation> findDelegationByDateByToUsers(final User user,
			final Date bDate, final Date eDate) {
		final StringBuilder hqlBuilder = new StringBuilder();

		hqlBuilder.append("SELECT d FROM Delegation d ");
		hqlBuilder.append("WHERE d.toUser.id = :user ");
		hqlBuilder.append("AND ");
		hqlBuilder.append('(');
		hqlBuilder.append("(:bDate BETWEEN d.beginDate AND d.endDate) ");
		hqlBuilder.append("OR (:eDate BETWEEN d.beginDate AND d.endDate) ");
		hqlBuilder.append("OR (d.beginDate BETWEEN :bDate AND :eDate) ");
		hqlBuilder.append("OR (d.endDate BETWEEN :bDate AND :eDate) ");
		hqlBuilder.append(") ");
		hqlBuilder.append("AND d.enabled = true ");
		hqlBuilder.append("AND d.deleted = false ");

		final TypedQuery<Delegation> query = super.entityManager.createQuery(
				hqlBuilder.toString(), Delegation.class);

		query.setParameter("bDate", bDate);
		query.setParameter("eDate", eDate);
		query.setParameter("user", user.getId());

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.dao.DelegationDao#findDelegationByUserAndCurrentDate
	 * (org.guce.siat.common.model.User)
	 */
	@Override
	public List<Delegation> findDelegationByUserAndCurrentDate(final User user) {
		final StringBuilder hqlBuilder = new StringBuilder();

		hqlBuilder.append("SELECT d FROM Delegation d ");
		hqlBuilder.append("WHERE d.toUser.id = :userId ");
		hqlBuilder.append("AND d.enabled = true ");
		hqlBuilder.append("AND d.deleted = false ");
		hqlBuilder.append("AND :now BETWEEN d.beginDate AND d.endDate ");

		final TypedQuery<Delegation> query = super.entityManager.createQuery(
				hqlBuilder.toString(), Delegation.class);

		query.setParameter("userId", user.getId());
		query.setParameter("now", new Date());

		if (CollectionUtils.isNotEmpty(query.getResultList())) {
			return query.getResultList();
		}

		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.dao.DelegationDao#findByOrganism(org.guce.siat.common
	 * .model.Organism)
	 */
	@Override
	public List<Delegation> findByOrganism(Organism organism) {
		if (organism != null) {
			
			TypedQuery<Delegation> query = entityManager .createQuery("SELECT d FROM Delegation d WHERE d.fromUser.administration IN (:administrationList)",Delegation.class);
			query.setParameter("administrationList", SiatUtils.getRecursiveSubAdministations(Collections.singletonList(organism)));
			return query.getResultList();
		}
		return Collections.emptyList();
	}
	
}
