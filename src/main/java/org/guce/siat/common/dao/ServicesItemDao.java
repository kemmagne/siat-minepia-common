package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.ServicesItem;


/**
 * The Interface CustomUserDetailsDao.
 */
public interface ServicesItemDao extends AbstractJpaDao<ServicesItem>
{

	/**
	 * Get list of service items by service.
	 *
	 * @param service
	 *           the service
	 * @return the list
	 */
	List<ServicesItem> loadServicesItemByService(Service service);

	/**
	 * Find by nsh.
	 *
	 * @param nsh
	 *           the nsh
	 * @return the list
	 */
	List<ServicesItem> findByNSH(final String nsh);

	/**
	 * Find native service item by nsh.
	 *
	 * @param nsh
	 *           the nsh
	 * @return the list
	 */
	ServicesItem findNativeServiceItemByNSH(final String nsh);


	/**
	 * Find by nsh and code.
	 *
	 * @param nsh
	 *           the nsh
	 * @param code
	 *           the code
	 * @return the services item
	 */
	ServicesItem findByNshAndCode(String nsh, String code);

	/**
	 * Find by organism.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<ServicesItem> findByOrganism(Organism organism);


	/**
	 * Find active item by organise.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<ServicesItem> findActiveItemByOrganism(final Organism organism);


	/**
	 * Fetch max code by nsh.
	 *
	 * @param nsh
	 *           the nsh
	 * @return the integer
	 */
	Integer fetchMaxCodeByNsh(String nsh);
}
