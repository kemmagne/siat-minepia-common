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
 * The Class FileTypeFlowReport.
 */
@javax.persistence.Entity
@Table(name = "FILE_TYPE_FLOW_REPORT")
@XmlRootElement
public class FileTypeFlowReport extends AbstractModel implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "FILE_TYPE_FLOW_REPORT_SEQ", sequenceName = "FILE_TYPE_FLOW_REPORT_SEQ", allocationSize = 1, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_TYPE_FLOW_REPORT_SEQ")
	private Long id;

	/** The report class name. */
	@Basic(optional = false)
	@Column(name = "REPORT_CLASS_NAME")
	protected String reportClassName;

	/** The report name. */
	@Basic(optional = false)
	@Column(name = "REPORT_NAME")
	protected String reportName;

	/** The report name. */
	@Basic(optional = false)
	@Column(name = "FILE_FIELD_NAME")
	protected String fileFieldName;

	/** The file type. */
	@ManyToOne
	@JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID")
	private FileType fileType;

	/** The flow. */
	@ManyToOne
	@JoinColumn(name = "FLOW_ID", referencedColumnName = "ID")
	private Flow flow;

	/** The organism. */
	@ManyToOne
	@JoinColumn(name = "ORGANISM_ID", referencedColumnName = "ID")
	private Organism organism;



	/**
	 * Instantiates a new file type flow report.
	 */
	public FileTypeFlowReport()
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
	 * Gets the report class name.
	 *
	 * @return the report class name
	 */
	public String getReportClassName()
	{
		return reportClassName;
	}


	/**
	 * Sets the report class name.
	 *
	 * @param reportClassName
	 *           the new report class name
	 */
	public void setReportClassName(final String reportClassName)
	{
		this.reportClassName = reportClassName;
	}


	/**
	 * Gets the report name.
	 *
	 * @return the report name
	 */
	public String getReportName()
	{
		return reportName;
	}


	/**
	 * Sets the report name.
	 *
	 * @param reportName
	 *           the new report name
	 */
	public void setReportName(final String reportName)
	{
		this.reportName = reportName;
	}


	/**
	 * Gets the file field name.
	 *
	 * @return the file field name
	 */
	public String getFileFieldName()
	{
		return fileFieldName;
	}


	/**
	 * Sets the file field name.
	 *
	 * @param fileFieldName
	 *           the new file field name
	 */
	public void setFileFieldName(final String fileFieldName)
	{
		this.fileFieldName = fileFieldName;
	}


	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public FileType getFileType()
	{
		return fileType;
	}


	/**
	 * Sets the file type.
	 *
	 * @param fileType
	 *           the new file type
	 */
	public void setFileType(final FileType fileType)
	{
		this.fileType = fileType;
	}


	/**
	 * Gets the flow.
	 *
	 * @return the flow
	 */
	public Flow getFlow()
	{
		return flow;
	}


	/**
	 * Sets the flow.
	 *
	 * @param flow
	 *           the new flow
	 */
	public void setFlow(final Flow flow)
	{
		this.flow = flow;
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
		if (!(object instanceof FileTypeFlowReport))
		{
			return false;
		}
		final FileTypeFlowReport other = (FileTypeFlowReport) object;
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
		builder.append("FileTypeFlowReport [id=");
		builder.append(id);
		builder.append(", reportClassName=");
		builder.append(reportClassName);
		builder.append(", reportName=");
		builder.append(reportName);
		builder.append(", fileFieldName=");
		builder.append(fileFieldName);
		builder.append(", fileType=");
		builder.append(fileType);
		builder.append(", flow=");
		builder.append(flow);
		builder.append(", organism=");
		builder.append(organism);
		builder.append("]");
		return builder.toString();
	}

}
