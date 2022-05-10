package org.guce.siat.common.model.dto;

import java.io.Serializable;

/**
 * The Class FileFieldDTO.
 */
public class FileFieldDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    private Long id;

    /**
     * The code.
     */
    private String code;

    /**
     * The label fr.
     */
    private String labelFr;

    /**
     * The label en.
     */
    private String labelEn;

    /**
     * The repeatable.
     */
    private Boolean repeatable;

    /**
     * The file type.
     */
    private FileTypeDTO fileType;

    /**
     * The group.
     */
    private FieldGroupDTO group;

    /**
     * The updatable.
     */
    private Boolean updatable;

    /**
     * The hidden.
     */
    private Boolean hidden;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#getId()
     */

    public Long getId() {
        return id;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
     */

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
    public FileTypeDTO getFileType() {
        return fileType;
    }

    /**
     * Sets the file type.
     *
     * @param fileType the fileType to set
     */
    public void setFileType(final FileTypeDTO fileType) {
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
    public FieldGroupDTO getGroup() {
        return group;
    }

    /**
     * Sets the group.
     *
     * @param group the group to set
     */
    public void setGroup(final FieldGroupDTO group) {
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
        if (!(obj instanceof FileFieldDTO)) {
            return false;
        }
        final FileFieldDTO other = (FileFieldDTO) obj;
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

    @Override
    public String toString() {
        return "FileField{" + "id=" + id + ", code=" + code + ", labelFr=" + labelFr + ", labelEn=" + labelEn + ", repeatable=" + repeatable + ", fileType=" + fileType + ", group=" + group + ", updatable=" + updatable + ", hidden=" + hidden + '}';
    }

}
