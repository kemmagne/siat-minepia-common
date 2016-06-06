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
 * The Class Attachment.
 */
@Entity
@Table(name = "ATTACHMENT")
@XmlRootElement
public class Attachment extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTACHMENT_SEQ")
	@SequenceGenerator(name = "ATTACHMENT_SEQ", sequenceName = "ATTACHMENT_SEQ", allocationSize = 1)
	private Long id;


	/** The document number. */
	@Column(name = "DOCUMENT_NUMBER")
	private Short documentNumber;

	/** The document name. */
	@Column(name = "DOCUMENT_NAME")
	private String documentName;

	/** The alfresco path. */
	@Column(unique = false, name = "DOCUMENT_PATH")
	private String path;

	/** The alfresco id. */
	@Column(unique = true, name = "ALFRESCO_ID")
	private String alfrescoId;

	/** The file. */
	@JoinColumn(name = "FILE_ID", referencedColumnName = "ID")
	@ManyToOne
	private File file;

	/** The attachment type. */
	@Column(name = "ATTACHMENT_TYPE")
	private String attachmentType;

	/**
	 * Instantiates a new attachment.
	 */
	public Attachment()
	{
	}

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
	 * Gets the document number.
	 *
	 * @return the documentNumber
	 */
	public Short getDocumentNumber()
	{
		return documentNumber;
	}

	/**
	 * Sets the document number.
	 *
	 * @param documentNumber
	 *           the documentNumber to set
	 */
	public void setDocumentNumber(final Short documentNumber)
	{
		this.documentNumber = documentNumber;
	}

	/**
	 * Gets the document name.
	 *
	 * @return the documentName
	 */
	public String getDocumentName()
	{
		return documentName;
	}

	/**
	 * Sets the document name.
	 *
	 * @param documentName
	 *           the documentName to set
	 */
	public void setDocumentName(final String documentName)
	{
		this.documentName = documentName;
	}

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
	 *           the file to set
	 */
	public void setFile(final File file)
	{
		this.file = file;
	}

	/**
	 * Gets the attachment type.
	 *
	 * @return the attachmentType
	 */
	public String getAttachmentType()
	{
		return attachmentType;
	}

	/**
	 * Sets the attachment type.
	 *
	 * @param attachmentType
	 *           the attachmentType to set
	 */
	public void setAttachmentType(final String attachmentType)
	{
		this.attachmentType = attachmentType;
	}

	/**
	 * Gets the alfresco id.
	 *
	 * @return the alfrescoId
	 */
	public String getAlfrescoId()
	{
		return alfrescoId;
	}

	/**
	 * Sets the alfresco id.
	 *
	 * @param alfrescoId
	 *           the alfrescoId to set
	 */
	public void setAlfrescoId(final String alfrescoId)
	{
		this.alfrescoId = alfrescoId;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path
	 *           the new path
	 */
	public void setPath(final String path)
	{
		this.path = path;
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
		if (!(object instanceof Attachment))
		{
			return false;
		}
		final Attachment other = (Attachment) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("Attachment [id=");
		builder.append(id);
		builder.append(", documentNumber=");
		builder.append(documentNumber);
		builder.append(", documentName=");
		builder.append(documentName);
		builder.append("]");
		return builder.toString();
	}

}
