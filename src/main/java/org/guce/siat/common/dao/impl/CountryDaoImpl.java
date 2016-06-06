/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.CountryDao;
import org.guce.siat.common.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CountryDaoImpl.
 */
@Repository("countryDao")
@Transactional(propagation = Propagation.REQUIRED)
public class CountryDaoImpl extends AbstractJpaDaoImpl<Country> implements CountryDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CountryDaoImpl.class);

	/**
	 * Instantiates a new country dao impl.
	 */
	public CountryDaoImpl()
	{
		super();
		setClasse(Country.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.CountryDao#findCountryByCountryIdAlpha2(java.lang.String)
	 */
	@Override
	public Country findCountryByCountryIdAlpha2(final String countryIdAlpha2)
	{
		try
		{
			final String hqlString = "SELECT c FROM Country c WHERE c.countryIdAlpha2 =:countryIdAlpha2";
			final TypedQuery<Country> query = super.entityManager.createQuery(hqlString, Country.class);
			query.setParameter("countryIdAlpha2", countryIdAlpha2);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info("Error to get Country with country id alpha2 : {} {} {}", countryIdAlpha2, Objects.toString(e));
			return null;
		}
	}


}
