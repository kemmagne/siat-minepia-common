package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ParamsDao;
import org.guce.siat.common.model.Params;
import org.guce.siat.common.service.ParamsService;
import org.guce.siat.common.utils.enums.ParamsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ParamsServiceImpl.
 */
@Service("paramsService")
@Transactional(readOnly = true)
public class ParamsServiceImpl extends AbstractServiceImpl<Params> implements ParamsService
{

	/** The params dao. */
	@Autowired
	ParamsDao paramsDao;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Params> getJpaDao()
	{
		return paramsDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Params> jpaDao)
	{
		this.paramsDao = (ParamsDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.ParamsService#findParamsByName(java.lang.String)
	 */
	@Override
	public Params findParamsByName(final String name)
	{
		return paramsDao.findParamsByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.ParamsService#findParamsByCategory(org.guce.siat.common.utils.enums.ParamsCategory)
	 */
	@Override
	public List<Params> findParamsByCategory(final ParamsCategory category)
	{
		return paramsDao.findParamsByCategory(category);
	}
}
