/**
 *
 */
package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The Class AuditEntity.
 */
@Entity
@Table(name = "HISTORY")
public class AuditEntity implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORY_SEQ")
	@SequenceGenerator(name = "HISTORY_SEQ", sequenceName = "HISTORY_SEQ", allocationSize = 1)
	private Long id;

	/** The action. */
	private String action;

	/** The model. */
	private String model;

	/** The id model. */
	private Long idModel;

	/** The value. */
	@Column(length = 5000)
	private String value;

	/** The username. */
	private String username;

	/** The ip address. */
	private String ipAddress;

	/** The mac adress. */
	private String macAddress;


	/** The audit date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditDate;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *           the id to set
	 */
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action
	 *           the action to set
	 */
	public void setAction(final String action)
	{
		this.action = action;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *           the model to set
	 */
	public void setModel(final String model)
	{
		this.model = model;
	}

	/**
	 * Gets the id model.
	 *
	 * @return the idModel
	 */
	public Long getIdModel()
	{
		return idModel;
	}

	/**
	 * Sets the id model.
	 *
	 * @param idModel
	 *           the idModel to set
	 */
	public void setIdModel(final Long idModel)
	{
		this.idModel = idModel;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *           the value to set
	 */
	public void setValue(final String value)
	{
		this.value = value;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *           the username to set
	 */
	public void setUsername(final String username)
	{
		this.username = username;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ipAddress
	 */
	public String getIpAddress()
	{
		return ipAddress;
	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress
	 *           the ipAddress to set
	 */
	public void setIpAddress(final String ipAddress)
	{
		this.ipAddress = ipAddress;
	}


	/**
	 * Gets the mac address.
	 *
	 * @return the mac address
	 */
	public String getMacAddress()
	{
		return macAddress;
	}

	/**
	 * Sets the mac address.
	 *
	 * @param macAddress
	 *           the new mac address
	 */
	public void setMacAddress(final String macAddress)
	{
		this.macAddress = macAddress;
	}

	/**
	 * Gets the audit date.
	 *
	 * @return the audit date
	 */
	public Date getAuditDate()
	{
		return auditDate;
	}

	/**
	 * Sets the audit date.
	 *
	 * @param auditDate
	 *           the new audit date
	 */
	public void setAuditDate(final Date auditDate)
	{
		this.auditDate = auditDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object)
	{
		if (!(object instanceof AuditEntity))
		{
			return false;
		}
		final AuditEntity other = (AuditEntity) object;
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
		builder.append("AuditEntity [id=");
		builder.append(id);
		builder.append(", action=");
		builder.append(action);
		builder.append(", model=");
		builder.append(model);
		builder.append(", idModel=");
		builder.append(idModel);
		builder.append(", value=");
		builder.append(value);
		builder.append(", username=");
		builder.append(username);
		builder.append(", ipAddress=");
		builder.append(ipAddress);
		builder.append(", macAddress=");
		builder.append(macAddress);
		builder.append(", auditDate=");
		builder.append(auditDate);
		builder.append("]");
		return builder.toString();
	}


}
