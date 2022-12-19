package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.ParamsOrganism;
import org.guce.siat.common.utils.enums.ParamsCategory;



/**
 * The Interface ParamsOrganismService.
 */
public interface ParamsOrganismService extends AbstractService<ParamsOrganism>
{

	/**
	 * Find params organism by organism.
	 *
	 * @param organism
	 *           the organism
	 * @param category
	 *           the category
	 * @return the list
	 */
	List<ParamsOrganism> findParamsOrganismByOrganism(Organism organism, ParamsCategory category);

	/**
	 * Find params organism by organism and name.
	 *
	 * @param organism
	 *           the organism
	 * @param paramName
	 *           the param name
	 * @return the params organism
	 */
	ParamsOrganism findParamsOrganismByOrganismAndName(final Organism organism, final String paramName);

	/**
	 * Find long params organism by organism and name.
	 *
	 * @param organism
	 *           the organism
	 * @param paramName
	 *           the param name
	 * @return the long
	 */
	Long findLongParamsOrganismByOrganismAndName(final Organism organism, final String paramName);

}
