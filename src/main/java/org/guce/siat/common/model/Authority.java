package org.guce.siat.common.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.utils.enums.AuthorityType;

/**
 * The Class Authority represent the authorities witch will be affected to
 * users.
 */
@Entity
@Table(name = "AUTHORITY")
public class Authority extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1348884670244154024L;

    /**
     * The id.
     */
    @Id
    private Long id;

    /**
     * The role.
     */
    @Column(unique = true, nullable = false, updatable = false, name = "ROLE")
    private String role;

    /**
     * The label fr.
     */
    @Column(unique = true, nullable = false, updatable = false, name = "LABEL_FR")
    private String labelFr;

    /**
     * The label en.
     */
    @Column(unique = true, nullable = false, updatable = false, name = "LABEL_EN")
    private String labelEn;

    /**
     * The authority type.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, name = "AUTHORITY_TYPE")
    private AuthorityType authorityType;

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
     * @param id the new id
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * Gets the label fr.
     *
     * @return the labelFr
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * Sets the label fr.
     *
     * @param labelFr the labelFr to set
     */
    public void setLabelFr(final String labelFr) {
        this.labelFr = labelFr;
    }

    /**
     * Gets the label en.
     *
     * @return the labelEn
     */
    public String getLabelEn() {
        return labelEn;
    }

    /**
     * Sets the label en.
     *
     * @param labelEn the labelEn to set
     */
    public void setLabelEn(final String labelEn) {
        this.labelEn = labelEn;
    }

    /**
     * Gets the authority type.
     *
     * @return the authorityType
     */
    public AuthorityType getAuthorityType() {
        return authorityType;
    }

    /**
     * Sets the authority type.
     *
     * @param authorityType the authorityType to set
     */
    public void setAuthorityType(final AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return role == null ? StringUtils.EMPTY.hashCode() : this.role.hashCode();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof String) {
            return obj.equals(this.getRole());
        }

        if (obj instanceof Authority) {
            final Authority attr = (Authority) obj;

            return this.getRole().equals(attr.getRole());
        }

        return false;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Authority [id=");
        builder.append(id);
        builder.append(", role=");
        builder.append(role);
        builder.append(", labelFr=");
        builder.append(labelFr);
        builder.append(", labelEn=");
        builder.append(labelEn);
        builder.append(", authorityType=");
        builder.append(authorityType);
        builder.append("]");
        return builder.toString();
    }

}
