package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The Class HourlyType.
 */
@Entity
@Table(name = "HOURLY_TYPE")
@XmlRootElement
public class HourlyType extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOURLY_TYPE_SEQ")
	@SequenceGenerator(name = "HOURLY_TYPE_SEQ", sequenceName = "HOURLY_TYPE_SEQ", allocationSize = 1)
	private Long id;

	/** The label fr. */
	@Column(name = "LABEL_FR")
	private String labelFr;

	/** The label en. */
	@Column(name = "LABEL_EN")
	private String labelEn;

	/** The work year config list. */
	@OneToMany(mappedBy = "hourlyType", cascade = CascadeType.REFRESH)
	private List<WorkYearConfig> workYearConfigList;

	/**
	 * Instantiates a new hourly type.
	 */
	public HourlyType()
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
	 * Gets the label fr.
	 *
	 * @return the label fr
	 */
	public String getLabelFr()
	{
		return labelFr;
	}

	/**
	 * Sets the label fr.
	 *
	 * @param labelFr
	 *           the new label fr
	 */
	public void setLabelFr(final String labelFr)
	{
		this.labelFr = labelFr;
	}

	/**
	 * Gets the label en.
	 *
	 * @return the label en
	 */
	public String getLabelEn()
	{
		return labelEn;
	}

	/**
	 * Sets the label en.
	 *
	 * @param labelEn
	 *           the new label en
	 */
	public void setLabelEn(final String labelEn)
	{
		this.labelEn = labelEn;
	}

	/**
	 * Gets the work year config list.
	 *
	 * @return the work year config list
	 */
	@XmlTransient
	public List<WorkYearConfig> getWorkYearConfigList()
	{
		return workYearConfigList;
	}

	/**
	 * Sets the work year config list.
	 *
	 * @param workYearConfigList
	 *           the new work year config list
	 */
	public void setWorkYearConfigList(final List<WorkYearConfig> workYearConfigList)
	{
		this.workYearConfigList = workYearConfigList;
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
		if (!(object instanceof HourlyType))
		{
			return false;
		}
		final HourlyType other = (HourlyType) object;
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
		builder.append("HourlyType [id=");
		builder.append(id);
		builder.append(", labelFr=");
		builder.append(labelFr);
		builder.append(", labelEn=");
		builder.append(labelEn);
		builder.append("]");
		return builder.toString();
	}

}
