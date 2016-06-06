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
 * The Class AnalyseType.
 */
@Entity
@Table(name = "ANALYSE_TYPE")
@XmlRootElement
public class AnalyseType extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5104029278317675867L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANALYSE_TYPE_SEQ")
	@SequenceGenerator(name = "ANALYSE_TYPE_SEQ", sequenceName = "ANALYSE_TYPE_SEQ", allocationSize = 1)
	private Long id;

	/** The label fr. */
	@Column(name = "LABEL_FR")
	private String labelFr;

	/** The label en. */
	@Column(name = "LABEL_EN")
	private String labelEn;

	/** The duration. */
	@Column(name = "DURATION")
	private Short duration;

	/** The deleted. */
	@Column(name = "DELETED")
	private Boolean deleted;

	/** The organism. */
	@JoinColumn(name = "ORGANISM_ID", referencedColumnName = "ID")
	@ManyToOne
	private Organism organism;

	/**
	 * Instantiates a new analyse type.
	 */
	public AnalyseType()
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
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public Short getDuration()
	{
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration
	 *           the new duration
	 */
	public void setDuration(final Short duration)
	{
		this.duration = duration;
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
		if (!(object instanceof AnalyseType))
		{
			return false;
		}
		final AnalyseType other = (AnalyseType) object;
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
		builder.append("AnalyseType [id=");
		builder.append(id);
		builder.append(", labelFr=");
		builder.append(labelFr);
		builder.append(", labelEn=");
		builder.append(labelEn);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", deleted=");
		builder.append(deleted);
		builder.append("]");
		return builder.toString();
	}


}
