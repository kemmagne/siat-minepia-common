/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.ControllerHolidayDao;
import org.guce.siat.common.model.ControllerHoliday;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.SiatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ControllerHolidayDaoImpl.
 */
@Repository("controllerHolidayDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ControllerHolidayDaoImpl extends AbstractJpaDaoImpl<ControllerHoliday> implements ControllerHolidayDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ControllerHolidayDaoImpl.class);

	/**
	 * Instantiates a new controller holiday dao impl.
	 */
	public ControllerHolidayDaoImpl() {
		super();
		setClasse(ControllerHoliday.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.ControllerHolidayDao#findHolidayByControllerAndDates(org.guce.siat.common.model.User,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<ControllerHoliday> findHolidayByControllerAndDates(final User user, final Date beginDate, final Date endDate) {
		if (user != null) {
			final StringBuilder hqlBuilder = new StringBuilder();

			hqlBuilder.append("SELECT h FROM ControllerHoliday h ");
			hqlBuilder.append("WHERE h.user.id = :userId ");
			hqlBuilder.append("AND h.enabled = true ");
			hqlBuilder.append("AND ");
			hqlBuilder.append('(');
			hqlBuilder.append("(:bDate BETWEEN h.fromDate AND h.toDate) ");
			hqlBuilder.append("OR (:eDate BETWEEN h.fromDate AND h.toDate) ");
			hqlBuilder.append("OR (h.fromDate BETWEEN :bDate AND :eDate) ");
			hqlBuilder.append("OR (h.toDate BETWEEN :bDate AND :eDate)");
			hqlBuilder.append(')');

			final TypedQuery<ControllerHoliday> query = super.entityManager.createQuery(hqlBuilder.toString(),
					ControllerHoliday.class);

			query.setParameter("userId", user.getId());
			query.setParameter("bDate", beginDate);
			query.setParameter("eDate", endDate);

			return query.getResultList();
		}

		return Collections.emptyList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ControllerHolidayDao#findHolidaysByListController(java.util.List)
	 */
	@Override
	public List<ControllerHoliday> findHolidaysByListController(final List<User> controllerList) {
		if (controllerList != null && !controllerList.isEmpty()) {
			final String hqlString = "SELECT h FROM ControllerHoliday h WHERE h.user IN (:controllerList)";
			final TypedQuery<ControllerHoliday> query = super.entityManager.createQuery(hqlString, ControllerHoliday.class);
			query.setParameter("controllerList", controllerList);
			return query.getResultList();
		} else {
			return Collections.emptyList();
		}
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ControllerHolidayDao#findHolidayByControllerAndDay(org.guce.siat.common.model.User,
	 * java.util.Date)
	 */
	@Override
	public ControllerHoliday findHolidayByControllerAndDay(final User controller, final Date date) {
		if (!Objects.equals(controller, null)) {
			try {
				final String hqlString = "SELECT h FROM ControllerHoliday h WHERE h.user.id=:controllerId AND :date BETWEEN h.fromDate AND h.toDate";
				final TypedQuery<ControllerHoliday> query = super.entityManager.createQuery(hqlString, ControllerHoliday.class);
				query.setParameter("controllerId", controller.getId());
				query.setParameter("date", date, TemporalType.DATE);
				return query.getSingleResult();
			} catch (final NoResultException | NonUniqueResultException e) {
				LOG.info(Objects.toString(e));
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.ControllerHolidayDao#findByOrganism(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<ControllerHoliday> findByOrganism(Organism organism) {
		if (organism != null) {
			TypedQuery<ControllerHoliday> query = entityManager.createQuery(
					"SELECT c FROM ControllerHoliday c WHERE c.user.administration IN (:administrationList)", ControllerHoliday.class);
			query.setParameter("administrationList", SiatUtils.getRecursiveSubAdministations(Collections.singletonList(organism)));
			return query.getResultList();
		}
		return Collections.emptyList();
	}

}
