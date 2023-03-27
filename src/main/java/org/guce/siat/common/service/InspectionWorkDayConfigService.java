package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.InspectionWorkDayConfig;
import org.guce.siat.common.model.Organism;


/**
 * The Interface InspectionWorkDayConfigService.
 */
public interface InspectionWorkDayConfigService extends AbstractService<InspectionWorkDayConfig>
{

	/**
	 * Find inspection work day config by hourly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	List<InspectionWorkDayConfig> findInspectionWorkDayConfigByHourlyType(HourlyType hourlyType);

	/**
	 * Find inspection work day config by hourly type and organism.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	List<InspectionWorkDayConfig> findInspectionWorkDayConfigByHourlyTypeAndOrganism(HourlyType hourlyType, Organism organism);

	/**
	 * Find by horly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	List<InspectionWorkDayConfig> findByHourlyType(HourlyType hourlyType);

}
