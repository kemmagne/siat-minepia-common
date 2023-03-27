package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Administration;


/**
 * The Interface AdministrationService.
 */
public interface AdministrationService extends AbstractService<Administration>
{

	/**
	 * Gets the sub administration by administration.
	 *
	 * @param administration
	 *           the administration
	 * @return the sub administration by administration
	 */
	List<Administration> getSubAdministrationByAdministration(final Administration administration);

	/**
	 * Gets the recursive sub administration by administration.
	 *
	 * @param administration
	 *           the administration
	 * @return the recursive sub administration by administration
	 */
	List<Administration> getRecursiveSubAdministrationByAdministration(final Administration administration);

}
