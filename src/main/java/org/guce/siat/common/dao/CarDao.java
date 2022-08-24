package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Organism;




/**
 * The Interface CarDao.
 */
public interface CarDao extends AbstractJpaDao<Car>
{

	/**
	 * Find by organism.
	 * 
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<Car> findByOrganism(Organism organism);

}
