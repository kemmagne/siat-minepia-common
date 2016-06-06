package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;




/**
 * The Interface OrganismDao.
 */
public interface OrganismDao extends AbstractJpaDao<Organism>
{

	/**
	 * Check usr affected.
	 *
	 * @param organism
	 *           the organism
	 * @return true, if successful
	 */
	Boolean hasDirectorAffected(final Organism organism);

	/**
	 * Checks for supervisor affected.
	 *
	 * @param organism
	 *           the organism
	 * @return the boolean
	 */
	Boolean hasSupervisorAffected(Organism organism);

	/**
	 * Find organisms by ministry.
	 *
	 * @param ministry
	 *           the ministry
	 * @return the list
	 */
	List<Organism> findOrganismsByMinistry(Ministry ministry);


	/**
	 * Find organism not have admin by ministry.
	 *
	 * @param ministry
	 *           the ministry
	 * @return the list
	 */
	List<Organism> findOrganismNotHaveAdminByMinistry(final Ministry ministry);


}
