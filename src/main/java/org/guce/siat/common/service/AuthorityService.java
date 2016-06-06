package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.utils.enums.PositionType;




/**
 * The Interface AuthorityService.
 */
public interface AuthorityService extends AbstractService<Authority>
{


	/**
	 * Find authorities by authority list.
	 *
	 * @param authorityList
	 *           the authority list
	 * @return the list
	 */
	List<Authority> findAuthoritiesByAuthorityList(List<String> authorityList);

	/**
	 * Find by authorities type.
	 *
	 * @return the list
	 */
	List<Authority> findByAuthoritiesType();

	/**
	 * Find authority by code.
	 *
	 * @param authorityCode
	 *           the authority code
	 * @return the authority
	 */
	Authority findAuthorityByCode(final String authorityCode);

	/**
	 * Find distinct autorities by post.
	 *
	 * @param post
	 *           the post
	 * @return the list
	 */
	List<Authority> findDistinctAutoritiesByPosition(final PositionType post);

}
