package org.guce.siat.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class SubDepartment.
 */
@Entity
@Table(name = "SUB_DEPARTMENT")
@XmlRootElement
public class SubDepartment extends Administration
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The abreviation. */
	@Column(name = "ABREVIATION")
	private String abreviation;

	/** The assistant director. */
	@Transient
	private User assistantDirector;

	/** The organism. */
	@ManyToOne
	@JoinColumn(name = "ORGANISM_ID", referencedColumnName = "ID")
	private Organism organism;


	/** The services list. */
	@OneToMany(mappedBy = "subDepartment", cascade = CascadeType.ALL)
	private List<Service> servicesList;

	/**
	 * Instantiates a new sub department.
	 */
	public SubDepartment()
	{
	}

	/**
	 * Gets the assistant director.
	 *
	 * @return the assistant director
	 */
	public User getAssistantDirector()
	{
		return assistantDirector;
	}

	/**
	 * Sets the assistant director.
	 *
	 * @param assistantDirector
	 *           the new assistant director
	 */
	public void setAssistantDirector(final User assistantDirector)
	{
		this.assistantDirector = assistantDirector;
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

	/**
	 * Gets the services list.
	 *
	 * @return the services list
	 */
	public List<Service> getServicesList()
	{
		return servicesList;
	}

	/**
	 * Sets the services list.
	 *
	 * @param servicesList
	 *           the new services list
	 */
	public void setServicesList(final List<Service> servicesList)
	{
		this.servicesList = servicesList;
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
		if (!(object instanceof SubDepartment))
		{
			return false;
		}
		final SubDepartment other = (SubDepartment) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
			return false;
		}
		return true;
	}

	/**
	 * Gets the abreviation.
	 *
	 * @return the abreviation
	 */
	public String getAbreviation()
	{
		return abreviation;
	}

	/**
	 * Sets the abreviation.
	 *
	 * @param abreviation
	 *           the new abreviation
	 */
	public void setAbreviation(final String abreviation)
	{
		this.abreviation = abreviation;
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
		builder.append("SubDepartment [id=");
		builder.append(id);
		builder.append(", labelFr=");
		builder.append(labelFr);
		builder.append(", labelEn=");
		builder.append(labelEn);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
