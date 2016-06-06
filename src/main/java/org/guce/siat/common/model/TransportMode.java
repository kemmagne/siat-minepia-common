package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class TransportMode.
 */
@Entity
@Table(name = "REP_MODE_TRANSPORT")
@XmlRootElement
public class TransportMode implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mode transport code. */
	@Id
	@Basic(optional = false)
	@Column(name = "MODE_TRANSPORT_CODE")
	private Long modeTransportCode;

	/** The mode transport name. */
	@Column(name = "MODE_TRANSPORT_NAME")
	private String modeTransportName;

	/** The mode transport name en. */
	@Column(name = "MODE_TRANSPORT_NAME_EN")
	private String modeTransportNameEn;

	/**
	 * Gets the mode transport code.
	 *
	 * @return the modeTransportCode
	 */
	public Long getModeTransportCode()
	{
		return modeTransportCode;
	}

	/**
	 * Sets the mode transport code.
	 *
	 * @param modeTransportCode
	 *           the modeTransportCode to set
	 */
	public void setModeTransportCode(final Long modeTransportCode)
	{
		this.modeTransportCode = modeTransportCode;
	}

	/**
	 * Gets the mode transport name.
	 *
	 * @return the modeTransportName
	 */
	public String getModeTransportName()
	{
		return modeTransportName;
	}

	/**
	 * Sets the mode transport name.
	 *
	 * @param modeTransportName
	 *           the modeTransportName to set
	 */
	public void setModeTransportName(final String modeTransportName)
	{
		this.modeTransportName = modeTransportName;
	}

	/**
	 * Gets the mode transport name en.
	 *
	 * @return the modeTransportNameEn
	 */
	public String getModeTransportNameEn()
	{
		return modeTransportNameEn;
	}

	/**
	 * Sets the mode transport name en.
	 *
	 * @param modeTransportNameEn
	 *           the modeTransportNameEn to set
	 */
	public void setModeTransportNameEn(final String modeTransportNameEn)
	{
		this.modeTransportNameEn = modeTransportNameEn;
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
		hash += (modeTransportCode != null ? modeTransportCode.hashCode() : 0);
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
		if (!(object instanceof TransportMode))
		{
			return false;
		}
		final TransportMode other = (TransportMode) object;
		if ((this.getModeTransportCode() == null && other.getModeTransportCode() != null)
				|| (this.getModeTransportCode() != null && !this.getModeTransportCode().equals(other.getModeTransportCode())))
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
		builder.append("TransportMode [modeTransportCode=");
		builder.append(modeTransportCode);
		builder.append(", modeTransportName=");
		builder.append(modeTransportName);
		builder.append(", modeTransportNameEn=");
		builder.append(modeTransportNameEn);
		builder.append("]");
		return builder.toString();
	}



}
