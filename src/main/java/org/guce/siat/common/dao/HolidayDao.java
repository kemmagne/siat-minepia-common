package org.guce.siat.common.dao;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.model.Holiday;


/**
 * The Interface HolidayDao.
 */
public interface HolidayDao extends AbstractJpaDao<Holiday>
{



	/**
	 * Find holiday by date.
	 *
	 * @param date
	 *           the date
	 * @return the holiday
	 */
	public Holiday findHolidayByDate(Date date);


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
