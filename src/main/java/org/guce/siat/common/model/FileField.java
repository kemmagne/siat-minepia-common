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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class FileField.
 */
@Entity
@Table(name = "FILE_FIELD", uniqueConstraints = @UniqueConstraint(columnNames = {"CODE", "FILE_TYPE_ID"}))
@XmlRootElement
public class FileField extends AbstractModel implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_FIELD_SEQ")
    @SequenceGenerator(name = "FILE_FIELD_SEQ", sequenceName = "FILE_FIELD_SEQ", allocationSize = 1)
    private Long id;

    /**
     * The code.
     */
    @Column(name = "CODE", length = 100)
    private String code;

    /**
     * The label fr.
     */
    @Column(name = "LABEL_FR", length = 150)
    private String labelFr;

    /**
     * The label en.
     */
    @Column(name = "LABEL_EN", length = 150)
    private String labelEn;

    /**
     * The repeatable.
     */
    @Column(name = "REPEATABLE")
    private Boolean repeatable;

    /**
     * The file type.
     */
    @ManyToOne
    @JoinColumn(name = "FILE_TYPE_ID", referencedColumnName = "ID")
    private FileType fileType;

    /**
     * The group.
     */
    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    private FieldGroup group;

    /**
     * The updatable.
     */
    @Column(name = "UPDATABLE")
    private Boolean updatable;

    /**
     * The hidden.
     */
    @Column(name = "HIDDEN")
    private Boolean hidden;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#getId()
     */
    @Override
    public Long getId() {
        return id;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Gets the label fr.
     *
     * @return the label fr
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * Sets the label fr.
     *
     * @param labelFr the new label fr
     */
    public void setLabelFr(final String labelFr) {
        this.labelFr = labelFr;
    }

    /**
     * Gets the file type.
     *
     * @return the fileType
     */
    public FileType getFileType() {
        return fileType;
    }

    /**
     * Sets the file type.
     *
     * @param fileType the fileType to set
     */
    public void setFileType(final FileType fileType) {
        this.fileType = fileType;
    }

    /**
     * Gets the label en.
     *
     * @return the label en
     */
    public String getLabelEn() {
        return labelEn;
    }

    /**
     * Sets the label en.
     *
     * @param labelEn the new label en
     */
    public void setLabelEn(final String labelEn) {
        this.labelEn = labelEn;
    }

    /**
     * Gets the repeatable.
     *
     * @return the repeatable
     */
    public Boolean getRepeatable() {
        return repeatable;
    }

    /**
     * Sets the repeatable.
     *
     * @param repeatable the new repeatable
     */
    public void setRepeatable(final Boolean repeatable) {
        this.repeatable = repeatable;
    }

    /**
     * Gets the group.
     *
     * @return the group
     */
    public FieldGroup getGroup() {
        return group;
    }

    /**
     * Sets the group.
     *
     * @param group the group to set
     */
    public void setGroup(final FieldGroup group) {
        this.group = group;
    }

    /**
     * Gets the updatable.
     *
     * @return the updatable
     */
    public Boolean getUpdatable() {
        return updatable;
    }

    /**
     * Sets the updatable.
     *
     * @param updatable the new updatable
     */
    public void setUpdatable(final Boolean updatable) {
        this.updatable = updatable;
    }

    /**
     * Gets the hidden
     *
     * @return the hidden
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * Sets the hidden
     *
     * @param hidden the new hidden
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FileField)) {
            return false;
        }
        final FileField other = (FileField) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        if (fileType == null) {
            if (other.fileType != null) {
                return false;
            }
        } else if (!fileType.equals(other.fileType)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
