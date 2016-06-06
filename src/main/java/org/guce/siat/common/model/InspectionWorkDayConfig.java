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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class InspectionWorkDayConfig.
 */
@Entity
@Table(name = "INSPECTION_WORK_DAY_CONFIG", uniqueConstraints = @UniqueConstraint(columnNames =
{ "organism_id", "hourly_type_id", "hour" }))
@XmlRootElement
public class InspectionWorkDayConfig extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IWDC_SEQ")
	@SequenceGenerator(name = "IWDC_SEQ", sequenceName = "IWDC_SEQ", allocationSize = 1)
	private Long id;

	/** The hour. */
	@Basic(optional = false)
	@Column(name = "HOUR")
	private Short hour;

	/** The organism. */
	@JoinColumn(name = "ORGANISM_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Organism organism;

	/** The hourly type. */
	@JoinColumn(name = "HOURLY_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private HourlyType hourlyType;

	/**
	 * Instantiates a new inspection work day config.
	 */
	public InspectionWorkDayConfig()
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
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public Short getHour()
	{
		return hour;
	}

	/**
	 * Sets the hour.
	 *
	 * @param hour
	 *           the new hour
	 */
	public void setHour(final Short hour)
	{
		this.hour = hour;
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
	 * Gets the hourly type.
	 *
	 * @return the hourly type
	 */
	public HourlyType getHourlyType()
	{
		return hourlyType;
	}


	/**
	 * Sets the hourly type.
	 *
	 * @param hourlyType
	 *           the new hourly type
	 */
	public void setHourlyType(final HourlyType hourlyType)
	{
		this.hourlyType = hourlyType;
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
		if (!(object instanceof InspectionWorkDayConfig))
		{
			return false;
		}
		final InspectionWorkDayConfig other = (InspectionWorkDayConfig) object;
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
		builder.append("InspectionWorkDayConfig [id=");
		builder.append(id);
		builder.append(", hour=");
		builder.append(hour);
		builder.append("]");
		return builder.toString();
	}

}
