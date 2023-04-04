package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.PortDao;
import org.guce.siat.common.model.Port;
import org.guce.siat.common.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class PortServiceImpl.
 */
@Service("portService")
@Transactional(readOnly = true)
public class PortServiceImpl extends AbstractServiceImpl<Port> implements PortService
{

	/** The port dao. */
	@Autowired
	private PortDao portDao;

	/**
	 * Instantiates a new port service impl.
	 */
	public PortServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Port> getJpaDao()
	{
		return portDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Port> jpaDao)
	{
		this.portDao = (PortDao) jpaDao;
	}

}
