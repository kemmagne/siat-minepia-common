package org.guce.siat.common.service;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.model.ControllerHoliday;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;




/**
 * The Interface ControllerHolidayService.
 */
public interface ControllerHolidayService extends AbstractService<ControllerHoliday>
{

	/**
	 * Find inspector holiday by inspector and dates.
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
	 * Find holidays by list inspector.
	 *
	 * @param inspectorList
	 *           the inspector list
	 * @return the list
	 */
	List<ControllerHoliday> findHolidaysByListController(List<User> inspectorList);


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
