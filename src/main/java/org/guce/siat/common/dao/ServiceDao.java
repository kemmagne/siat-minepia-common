package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;

/**
 * The Interface ServiceDao.
 */
public interface ServiceDao extends AbstractJpaDao<Service> {

	/**
	 * Find services by organism.
	 *
	 * @param organism the organism
	 * @return the list
	 */
	List<Service> findServicesByOrganism(Organism organism);

	/**
	 * Find non affected by sub department.
	 *
	 * @param subDepartment the sub department
	 * @return the list
	 */
	List<Service> findNonAffectedServicesBySubDepartment(SubDepartment subDepartment);

	/**
	 * Find service by file type.
	 *
	 * @param fileType the file type
	 * @return the org.guce.siat.common.model. service
	 */
	List<org.guce.siat.common.model.Service> findServiceByFileType(FileType fileType);

	/**
	 * Find service by file type.
	 *
	 * @param fileType the file type
	 * @param ministryCode the ministry
	 * @return the org.guce.siat.common.model. service
	 */
	org.guce.siat.common.model.Service findServiceByFileTypeAndMinistry(FileType fileType, String ministryCode);

	/**
	 * Find services by file type.
	 *
	 * @param fileType the file type
	 * @param ministryCode the ministry
	 * @return the list of org.guce.siat.common.model.Service
	 */
	List<org.guce.siat.common.model.Service> findServicesByFileTypeAndMinistry(FileType fileType, String ministryCode);

}
