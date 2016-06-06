package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;


/**
 * The Interface OrganismService.
 */
public interface OrganismService extends AbstractService<Organism>
{
	/**
	 * Find organism by user.
	 *
	 * @param user
	 *           the user
	 * @return the organism
	 */
	Organism findOrganismByUser(User user);

	/**
	 * Check usr affected.
	 *
	 * @param organism
	 *           the organism
	 * @return true, if successful
	 */
	Boolean hasDirectorAffected(Organism organism);

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
	List<Organism> findOrganismNotHaveAdminByMinistry(Ministry ministry);

}
