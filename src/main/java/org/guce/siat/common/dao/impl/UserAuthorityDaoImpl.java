package org.guce.siat.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.UserAuthorityDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class UserAuthorityDaoImpl.
 */
@Repository("userAuthorityDao")
@Transactional(propagation = Propagation.REQUIRED)
public class UserAuthorityDaoImpl extends AbstractJpaDaoImpl<UserAuthority> implements UserAuthorityDao
{
	/**
	 * Instantiates a new user authority dao impl.
	 */
	public UserAuthorityDaoImpl()
	{
		super();
		setClasse(UserAuthority.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.dao.UserAuthorityDao#findByUserAndAuthoriy(org.guce.siat.common.model.User,
	 * org.guce.siat.common.model.Authority)
	 */
	@Override
	public UserAuthority findByUserAndAuthoriy(final User user, final Authority authority)
	{
		final StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("SELECT u FROM UserAuthority u WHERE u.user.id=:userId AND u.authorityGranted.id=:authorityId");
		final TypedQuery<UserAuthority> query = entityManager.createQuery(hqlQuery.toString(), UserAuthority.class);
		query.setParameter("userId", user.getId());
		query.setParameter("authorityId", authority.getId());
		final List<UserAuthority> result = query.getResultList();
		if (CollectionUtils.isNotEmpty(result))
		{
			return result.get(0);
		}
		else
		{
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.UserAuthorityDao#removeUnusedAuthrities(org.guce.siat.common.model.User,
	 * java.util.List)
	 */
	/**
	 * Removes the unused authrities.
	 *
	 * @param user
	 *           the user
	 * @param userAuthorities
	 *           the user authorities
	 */
	@Override
	public void removeUnusedAuthrities(final User user, final List<UserAuthority> userAuthorities)
	{
		if (user != null && CollectionUtils.isNotEmpty(userAuthorities))
		{
			final List<Long> listIds = new ArrayList<Long>();
			for (final UserAuthority authority : userAuthorities)
			{
				listIds.add(authority.getId());
			}
			final StringBuilder hqlQuery = new StringBuilder();
			hqlQuery.append("DELETE FROM UserAuthority u WHERE u.user.id=:userId AND u.id NOT IN (:authoritiesIds)");
			final javax.persistence.Query query = entityManager.createQuery(hqlQuery.toString());
			query.setParameter("userId", user.getId());
			query.setParameter("authoritiesIds", listIds);
			query.executeUpdate();
		}
	}

}
