package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The Class FileTypeStepId.
 */
@Embeddable
public class FileTypeStepId implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The step. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STEP_ID", referencedColumnName = "ID", updatable = false)
	private Step step;

	/** The file type. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID", updatable = false)
	private FileType fileType;

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public Step getStep()
	{
		return step;
	}

	/**
	 * Sets the step.
	 *
	 * @param step
	 *           the step to set
	 */
	public void setStep(final Step step)
	{
		this.step = step;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		result = prime * result + ((step == null) ? 0 : step.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof FileTypeStepId))
		{
			return false;
		}
		FileTypeStepId other = (FileTypeStepId) obj;
		if (fileType == null)
		{
			if (other.fileType != null)
			{
				return false;
			}
		}
		else if (!fileType.equals(other.fileType))
		{
			return false;
		}
		if (step == null)
		{
			if (other.step != null)
			{
				return false;
			}
		}
		else if (!step.equals(other.step))
		{
			return false;
		}
		return true;
	}



}
