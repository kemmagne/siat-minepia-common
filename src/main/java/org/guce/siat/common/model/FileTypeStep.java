package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.guce.siat.common.model.AbstractModel;


/**
 * The Class FileTypeStep.
 */
@Entity
@Table(name = "FILE_TYPE_STEP")
@XmlRootElement
@AssociationOverrides(
{ @AssociationOverride(name = "primaryKey.step", joinColumns = @JoinColumn(name = "STEP_ID")),
		@AssociationOverride(name = "primaryKey.fileType", joinColumns = @JoinColumn(name = "FILE_TYPE_ID")) })
public class FileTypeStep extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The primary key. */
	@EmbeddedId
	private FileTypeStepId primaryKey = new FileTypeStepId();

	/** The is ap decision. */
	@Column(name = "IS_AP_DECISION")
	private Boolean isApDecision;
	
	/** The label fr. */
	@Column(name = "LABEL_FR", length = 100)
	private String labelFr;

	/** The label en. */
	@Column(name = "LABEL_EN", length = 100)
	private String labelEn;

	/**
	 * Gets the primary key.
	 *
	 * @return the primaryKey
	 */
	public FileTypeStepId getPrimaryKey()
	{
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey
	 *           the primaryKey to set
	 */
	public void setPrimaryKey(final FileTypeStepId primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	@Transient
	public Step getStep()
	{
		return primaryKey.getStep();
	}

	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	@Transient
	public FileType getFileType()
	{
		return primaryKey.getFileType();
	}

	/**
	 * Sets the step.
	 *
	 * @param step
	 *           the new step
	 */
	public void setStep(final Step step)
	{
		primaryKey.setStep(step);
	}


	/**
	 * Sets the file type.
	 *
	 * @param fileType
	 *           the new file type
	 */
	public void setFileType(final FileType fileType)
	{
		primaryKey.setFileType(fileType);
	}

	/**
	 * Gets the checks if is ap decision.
	 *
	 * @return the isApDecision
	 */
	public Boolean getIsApDecision()
	{
		return isApDecision;
	}

	/**
	 * Sets the checks if is ap decision.
	 *
	 * @param isApDecision
	 *           the isApDecision to set
	 */
	public void setIsApDecision(final Boolean isApDecision)
	{
		this.isApDecision = isApDecision;
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

}
