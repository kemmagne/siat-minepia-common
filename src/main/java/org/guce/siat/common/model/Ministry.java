package org.guce.siat.common.model;

import java.util.List;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Ministry.
 */
@Entity
@Table(name = "MINISTRY")
@XmlRootElement
public class Ministry extends Administration {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "CODE", length = 10)
    private String code;

    /**
     * The admin ministry.
     */
    @Transient
    private User adminMinistry;

    /**
     * The minister.
     */
    @Transient
    private User minister;

    /**
     * The general secretary.
     */
    @Transient
    private User generalSecretary;

    /**
     * The organisms list.
     */
    @OneToMany(mappedBy = "ministry")
    private List<Organism> organismsList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the admin ministry.
     *
     * @return the adminMinistry
     */
    public User getAdminMinistry() {
        return adminMinistry;
    }

    /**
     * Sets the admin ministry.
     *
     * @param adminMinistry the adminMinistry to set
     */
    public void setAdminMinistry(final User adminMinistry) {
        this.adminMinistry = adminMinistry;
    }

    /**
     * Gets the minister.
     *
     * @return the minister
     */
    public User getMinister() {
        return minister;
    }

    /**
     * Sets the minister.
     *
     * @param minister the minister to set
     */
    public void setMinister(final User minister) {
        this.minister = minister;
    }

    /**
     * Gets the general secretary.
     *
     * @return the generalSecretary
     */
    public User getGeneralSecretary() {
        return generalSecretary;
    }

    /**
     * Sets the general secretary.
     *
     * @param generalSecretary the generalSecretary to set
     */
    public void setGeneralSecretary(final User generalSecretary) {
        this.generalSecretary = generalSecretary;
    }

    /**
     * Gets the organisms list.
     *
     * @return the organismsList
     */
    public List<Organism> getOrganismsList() {
        return organismsList;
    }

    /**
     * Sets the organisms list.
     *
     * @param organismsList the organismsList to set
     */
    public void setOrganismsList(final List<Organism> organismsList) {
        this.organismsList = organismsList;
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
        if (!(object instanceof Ministry)) {
            return false;
        }
        final Ministry other = (Ministry) object;
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
