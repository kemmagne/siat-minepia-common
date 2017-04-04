package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.RecommandationAuthorityDao;
import org.guce.siat.common.model.RecommandationAuthority;
import org.guce.siat.common.service.RecommandationAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class RecommandationAuthorityServiceImpl.
 */
@Service("recommandationAuthorityService")
@Transactional(readOnly = true)
public class RecommandationAuthorityServiceImpl extends AbstractServiceImpl<RecommandationAuthority> implements
		RecommandationAuthorityService
{
	@Autowired
	private RecommandationAuthorityDao recommandationAuthorityDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<RecommandationAuthority> getJpaDao()
	{
		return recommandationAuthorityDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<RecommandationAuthority> jpaDao)
	{
		this.recommandationAuthorityDao = (RecommandationAuthorityDao) jpaDao;
	}

}
