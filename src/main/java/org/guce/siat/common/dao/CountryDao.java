package org.guce.siat.common.dao;

import org.guce.siat.common.model.Country;



/**
 * The Interface CountryDao.
 */
public interface CountryDao extends AbstractJpaDao<Country>
{


	/**
	 * Find country by country id alpha2.
	 *
	 * @param countryIdAlpha2
	 *           the country id alpha2
	 * @return the country
	 */
	Country findCountryByCountryIdAlpha2(String countryIdAlpha2);
}
