package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FlowGuceSiatDao;
import org.guce.siat.common.model.FlowGuceSiat;
import org.guce.siat.common.service.FlowGuceSiatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CountryServiceImpl.
 */
@Service("flowGuceSiatService")
@Transactional(readOnly = true)
public class FlowGuceSiatServiceImpl extends AbstractServiceImpl<FlowGuceSiat> implements FlowGuceSiatService
{


	@Autowired
	private FlowGuceSiatDao flowGuceSiatDao;

	/**
	 * Instantiates a new country service impl.
	 */
	public FlowGuceSiatServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FlowGuceSiat> getJpaDao()
	{
		return flowGuceSiatDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FlowGuceSiat> jpaDao)
	{
		this.flowGuceSiatDao = (FlowGuceSiatDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.FlowGuceSiatService#findFlowGuceSiatByFlowGuce(java.lang.String)
	 */
	@Override
	public FlowGuceSiat findFlowGuceSiatByFlowGuce(final String flowGuce)
	{

		return flowGuceSiatDao.findFlowGuceSiatByFlowGuce(flowGuce);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.FlowGuceSiatService#findFlowGuceSiatByFlowSiatAndFileType(java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public FlowGuceSiat findFlowGuceSiatByFlowSiatAndFileType(final String flowSiat, final Long fileTypeId)
	{

		return flowGuceSiatDao.findFlowGuceSiatByFlowSiatAndFileType(flowSiat, fileTypeId);
	}
}
