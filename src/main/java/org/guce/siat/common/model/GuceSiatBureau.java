package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Bureau.
 */
@javax.persistence.Entity
@Table(name = "BUREAU_GUCE_SIAT")
@XmlRootElement
public class GuceSiatBureau extends AbstractModel implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The bureau guce code. */
	@Column(name = "BUREAU_GUCE")
	@Id
	private String bureauGuceCode;

	/** The siat bureau. */
	@Column(name = "BUREAU_SIAT")
	private String siatBureau;



	/**
	 * Instantiates a new bureau.
	 */
	public GuceSiatBureau()
	{
	}

	/**
	 * Gets the siat bureau.
	 *
	 * @return the siat bureau
	 */
	public String getSiatBureau()
	{
		return siatBureau;
	}

	/**
	 * Sets the siat bureau.
	 *
	 * @param siatBureau
	 *           the new siat bureau
	 */
	public void setSiatBureau(final String siatBureau)
	{
		this.siatBureau = siatBureau;
	}

	/**
	 * Gets the bureau guce code.
	 *
	 * @return the bureau guce code
	 */
	public String getBureauGuceCode()
	{
		return bureauGuceCode;
	}

	/**
	 * Sets the bureau guce code.
	 *
	 * @param bureauGuceCode
	 *           the new bureau guce code
	 */
	public void setBureauGuceCode(final String bureauGuceCode)
	{
		this.bureauGuceCode = bureauGuceCode;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.Entity#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bureauGuceCode == null) ? 0 : bureauGuceCode.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (!(obj instanceof GuceSiatBureau))
		{
			return false;
		}
		final GuceSiatBureau other = (GuceSiatBureau) obj;
		if (bureauGuceCode == null)
		{
			if (other.getBureauGuceCode() != null)
			{
				return false;
			}
		}
		else if (!bureauGuceCode.equals(other.getBureauGuceCode()))
		{
			return false;
		}
		return true;
	}

}
