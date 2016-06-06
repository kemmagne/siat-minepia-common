/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.CarDao;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Organism;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CarDaoImpl.
 */
@Repository("carDao")
@Transactional(propagation = Propagation.REQUIRED)
public class CarDaoImpl extends AbstractJpaDaoImpl<Car> implements CarDao
{

	/**
	 * Instantiates a new car dao impl.
	 */
	public CarDaoImpl()
	{
		super();
		setClasse(Car.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.CarDao#findByOrganism(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<Car> findByOrganism(final Organism organism)
	{
		if (organism != null)
		{
			final String hqlString = "SELECT c FROM Car c WHERE c.organism.id = :organismId AND c.deleted=false";
			final TypedQuery<Car> query = super.entityManager.createQuery(hqlString, Car.class);
			query.setParameter("organismId", organism.getId());
			return query.getResultList();
		}
		return Collections.emptyList();
	}

}
