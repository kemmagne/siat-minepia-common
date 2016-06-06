package org.guce.siat.common.service;

import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.service.AbstractService;


/**
 * The Interface UserAuthorityService.
 */
public interface UserAuthorityService extends AbstractService<UserAuthority>
{

	/**
	 * Creates the user and authorities.
	 *
	 * @param user
	 *           the user
	 * @param authorities
	 *           the authorities
	 */
	void createUserAndAuthorities(User user, UserAuthority... authorities);
}
