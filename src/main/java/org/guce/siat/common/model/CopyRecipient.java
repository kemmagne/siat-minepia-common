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
 * The Class CopyRecipient.
 */
@Entity
@Table(name = "COPY_RECIPIENT")
@XmlRootElement
public class CopyRecipient extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COPY_RECIPIENT_SEQ")
	@SequenceGenerator(name = "COPY_RECIPIENT_SEQ", sequenceName = "COPY_RECIPIENT_SEQ", allocationSize = 1)
	private Long id;

	/** The flow. */
	@JoinColumn(name = "FLOW_ID", referencedColumnName = "ID")
	@ManyToOne
	private Flow flow;

	/** The to authority. */
	@JoinColumn(name = "TO_AUTHORITY_ID", referencedColumnName = "ID")
	@ManyToOne
	private Authority toAuthority;

	/**
	 * Instantiates a new copy recipient.
	 */
	public CopyRecipient()
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
	 *           the id to set
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the flow.
	 *
	 * @return the flow
	 */
	public Flow getFlow()
	{
		return flow;
	}

	/**
	 * Sets the flow.
	 *
	 * @param flow
	 *           the flow to set
	 */
	public void setFlow(final Flow flow)
	{
		this.flow = flow;
	}

	/**
	 * Gets the to authority.
	 *
	 * @return the toAuthority
	 */
	public Authority getToAuthority()
	{
		return toAuthority;
	}

	/**
	 * Sets the to authority.
	 *
	 * @param toAuthority
	 *           the toAuthority to set
	 */
	public void setToAuthority(final Authority toAuthority)
	{
		this.toAuthority = toAuthority;
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
		if (!(object instanceof CopyRecipient))
		{
			return false;
		}
		final CopyRecipient other = (CopyRecipient) object;
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
		builder.append("CopyRecipient [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}
