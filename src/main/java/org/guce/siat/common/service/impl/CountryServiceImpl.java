package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.CountryDao;
import org.guce.siat.common.model.Country;
import org.guce.siat.common.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CountryServiceImpl.
 */
@Service("countryService")
@Transactional(readOnly = true)
public class CountryServiceImpl extends AbstractServiceImpl<Country> implements CountryService
{

	/** The country dao. */
	@Autowired
	private CountryDao countryDao;

	/**
	 * Instantiates a new country service impl.
	 */
	public CountryServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Country> getJpaDao()
	{
		return countryDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Country> jpaDao)
	{
		this.countryDao = (CountryDao) jpaDao;
	}

	@Override
	public Country findCountryByCountryIdAlpha2(final String countryIdAlpha2)
	{
		return countryDao.findCountryByCountryIdAlpha2(countryIdAlpha2);
	}
}
