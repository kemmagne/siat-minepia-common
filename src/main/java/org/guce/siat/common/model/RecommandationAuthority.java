package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class RecommandationAuthority
 */
@Entity
@Table(name = "Recommandation_Authority")
@XmlRootElement
@AssociationOverrides(
{ @AssociationOverride(name = "primaryKey.recommandation", joinColumns = @JoinColumn(name = "RECOMMANDATION_ID")),
		@AssociationOverride(name = "primaryKey.authority", joinColumns = @JoinColumn(name = "AUTHORITY_ID")) })
public class RecommandationAuthority extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1661540340062149431L;

	/** The primary key. */
	@EmbeddedId
	private RecommandationAuthorityId primaryKey = new RecommandationAuthorityId();

	/**
	 * Instantiates a new recommandation authority.
	 */
	public RecommandationAuthority()
	{
	}

	/**
	 * Instantiates a new recommandation authority.
	 *
	 * @param primaryKey
	 *           the primary key
	 */
	public RecommandationAuthority(final RecommandationAuthorityId primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the primary key.
	 *
	 * @return the primary key
	 */
	public RecommandationAuthorityId getPrimaryKey()
	{
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey
	 *           the new primary key
	 */
	public void setPrimaryKey(final RecommandationAuthorityId primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the recommandation.
	 *
	 * @return the recommandation
	 */
	@Transient
	public Recommandation getRecommandation()
	{
		return primaryKey.getRecommandation();
	}

	/**
	 * Gets the authority.
	 *
	 * @return the authority
	 */
	@Transient
	public Authority getAuthority()
	{
		return primaryKey.getAuthority();
	}

	/**
	 * Sets the recommandation.
	 *
	 * @param recommandation
	 *           the new recommandation
	 */
	public void setRecommandation(final Recommandation recommandation)
	{
		primaryKey.setRecommandation(recommandation);
	}

	/**
	 * Sets the authority.
	 *
	 * @param authority
	 *           the new authority
	 */
	public void setAuthority(final Authority authority)
	{
		primaryKey.setAuthority(authority);
	}

}
