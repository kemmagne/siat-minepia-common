/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Appointment.
 */
@Entity
@Table(name = "APPOINTMENT")
@XmlRootElement
public class Appointment extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPOINTMENT_SEQ")
	@SequenceGenerator(name = "APPOINTMENT_SEQ", sequenceName = "APPOINTMENT_SEQ", allocationSize = 1)
	private Long id;

	/** The begin time. */
	@Column(name = "BEGIN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginTime;

	/** The end time. */
	@Column(name = "END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	/** The inspection on dock. */
	@Column(name = "INSPECTION_ON_DOCK")
	private Boolean inspectionOnDock;

	/** The deleted. */
	@Column(name = "DELETED", nullable = true)
	private Boolean deleted;

	/** The observations. */
	@Column(name = "OBSERVATIONS")
	private String observations;

	/** The history. */
	@Column(name = "HISTORY")
	private Short history;

	/** The controller. */
	@JoinColumn(name = "CONTROLLER_ID", referencedColumnName = "ID")
	@ManyToOne
	private User controller;

	/** The car. */
	@JoinColumn(name = "car_id", referencedColumnName = "ID")
	@ManyToOne
	private Car car;

	/** The appointment item flow list. */
	@OneToMany(mappedBy = "primaryKey.appointment", cascade =
	{ CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<AppointmentItemFlow> appointmentItemFlowList = new ArrayList<AppointmentItemFlow>();

	/** The inspection place. */
	@Transient
	private String inspectionPlace;

	/**
	 * Instantiates a new appointment.
	 */
	public Appointment()
	{
	}

	/**
	 * Pre persist.
	 */
	@PrePersist
	public void prePersist()
	{
		this.deleted = Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.model.AbstractModel#getId()
	 */
	@Override
	public Long getId()
	{
		return id;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}


	/**
	 * Gets the begin time.
	 *
	 * @return the begin time
	 */
	public Date getBeginTime()
	{
		return beginTime;
	}


	/**
	 * Sets the begin time.
	 *
	 * @param beginTime
	 *           the new begin time
	 */
	public void setBeginTime(final Date beginTime)
	{
		this.beginTime = beginTime;
	}


	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public Date getEndTime()
	{
		return endTime;
	}


	/**
	 * Sets the end time.
	 *
	 * @param endTime
	 *           the new end time
	 */
	public void setEndTime(final Date endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * Gets the inspection on dock.
	 *
	 * @return the inspection on dock
	 */
	public Boolean getInspectionOnDock()
	{
		return inspectionOnDock;
	}


	/**
	 * Sets the inspection on dock.
	 *
	 * @param inspectionOnDock
	 *           the new inspection on dock
	 */
	public void setInspectionOnDock(final Boolean inspectionOnDock)
	{
		this.inspectionOnDock = inspectionOnDock;
	}


	/**
	 * Gets the observations.
	 *
	 * @return the observations
	 */
	public String getObservations()
	{
		return observations;
	}


	/**
	 * Sets the observations.
	 *
	 * @param observations
	 *           the new observations
	 */
	public void setObservations(final String observations)
	{
		this.observations = observations;
	}


	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	public Short getHistory()
	{
		return history;
	}


	/**
	 * Sets the history.
	 *
	 * @param history
	 *           the new history
	 */
	public void setHistory(final Short history)
	{
		this.history = history;
	}

	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public User getController()
	{
		return controller;
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 *           the controller to set
	 */
	public void setController(final User controller)
	{
		this.controller = controller;
	}

	/**
	 * Gets the car.
	 *
	 * @return the car
	 */
	public Car getCar()
	{
		return car;
	}

	/**
	 * Sets the car.
	 *
	 * @param car
	 *           the new car
	 */
	public void setCar(final Car car)
	{
		this.car = car;
	}

	/**
	 * Gets the appointment item flow list.
	 *
	 * @return the appointmentItemFlowList
	 */
	public List<AppointmentItemFlow> getAppointmentItemFlowList()
	{
		return appointmentItemFlowList;
	}

	/**
	 * Sets the appointment item flow list.
	 *
	 * @param appointmentItemFlowList
	 *           the appointmentItemFlowList to set
	 */
	public void setAppointmentItemFlowList(final List<AppointmentItemFlow> appointmentItemFlowList)
	{
		this.appointmentItemFlowList = appointmentItemFlowList;
	}

	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted
	 *           the deleted to set
	 */
	public void setDeleted(final Boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * Gets the inspection place.
	 *
	 * @return the inspectionPlace
	 */
	public String getInspectionPlace()
	{
		return inspectionPlace;
	}

	/**
	 * Sets the inspection place.
	 *
	 * @param inspectionPlace
	 *           the inspectionPlace to set
	 */
	public void setInspectionPlace(final String inspectionPlace)
	{
		this.inspectionPlace = inspectionPlace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object)
	{
		if (!(object instanceof Appointment))
		{
			return false;
		}
		final Appointment other = (Appointment) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("Appointment [id=");
		builder.append(id);
		builder.append(", beginTime=");
		builder.append(beginTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", inspectionOnDock=");
		builder.append(inspectionOnDock);
		builder.append(", observations=");
		builder.append(observations);
		builder.append(", history=");
		builder.append(history);
		builder.append("]");
		return builder.toString();
	}

}
