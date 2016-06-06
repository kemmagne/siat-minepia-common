package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.TransportTypeDao;
import org.guce.siat.common.model.TransportType;
import org.guce.siat.common.service.TransportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class TransportTypeServiceImpl.
 */
@Service("transportTypeService")
@Transactional(readOnly = true)
public class TransportTypeServiceImpl extends AbstractServiceImpl<TransportType> implements TransportTypeService
{


	/** The transport type dao. */
	@Autowired
	private TransportTypeDao transportTypeDao;

	/**
	 * Instantiates a new transport type service impl.
	 */
	public TransportTypeServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<TransportType> getJpaDao()
	{
		return transportTypeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<TransportType> jpaDao)
	{
		this.transportTypeDao = (TransportTypeDao) jpaDao;
	}

}
