package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Bureau.
 */
@javax.persistence.Entity
@Table(name = "FLOW_GUCE_SIAT")
@XmlRootElement
public class FlowGuceSiat extends AbstractModel implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "FLOW_GUCE_SIAT_SEQ", sequenceName = "FLOW_GUCE_SIAT_SEQ", allocationSize = 1, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLOW_GUCE_SIAT_SEQ")
	private Long id;

	/** The label fr. */
	@Basic(optional = false)
	@Column(name = "FLOW_GUCE")
	protected String flowGuce;

	/** The flow siat. */
	@Basic(optional = true)
	@Column(name = "FLOW_SIAT")
	protected String flowSiat;

	/** The file type. */
	@ManyToOne
	@JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID")
	private FileType fileType;

	/**
	 * Instantiates a new flow guce siat.
	 */
	public FlowGuceSiat()
	{
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.model.AbstractModel#getId()
	 */
	@Override
	public Long getId()
	{
		return id;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}


	/**
	 * Gets the flow guce.
	 *
	 * @return the flow guce
	 */
	public String getFlowGuce()
	{
		return flowGuce;
	}


	/**
	 * Sets the flow guce.
	 *
	 * @param flowGuce
	 *           the new flow guce
	 */
	public void setFlowGuce(final String flowGuce)
	{
		this.flowGuce = flowGuce;
	}


	/**
	 * Gets the flow siat.
	 *
	 * @return the flow siat
	 */
	public String getFlowSiat()
	{
		return flowSiat;
	}


	/**
	 * Sets the flow siat.
	 *
	 * @param flowSiat
	 *           the new flow siat
	 */
	public void setFlowSiat(final String flowSiat)
	{
		this.flowSiat = flowSiat;
	}


	/**
	 * Gets the file type.
	 *
	 * @return the fileType
	 */
	public FileType getFileType()
	{
		return fileType;
	}

	/**
	 * Sets the file type.
	 *
	 * @param fileType
	 *           the fileType to set
	 */
	public void setFileType(final FileType fileType)
	{
		this.fileType = fileType;
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
		if (!(object instanceof FlowGuceSiat))
		{
			return false;
		}
		final FlowGuceSiat other = (FlowGuceSiat) object;
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
		builder.append("FlowGuceSiat [id=");
		builder.append(id);
		builder.append(", flowGuce=");
		builder.append(flowGuce);
		builder.append(", flowSiat=");
		builder.append(flowSiat);
		builder.append("]");
		return builder.toString();
	}
}
