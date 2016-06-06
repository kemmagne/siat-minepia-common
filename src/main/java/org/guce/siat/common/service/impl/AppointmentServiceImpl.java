/**
 *
 */
package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AppointmentDao;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.model.Appointment;
import org.guce.siat.common.model.AppointmentItemFlow;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AppointmentServiceImpl.
 */
@Service("appointmentService")
@Transactional(readOnly = true)
public class AppointmentServiceImpl extends AbstractServiceImpl<Appointment>implements AppointmentService
{


	/** The appointment DAO. */
	@Autowired
	private AppointmentDao appointmentDao;

	@Autowired
	private FileDao fileDao;

	/**
	 * Instantiates a new appointment service impl.
	 */
	public AppointmentServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Appointment> getJpaDao()
	{
		return appointmentDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Appointment> jpaDao)
	{
		this.appointmentDao = (AppointmentDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AppointmentService#findAppointmentsByControllerList(java.util.List)
	 */
	@Override
	public List<Appointment> findAppointmentsByControllerList(final List<User> controllerList)
	{
		return appointmentDao.findAppointmentsByControllerList(controllerList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AppointmentService#finAppointmentsByItemFlowList(java.util.List)
	 */
	@Override
	public Appointment findAppointmentByItemFlowList(final List<ItemFlow> itemFlows)
	{
		return appointmentDao.findAppointmentByItemFlowList(itemFlows);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AppointmentService#findAppoitmentByFileItemAndController(java.util.List,
	 * org.guce.siat.common.model.User)
	 */
	@Override
	public Appointment findAppoitmentByFileItemAndController(final List<FileItem> fileItems, final User controller)
	{
		return appointmentDao.findAppoitmentByFileItemAndController(fileItems, controller);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.AppointmentService#finAppointmentsByItemFlow(org.guce.siat.common.model.ItemFlow)
	 */
	@Override
	public Appointment findAppointmentsByItemFlow(final ItemFlow itemFlow)
	{
		final Appointment appointment = appointmentDao.findLastAppointmentByItemFlow(itemFlow);

		if (appointment != null)
		{
			final List<AppointmentItemFlow> appItemFlowList = new ArrayList<AppointmentItemFlow>(
					appointment.getAppointmentItemFlowList());

			if (BooleanUtils.isFalse(appointment.getInspectionOnDock()))
			{
				appointment
						.setInspectionPlace(appItemFlowList.get(0).getItemFlow().getFileItem().getFile().getClient().getFirstAddress());
			}
			else
			{
				final FileFieldValue portAddress = fileDao.findFileFieldValueByFieldCode(
						appItemFlowList.get(0).getItemFlow().getFileItem().getFile(), "LIEUDECHARGEMENT_LIBELLE");

				if (portAddress != null)
				{
					appointment.setInspectionPlace(portAddress.getValue());
				}

			}
		}
		return appointment;
	}




}
