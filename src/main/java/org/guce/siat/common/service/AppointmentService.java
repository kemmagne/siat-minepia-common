package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Appointment;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.User;




/**
 * The Interface AppointmentService.
 */
public interface AppointmentService extends AbstractService<Appointment>
{

	/**
	 * Find appointments by controller list.
	 *
	 * @param controllerList
	 *           the controller list
	 * @return the list
	 */
	List<Appointment> findAppointmentsByControllerList(List<User> controllerList);

	/**
	 * Fin appointments by item flow list.
	 *
	 * @param itemFlows
	 *           the item flows
	 * @return the list
	 */
	Appointment findAppointmentByItemFlowList(List<ItemFlow> itemFlows);

	/**
	 * Fin appointments by item flow.
	 *
	 * @param itemFlow
	 *           the item flows
	 * @return the appointment
	 */
	Appointment findAppointmentsByItemFlow(ItemFlow itemFlow);

	/**
	 *
	 * @param fileItems
	 *           the file items
	 * @param controller
	 *           the controller
	 * @return the appointment
	 */
	Appointment findAppoitmentByFileItemAndController(List<FileItem> fileItems, User controller);

}
