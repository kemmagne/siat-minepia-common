package org.guce.siat.common.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.CompanyDao;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.utils.enums.CompanyType;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CompanyDaoImpl.
 */
@Repository("companyDao")
@Transactional(propagation = Propagation.REQUIRED)
public class CompanyDaoImpl extends AbstractJpaDaoImpl<Company> implements CompanyDao {

    /**
     * Instantiates a new company dao impl.
     */
    public CompanyDaoImpl() {
        setClasse(Company.class);
    }

    @Override
    public List<Company> findOperator() {
        final String hqlString = "SELECT c FROM Company c WHERE c.companyType=:companyType";
        final TypedQuery<Company> query = super.entityManager.createQuery(hqlString, Company.class);
        query.setParameter("companyType", CompanyType.DECLARANT);
        return query.getResultList();
    }

    @Override
    public Company findCompanyByNumContribuable(String numContribuable) {
        try {
            String hqlString = "SELECT c FROM Company c WHERE c.numContribuable = :numContribuable ORDER BY c.id DESC";
            TypedQuery<Company> query = super.entityManager.createQuery(hqlString, Company.class);
            query.setParameter("numContribuable", numContribuable);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            logger.error("Error to get Company with num contribuable : {} --- Détails : {}", numContribuable, Objects.toString(e));
            return null;
        }
    }

    @Override
    public List<Company> findCompaniesByFileTypes(FileTypeCode... fileTypeCodes) {

        TypedQuery<Company> query = super.entityManager.createQuery("SELECT DISTINCT f.client FROM File f WHERE f.fileType.code IN (:fileTypeCodes) AND f.bureau IS NOT NULL AND f.numeroDemande IS NOT NULL AND f.client.numContribuable IS NOT NULL", Company.class);

        query.setParameter("fileTypeCodes", Arrays.asList(fileTypeCodes));

        return query.getResultList();
    }

}
