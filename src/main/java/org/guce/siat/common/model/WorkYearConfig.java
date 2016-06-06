package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class WorkYearConfig.
 */
@Entity
@Table(name = "WORK_YEAR_CONFIG")
@XmlRootElement
public class WorkYearConfig extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_YEAR_CONFIG_SEQ")
	@SequenceGenerator(name = "WORK_YEAR_CONFIG_SEQ", sequenceName = "WORK_YEAR_CONFIG_SEQ", allocationSize = 1)
	private Long id;

	/** The begin date. */
	@Column(name = "BEGIN_DATE")
	@Temporal(TemporalType.DATE)
	private Date beginDate;

	/** The end date. */
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	/** The hourly type. */
	@JoinColumn(name = "HOURLY_TYPE_ID", referencedColumnName = "ID")
	@ManyToOne
	private HourlyType hourlyType;

	/**
	 * Instantiates a new work year config.
	 */
	public WorkYearConfig()
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
	 * Gets the begin date.
	 *
	 * @return the begin date
	 */
	public Date getBeginDate()
	{
		return beginDate;
	}

	/**
	 * Sets the begin date.
	 *
	 * @param beginDate
	 *           the new begin date
	 */
	public void setBeginDate(final Date beginDate)
	{
		this.beginDate = beginDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *           the new end date
	 */
	public void setEndDate(final Date endDate)
	{
		this.endDate = endDate;
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
		if (!(object instanceof WorkYearConfig))
		{
			return false;
		}
		final WorkYearConfig other = (WorkYearConfig) object;
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
		builder.append("WorkYearConfig [id=");
		builder.append(id);
		builder.append(", beginDate=");
		builder.append(beginDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append("]");
		return builder.toString();
	}

}
