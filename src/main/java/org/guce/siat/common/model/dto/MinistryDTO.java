package org.guce.siat.common.model.dto;


/**
 * The Class MinistryDTO.
 */
public class MinistryDTO extends AdministrationDTO {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(object instanceof MinistryDTO)) {
            return false;
        }
        final MinistryDTO other = (MinistryDTO) object;
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
        builder.append("Ministry [id=");
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
