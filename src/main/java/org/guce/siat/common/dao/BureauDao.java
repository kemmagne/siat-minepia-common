package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.utils.enums.BureauType;




/**
 * The Interface BureauDao.
 */
public interface BureauDao extends AbstractJpaDao<Bureau>
{

	/**
	 * Find bureau by type and organism.
	 *
	 * @param type
	 *           the type
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<Bureau> findBureauByTypeAndOrganism(BureauType type, Organism organism);


	/**
	 * Find by service and code.
	 *
	 * @param service
	 *           the service
	 * @param codeBureau
	 *           the code bureau
	 * @return the bureau
	 */
	Bureau findByServiceAndCode(Service service, String codeBureau);
}
