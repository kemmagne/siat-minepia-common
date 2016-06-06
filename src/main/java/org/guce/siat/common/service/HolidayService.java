package org.guce.siat.common.service;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.model.Holiday;


/**
 * The Interface HolidayService.
 */
public interface HolidayService extends AbstractService<Holiday>
{

	/**
	 * Find holiday by date.
	 *
	 * @param date
	 *           the date
	 * @return the holiday
	 */
	Holiday findHolidayByDate(final Date date);

	/**
	 * Find between two dates.
	 *
	 * @param beginDate
	 *           the begin date
	 * @param endDate
	 *           the end date
	 * @return the list
	 */
	List<Holiday> findBetweenTwoDates(Date beginDate, Date endDate);

}
