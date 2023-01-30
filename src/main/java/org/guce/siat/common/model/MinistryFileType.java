package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class MinistryFileType.
 */
@Entity
@Table(name = "MINISTRY_FILE_TYPE")
@XmlRootElement
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.ministry", joinColumns = @JoinColumn(name = "MINISTRY_ID")),
    @AssociationOverride(name = "primaryKey.fileType", joinColumns = @JoinColumn(name = "FILE_TYPE_ID"))}
)
public class MinistryFileType implements Serializable {

    /**
     * The primary key.
     */
    @EmbeddedId
    private MinistryFileTypeId primaryKey = new MinistryFileTypeId();

    @Column(name = "AUTOMATIC_COTATION_ALLOWED")
    private Boolean automaticCotationAllowed;

    /**
     * Gets the primary key.
     *
     * @return the primaryKey
     */
    public MinistryFileTypeId getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the primary key.
     *
     * @param primaryKey the primaryKey to set
     */
    public void setPrimaryKey(final MinistryFileTypeId primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the ministry.
     *
     * @return the ministry
     */
    public Ministry getMinistry() {
        return primaryKey.getMinistry();
    }

    /**
     * Sets the ministry.
     *
     * @param ministry the new ministry
     */
    public void setMinistry(final Ministry ministry) {
        primaryKey.setMinistry(ministry);
    }

    /**
     * Gets the file type.
     *
     * @return the file type
     */
    public FileType getFileType() {
        return primaryKey.getFileType();
    }

    /**
     * Sets the file type.
     *
     * @param fileType the new file type
     */
    public void setFileType(final FileType fileType) {
        primaryKey.setFileType(fileType);
    }

    public Boolean getAutomaticCotationAllowed() {
        return automaticCotationAllowed;
    }

    public void setAutomaticCotationAllowed(Boolean automaticCotationAllowed) {
        this.automaticCotationAllowed = automaticCotationAllowed;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.primaryKey);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MinistryFileType other = (MinistryFileType) obj;
        return Objects.equals(this.primaryKey, other.primaryKey);
    }

}
