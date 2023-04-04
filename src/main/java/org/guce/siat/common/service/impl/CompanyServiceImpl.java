package org.guce.siat.common.service.impl;

import java.util.List;
import java.util.Map;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.CompanyDao;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.model.Pair;
import org.guce.siat.common.service.CompanyService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CompanyServiceImpl.
 */
@Service("companyService")
@Transactional(readOnly = true)
public class CompanyServiceImpl extends AbstractServiceImpl<Company> implements CompanyService {

    /**
     * The company dao.
     */
    @Autowired
    private CompanyDao companyDao;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<Company> getJpaDao() {
        return companyDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<Company> jpaDao) {
        this.companyDao = (CompanyDao) jpaDao;
    }

    @Override
    public List<Company> findOperator() {
        return companyDao.findOperator();
    }

    @Override
    public Company findCompanyByNumContribuable(final String numContribuable) {
        return companyDao.findCompanyByNumContribuable(numContribuable);
    }

    @Override
    public List<Pair> findCompanies() {
        return companyDao.findCompanies();
    }

    @Override
    public List<Pair> findCompaniesByNumeroContribuableOrCompanyName(String searchQuery) {
        return companyDao.findCompaniesByNumeroContribuableOrCompanyName(searchQuery);
    }

}
