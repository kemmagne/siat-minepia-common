package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.ServicesItem;


/**
 * The Interface ServicesItemService.
 */
public interface ServicesItemService extends AbstractService<ServicesItem>
{

	/**
	 * Find services item by service.
	 *
	 * @param service
	 *           the service
	 * @return the list
	 */
	List<ServicesItem> findServicesItemByService(Service service);


	/**
	 * Find services item by nsh.
	 *
	 * @param nsh
	 *           the nsh
	 * @return the list
	 */
	List<ServicesItem> findServicesItemByNsh(String nsh);


	/**
	 * Find services item by nsh and code.
	 *
	 * @param nsh
	 *           the nsh
	 * @param code
	 *           the code
	 * @return the services item
	 */
	ServicesItem findServicesItemByNshAndCode(String nsh, String code);

	/**
	 * Find services item by organisme.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<ServicesItem> findServicesItemByOrganism(Organism organism);

	/**
	 * Find active services item by organisme.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<ServicesItem> findActiveServicesItemByOrganism(Organism organism);

	/**
	 * Find all active services item by organisme.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<ServicesItem> findAllActiveServicesItemByOrganism(Organism organism);

	/**
	 * Handle max code by nsh.
	 *
	 * @param nsh
	 *           the nsh
	 * @return the integer
	 */
	Integer fetchMaxCodeByNsh(final String nsh);

}
