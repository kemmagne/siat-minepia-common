package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.User;




/**
 * The Interface MinistryService.
 */
public interface MinistryService extends AbstractService<Ministry>
{

	/**
	 * Find ministry by user.
	 *
	 * @param user
	 *           the user
	 * @return the ministry
	 */
	Ministry findMinistryByUser(User user);

	/**
	 * Check minister affected.
	 *
	 * @param ministry
	 *           the ministry
	 * @return true, if successful
	 */
	boolean hasMinisterAffected(Ministry ministry);

	/**
	 * Check sg affected.
	 *
	 * @param ministry
	 *           the ministry
	 * @return true, if successful
	 */
	boolean hasSGAffected(Ministry ministry);

	/**
	 * Find ministry hasnt admin.
	 *
	 * @return the list
	 */
	List<Ministry> findMinistryHasntAdmin();
}
