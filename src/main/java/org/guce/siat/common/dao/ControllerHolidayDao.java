package org.guce.siat.common.dao;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.model.ControllerHoliday;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;



/**
 * The Interface ControllerHolidayDao.
 */
public interface ControllerHolidayDao extends AbstractJpaDao<ControllerHoliday>
{

	/**
	 * Find holiday by controller and dates.
	 *
	 * @param user
	 *           the user
	 * @param beginDate
	 *           the begin date
	 * @param endDate
	 *           the end date
	 * @return the list
	 */
	List<ControllerHoliday> findHolidayByControllerAndDates(User user, Date beginDate, Date endDate);

	/**
	 * Find holidays by list controller.
	 *
	 * @param controllerList
	 *           the controller list
	 * @return the list
	 */
	List<ControllerHoliday> findHolidaysByListController(List<User> controllerList);

	/**
	 * Find holiday by controller and day.
	 *
	 * @param controller
	 *           the controller
	 * @param date
	 *           the date
	 * @return the controller holiday
	 */
	ControllerHoliday findHolidayByControllerAndDay(User controller, Date date);
	
	/**
	 * Find by organism.
	 *
	 * @param organism the organism
	 * @return the list
	 */
	List<ControllerHoliday> findByOrganism(Organism organism);

}
