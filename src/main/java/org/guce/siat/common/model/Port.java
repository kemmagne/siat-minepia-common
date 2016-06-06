package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * The Class Port.
 */
@Entity
@Table(name = "REP_UNLOCODE")
@XmlRootElement
public class Port implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The location code. */
	@Id
	@Basic(optional = false)
	@Column(name = "LOCATION_CODE")
	private String locationCode;

	/** The country id alpha2. */
	@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID_ALPHA2")
	@ManyToOne
	private Country country;

	/** The location name. */
	@Column(name = "LOCATION_NAME")
	private String locationName;

	/** The location name en. */
	@Column(name = "LOCATION_NAME_EN")
	private String locationNameEn;

	/** The location function code. */
	@Column(name = "LOCATION_FUNCTION_CODE")
	private String locationFunctionCode;

	/** The subdivision code. */
	@Column(name = "SUBDIVISION_CODE")
	private String subdivisionCode;

	/** The location iata code. */
	@Column(name = "LOCATION_IATA_CODE")
	private String locationIataCode;

	/** The location coordinates. */
	@Column(name = "LOCATION_COORDINATES")
	private String locationCoordinates;

	/** The location date. */
	@Column(name = "LOCATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date locationDate;

	/** The location statut. */
	@Column(name = "LOCATION_STATUT")
	private String locationStatut;

	/**
	 * Gets the location code.
	 *
	 * @return the locationCode
	 */
	public String getLocationCode()
	{
		return locationCode;
	}

	/**
	 * Sets the location code.
	 *
	 * @param locationCode
	 *           the locationCode to set
	 */
	public void setLocationCode(final String locationCode)
	{
		this.locationCode = locationCode;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *           the country to set
	 */
	public void setCountry(final Country country)
	{
		this.country = country;
	}

	/**
	 * Gets the location name.
	 *
	 * @return the locationName
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * Sets the location name.
	 *
	 * @param locationName
	 *           the locationName to set
	 */
	public void setLocationName(final String locationName)
	{
		this.locationName = locationName;
	}

	/**
	 * Gets the location name en.
	 *
	 * @return the locationNameEn
	 */
	public String getLocationNameEn()
	{
		return locationNameEn;
	}

	/**
	 * Sets the location name en.
	 *
	 * @param locationNameEn
	 *           the locationNameEn to set
	 */
	public void setLocationNameEn(final String locationNameEn)
	{
		this.locationNameEn = locationNameEn;
	}

	/**
	 * Gets the location function code.
	 *
	 * @return the locationFunctionCode
	 */
	public String getLocationFunctionCode()
	{
		return locationFunctionCode;
	}

	/**
	 * Sets the location function code.
	 *
	 * @param locationFunctionCode
	 *           the locationFunctionCode to set
	 */
	public void setLocationFunctionCode(final String locationFunctionCode)
	{
		this.locationFunctionCode = locationFunctionCode;
	}

	/**
	 * Gets the subdivision code.
	 *
	 * @return the subdivisionCode
	 */
	public String getSubdivisionCode()
	{
		return subdivisionCode;
	}

	/**
	 * Sets the subdivision code.
	 *
	 * @param subdivisionCode
	 *           the subdivisionCode to set
	 */
	public void setSubdivisionCode(final String subdivisionCode)
	{
		this.subdivisionCode = subdivisionCode;
	}

	/**
	 * Gets the location iata code.
	 *
	 * @return the locationIataCode
	 */
	public String getLocationIataCode()
	{
		return locationIataCode;
	}

	/**
	 * Sets the location iata code.
	 *
	 * @param locationIataCode
	 *           the locationIataCode to set
	 */
	public void setLocationIataCode(final String locationIataCode)
	{
		this.locationIataCode = locationIataCode;
	}

	/**
	 * Gets the location coordinates.
	 *
	 * @return the locationCoordinates
	 */
	public String getLocationCoordinates()
	{
		return locationCoordinates;
	}

	/**
	 * Sets the location coordinates.
	 *
	 * @param locationCoordinates
	 *           the locationCoordinates to set
	 */
	public void setLocationCoordinates(final String locationCoordinates)
	{
		this.locationCoordinates = locationCoordinates;
	}



	/**
	 * Gets the location date.
	 *
	 * @return the locationDate
	 */
	public Date getLocationDate()
	{
		return locationDate;
	}

	/**
	 * Sets the location date.
	 *
	 * @param locationDate
	 *           the locationDate to set
	 */
	public void setLocationDate(final Date locationDate)
	{
		this.locationDate = locationDate;
	}

	/**
	 * Gets the location statut.
	 *
	 * @return the locationStatut
	 */
	public String getLocationStatut()
	{
		return locationStatut;
	}

	/**
	 * Sets the location statut.
	 *
	 * @param locationStatut
	 *           the locationStatut to set
	 */
	public void setLocationStatut(final String locationStatut)
	{
		this.locationStatut = locationStatut;
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
		hash += (locationCode != null ? locationCode.hashCode() : 0);
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
		if (!(object instanceof Port))
		{
			return false;
		}
		final Port other = (Port) object;
		if ((this.getLocationCode() == null && other.getLocationCode() != null)
				|| (this.getLocationCode() != null && !this.getLocationCode().equals(other.getLocationCode())))
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
		builder.append("Port [locationCode=");
		builder.append(locationCode);
		builder.append(", locationName=");
		builder.append(locationName);
		builder.append(", locationNameEn=");
		builder.append(locationNameEn);
		builder.append(", locationFunctionCode=");
		builder.append(locationFunctionCode);
		builder.append(", subdivisionCode=");
		builder.append(subdivisionCode);
		builder.append(", locationIataCode=");
		builder.append(locationIataCode);
		builder.append(", locationCoordinates=");
		builder.append(locationCoordinates);
		builder.append(", locationDate=");
		builder.append(locationDate);
		builder.append(", locationStatut=");
		builder.append(locationStatut);
		builder.append("]");
		return builder.toString();
	}

}
