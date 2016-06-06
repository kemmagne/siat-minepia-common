package org.guce.siat.common.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.HolidayDao;
import org.guce.siat.common.model.Holiday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class HolidayDaoImpl.
 */
@Repository("holidayDao")
@Transactional(propagation = Propagation.REQUIRED)
public class HolidayDaoImpl extends AbstractJpaDaoImpl<Holiday> implements HolidayDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HolidayDaoImpl.class);

	/**
	 * Instantiates a new holiday dao impl.
	 */
	public HolidayDaoImpl()
	{
		super();
		setClasse(Holiday.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.HolidayDao#findHolidayByDate(java.util.Date)
	 */
	@Override
	public Holiday findHolidayByDate(final Date date)
	{
		try
		{
			if (date != null)
			{
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery.append("SELECT h FROM Holiday h WHERE h.holidayDate = :holidayDate AND h.deleted = false");
				final TypedQuery<Holiday> query = entityManager.createQuery(hqlQuery.toString(), Holiday.class);
				query.setParameter("holidayDate", date);
				return query.getSingleResult();
			}
			else
			{
				return null;
			}
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.HolidayDao#findBetweenTwoDates(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Holiday> findBetweenTwoDates(final Date beginDate, final Date endDate)
	{
		final StringBuilder hqlQuery = new StringBuilder(
				"SELECT h FROM Holiday h WHERE h.deleted = false AND h.holidayDate BETWEEN :startDate AND :endDate");
		final TypedQuery<Holiday> query = entityManager.createQuery(hqlQuery.toString(), Holiday.class);
		query.setParameter("startDate", beginDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		return query.getResultList();
	}
}
