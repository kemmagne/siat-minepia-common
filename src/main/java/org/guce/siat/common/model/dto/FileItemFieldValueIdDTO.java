/*
 *
 */
package org.guce.siat.common.model.dto;

import java.io.Serializable;



/**
 * The Class FileItemFieldValueIdDTO.
 */

public class FileItemFieldValueIdDTO implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 662058985247014320L;

	/** The file item. */
	FileItemLiteDTO fileItem;

	/** The file item field. */
	FileItemFieldLiteDTO fileItemField;

	/**
	 * Gets the file item.
	 *
	 * @return the file item
	 */
	public FileItemLiteDTO getFileItem()
	{
		return fileItem;
	}

	/**
	 * Sets the file item.
	 *
	 * @param fileItem
	 *           the new file item
	 */
	public void setFileItem(final FileItemLiteDTO fileItem)
	{
		this.fileItem = fileItem;
	}

	/**
	 * Gets the file item field.
	 *
	 * @return the file item field
	 */
	public FileItemFieldLiteDTO getFileItemField()
	{
		return fileItemField;
	}

	/**
	 * Sets the file item field.
	 *
	 * @param fileItemField
	 *           the new file item field
	 */
	public void setFileItemField(final FileItemFieldLiteDTO fileItemField)
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
		if (!(obj instanceof FileItemFieldValueIdDTO))
		{
			return false;
		}
		final FileItemFieldValueIdDTO other = (FileItemFieldValueIdDTO) obj;
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
