package org.guce.siat.common.model.dto;

import java.io.Serializable;

/**
 * The Class AdministrationDTO.
 */
public class AdministrationDTO  implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    protected Long id;

    /**
     * The label fr.
     */
    protected String labelFr;

    /**
     * The label en.
     */
    protected String labelEn;

    /**
     * The deleted.
     */
    protected Boolean deleted;


    /**
     * Instantiates a new administration.
     */
    public AdministrationDTO() {
    }

    /**
     * Instantiates a new administration.
     *
     * @param id
     */
    public AdministrationDTO(Long id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
   
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the labelFr
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * @param labelFr the labelFr to set
     */
    public void setLabelFr(final String labelFr) {
        this.labelFr = labelFr;
    }

    /**
     * @return the labelEn
     */
    public String getLabelEn() {
        return labelEn;
    }

    /**
     * @param labelEn the labelEn to set
     */
    public void setLabelEn(final String labelEn) {
        this.labelEn = labelEn;
    }

    /**
     * @return the deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
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
    public boolean equals(final Object object) {
        if (!(object instanceof AdministrationDTO)) {
            return false;
        }
        final AdministrationDTO other = (AdministrationDTO) object;
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
        builder.append("Administration [id=");
        builder.append(id);
        builder.append(", labelFr=");
        builder.append(labelFr);
        builder.append(", labelEn=");
        builder.append(labelEn);
        builder.append(", deleted=");
        builder.append(deleted);
        builder.append("]");
        return builder.toString();
    }

}
