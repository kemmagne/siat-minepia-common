package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The Class MinistryFileTypeId.
 */
@Embeddable
public class MinistryFileTypeId implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MINISTRY_ID", referencedColumnName = "ID")
	private Ministry ministry;

	/** The file type. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID")
	private FileType fileType;


	/**
	 * @return the ministry
	 */
	public Ministry getMinistry()
	{
		return ministry;
	}

	/**
	 * @param ministry
	 *           the ministry to set
	 */
	public void setMinistry(final Ministry ministry)
	{
		this.ministry = ministry;
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
		result = prime * result + ((ministry == null) ? 0 : ministry.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof MinistryFileTypeId))
		{
			return false;
		}
		final MinistryFileTypeId other = (MinistryFileTypeId) obj;
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
		if (ministry == null)
		{
			if (other.ministry != null)
			{
				return false;
			}
		}
		else if (!ministry.equals(other.ministry))
		{
			return false;
		}
		return true;
	}



}
