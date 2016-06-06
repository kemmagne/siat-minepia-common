package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 */
@Embeddable
public class RecommandationAuthorityId implements Serializable
{

	private static final long serialVersionUID = 8836011199107093252L;

	/** The recommandation. */
	@ManyToOne()
	@JoinColumn(name = "RECOMMANDATION_ID", referencedColumnName = "ID")
	private Recommandation recommandation;

	/** The authority. */
	@ManyToOne
	@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")
	private Authority authority;

	/**
	 * Gets the recommandation.
	 *
	 * @return the recommandation
	 */
	public Recommandation getRecommandation()
	{
		return recommandation;
	}

	/**
	 * Sets the recommandation.
	 *
	 * @param recommandation
	 *           the recommandation to set
	 */
	public void setRecommandation(final Recommandation recommandation)
	{
		this.recommandation = recommandation;
	}

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
				+ ((recommandation == null) ? 0 : recommandation.hashCode());
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
		if (!(obj instanceof RecommandationAuthorityId)) {
			return false;
		}
		RecommandationAuthorityId other = (RecommandationAuthorityId) obj;
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		if (recommandation == null) {
			if (other.recommandation != null) {
				return false;
			}
		} else if (!recommandation.equals(other.recommandation)) {
			return false;
		}
		return true;
	}
	
	

}
