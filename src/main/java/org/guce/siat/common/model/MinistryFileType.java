package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.guce.siat.common.model.Ministry;


/**
 * The Class MinistryFileType.
 */
@Entity
@Table(name = "MINISTRY_FILE_TYPE")
@XmlRootElement
@AssociationOverrides(
{ @AssociationOverride(name = "primaryKey.ministry", joinColumns = @JoinColumn(name = "MINISTRY_ID")),
		@AssociationOverride(name = "primaryKey.fileType", joinColumns = @JoinColumn(name = "FILE_TYPE_ID")) })
public class MinistryFileType implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The primary key. */
	@EmbeddedId
	private MinistryFileTypeId primaryKey = new MinistryFileTypeId();


	/**
	 * Gets the primary key.
	 *
	 * @return the primaryKey
	 */
	public MinistryFileTypeId getPrimaryKey()
	{
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey
	 *           the primaryKey to set
	 */
	public void setPrimaryKey(final MinistryFileTypeId primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the ministry.
	 *
	 * @return the ministry
	 */
	public Ministry getMinistry()
	{
		return primaryKey.getMinistry();
	}

	/**
	 * Sets the ministry.
	 *
	 * @param ministry
	 *           the new ministry
	 */
	public void setMinistry(final Ministry ministry)
	{
		primaryKey.setMinistry(ministry);
	}

	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public FileType getFileType()
	{
		return primaryKey.getFileType();
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


}
