package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;


/**
 * The Interface ServiceService.
 */
public interface ServiceService extends AbstractService<Service>
{

	/**
	 * Find service by user.
	 *
	 * @param user
	 *           the user
	 * @return the service
	 */
	Service findServiceByUser(User user);

	/**
	 * Find service by organism.
	 *
	 * @param currentOrganism
	 *           the current organism
	 * @return the list
	 */
	List<Service> findServicesByOrganism(Organism currentOrganism);


	/**
	 * Find non affected by sub department.
	 *
	 * @param subDepartment
	 *           the sub department
	 * @return the list
	 */
	List<Service> findNonAffectedServicesBySubDepartment(SubDepartment subDepartment);

	/**
	 * Find services ids by administration.
	 *
	 * @param administration
	 *           the administration
	 * @return the list
	 */
	List<Long> findServicesIdsByAdministration(Administration administration);

	/**
	 * Find services by administration.
	 *
	 * @param currentAdministration
	 *           the current administration
	 * @return the list
	 */
	List<Service> findServicesByAdministration(Administration currentAdministration);
        
        
        /**
	 * Find services by FileType.
	 *
	 * @param fileType
	 *           the fileType
	 * @return the list
	 */
	List<Service> findServicesByAdministration(FileType fileType);

}
