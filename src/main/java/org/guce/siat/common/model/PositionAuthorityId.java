package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.utils.enums.PositionType;


/**
 * The Class PositionAuthorityId.
 */
@Embeddable
public class PositionAuthorityId implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The authority. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "id")
	private Authority authority;

	/** The position type. */
	@Enumerated(EnumType.STRING)
	@Column(name = "POSITION_TYPE")
	private PositionType positionType;

	/**
	 * Gets the authority.
	 *
	 * @return the authority
	 */
	public Authority getAuthority()
	{
		return authority;
	}

	/**
	 * Sets the authority.
	 *
	 * @param authority
	 *           the authority to set
	 */
	public void setAuthority(final Authority authority)
	{
		this.authority = authority;
	}

	/**
	 * Gets the position type.
	 *
	 * @return the positionType
	 */
	public PositionType getPositionType()
	{
		return positionType;
	}

	/**
	 * Sets the position type.
	 *
	 * @param positionType
	 *           the positionType to set
	 */
	public void setPositionType(final PositionType positionType)
	{
		this.positionType = positionType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result
				+ ((positionType == null) ? 0 : positionType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PositionAuthorityId)) {
			return false;
		}
		PositionAuthorityId other = (PositionAuthorityId) obj;
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		if (positionType != other.positionType) {
			return false;
		}
		return true;
	}


}
