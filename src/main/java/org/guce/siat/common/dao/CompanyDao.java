package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Company;
import org.guce.siat.common.model.Pair;

/**
 * The Interface CompanyDao.
 */
public interface CompanyDao extends AbstractJpaDao<Company> {

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
    Company findCompanyByNumContribuable(String numContribuable);

    /**
     *
     * @return
     */
    List<Pair> findCompanies();
    
    /**
     *
     * @param searchQuery
     * @return
     */
    List<Pair> findCompaniesByNumeroContribuableOrCompanyName(String searchQuery);

}
