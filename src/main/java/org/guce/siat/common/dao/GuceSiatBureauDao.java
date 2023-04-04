package org.guce.siat.common.dao;

import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.GuceSiatBureau;





/**
 * The Interface ItemFlowDao.
 */
public interface GuceSiatBureauDao extends AbstractJpaDao<GuceSiatBureau>
{

	/**
	 * Find by bureau siat.
	 *
	 * @param bureau
	 *           the bureau
	 * @return the guce siat bureau
	 */
	GuceSiatBureau findByBureauSiat(Bureau bureau);


	/**
	 * Find by bureau guce.
	 *
	 * @param bureauGuceCode
	 *           the bureau guce code
	 * @return the guce siat bureau
	 */
	GuceSiatBureau findByBureauGuce(String bureauGuceCode);
}
