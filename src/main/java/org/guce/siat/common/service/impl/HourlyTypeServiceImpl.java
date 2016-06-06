/*
 *
 */
package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.HourlyTypeDao;
import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.service.HourlyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class HourlyTypeServiceImpl.
 */
@Service("hourlyTypeService")
@Transactional(readOnly = true)
public class HourlyTypeServiceImpl extends AbstractServiceImpl<HourlyType> implements HourlyTypeService
{

	/** The hourly type dao. */
	@Autowired
	private HourlyTypeDao hourlyTypeDao;

	/**
	 * Instantiates a new hourly type service impl.
	 */
	public HourlyTypeServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<HourlyType> getJpaDao()
	{
		return hourlyTypeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<HourlyType> jpaDao)
	{
		this.hourlyTypeDao = (HourlyTypeDao) jpaDao;
	}

}
