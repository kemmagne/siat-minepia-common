package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Params;
import org.guce.siat.common.utils.enums.ParamsCategory;


/**
 * The Interface ParamsService.
 */
public interface ParamsService extends AbstractService<Params>
{

	/**
	 * Find params by name.
	 *
	 * @param name
	 *           the name
	 * @return the params
	 */
	Params findParamsByName(final String name);

	/**
	 * Find params by category.
	 *
	 * @param category
	 *           the category
	 * @return the list
	 */
	List<Params> findParamsByCategory(ParamsCategory category);

}
