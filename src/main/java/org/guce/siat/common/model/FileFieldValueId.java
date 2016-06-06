package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



/**
 * The Class FileFieldValueId.
 */
@Embeddable
public class FileFieldValueId implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 662058985247014320L;

	/** The file. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILE_ID", referencedColumnName = "ID")
	private File file;

	/** The file field. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILE_FIELD_ID", referencedColumnName = "ID")
	private FileField fileField;

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file
	 *           the new file
	 */
	public void setFile(final File file)
	{
		this.file = file;
	}

	/**
	 * Gets the file field.
	 *
	 * @return the file field
	 */
	public FileField getFileField()
	{
		return fileField;
	}

	/**
	 * Sets the file field.
	 *
	 * @param fileField
	 *           the new file field
	 */
	public void setFileField(final FileField fileField)
	{
		this.fileField = fileField;
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
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((fileField == null) ? 0 : fileField.hashCode());
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
		if (!(obj instanceof FileFieldValueId))
		{
			return false;
		}
		final FileFieldValueId other = (FileFieldValueId) obj;
		if (file == null)
		{
			if (other.file != null)
			{
				return false;
			}
		}
		else if (!file.equals(other.file))
		{
			return false;
		}
		if (fileField == null)
		{
			if (other.fileField != null)
			{
				return false;
			}
		}
		else if (!fileField.equals(other.fileField))
		{
			return false;
		}
		return true;
	}


}
