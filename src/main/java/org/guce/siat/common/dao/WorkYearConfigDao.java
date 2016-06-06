package org.guce.siat.common.dao;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.WorkYearConfig;


/**
 * The Interface WorkYearConfigDao.
 */
public interface WorkYearConfigDao extends AbstractJpaDao<WorkYearConfig>
{

	/**
	 * Find work year config by year.
	 *
	 * @param year
	 *           the year
	 * @return the list
	 */
	List<WorkYearConfig> findWorkYearConfigByYear(Integer year);

	/**
	 * Delete work year config by year.
	 *
	 * @param year
	 *           the year
	 */
	void deleteWorkYearConfigByYear(Integer year);

	/**
	 * Find by hourly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	List<WorkYearConfig> findByHourlyType(HourlyType hourlyType);

	/**
	 * Find between tow dates.
	 *
	 * @param beginDate
	 *           the begin date
	 * @param endDate
	 *           the end date
	 * @return the list
	 */
	List<WorkYearConfig> findBetweenTwoDates(final Date beginDate, final Date endDate);
}
