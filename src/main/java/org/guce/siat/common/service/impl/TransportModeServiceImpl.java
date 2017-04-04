package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.TransportModeDao;
import org.guce.siat.common.model.TransportMode;
import org.guce.siat.common.service.TransportModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




/**
 * The Class TransportModeServiceImpl.
 */
@Service("transportModeService")
@Transactional(readOnly = true)
public class TransportModeServiceImpl extends AbstractServiceImpl<TransportMode> implements TransportModeService
{

	/** The transport mode dao. */
	@Autowired
	private TransportModeDao transportModeDao;


	/**
	 * Instantiates a new transport mode service impl.
	 */
	public TransportModeServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<TransportMode> getJpaDao()
	{
		return transportModeDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<TransportMode> jpaDao)
	{
		this.transportModeDao = (TransportModeDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.TransportModeService#findByModeTransportCode(java.lang.String)
	 */
	@Override
	public TransportMode findByTransportModeCode(final Long transportModeCode)
	{
		return transportModeDao.findByTransportModeCode(transportModeCode);
	}





}
