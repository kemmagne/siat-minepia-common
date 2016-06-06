package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Company;


/**
 * The Interface CompanyService.
 */
public interface CompanyService extends AbstractService<Company>
{

	/**
	 * Find operator.
	 *
	 * @return the list
	 */
	List<Company> findOperator();

	/**
	 * Find company by num contribuable.
	 *
	 * @param numContribuable
	 *           the num contribuable
	 * @return the company
	 */
	Company findCompanyByNumContribuable(final String numContribuable);
}
