package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class InspectionWorkWeekConfig.
 */
@Entity
@Table(name = "INSPECTION_WORK_WEEK_CONFIG")
@XmlRootElement
public class InspectionWorkWeekConfig extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IWWC_SEQ")
	@SequenceGenerator(name = "IWWC_SEQ", sequenceName = "IWWC_SEQ", allocationSize = 1)
	private Long id;

	/** The monday. */
	@Column(name = "MONDAY")
	private Boolean monday;

	/** The tuesday. */
	@Column(name = "TUESDAY")
	private Boolean tuesday;

	/** The wednesday. */
	@Column(name = "WEDNESDAY")
	private Boolean wednesday;

	/** The thursday. */
	@Column(name = "THURSDAY")
	private Boolean thursday;

	/** The friday. */
	@Column(name = "FRIDAY")
	private Boolean friday;

	/** The saturday. */
	@Column(name = "SATURDAY")
	private Boolean saturday;

	/** The sunday. */
	@Column(name = "SUNDAY")
	private Boolean sunday;

	/** The organism. */
	@JoinColumn(name = "ORGANISM_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Organism organism;

	/** The hourly type id. */
	@JoinColumn(name = "HOURLY_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private HourlyType hourlyTypeId;

	/**
	 * Instantiates a new inspection work week config.
	 */
	public InspectionWorkWeekConfig()
	{
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *           the new id
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the monday.
	 *
	 * @return the monday
	 */
	public Boolean getMonday()
	{
		return monday;
	}

	/**
	 * Sets the monday.
	 *
	 * @param monday
	 *           the new monday
	 */
	public void setMonday(final Boolean monday)
	{
		this.monday = monday;
	}

	/**
	 * Gets the tuesday.
	 *
	 * @return the tuesday
	 */
	public Boolean getTuesday()
	{
		return tuesday;
	}

	/**
	 * Sets the tuesday.
	 *
	 * @param tuesday
	 *           the new tuesday
	 */
	public void setTuesday(final Boolean tuesday)
	{
		this.tuesday = tuesday;
	}

	/**
	 * Gets the wednesday.
	 *
	 * @return the wednesday
	 */
	public Boolean getWednesday()
	{
		return wednesday;
	}

	/**
	 * Sets the wednesday.
	 *
	 * @param wednesday
	 *           the new wednesday
	 */
	public void setWednesday(final Boolean wednesday)
	{
		this.wednesday = wednesday;
	}

	/**
	 * Gets the thursday.
	 *
	 * @return the thursday
	 */
	public Boolean getThursday()
	{
		return thursday;
	}

	/**
	 * Sets the thursday.
	 *
	 * @param thursday
	 *           the new thursday
	 */
	public void setThursday(final Boolean thursday)
	{
		this.thursday = thursday;
	}

	/**
	 * Gets the friday.
	 *
	 * @return the friday
	 */
	public Boolean getFriday()
	{
		return friday;
	}

	/**
	 * Sets the friday.
	 *
	 * @param friday
	 *           the new friday
	 */
	public void setFriday(final Boolean friday)
	{
		this.friday = friday;
	}

	/**
	 * Gets the saturday.
	 *
	 * @return the saturday
	 */
	public Boolean getSaturday()
	{
		return saturday;
	}

	/**
	 * Sets the saturday.
	 *
	 * @param saturday
	 *           the new saturday
	 */
	public void setSaturday(final Boolean saturday)
	{
		this.saturday = saturday;
	}

	/**
	 * Gets the sunday.
	 *
	 * @return the sunday
	 */
	public Boolean getSunday()
	{
		return sunday;
	}

	/**
	 * Sets the sunday.
	 *
	 * @param sunday
	 *           the new sunday
	 */
	public void setSunday(final Boolean sunday)
	{
		this.sunday = sunday;
	}

	/**
	 * Gets the organism.
	 *
	 * @return the organism
	 */
	public Organism getOrganism()
	{
		return organism;
	}

	/**
	 * Sets the organism.
	 *
	 * @param organism
	 *           the new organism
	 */
	public void setOrganism(final Organism organism)
	{
		this.organism = organism;
	}

	/**
	 * Gets the hourly type id.
	 *
	 * @return the hourly type id
	 */
	public HourlyType getHourlyTypeId()
	{
		return hourlyTypeId;
	}

	/**
	 * Sets the hourly type id.
	 *
	 * @param hourlyTypeId
	 *           the new hourly type id
	 */
	public void setHourlyTypeId(final HourlyType hourlyTypeId)
	{
		this.hourlyTypeId = hourlyTypeId;
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
		if (!(object instanceof InspectionWorkWeekConfig))
		{
			return false;
		}
		final InspectionWorkWeekConfig other = (InspectionWorkWeekConfig) object;
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
		builder.append("InspectionWorkWeekConfig [id=");
		builder.append(id);
		builder.append(", monday=");
		builder.append(monday);
		builder.append(", tuesday=");
		builder.append(tuesday);
		builder.append(", wednesday=");
		builder.append(wednesday);
		builder.append(", thursday=");
		builder.append(thursday);
		builder.append(", friday=");
		builder.append(friday);
		builder.append(", saturday=");
		builder.append(saturday);
		builder.append(", sunday=");
		builder.append(sunday);
		builder.append("]");
		return builder.toString();
	}

}
