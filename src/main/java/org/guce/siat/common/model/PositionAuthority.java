package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.guce.siat.common.model.AbstractModel;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.utils.enums.PositionType;


/**
 * The Class PositionAuthority.
 */
@Entity
@Table(name = "POSITION_AUTHORITY")
@XmlRootElement
@AssociationOverrides(
{ @AssociationOverride(name = "primaryKey.authority", joinColumns = @JoinColumn(name = "AUTHORITY_ID")),
		@AssociationOverride(name = "primaryKey.positionType", joinColumns = @JoinColumn(name = "POSITION_TYPE")) })
public class PositionAuthority extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The primary key. */
	@EmbeddedId
	private PositionAuthorityId primaryKey = new PositionAuthorityId();


	/**
	 * Gets the primary key.
	 *
	 * @return the primaryKey
	 */
	public PositionAuthorityId getPrimaryKey()
	{
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey
	 *           the primaryKey to set
	 */
	public void setPrimaryKey(final PositionAuthorityId primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the authority.
	 *
	 * @return the authority
	 */
	public Authority getAuthority()
	{
		return primaryKey.getAuthority();
	}

	/**
	 * Sets the authority.
	 *
	 * @param authority
	 *           the authority to set
	 */
	public void setAuthority(final Authority authority)
	{
		primaryKey.setAuthority(authority);
	}

	/**
	 * Gets the position type.
	 *
	 * @return the positionType
	 */
	public PositionType getPositionType()
	{
		return primaryKey.getPositionType();
	}

	/**
	 * Sets the position type.
	 *
	 * @param positionType
	 *           the positionType to set
	 */
	public void setPositionType(final PositionType positionType)
	{
		primaryKey.setPositionType(positionType);
	}

}
