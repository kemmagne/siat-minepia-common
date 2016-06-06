package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.InspectionWorkDayConfigDao;
import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.InspectionWorkDayConfig;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.service.InspectionWorkDayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class InspectionWorkDayConfigServiceImpl.
 */
@Service("inspectionWorkDayConfigService")
@Transactional(readOnly = true)
public class InspectionWorkDayConfigServiceImpl extends AbstractServiceImpl<InspectionWorkDayConfig> implements
		InspectionWorkDayConfigService
{

	/** The inspection work day config dao. */
	@Autowired
	private InspectionWorkDayConfigDao inspectionWorkDayConfigDao;

	/**
	 * Instantiates a new inspection work day config service impl.
	 */
	public InspectionWorkDayConfigServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<InspectionWorkDayConfig> getJpaDao()
	{
		return inspectionWorkDayConfigDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<InspectionWorkDayConfig> jpaDao)
	{
		this.inspectionWorkDayConfigDao = (InspectionWorkDayConfigDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.InspectionWorkDayConfigService#findInspectionWorkDayConfigByHourlyType(org.guce.siat
	 * .common.model.HourlyType)
	 */
	@Override
	public List<InspectionWorkDayConfig> findInspectionWorkDayConfigByHourlyType(final HourlyType hourlyType)
	{
		return inspectionWorkDayConfigDao.findInspectionWorkDayConfigByHourlyType(hourlyType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.InspectionWorkDayConfigService#findInspectionWorkDayConfigByHourlyTypeAndOrganism
	 * (org.guce.siat.common.model.HourlyType, org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<InspectionWorkDayConfig> findInspectionWorkDayConfigByHourlyTypeAndOrganism(final HourlyType hourlyType,
			final Organism organism)
	{
		return inspectionWorkDayConfigDao.findInspectionWorkDayConfigByHourlyTypeAndOrganism(hourlyType, organism);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.InspectionWorkDayConfigService#findByHorlyType(org.guce.siat.common.model.HourlyType)
	 */
	/**
	 * Find by hourly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	@Override
	public List<InspectionWorkDayConfig> findByHourlyType(final HourlyType hourlyType)
	{
		return inspectionWorkDayConfigDao.findByHourlyType(hourlyType);
	}





}
