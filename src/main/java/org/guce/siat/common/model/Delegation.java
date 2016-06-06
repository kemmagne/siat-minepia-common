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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Delegation.
 */
@Entity
@Table(name = "DELEGATION", uniqueConstraints = @UniqueConstraint(columnNames =
{ "FROM_USER", "ENABLED" }))
@XmlRootElement
public class Delegation extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELEGATION_SEQ")
	@SequenceGenerator(name = "DELEGATION_SEQ", sequenceName = "DELEGATION_SEQ", allocationSize = 1)
	private Long id;

	/** The begin date. */
	@Basic(optional = false)
	@Column(name = "BEGIN_DATE")
	@Temporal(TemporalType.DATE)
	private Date beginDate;

	/** The end date. */
	@Basic(optional = false)
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	/** The enabled. */
	@Column(name = "ENABLED")
	private Boolean enabled;

	/** The deleted. */
	@Column(name = "DELETED")
	private Boolean deleted;

	/** The to user. */
	@JoinColumn(name = "TO_USER", referencedColumnName = "ID")
	@ManyToOne
	private User toUser;

	/** The from user. */
	@JoinColumn(name = "FROM_USER", referencedColumnName = "ID")
	@ManyToOne
	private User fromUser;

	/**
	 * Instantiates a new delegation.
	 */
	public Delegation()
	{
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id
	 *           the id to set
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate()
	{
		return beginDate;
	}

	/**
	 * @param beginDate
	 *           the beginDate to set
	 */
	public void setBeginDate(final Date beginDate)
	{
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 *           the endDate to set
	 */
	public void setEndDate(final Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled()
	{
		return enabled;
	}

	/**
	 * @param enabled
	 *           the enabled to set
	 */
	public void setEnabled(final Boolean enabled)
	{
		this.enabled = enabled;
	}

	/**
	 * @return the deleted
	 */
	public Boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * @param deleted
	 *           the deleted to set
	 */
	public void setDeleted(final Boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * @return the toUser
	 */
	public User getToUser()
	{
		return toUser;
	}

	/**
	 * @param toUser
	 *           the toUser to set
	 */
	public void setToUser(final User toUser)
	{
		this.toUser = toUser;
	}

	/**
	 * @return the fromUser
	 */
	public User getFromUser()
	{
		return fromUser;
	}

	/**
	 * @param fromUser
	 *           the fromUser to set
	 */
	public void setFromUser(final User fromUser)
	{
		this.fromUser = fromUser;
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
		if (!(object instanceof Delegation))
		{
			return false;
		}
		final Delegation other = (Delegation) object;
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
		builder.append("Delegation [id=");
		builder.append(id);
		builder.append(", beginDate=");
		builder.append(beginDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
