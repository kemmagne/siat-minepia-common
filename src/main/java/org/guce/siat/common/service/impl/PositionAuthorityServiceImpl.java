package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.PositionAuthorityDao;
import org.guce.siat.common.model.PositionAuthority;
import org.guce.siat.common.service.PositionAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class PositionAuthorityServiceImpl.
 */
@Service("positionAuthorityService")
@Transactional(readOnly = true)
public class PositionAuthorityServiceImpl extends AbstractServiceImpl<PositionAuthority> implements PositionAuthorityService

{

	@Autowired
	private PositionAuthorityDao positionAuthorityDao;


	/**
	 * Instantiates a new position authority service impl.
	 */
	public PositionAuthorityServiceImpl()
	{
		super();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<PositionAuthority> getJpaDao()
	{
		return positionAuthorityDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<PositionAuthority> jpaDao)
	{
		this.positionAuthorityDao = (PositionAuthorityDao) jpaDao;
	}

}
