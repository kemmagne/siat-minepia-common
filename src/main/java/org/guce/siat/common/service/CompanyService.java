package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Company;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface CompanyService.
 */
public interface CompanyService extends AbstractService<Company> {

    /**
     * Find operator.
     *
     * @return the list
     */
    List<Company> findOperator();

    /**
     * Find company by num contribuable.
     *
     * @param numContribuable the num contribuable
     * @return the company
     */
    Company findCompanyByNumContribuable(final String numContribuable);

    /**
     *
     * @param fileTypeCodes
     * @return
     */
    List<Company> findCompaniesByFileTypes(FileTypeCode... fileTypeCodes);
}
