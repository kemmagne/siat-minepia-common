package org.guce.siat.common.service.impl;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.HolidayDao;
import org.guce.siat.common.model.Holiday;
import org.guce.siat.common.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class HolidayServiceImpl.
 */
@Service("holidayService")
@Transactional(readOnly = true)
public class HolidayServiceImpl extends AbstractServiceImpl<Holiday> implements HolidayService
{

	/** The holiday dao. */
	@Autowired
	private HolidayDao holidayDao;


	/**
	 * Instantiates a new holiday service impl.
	 */
	public HolidayServiceImpl()
	{
		super();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Holiday> getJpaDao()
	{
		return holidayDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Holiday> jpaDao)
	{
		this.holidayDao = (HolidayDao) jpaDao;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.HolidayService#findHolidayByDate(java.util.Date)
	 */
	@Override
	public Holiday findHolidayByDate(final Date date)
	{
		return holidayDao.findHolidayByDate(date);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.HolidayService#findBetweenTwoDates(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Holiday> findBetweenTwoDates(final Date begin, final Date end)
	{

		return holidayDao.findBetweenTwoDates(begin, end);
	}

}
