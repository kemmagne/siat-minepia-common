/*
 *
 */
package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The Class FileItemFieldValueId.
 */
@Embeddable
public class FileItemFieldValueId implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 662058985247014320L;

	/** The file item. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILE_ITEM_ID", referencedColumnName = "ID")
	private FileItem fileItem;

	/** The file item field. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILE_ITEM_FIELD_ID", referencedColumnName = "ID")
	private FileItemField fileItemField;

	/**
	 * Gets the file item.
	 *
	 * @return the file item
	 */
	public FileItem getFileItem()
	{
		return fileItem;
	}

	/**
	 * Sets the file item.
	 *
	 * @param fileItem
	 *           the new file item
	 */
	public void setFileItem(final FileItem fileItem)
	{
		this.fileItem = fileItem;
	}

	/**
	 * Gets the file item field.
	 *
	 * @return the file item field
	 */
	public FileItemField getFileItemField()
	{
		return fileItemField;
	}

	/**
	 * Sets the file item field.
	 *
	 * @param fileItemField
	 *           the new file item field
	 */
	public void setFileItemField(final FileItemField fileItemField)
	{
		this.fileItemField = fileItemField;
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
		result = prime * result + ((fileItem == null) ? 0 : fileItem.hashCode());
		result = prime * result + ((fileItemField == null) ? 0 : fileItemField.hashCode());
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
		if (!(obj instanceof FileItemFieldValueId))
		{
			return false;
		}
		final FileItemFieldValueId other = (FileItemFieldValueId) obj;
		if (fileItem == null)
		{
			if (other.fileItem != null)
			{
				return false;
			}
		}
		else if (!fileItem.equals(other.fileItem))
		{
			return false;
		}
		if (fileItemField == null)
		{
			if (other.fileItemField != null)
			{
				return false;
			}
		}
		else if (!fileItemField.equals(other.fileItemField))
		{
			return false;
		}
		return true;
	}



}
