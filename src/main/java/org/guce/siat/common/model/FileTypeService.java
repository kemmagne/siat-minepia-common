package org.guce.siat.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class FileType.
 */
@javax.persistence.Entity
@Table(name = "FILE_TYPE_SERVICE")
@XmlRootElement
public class FileTypeService extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "FILE_TYPE_SERVICE_SEQ", sequenceName = "FILE_TYPE_SERVICE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_TYPE_SERVICE_SEQ")
	private Long id;


	/** The file type. */
	@ManyToOne
	@JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID")
	private FileType fileType;


	/** The service. */
	@ManyToOne
	@JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID")
	private Service service;


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
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public FileType getFileType()
	{
		return fileType;
	}


	/**
	 * Sets the file type.
	 *
	 * @param fileType
	 *           the new file type
	 */
	public void setFileType(final FileType fileType)
	{
		this.fileType = fileType;
	}


	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Service getService()
	{
		return service;
	}


	/**
	 * Sets the service.
	 *
	 * @param service
	 *           the new service
	 */
	public void setService(final Service service)
	{
		this.service = service;
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
		if (!(object instanceof Administration))
		{
			return false;
		}
		final Administration other = (Administration) object;
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
		builder.append("FileTypeService [id=");
		builder.append(id);
		builder.append(", fileType=");
		builder.append(fileType);
		builder.append(", service=");
		builder.append(service);
		builder.append("]");
		return builder.toString();
	}



}
