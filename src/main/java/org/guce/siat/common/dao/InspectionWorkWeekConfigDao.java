package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.InspectionWorkWeekConfig;
import org.guce.siat.common.model.Organism;


/**
 * The Interface InspectionWorkWeekConfigDao.
 */
public interface InspectionWorkWeekConfigDao extends AbstractJpaDao<InspectionWorkWeekConfig>
{

	/**
	 * Find iww config by organism.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<InspectionWorkWeekConfig> findIWWConfigByOrganism(Organism organism);

	/**
	 * Find by horly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	List<InspectionWorkWeekConfig> findByHourlyType(HourlyType hourlyType);
}
