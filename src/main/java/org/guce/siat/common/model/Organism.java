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
 * The Class Organism.
 */
@Entity
@Table(name = "ORGANISM")
@XmlRootElement
public class Organism extends Administration
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The abreviation. */
	@Column(name = "ABREVIATION")
	private String abreviation;

	/** The admin organism. */
	@Transient
	private User adminOrganism;

	/** The manager. */
	@Transient
	private User manager;

	/** The ministry. */
	@ManyToOne
	@JoinColumn(name = "MINISTRY_ID", referencedColumnName = "ID")
	private Ministry ministry;

	/** The inspection work week config list. */
	@OneToMany(mappedBy = "organism", cascade = CascadeType.ALL)
	private List<InspectionWorkWeekConfig> inspectionWorkWeekConfigList;

	/** The sub departments list. */
	@OneToMany(mappedBy = "organism", cascade = CascadeType.ALL)
	private List<SubDepartment> subDepartmentsList;


	/**
	 * Instantiates a new organism.
	 */
	public Organism()
	{
	}

	/**
	 * Instantiates a new organism.
	 *
	 * @param id
	 *           the id
	 */
	public Organism(final Long id)
	{
		this.id = id;
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
	 *           the abreviation to set
	 */
	public void setAbreviation(final String abreviation)
	{
		this.abreviation = abreviation;
	}

	/**
	 * Gets the ministry.
	 *
	 * @return the ministry
	 */
	public Ministry getMinistry()
	{
		return ministry;
	}

	/**
	 * Sets the ministry.
	 *
	 * @param ministry
	 *           the ministry to set
	 */
	public void setMinistry(final Ministry ministry)
	{
		this.ministry = ministry;
	}

	/**
	 * Gets the admin organism.
	 *
	 * @return the adminOrganism
	 */
	public User getAdminOrganism()
	{
		return adminOrganism;
	}

	/**
	 * Sets the admin organism.
	 *
	 * @param adminOrganism
	 *           the adminOrganism to set
	 */
	public void setAdminOrganism(final User adminOrganism)
	{
		this.adminOrganism = adminOrganism;
	}

	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	public User getManager()
	{
		return manager;
	}

	/**
	 * Sets the manager.
	 *
	 * @param manager
	 *           the manager to set
	 */
	public void setManager(final User manager)
	{
		this.manager = manager;
	}

	/**
	 * Gets the inspection work week config list.
	 *
	 * @return the inspectionWorkWeekConfigList
	 */
	public List<InspectionWorkWeekConfig> getInspectionWorkWeekConfigList()
	{
		return inspectionWorkWeekConfigList;
	}

	/**
	 * Sets the inspection work week config list.
	 *
	 * @param inspectionWorkWeekConfigList
	 *           the inspectionWorkWeekConfigList to set
	 */
	public void setInspectionWorkWeekConfigList(final List<InspectionWorkWeekConfig> inspectionWorkWeekConfigList)
	{
		this.inspectionWorkWeekConfigList = inspectionWorkWeekConfigList;
	}

	/**
	 * Gets the sub departments list.
	 *
	 * @return the subDepartmentsList
	 */
	public List<SubDepartment> getSubDepartmentsList()
	{
		return subDepartmentsList;
	}

	/**
	 * Sets the sub departments list.
	 *
	 * @param subDepartmentsList
	 *           the subDepartmentsList to set
	 */
	public void setSubDepartmentsList(final List<SubDepartment> subDepartmentsList)
	{
		this.subDepartmentsList = subDepartmentsList;
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
		if (!(object instanceof Organism))
		{
			return false;
		}
		final Organism other = (Organism) object;
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
		builder.append("Organism [id=");
		builder.append(id);
		builder.append(", labelFr=");
		builder.append(labelFr);
		builder.append(", labelEn=");
		builder.append(labelEn);
		builder.append(", abreviation=");
		builder.append(abreviation);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}

}
