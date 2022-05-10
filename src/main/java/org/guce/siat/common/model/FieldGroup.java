package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class FieldGroup.
 */
@javax.persistence.Entity
@Table(name = "FIELD_GROUP")
@XmlRootElement
public class FieldGroup extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9024094519041273961L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FIELD_GROUP_SEQ")
	@SequenceGenerator(name = "FIELD_GROUP_SEQ", sequenceName = "FIELD_GROUP_SEQ", allocationSize = 1)
	private Long id;

	/** The label fr. */
	@Column(name = "LABEL_FR", length = 150)
	private String labelFr;

	/** The label en. */
	@Column(name = "LABEL_EN", length = 150)
	private String labelEn;

	/** The group list. */
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private List<FileField> fileFieldList;

	/** The file item field list. */
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private List<FileItemField> fileItemFieldList;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *           the id to set
	 */
	@Override
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the label fr.
	 *
	 * @return the labelFr
	 */
	public String getLabelFr()
	{
		return labelFr;
	}

	/**
	 * Sets the label fr.
	 *
	 * @param labelFr
	 *           the labelFr to set
	 */
	public void setLabelFr(final String labelFr)
	{
		this.labelFr = labelFr;
	}

	/**
	 * Gets the label en.
	 *
	 * @return the labelEn
	 */
	public String getLabelEn()
	{
		return labelEn;
	}

	/**
	 * Sets the label en.
	 *
	 * @param labelEn
	 *           the labelEn to set
	 */
	public void setLabelEn(final String labelEn)
	{
		this.labelEn = labelEn;
	}

	/**
	 * Gets the file field list.
	 *
	 * @return the fileFieldList
	 */
	public List<FileField> getFileFieldList()
	{
		return fileFieldList;
	}

	/**
	 * Sets the file field list.
	 *
	 * @param fileFieldList
	 *           the fileFieldList to set
	 */
	public void setFileFieldList(final List<FileField> fileFieldList)
	{
		this.fileFieldList = fileFieldList;
	}

	/**
	 * Gets the file item field list.
	 *
	 * @return the fileItemFieldList
	 */
	public List<FileItemField> getFileItemFieldList()
	{
		return fileItemFieldList;
	}

	/**
	 * Sets the file item field list.
	 *
	 * @param fileItemFieldList
	 *           the fileItemFieldList to set
	 */
	public void setFileItemFieldList(final List<FileItemField> fileItemFieldList)
	{
		this.fileItemFieldList = fileItemFieldList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object)
	{
		if (!(object instanceof FieldGroup))
		{
			return false;
		}
		final FieldGroup other = (FieldGroup) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
			return false;
		}
		return true;
	}

//    @Override
//    public String toString() {
//        return "FieldGroup{" + "id=" + id + ", labelFr=" + labelFr + ", labelEn=" + labelEn + ", fileFieldList=" + fileFieldList + ", fileItemFieldList=" + fileItemFieldList + '}';
//    }

}
