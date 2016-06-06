package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.CarDao;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CarServiceImpl.
 */
@Service("carService")
@Transactional(readOnly = true)
public class CarServiceImpl extends AbstractServiceImpl<Car> implements CarService
{

	/** The car dao. */
	@Autowired
	private CarDao carDao;

	/**
	 * Instantiates a new car service impl.
	 */
	public CarServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Car> getJpaDao()
	{
		return carDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Car> jpaDao)
	{
		this.carDao = (CarDao) jpaDao;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.CarService#findByOrganism(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<Car> findByOrganism(final Organism organism)
	{
		return carDao.findByOrganism(organism);
	}


}
