package org.guce.siat.common.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.guce.siat.common.utils.enums.BureauType;


/**
 * The Class Service.
 */
@javax.persistence.Entity
@Table(name = "SERVICE")
@XmlRootElement
public class Service extends Administration
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The abreviation. */
	@Column(name = "ABREVIATION")
	private String abreviation;

	/** The head service. */
	@Transient
	private User headService;

	/** The sub department. */
	@ManyToOne
	@JoinColumn(name = "SUB_DEPARTMENT_ID", referencedColumnName = "ID")
	private SubDepartment subDepartment;

	/** The entity list. */
	@OneToMany(mappedBy = "service")
	private List<Entity> entityList;

	/** The services items list. */
	@OneToMany(mappedBy = "service")
	private List<ServicesItem> servicesItemsList;

	/** The central bureau. */
	@Transient
	private Bureau centralBureau;

	/**
	 * Instantiates a new service.
	 */
	public Service()
	{
	}

	/**
	 * Gets the head service.
	 *
	 * @return the head service
	 */
	public User getHeadService()
	{
		return headService;
	}

	/**
	 * Sets the head service.
	 *
	 * @param headService
	 *           the new head service
	 */
	public void setHeadService(final User headService)
	{
		this.headService = headService;
	}

	/**
	 * Gets the sub department.
	 *
	 * @return the sub department
	 */
	public SubDepartment getSubDepartment()
	{
		return subDepartment;
	}

	/**
	 * Sets the sub department.
	 *
	 * @param subDepartment
	 *           the new sub department
	 */
	public void setSubDepartment(final SubDepartment subDepartment)
	{
		this.subDepartment = subDepartment;
	}

	/**
	 * Gets the entity list.
	 *
	 * @return the entity list
	 */
	public List<Entity> getEntityList()
	{
		return entityList;
	}

	/**
	 * Sets the entity list.
	 *
	 * @param entityList
	 *           the new entity list
	 */
	public void setEntityList(final List<Entity> entityList)
	{
		this.entityList = entityList;
	}

	/**
	 * Gets the services items list.
	 *
	 * @return the services items list
	 */
	public List<ServicesItem> getServicesItemsList()
	{
		return servicesItemsList;
	}

	/**
	 * Sets the services items list.
	 *
	 * @param servicesItemsList
	 *           the new services items list
	 */
	public void setServicesItemsList(final List<ServicesItem> servicesItemsList)
	{
		this.servicesItemsList = servicesItemsList;
	}

	/**
	 * Gets the central bureau.
	 *
	 * @return the central bureau
	 */
	public Bureau getCentralBureau()
	{
		if (centralBureau == null)
		{
			centralBureau = (Bureau) CollectionUtils.find(entityList, new Predicate()
			{
				@Override
				public boolean evaluate(final Object object)
				{
					return ((Entity) object) instanceof Bureau && BureauType.BUREAU_CENTRAL.equals(((Bureau) object).getBureauType());
				}
			});
		}
		return centralBureau;
	}

	/**
	 * Sets the central bureau.
	 *
	 * @param centralBureau
	 *           the new central bureau
	 */
	public void setCentralBureau(final Bureau centralBureau)
	{
		this.centralBureau = centralBureau;
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
		if (!(object instanceof Service))
		{
			return false;
		}
		final Service other = (Service) object;
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
	 * @see org.guce.siat.common.model.Administration#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("Service [abreviation=");
		builder.append(abreviation);
		builder.append(", headService=");
		builder.append(headService);
		builder.append(", subDepartment=");
		builder.append(subDepartment.getAbreviation());
		builder.append("]");
		return builder.toString();
	}


}
