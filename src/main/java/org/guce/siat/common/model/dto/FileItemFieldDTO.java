package org.guce.siat.common.model.dto;

import java.io.Serializable;



/**
 * The Class FileFieldDTO.
 */
public class FileItemFieldDTO implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The code. */
	private String code;

	/** The label fr. */
	private String labelFr;

	/** The label en. */
	private String labelEn;

	/** The repeatable. */
	private Boolean repeatable;

	/**
	 * Gets the repeatable.
	 *
	 * @return the repeatable
	 */
	public Boolean getRepeatable()
	{
		return repeatable;
	}

	/**
	 * Sets the repeatable.
	 *
	 * @param repeatable
	 *           the new repeatable
	 */
	public void setRepeatable(final Boolean repeatable)
	{
		this.repeatable = repeatable;
	}

	/** The file type. */
	private FileTypeDTO fileType;

	/** The group. */
	private FieldGroupDTO group;

	/** The updatable. */
	private Boolean updatable;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#getId()
	 */
	public Long getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
	 */
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *           the new code
	 */
	public void setCode(final String code)
	{
		this.code = code;
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
	 * Gets the file type.
	 *
	 * @return the fileType
	 */
	public FileTypeDTO getFileType()
	{
		return fileType;
	}

	/**
	 * Sets the file type.
	 *
	 * @param fileType
	 *           the fileType to set
	 */
	public void setFileType(final FileTypeDTO fileType)
	{
		this.fileType = fileType;
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

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public FieldGroupDTO getGroup()
	{
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group
	 *           the group to set
	 */
	public void setGroup(final FieldGroupDTO group)
	{
		this.group = group;
	}

	/**
	 * Gets the updatable.
	 *
	 * @return the updatable
	 */
	public Boolean getUpdatable()
	{
		return updatable;
	}

	/**
	 * Sets the updatable.
	 *
	 * @param updatable
	 *           the new updatable
	 */
	public void setUpdatable(final Boolean updatable)
	{
		this.updatable = updatable;
	}

}
