package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;


/**
 * The Interface SubDepartmentService.
 */
public interface SubDepartmentService extends AbstractService<SubDepartment>
{

	/**
	 * Find sub departments by organism.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<SubDepartment> findSubDepartmentsByOrganism(final Organism organism);

	/**
	 * Find sub department by user.
	 *
	 * @param user
	 *           the user
	 * @return the sub department
	 */
	SubDepartment findSubDepartmentByUser(final User user);

	/**
	 * Find non affected by organisme.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<SubDepartment> findNonAffectedByOrganism(final Organism organism);

}
