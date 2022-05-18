package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.Appointment;
import org.guce.siat.common.model.AppointmentItemFlow;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface AppointmentDao.
 */
public interface AppointmentDao extends AbstractJpaDao<Appointment> {

    /**
     * Delete.
     *
     * @param appointmentItemFlow the appointment item flow
     */
    void delete(AppointmentItemFlow appointmentItemFlow);

    /**
     * Update.
     *
     * @param appointmentItemFlow the appointment item flow
     */
    void update(AppointmentItemFlow appointmentItemFlow);

    /**
     * Find appointments by controller list.
     *
     * @param controllerList the controller list
     * @return the list
     */
    List<Appointment> findAppointmentsByControllerList(List<User> controllerList);

    /**
     * Fin appointments by item flow list.
     *
     * @param itemFlows the item flows
     * @return the list
     */
    Appointment findAppointmentByItemFlowList(List<ItemFlow> itemFlows);

    /**
     * Find appointment by item flow.
     *
     * @param itemFlow the item flow
     * @return the appointment
     */
    Appointment findAppointmentByItemFlow(ItemFlow itemFlow);

    /**
     * Find appointment item flow by item flow.
     *
     * @param itemFlow the item flow
     * @return the appointment item flow
     */
    AppointmentItemFlow findAppointmentItemFlowByItemFlow(ItemFlow itemFlow);

    List<AppointmentItemFlow> findAppointmentItemFlowsByItemFlows(List<ItemFlow> itemFlows);

    /**
     * Find deleted appointment item flow appointment by item flow.
     *
     * @param appointment the appointment
     * @return the appointment item flow
     */
    AppointmentItemFlow findDeletedAppointmentItemFlowAppointmentByAppointment(Appointment appointment);

    /**
     * Fin appointments by item flow.
     *
     * @param itemFlows the item flows
     * @return the appointment
     */
    Appointment findAppointmentsByItemFlow(ItemFlow itemFlows);

    /**
     * Find last appointment by item flow.
     *
     * @param itemFlows the item flows
     * @return the appointment
     */
    Appointment findLastAppointmentByItemFlow(ItemFlow itemFlows);

    /**
     * Find appoitment by file item and controller.
     *
     * @param fileItems the file items
     * @param controller the controller
     * @return the ppointment
     */
    Appointment findAppoitmentByFileItemAndController(List<FileItem> fileItems, User controller);

    /**
     * Fin appointments by file item.
     *
     * @param fileItem
     * @return the appointment
     */
    Appointment findAppointmentByFileItem(FileItem fileItem);

    Appointment findDeletedAppointmentByFileItem(FileItem fileItem);

    List<Appointment> findRelatedAppointments(File currentFile, FileTypeCode... fileTypeCodes);

}
