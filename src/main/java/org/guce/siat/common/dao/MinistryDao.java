package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Ministry;


/**
 * The Interface MinistryDao.
 */
public interface MinistryDao extends AbstractJpaDao<Ministry>
{

	/**
	 * Checks for minister affected.
	 *
	 * @param ministry
	 *           the ministry
	 * @return true, if successful
	 */
	boolean hasMinisterAffected(final Ministry ministry);

	/**
	 * Checks for sg affected.
	 *
	 * @param ministry
	 *           the ministry
	 * @return true, if successful
	 */
	boolean hasSGAffected(final Ministry ministry);


	/**
	 * Find ministry has'nt admin.
	 *
	 * @return the list
	 */
	List<Ministry> findMinistryHasntAdmin();
}
