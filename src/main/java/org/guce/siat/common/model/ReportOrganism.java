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
 * The Class ReportOrganism.
 */
@javax.persistence.Entity
@Table(name = "REPORT_ORGANISM")
@XmlRootElement
public class ReportOrganism extends AbstractModel implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "REPORT_ORGANISME_SEQ", sequenceName = "REPORT_ORGANISME_SEQ", allocationSize = 1, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_ORGANISME_SEQ")
	private Long id;

	/** The value. */
	@Basic(optional = true)
	@Column(name = "VALUE")
	protected String value;

	/** The sequence. */
	@Basic(optional = true)
	@Column(name = "SEQUENCE")
	protected Integer sequence;

	/** The organism. */
	@ManyToOne
	@JoinColumn(name = "ORGANISM", referencedColumnName = "id")
	private Organism organism;

	/** The file type flow report. */
	@ManyToOne
	@JoinColumn(name = "FILE_TYPE_FLOW_REPORT", referencedColumnName = "id")
	private FileTypeFlowReport fileTypeFlowReport;



	/**
	 * Instantiates a new file type flow report.
	 */
	public ReportOrganism()
	{
	}



	/* (non-Javadoc)
	 * @see org.guce.siat.common.model.AbstractModel#getId()
	 */
	@Override
	public Long getId()
	{
		return id;
	}



	/* (non-Javadoc)
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
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
	 * @param value the new value
	 */
	public void setValue(final String value)
	{
		this.value = value;
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
	 * @param organism the new organism
	 */
	public void setOrganism(final Organism organism)
	{
		this.organism = organism;
	}



	/**
	 * Gets the file type flow report.
	 *
	 * @return the file type flow report
	 */
	public FileTypeFlowReport getFileTypeFlowReport()
	{
		return fileTypeFlowReport;
	}

	/**
	 * Sets the file type flow report.
	 *
	 * @param fileTypeFlowReport the new file type flow report
	 */
	public void setFileTypeFlowReport(final FileTypeFlowReport fileTypeFlowReport)
	{
		this.fileTypeFlowReport = fileTypeFlowReport;
	}

	/**
	 * @return the sequence
	 */
	public Integer getSequence()
	{
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(final Integer sequence)
	{
		this.sequence = sequence;
	}




}
