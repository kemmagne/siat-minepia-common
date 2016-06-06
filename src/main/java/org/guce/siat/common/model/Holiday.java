package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Holiday.
 */
@Entity
@Table(name = "HOLIDAY")
@XmlRootElement
public class Holiday extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOLIDAY_SEQ")
	@SequenceGenerator(name = "HOLIDAY_SEQ", sequenceName = "HOLIDAY_SEQ", allocationSize = 1)
	private Long id;

	/** The holiday date. */
	@Column(name = "HOLIDAY_DATE", unique = true)
	@Temporal(TemporalType.DATE)
	private Date holidayDate;

	/** The label. */
	@Column(name = "LABEL")
	private String label;

	/** The deleted. */
	@Column(name = "DELETED")
	private Boolean deleted;

	/**
	 * Instantiates a new holiday.
	 */
	public Holiday()
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
	 * Gets the holiday date.
	 *
	 * @return the holiday date
	 */
	public Date getHolidayDate()
	{
		return holidayDate;
	}

	/**
	 * Sets the holiday date.
	 *
	 * @param holidayDate
	 *           the new holiday date
	 */
	public void setHolidayDate(final Date holidayDate)
	{
		this.holidayDate = holidayDate;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *           the new label
	 */
	public void setLabel(final String label)
	{
		this.label = label;
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
		if (!(object instanceof Holiday))
		{
			return false;
		}
		final Holiday other = (Holiday) object;
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
		builder.append("Holiday [id=");
		builder.append(id);
		builder.append(", holidayDate=");
		builder.append(holidayDate);
		builder.append(", label=");
		builder.append(label);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
