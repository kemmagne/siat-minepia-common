package org.guce.siat.common.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


/**
 * The Class FileTypeFlow.
 */
@Embeddable
public class FileTypeFlowId implements java.io.Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5091812182790961198L;

	/** The flow. */
	@ManyToOne
	private Flow flow;

	/** The file type. */
	@ManyToOne
	private FileType fileType;

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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final FileTypeFlowId that = (FileTypeFlowId) o;

		if (flow != null ? !flow.equals(that.flow) : that.flow != null)
		{
			return false;
		}
		if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null)
		{
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		int result;
		result = (flow != null ? flow.hashCode() : 0);
		result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
		return result;
	}
}
