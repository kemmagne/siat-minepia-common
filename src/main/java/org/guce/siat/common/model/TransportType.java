package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class TransportMode.
 */
@Entity
@Table(name = "REP_TYPES_MOYEN_TRANSPORT")
@XmlRootElement
public class TransportType implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mode transport code. */
	@Id
	@Basic(optional = false)
	@Column(name = "MEAN_TRANSPORT_CODE")
	private String typeMeanTransportCode;

	/** The transport mode. */
	@JoinColumn(name = "MODE_TRANSPORT_CODE", referencedColumnName = "MODE_TRANSPORT_CODE")
	@ManyToOne
	private TransportMode transportMode;

	/** The type mean transport parent code. */
	@Column(name = "MEAN_TRANSPORT_PARENT_CODE")
	private String typeMeanTransportParentCode;

	/** The type mean transport name. */
	@Column(name = "MEAN_TRANSPORT_NAME")
	private String typeMeanTransportName;

	/** The type mean transport name en. */
	@Column(name = "MEAN_TRANSPORT_NAME_EN")
	private String typeMeanTransportNameEn;

	/**
	 * Gets the type mean transport code.
	 *
	 * @return the typeMeanTransportCode
	 */
	public String getTypeMeanTransportCode()
	{
		return typeMeanTransportCode;
	}

	/**
	 * Sets the type mean transport code.
	 *
	 * @param typeMeanTransportCode
	 *           the typeMeanTransportCode to set
	 */
	public void setTypeMeanTransportCode(final String typeMeanTransportCode)
	{
		this.typeMeanTransportCode = typeMeanTransportCode;
	}

	/**
	 * Gets the transport mode.
	 *
	 * @return the transportMode
	 */
	public TransportMode getTransportMode()
	{
		return transportMode;
	}

	/**
	 * Sets the transport mode.
	 *
	 * @param transportMode
	 *           the transportMode to set
	 */
	public void setTransportMode(final TransportMode transportMode)
	{
		this.transportMode = transportMode;
	}

	/**
	 * Gets the type mean transport parent code.
	 *
	 * @return the typeMeanTransportParentCode
	 */
	public String getTypeMeanTransportParentCode()
	{
		return typeMeanTransportParentCode;
	}

	/**
	 * Sets the type mean transport parent code.
	 *
	 * @param typeMeanTransportParentCode
	 *           the typeMeanTransportParentCode to set
	 */
	public void setTypeMeanTransportParentCode(final String typeMeanTransportParentCode)
	{
		this.typeMeanTransportParentCode = typeMeanTransportParentCode;
	}

	/**
	 * @return the typeMeanTransportName
	 */
	public String getTypeMeanTransportName()
	{
		return typeMeanTransportName;
	}

	/**
	 * @param typeMeanTransportName
	 *           the typeMeanTransportName to set
	 */
	public void setTypeMeanTransportName(final String typeMeanTransportName)
	{
		this.typeMeanTransportName = typeMeanTransportName;
	}

	/**
	 * Gets the type mean transport name en.
	 *
	 * @return the typeMeanTransportNameEn
	 */
	public String getTypeMeanTransportNameEn()
	{
		return typeMeanTransportNameEn;
	}

	/**
	 * Sets the type mean transport name en.
	 *
	 * @param typeMeanTransportNameEn
	 *           the typeMeanTransportNameEn to set
	 */
	public void setTypeMeanTransportNameEn(final String typeMeanTransportNameEn)
	{
		this.typeMeanTransportNameEn = typeMeanTransportNameEn;
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
		hash += (typeMeanTransportCode != null ? typeMeanTransportCode.hashCode() : 0);
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
		if (!(object instanceof TransportType))
		{
			return false;
		}
		final TransportType other = (TransportType) object;
		if ((this.getTypeMeanTransportCode() == null && other.getTypeMeanTransportCode() != null)
				|| (this.getTypeMeanTransportCode() != null && !this.getTypeMeanTransportCode().equals(
						other.getTypeMeanTransportCode())))
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
		builder.append("TransportType [typeMeanTransportCode=");
		builder.append(typeMeanTransportCode);
		builder.append(", typeMeanTransportParentCode=");
		builder.append(typeMeanTransportParentCode);
		builder.append(", typeMeanTransportName=");
		builder.append(typeMeanTransportName);
		builder.append(", typeMeanTransportNameEn=");
		builder.append(typeMeanTransportNameEn);
		builder.append("]");
		return builder.toString();
	}


}
