package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;


/**
 * The Class UserAuthority.
 */
@javax.persistence.Entity
@Table(name = "USER_AUTHORITY")
@XmlRootElement
public class UserAuthority extends AbstractModel implements GrantedAuthority, Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "USER_AUTHORITY_SEQ", sequenceName = "USER_AUTHORITY_SEQ", allocationSize = 1, initialValue = 500)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_AUTHORITY_SEQ")
	private Long id;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;


	/** The authority granted. */
	@ManyToOne
	@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")
	private Authority authorityGranted;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority()
	{
		return authorityGranted.getRole();
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
	 *           the new user
	 */
	public void setUser(final User user)
	{
		this.user = user;
	}

	/**
	 * Gets the authority granted.
	 *
	 * @return the authorityGranted
	 */
	public Authority getAuthorityGranted()
	{
		return authorityGranted;
	}

	/**
	 * Sets the authority granted.
	 *
	 * @param authorityGranted
	 *           the authorityGranted to set
	 */
	public void setAuthorityGranted(final Authority authorityGranted)
	{
		this.authorityGranted = authorityGranted;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return authorityGranted.getRole() == null ? StringUtils.EMPTY.hashCode() : this.authorityGranted.getRole().hashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof String)
		{
			return obj.equals(this.getAuthorityGranted().getRole());
		}

		if (obj instanceof GrantedAuthority)
		{
			final GrantedAuthority attr = (GrantedAuthority) obj;

			return this.getAuthorityGranted().getRole().equals(attr.getAuthority());
		}

		return false;
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
		builder.append("UserAuthority [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user.getFirstName() + user.getLastName());
		builder.append(", role=");
		builder.append(authorityGranted.getRole());
		builder.append(", labelFr=");
		builder.append(authorityGranted.getLabelFr());
		builder.append(", labelEn=");
		builder.append(authorityGranted.getLabelEn());
		builder.append(", authorityType=");
		builder.append(authorityGranted.getAuthorityType());
		builder.append("]");
		return builder.toString();
	}

}
