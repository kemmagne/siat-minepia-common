package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.SubDepartment;


/**
 * The Interface SubDepartmentDao.
 */
public interface SubDepartmentDao extends AbstractJpaDao<SubDepartment>
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
	 * Find non affected by organisme.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<SubDepartment> findNonAffectedByOrganism(final Organism organism);

}
