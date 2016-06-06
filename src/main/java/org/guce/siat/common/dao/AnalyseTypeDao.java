package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.AnalyseType;



/**
 * The Interface AnalyseTypeDao.
 */
public interface AnalyseTypeDao extends AbstractJpaDao<AnalyseType>
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
