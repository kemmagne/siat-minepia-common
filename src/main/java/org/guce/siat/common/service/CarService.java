package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Organism;




/**
 * The Interface CarService.
 */
public interface CarService extends AbstractService<Car>
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
