package org.guce.siat.common.service;

import org.guce.siat.common.model.Country;


/**
 * The Interface CountryService.
 */
public interface CountryService extends AbstractService<Country>
{

	/**
	 * Find country by country id alpha2.
	 *
	 * @param countryIdAlpha2
	 *           the country id alpha2
	 * @return the country
	 */
	Country findCountryByCountryIdAlpha2(final String countryIdAlpha2);

}
