package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AuthorityDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.service.AuthorityService;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class AuthorityServiceImpl.
 */
@Service("authorityService")
@Transactional(readOnly = true)
public class AuthorityServiceImpl extends AbstractServiceImpl<Authority> implements AuthorityService
{

	/** The authority dao. */
	@Autowired
	AuthorityDao authorityDao;

	/**
	 * Instantiates a new authority service impl.
	 */
	public AuthorityServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AuthorityService#findAuthoritiesByAuthorityList(java.util.List)
	 */
	@Override
	public List<Authority> findAuthoritiesByAuthorityList(final List<String> authorityList)
	{
		return authorityDao.findAuthoritiesByAuthorityList(authorityList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Authority> getJpaDao()
	{
		return authorityDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Authority> jpaDao)
	{
		this.authorityDao = (AuthorityDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AuthorityService#findByAuthoritiesType()
	 */
	@Override
	public List<Authority> findByAuthoritiesType()
	{
		return authorityDao.findByAuthoritiesType();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AuthorityService#findAuthorityByCode(java.lang.String)
	 */
	@Override
	public Authority findAuthorityByCode(final String authorityCode)
	{
		return authorityDao.findAuthorityByCode(authorityCode);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.AuthorityService#findDistinctAutoritiesByPosition(org.guce.siat.common.utils.enums
	 * .PositionType)
	 */
	@Override
	public List<Authority> findDistinctAutoritiesByPosition(final PositionType post)
	{
		return authorityDao.findDistinctAutoritiesByPosition(post);
	}

}
