package org.guce.siat.common.model.dto;


import org.guce.siat.common.utils.enums.BureauType;

/**
 * The Class Bureau.
 */
public class BureauDTO extends EntityDTO {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;


    /**
     * The bureau type.
     */
    private BureauType bureauType;

    /**
     * Instantiates a new bureau.
     */
    public BureauDTO() {
    }

   
    /**
     * Gets the bureau type.
     *
     * @return the bureauType
     */
    public BureauType getBureauType() {
        return bureauType;
    }

    /**
     * Sets the bureau type.
     *
     * @param bureauType the bureauType to set
     */
    public void setBureauType(final BureauType bureauType) {
        this.bureauType = bureauType;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.Entity#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("bureau [id=");
        builder.append(super.getId());
        builder.append(", code=");
        builder.append(super.getCode());
        builder.append(", labelFr=");
        builder.append(super.getLabelFr());
        builder.append(", labelEn=");
        builder.append(super.getLabelEn());
        builder.append(", address=");
        builder.append(super.getAddress());
        builder.append(", telephone=");
        builder.append(super.getTelephone());
        builder.append(", fax=");
        builder.append(super.getFax());
        builder.append(", email=");
        builder.append(super.getEmail());
        builder.append(", deleted=");
        builder.append(super.getDeleted());
        builder.append("]");
        return builder.toString();
    }

}
