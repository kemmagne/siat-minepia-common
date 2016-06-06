package org.guce.siat.common.service.impl;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.WorkYearConfigDao;
import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.WorkYearConfig;
import org.guce.siat.common.service.WorkYearConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class WorkYearConfigServiceImpl.
 */
@Service("workYearConfigService")
@Transactional(readOnly = true)
public class WorkYearConfigServiceImpl extends AbstractServiceImpl<WorkYearConfig> implements WorkYearConfigService
{

	/** The work year config dao. */
	@Autowired
	private WorkYearConfigDao workYearConfigDao;

	/**
	 * Instantiates a new work year config service impl.
	 */
	public WorkYearConfigServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<WorkYearConfig> getJpaDao()
	{
		return workYearConfigDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<WorkYearConfig> jpaDao)
	{
		this.workYearConfigDao = (WorkYearConfigDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.WorkYearConfigService#findWorkYearConfigByYear(java.lang.Integer)
	 */
	@Override
	public List<WorkYearConfig> findWorkYearConfigByYear(final Integer year)
	{
		return workYearConfigDao.findWorkYearConfigByYear(year);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.WorkYearConfigService#deleteWorkYearConfigByYear(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteWorkYearConfigByYear(final Integer year)
	{

		workYearConfigDao.deleteWorkYearConfigByYear(year);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.WorkYearConfigService#findByHourlyType(org.guce.siat.common.model.HourlyType)
	 */
	@Override
	public List<WorkYearConfig> findByHourlyType(final HourlyType hourlyType)
	{
		return workYearConfigDao.findByHourlyType(hourlyType);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.WorkYearConfigService#findBetweenTowDates(java.util.Date, java.util.Date)
	 */
	@Override
	public List<WorkYearConfig> findBetweenTwoDates(final Date beginDate, final Date endDate)
	{
		return workYearConfigDao.findBetweenTwoDates(beginDate, endDate);
	}
}
