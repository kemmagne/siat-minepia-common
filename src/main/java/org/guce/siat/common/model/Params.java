package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.guce.siat.common.utils.enums.ParamsCategory;


/**
 * The Class Params.
 */
@Entity
@Table(name = "PARAMS")
@XmlRootElement
public class Params extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;

	/** The name. */
	@Column(name = "NAME")
	private String name;

	/** The value. */
	@Column(name = "VALUE")
	private String value;

	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY")
	private ParamsCategory paramsCategory;

	/** The params organism list. */
	@OneToMany(mappedBy = "param")
	private List<ParamsOrganism> paramsOrganismList;

	/**
	 * Instantiates a new params.
	 */
	public Params()
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
	 *           the id to set
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *           the new name
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *           the new value
	 */
	public void setValue(final String value)
	{
		this.value = value;
	}

	/**
	 * Gets the params organism list.
	 *
	 * @return the params organism list
	 */
	@XmlTransient
	public List<ParamsOrganism> getParamsOrganismList()
	{
		return paramsOrganismList;
	}

	/**
	 * Sets the params organism list.
	 *
	 * @param paramsOrganismList
	 *           the new params organism list
	 */
	public void setParamsOrganismList(final List<ParamsOrganism> paramsOrganismList)
	{
		this.paramsOrganismList = paramsOrganismList;
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
		if (!(object instanceof Params))
		{
			return false;
		}
		final Params other = (Params) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the paramsCategory
	 */
	public ParamsCategory getParamsCategory()
	{
		return paramsCategory;
	}

	/**
	 * @param paramsCategory
	 *           the paramsCategory to set
	 */
	public void setParamsCategory(final ParamsCategory paramsCategory)
	{
		this.paramsCategory = paramsCategory;
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
		builder.append("Params [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", value=");
		builder.append(value);
		builder.append(", paramsCategory=");
		builder.append(paramsCategory.getCode());
		builder.append(", paramsOrganismList=");
		builder.append(paramsOrganismList);
		builder.append("]");
		return builder.toString();
	}
}
