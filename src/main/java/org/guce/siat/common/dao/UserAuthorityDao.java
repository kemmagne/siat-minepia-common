package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;


/**
 * The Interface UserAuthorityDao.
 */
public interface UserAuthorityDao extends AbstractJpaDao<UserAuthority>
{
	
	/**
	 * Find by user and authority.
	 *
	 * @param user
	 *           the user
	 * @param authority
	 *           the authority
	 * @return the user authority
	 */
	UserAuthority findByUserAndAuthoriy(User user, Authority authority);

	/**
	 * Removes the unused authrities.
	 *
	 * @param user
	 *           the user
	 * @param userAuthorities
	 *           the user authorities
	 */
	void removeUnusedAuthrities(final User user, final List<UserAuthority> userAuthorities);
        
        /**
	 * Removes the used authrities.
	 *
	 * @param userAuthoritiesIds
	 *           the user authorities ids
	 */
	void removeUsedAuthrities(final List<Long> userAuthoritiesIds);
}
