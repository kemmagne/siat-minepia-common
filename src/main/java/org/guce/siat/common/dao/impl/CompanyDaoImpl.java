package org.guce.siat.common.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.CompanyDao;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.model.Pair;
import org.guce.siat.common.utils.enums.CompanyType;
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
            List<Company> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (NoResultException | NonUniqueResultException e) {
            logger.error("Error to get Company with num contribuable : {} --- Détails : {}", numContribuable, Objects.toString(e));
            return null;
        }
    }

    @Override
    public List<Pair> findCompanies() {

        Query query = super.entityManager.createNativeQuery("SELECT C.NUM_CONTRIBUABLE, C.COMPANY_NAME FROM SIAT_CT.COMPANY C WHERE C.NUM_CONTRIBUABLE IS NOT NULL AND C.ID = (SELECT MAX(CC.ID) FROM SIAT_CT.COMPANY CC WHERE CC.NUM_CONTRIBUABLE = C.NUM_CONTRIBUABLE) ORDER BY C.COMPANY_NAME ASC");

        List<Pair> companies = new ArrayList<>();
        List list = query.getResultList();
        for (Object object : list) {
            Object[] line = (Object[]) object;
            companies.add(new Pair((String) line[0], (String) line[1]));
        }

        return companies;
    }

    @Override
    public List<Pair> findCompaniesByNumeroContribuableOrCompanyName(String searchQuery) {

        Query query = super.entityManager.createNativeQuery("SELECT C.NUM_CONTRIBUABLE, C.COMPANY_NAME FROM SIAT_CT.COMPANY C WHERE C.NUM_CONTRIBUABLE IS NOT NULL AND C.ID = (SELECT MAX(CC.ID) FROM SIAT_CT.COMPANY CC WHERE CC.NUM_CONTRIBUABLE = C.NUM_CONTRIBUABLE) AND (LOWER(C.NUM_CONTRIBUABLE) LIKE concat('%', concat(:searchQuery,'%')) OR LOWER(C.COMPANY_NAME) LIKE concat('%', concat(:searchQuery,'%'))) ORDER BY C.COMPANY_NAME ASC");
        query.setParameter("searchQuery", searchQuery);
        List<Pair> companies = new ArrayList<>();
        List list = query.getResultList();
        for (Object object : list) {
            Object[] line = (Object[]) object;
            companies.add(new Pair((String) line[0], (String) line[1]));
        }

        return companies;
    }
}
