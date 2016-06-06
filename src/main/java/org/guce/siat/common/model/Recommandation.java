package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Recommandation.
 */
@Entity
@Table(name = "RECOMMANDATION")
@XmlRootElement
public class Recommandation extends AbstractModel implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECOMMANDATION_SEQ")
	@SequenceGenerator(name = "RECOMMANDATION_SEQ", sequenceName = "RECOMMANDATION_SEQ", allocationSize = 1)
	private Long id;

	/** The value. */
	@Column(name = "value")
	private String value;

	/** The created. */
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	/** The supervisor. */
	@JoinColumn(name = "SUPERVISOR_ID", referencedColumnName = "ID")
	@ManyToOne
	private User supervisor;

	/** The file item. */
	@JoinColumn(name = "FILE_ITEM_ID", referencedColumnName = "ID")
	@ManyToOne
	private FileItem fileItem;

	/** The file. */
	@JoinColumn(name = "file_id", referencedColumnName = "ID")
	@ManyToOne
	private File file;

	/** The authorized authority list. */
	@OneToMany(mappedBy = "primaryKey.recommandation", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<RecommandationAuthority> authorizedAuthorityList;

	/**
	 * Instantiates a new recommandation.
	 */
	public Recommandation()
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
	 *           the value to set
	 */
	public void setValue(final String value)
	{
		this.value = value;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated()
	{
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created
	 *           the created to set
	 */
	public void setCreated(final Date created)
	{
		this.created = created;
	}

	/**
	 * Gets the supervisor.
	 *
	 * @return the supervisor
	 */
	public User getSupervisor()
	{
		return supervisor;
	}

	/**
	 * Sets the supervisor.
	 *
	 * @param supervisor
	 *           the supervisor to set
	 */
	public void setSupervisor(final User supervisor)
	{
		this.supervisor = supervisor;
	}

	/**
	 * Gets the file item.
	 *
	 * @return the fileItem
	 */
	public FileItem getFileItem()
	{
		return fileItem;
	}

	/**
	 * Sets the file item.
	 *
	 * @param fileItem
	 *           the fileItem to set
	 */
	public void setFileItem(final FileItem fileItem)
	{
		this.fileItem = fileItem;
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
	 * Gets the authorized authority list.
	 *
	 * @return the authorizedAuthorityList
	 */
	public List<RecommandationAuthority> getAuthorizedAuthorityList()
	{
		return authorizedAuthorityList;
	}

	/**
	 * @param authorizedAuthorityList
	 */
	public void setAuthorizedAuthorityList(final List<RecommandationAuthority> authorizedAuthorityList)
	{
		this.authorizedAuthorityList = authorizedAuthorityList;
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
		if (!(object instanceof Recommandation))
		{
			return false;
		}
		final Recommandation other = (Recommandation) object;
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
		builder.append("Recommandation [id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append(", created=");
		builder.append(created);
		builder.append("]");
		return builder.toString();
	}
}
