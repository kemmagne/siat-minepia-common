package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.UserAuthorityDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.service.UserAuthorityService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class UserAuthorityServiceImpl.
 */
@Service("userAuthorityService")
@Transactional(readOnly = true)
public class UserAuthorityServiceImpl extends AbstractServiceImpl<UserAuthority> implements UserAuthorityService
{

	/** The authority dao. */
	@Autowired
	private UserAuthorityDao authorityDao;

	/** The user dao. */
	@Autowired
	private UserDao userDao;


	/**
	 * Instantiates a new user authority service impl.
	 */
	public UserAuthorityServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<UserAuthority> getJpaDao()
	{

		return authorityDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<UserAuthority> jpaDao)
	{
		this.authorityDao = (UserAuthorityDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.UserAuthorityService#createUserAndAuthorities(org.guce.siat.common.model.User,
	 * org.guce.siat.common.model.UserAuthority[])
	 */
	@Override
	public void createUserAndAuthorities(final User user, final UserAuthority... authorities)
	{
		try
		{
			if (user != null && authorities != null)
			{
				userDao.createUser(user);
				for (int i = 0; i < authorities.length; i++)
				{
					authorityDao.save(authorities[i]);
				}
			}
		}
		catch (final Exception e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
		}

	}

}
