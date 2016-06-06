package org.guce.siat.common.dao.impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.CompanyDao;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.utils.enums.CompanyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CompanyDaoImpl.
 */
@Repository("companyDao")
@Transactional(propagation = Propagation.REQUIRED)
public class CompanyDaoImpl extends AbstractJpaDaoImpl<Company> implements CompanyDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

	/**
	 * Instantiates a new company dao impl.
	 */
	public CompanyDaoImpl()
	{
		super();
		setClasse(Company.class);
	}

	@Override
	public List<Company> findOperator()
	{
		final String hqlString = "SELECT c FROM Company c WHERE c.companyType=:companyType";
		final TypedQuery<Company> query = super.entityManager.createQuery(hqlString, Company.class);
		query.setParameter("companyType", CompanyType.DECLARANT);
		return query.getResultList();
	}

	@Override
	public Company findCompanyByNumContribuable(final String numContribuable)
	{
		try
		{
			final String hqlString = "SELECT c FROM Company c WHERE c.numContribuable =:numContribuable";
			final TypedQuery<Company> query = super.entityManager.createQuery(hqlString, Company.class);
			query.setParameter("numContribuable", numContribuable);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.error("Error to get Company with num contribuable : {} --- Détails : {}", numContribuable, Objects.toString(e));
			return null;
		}


	}

}
