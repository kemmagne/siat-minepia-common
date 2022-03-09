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
 * The Class FileItemFieldValue.
 */
@Entity
@Table(name = "FILE_ITEM_FIELD_VALUE")
@XmlRootElement
@AssociationOverrides(
{ @AssociationOverride(name = "primaryKey.fileItem", joinColumns = @JoinColumn(name = "FILE_ITEM_ID")),
		@AssociationOverride(name = "primaryKey.fileItemField", joinColumns = @JoinColumn(name = "FILE_ITEM_FIELD_ID")) })
public class FileItemFieldValue extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9098449738561410816L;

	/** The primary key. */
	@EmbeddedId
	private final FileItemFieldValueId primaryKey = new FileItemFieldValueId();

	/** The value. */
	@Column(length = 2000)
	private String value;

	/** The level. */
	@Column(name = "STAGE")
	private Integer level;


	/** The history. */
	@Column(length = 2000)
	private String history;

	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	public String getHistory()
	{
		return history;
	}

	/**
	 * Sets the history.
	 *
	 * @param history
	 *           the new history
	 */
	public void setHistory(final String history)
	{
		this.history = history;
	}

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
	 * Gets the file item.
	 *
	 * @return the file item
	 */
	@Transient
	public FileItem getFileItem()
	{
		return primaryKey.getFileItem();
	}

	/**
	 * Sets the file item.
	 *
	 * @param fileItem
	 *           the new file item
	 */
	public void setFileItem(final FileItem fileItem)
	{
		primaryKey.setFileItem(fileItem);
	}

	/**
	 * Gets the file item field.
	 *
	 * @return the file item field
	 */
	@Transient
	public FileItemField getFileItemField()
	{
		return primaryKey.getFileItemField();
	}

	/**
	 * Sets the file item field.
	 *
	 * @param fileItemField
	 *           the new file item field
	 */
	public void setFileItemField(final FileItemField fileItemField)
	{
		primaryKey.setFileItemField(fileItemField);
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public Integer getLevel()
	{
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level
	 *           the new level
	 */
	public void setLevel(final Integer level)
	{
		this.level = level;
	}

    @Override
    public String toString() {
        return "FileItemFieldValue{" + "primaryKey=" + primaryKey + ", value=" + value + ", level=" + level + ", history=" + history + '}';
    }

}
