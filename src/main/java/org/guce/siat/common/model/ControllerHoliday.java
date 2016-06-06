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
 * The Class ControllerHoliday.
 */
@Entity
@Table(name = "CONTROLLER_HOLIDAY")
@XmlRootElement
public class ControllerHoliday extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLLER_HOLIDAY_SEQ")
	@SequenceGenerator(name = "CONTROLLER_HOLIDAY_SEQ", sequenceName = "CONTROLLER_HOLIDAY_SEQ", allocationSize = 1)
	private Long id;

	/** The from date. */
	@Column(name = "FROM_DATE")
	@Temporal(TemporalType.DATE)
	private Date fromDate;

	/** The to date. */
	@Column(name = "TO_DATE")
	@Temporal(TemporalType.DATE)
	private Date toDate;

	/** The enabled. */
	@Column(name = "ENABLED")
	private Boolean enabled;

	/** The deleted. */
	@Column(name = "DELETED")
	private Boolean deleted;

	/** The user. */
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	@ManyToOne
	private User user;

	/**
	 * Instantiates a new inspector holiday.
	 */
	public ControllerHoliday()
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
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public Date getFromDate()
	{
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate
	 *           the new from date
	 */
	public void setFromDate(final Date fromDate)
	{
		this.fromDate = fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public Date getToDate()
	{
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate
	 *           the new to date
	 */
	public void setToDate(final Date toDate)
	{
		this.toDate = toDate;
	}

	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public Boolean getEnabled()
	{
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *           the new enabled
	 */
	public void setEnabled(final Boolean enabled)
	{
		this.enabled = enabled;
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
	 *           the new deleted
	 */
	public void setDeleted(final Boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *           the user to set
	 */
	public void setUser(final User user)
	{
		this.user = user;
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
		if (!(object instanceof ControllerHoliday))
		{
			return false;
		}
		final ControllerHoliday other = (ControllerHoliday) object;
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
		builder.append("ControllerHoliday [id=");
		builder.append(id);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
