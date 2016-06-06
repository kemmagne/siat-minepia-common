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


/**
 * The Class FileFieldValue.
 */
@Entity
@Table(name = "FILE_FIELD_VALUE")
@XmlRootElement
@AssociationOverrides(
{ @AssociationOverride(name = "primaryKey.file", joinColumns = @JoinColumn(name = "FILE_ID")),
		@AssociationOverride(name = "primaryKey.fileField", joinColumns = @JoinColumn(name = "FILE_FIELD_ID")) })
public class FileFieldValue extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9098449738561410816L;

	/** The primary key. */
	@EmbeddedId
	private final FileFieldValueId primaryKey = new FileFieldValueId();

	/** The value. */
	@Column(length = 2000)
	private String value;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *           the new value
	 */
	public void setValue(final String value)
	{
		this.value = value;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	@Transient
	public File getFile()
	{
		return primaryKey.getFile();
	}

	/**
	 * Sets the file.
	 *
	 * @param file
	 *           the new file
	 */
	public void setFile(final File file)
	{
		primaryKey.setFile(file);
	}

	/**
	 * Gets the file field.
	 *
	 * @return the file field
	 */
	@Transient
	public FileField getFileField()
	{
		return primaryKey.getFileField();
	}

	/**
	 * Sets the file field.
	 *
	 * @param fileField
	 *           the new file field
	 */
	public void setFileField(final FileField fileField)
	{
		primaryKey.setFileField(fileField);
	}


}
