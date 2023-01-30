package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.InspectionWorkWeekConfigDao;
import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.InspectionWorkWeekConfig;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.service.InspectionWorkWeekConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class InspectionWorkWeekConfigServiceImpl.
 */
@Service("inspectionWorkWeekConfigService")
@Transactional(readOnly = true)
public class InspectionWorkWeekConfigServiceImpl extends AbstractServiceImpl<InspectionWorkWeekConfig> implements
		InspectionWorkWeekConfigService
{

	/** The inspection work week config dao. */
	@Autowired
	private InspectionWorkWeekConfigDao inspectionWorkWeekConfigDao;

	/**
	 * Instantiates a new inspection work week config service impl.
	 */
	public InspectionWorkWeekConfigServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<InspectionWorkWeekConfig> getJpaDao()
	{
		return inspectionWorkWeekConfigDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<InspectionWorkWeekConfig> jpaDao)
	{
		this.inspectionWorkWeekConfigDao = (InspectionWorkWeekConfigDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.InspectionWorkWeekConfigService#findIWWConfigByOrganism(org.guce.siat.common.model
	 * .Organism)
	 */
	@Override
	public List<InspectionWorkWeekConfig> findIWWConfigByOrganism(final Organism organism)
	{
		return inspectionWorkWeekConfigDao.findIWWConfigByOrganism(organism);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.InspectionWorkWeekConfigService#findByHorlyType(org.guce.siat.common.model.HourlyType
	 * )
	 */
	/**
	 * Find by hourly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	@Override
	public List<InspectionWorkWeekConfig> findByHourlyType(final HourlyType hourlyType)
	{
		return inspectionWorkWeekConfigDao.findByHourlyType(hourlyType);
	}

}
