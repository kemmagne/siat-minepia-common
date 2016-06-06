package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The Class AppointmentItemFlowId.
 */
@Embeddable
public class AppointmentItemFlowId implements Serializable
{

	private static final long serialVersionUID = 8836011199107093252L;

	/** The appointment. */
	@ManyToOne(cascade =
	{ CascadeType.REMOVE, CascadeType.MERGE })
	@JoinColumn(name = "APPOINTMENT_ID", referencedColumnName = "ID")
	private Appointment appointment;

	/** The item flow. */
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ITEM_FLOW_ID", referencedColumnName = "ID")
	private ItemFlow itemFlow;

	/**
	 * Gets the appointment.
	 *
	 * @return the appointment
	 */
	public Appointment getAppointment()
	{
		return appointment;
	}

	/**
	 * Sets the appointment.
	 *
	 * @param appointment
	 *           the appointment to set
	 */
	public void setAppointment(final Appointment appointment)
	{
		this.appointment = appointment;
	}

	/**
	 * Gets the item flow.
	 *
	 * @return the itemFlow
	 */
	public ItemFlow getItemFlow()
	{
		return itemFlow;
	}

	/**
	 * Sets the item flow.
	 *
	 * @param itemFlow
	 *           the itemFlow to set
	 */
	public void setItemFlow(final ItemFlow itemFlow)
	{
		this.itemFlow = itemFlow;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appointment == null) ? 0 : appointment.hashCode());
		result = prime * result
				+ ((itemFlow == null) ? 0 : itemFlow.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AppointmentItemFlowId)) {
			return false;
		}
		AppointmentItemFlowId other = (AppointmentItemFlowId) obj;
		if (appointment == null) {
			if (other.appointment != null) {
				return false;
			}
		} else if (!appointment.equals(other.appointment)) {
			return false;
		}
		if (itemFlow == null) {
			if (other.itemFlow != null) {
				return false;
			}
		} else if (!itemFlow.equals(other.itemFlow)) {
			return false;
		}
		return true;
	}
	
	

}
