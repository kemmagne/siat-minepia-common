package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class FileField.
 */
@Entity
@Table(name = "FILE_ITEM_FIELD")
@XmlRootElement
public class FileItemField extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ITEM_FIELD_SEQ")
	@SequenceGenerator(name = "FILE_ITEM_FIELD_SEQ", sequenceName = "FILE_ITEM_FIELD_SEQ", allocationSize = 1)
	private Long id;

	/** The code. */
	@Column(name = "CODE")
	private String code;

	/** The label fr. */
	@Column(name = "LABEL_FR")
	private String labelFr;

	/** The label en. */
	@Column(name = "LABEL_EN")
	private String labelEn;

	/** The repeatable. */
	@Column(name = "REPEATABLE")
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
	@ManyToOne
	@JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID")
	private FileType fileType;

	/** The group. */
	@ManyToOne
	@JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
	private FieldGroup group;

	/** The updatable. */
	@Column(name = "UPDATABLE")
	private Boolean updatable;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#getId()
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
	 */
	@Override
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
	public FieldGroup getGroup()
	{
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group
	 *           the group to set
	 */
	public void setGroup(final FieldGroup group)
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
