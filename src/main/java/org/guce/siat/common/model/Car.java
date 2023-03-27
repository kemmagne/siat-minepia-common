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
 * The Class Car.
 */
@Entity
@Table(name = "CAR")
@XmlRootElement
public class Car extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ")
	@SequenceGenerator(name = "CAR_SEQ", sequenceName = "CAR_SEQ", allocationSize = 1)
	private Long id;

	/** The serial number. */
	@Column(unique = true, name = "SERIAL_NUMBER")
	private String serialNumber;

	/** The model. */
	@Column(name = "MODEL")
	private String model;

	/** The enabled. */
	@Column(name = "ENABLED")
	private Boolean enabled;

	/** The deleted. */
	@Column(name = "DELETED")
	private Boolean deleted;

	/** The organism. */
	@JoinColumn(name = "ORGANISM_ID", referencedColumnName = "ID")
	@ManyToOne
	private Organism organism;

	/**
	 * Instantiates a new car.
	 */
	public Car()
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
	 *           the new id
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the serial number.
	 *
	 * @return the serial number
	 */
	public String getSerialNumber()
	{
		return serialNumber;
	}

	/**
	 * Sets the serial number.
	 *
	 * @param serialNumber
	 *           the new serial number
	 */
	public void setSerialNumber(final String serialNumber)
	{
		this.serialNumber = serialNumber;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *           the new model
	 */
	public void setModel(final String model)
	{
		this.model = model;
	}

	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public Boolean getEnabled()
	{
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *           the new enabled
	 */
	public void setEnabled(final Boolean enabled)
	{
		this.enabled = enabled;
	}

	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted
	 *           the new deleted
	 */
	public void setDeleted(final Boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * Gets the organism.
	 *
	 * @return the organism
	 */
	public Organism getOrganism()
	{
		return organism;
	}

	/**
	 * Sets the organism.
	 *
	 * @param organism
	 *           the new organism
	 */
	public void setOrganism(final Organism organism)
	{
		this.organism = organism;
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
		if (!(object instanceof Car))
		{
			return false;
		}
		final Car other = (Car) object;
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
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", serialNumber=");
		builder.append(serialNumber);
		builder.append(", model=");
		builder.append(model);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
