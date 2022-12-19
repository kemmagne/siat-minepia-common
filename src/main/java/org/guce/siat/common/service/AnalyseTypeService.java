package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.AnalyseType;




/**
 * The Interface AnalyseTypeService.
 */
public interface AnalyseTypeService extends AbstractService<AnalyseType>
{
	/**
	 * Find by administration.
	 *
	 * @param administration
	 *           the administration
	 * @return the list
	 */
	List<AnalyseType> findByAdministration(Administration administration);

}
