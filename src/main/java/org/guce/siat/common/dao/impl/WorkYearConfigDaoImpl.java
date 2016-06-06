package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.WorkYearConfigDao;
import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.WorkYearConfig;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class WorkYearConfigDaoImpl.
 */
@Repository("workYearConfigDao")
@Transactional(propagation = Propagation.REQUIRED)
public class WorkYearConfigDaoImpl extends AbstractJpaDaoImpl<WorkYearConfig> implements WorkYearConfigDao
{

	/**
	 * Instantiates a new work year config dao impl.
	 */
	public WorkYearConfigDaoImpl()
	{
		super();
		setClasse(WorkYearConfig.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.WorkYearConfigDao#findWorkYearConfigByYear(java.lang.Integer)
	 */
	@Override
	public List<WorkYearConfig> findWorkYearConfigByYear(final Integer year)
	{

		final String hqlQuery = "SELECT w FROM WorkYearConfig w WHERE EXTRACT(year FROM w.beginDate) = :year ORDER BY w.id";
		final TypedQuery<WorkYearConfig> query = entityManager.createQuery(hqlQuery, WorkYearConfig.class);
		query.setParameter("year", year);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.WorkYearConfigDao#deleteWorkYearConfigByYear(java.lang.Integer)
	 */
	@Override
	public void deleteWorkYearConfigByYear(final Integer year)
	{
		String hqlQuery = null;

		hqlQuery = "DELETE WorkYearConfig w WHERE EXTRACT(year FROM w.beginDate) = :year ";
		final Query query = super.entityManager.createQuery(hqlQuery);
		query.setParameter("year", year);
		query.executeUpdate();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.WorkYearConfigDao#findByHourlyType(org.guce.siat.common.model.HourlyType)
	 */
	@Override
	public List<WorkYearConfig> findByHourlyType(final HourlyType hourlyType)
	{
		final String hqlString = "SELECT w FROM WorkYearConfig w WHERE w.hourlyType.id = :hourlyTypeId ";
		final TypedQuery<WorkYearConfig> query = super.entityManager.createQuery(hqlString, WorkYearConfig.class);
		query.setParameter("hourlyTypeId", hourlyType.getId());
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.WorkYearConfigDao#findBetweenTowDates(java.util.Date, java.util.Date)
	 */
	@Override
	public List<WorkYearConfig> findBetweenTwoDates(final Date beginDate, final Date endDate)
	{
		if (beginDate != null && endDate != null && beginDate.before(endDate))
		{
			final String hqlString = "SELECT w FROM WorkYearConfig w WHERE :beginDate BETWEEN  w.beginDate AND w.endDate OR :endDate BETWEEN  w.beginDate AND w.endDate";
			final TypedQuery<WorkYearConfig> query = super.entityManager.createQuery(hqlString, WorkYearConfig.class);
			query.setParameter("beginDate", beginDate, TemporalType.DATE);
			query.setParameter("endDate", endDate, TemporalType.DATE);
			return query.getResultList();
		}

		return Collections.emptyList();
	}
}
