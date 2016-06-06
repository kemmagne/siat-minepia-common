package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.utils.enums.PositionType;




/**
 * The Interface AuthorityDao.
 */
public interface AuthorityDao extends AbstractJpaDao<Authority>
{

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
	 * Find distinct autorities by position.
	 *
	 * @param post
	 *           the post
	 * @return the list
	 */
	List<Authority> findDistinctAutoritiesByPosition(PositionType post);


	/**
	 * Find authorities by authority list.
	 *
	 * @param authorityList
	 *           the authority list
	 * @return the list
	 */
	List<Authority> findAuthoritiesByAuthorityList(List<String> authorityList);
}
