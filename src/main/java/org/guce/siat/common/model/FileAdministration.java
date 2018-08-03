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

import org.guce.siat.common.utils.Constants;

/**
 * The Class FileAdministration.
 */
/**
 *
 */
@Entity
@Table(name = "FILE_ADMINISTRATION")
@XmlRootElement
public class FileAdministration extends AbstractModel implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ADMINISTRATION_SEQ")
	@SequenceGenerator(name = "FILE_ADMINISTRATION_SEQ", sequenceName = "FILE_ADMINISTRATION_SEQ", allocationSize = 1)
	private Long id;

	/**
	 * The file.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "FILE_ID", referencedColumnName = "ID")
	private File file;

	/**
	 * The administration.
	 */
	@ManyToOne
	@JoinColumn(name = "ADMINISTRATION_ID", referencedColumnName = "ID")
	private Administration administration;

	/**
	 * Instantiates a new FileAdministration.
	 */
	public FileAdministration() {
	}

	/**
	 * Instantiates a new file item.
	 *
	 * @param id the id
	 */
	public FileAdministration(final Long id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the file to set
	 */
	public void setFile(final File file) {
		this.file = file;
	}

	/**
	 * Gets the administration.
	 *
	 * @return the administration
	 */
	public Administration getAdministration() {
		return administration;
	}

	/**
	 * Sets the administration.
	 *
	 * @param administration the administration to set
	 */
	public void setAdministration(Administration administration) {
		this.administration = administration;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 1;
		result = Constants.THIRTYONE * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof FileAdministration)) {
			return false;
		}
		final FileAdministration other = (FileAdministration) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
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
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("FileAdministration [id=");
		builder.append(id);
		builder.append(", file=");
		builder.append(file);
		builder.append(", administration=");
		builder.append(administration);
		builder.append("]");
		return builder.toString();
	}

}
