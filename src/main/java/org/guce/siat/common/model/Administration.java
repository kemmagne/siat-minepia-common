package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Administration.
 */
@Entity
@Table(name = "ADMINISTRATION")
@XmlRootElement
@Inheritance(strategy = InheritanceType.JOINED)
public class Administration extends AbstractModel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "ADMINISTRATION_SEQ", sequenceName = "ADMINISTRATION_SEQ", allocationSize = 1, initialValue = 500)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMINISTRATION_SEQ")
    protected Long id;

    /**
     * The label fr.
     */
    @Basic(optional = false)
    @Column(name = "LABEL_FR")
    protected String labelFr;

    /**
     * The label en.
     */
    @Basic(optional = false)
    @Column(name = "LABEL_EN")
    protected String labelEn;

    /**
     * The deleted.
     */
    @Column(name = "DELETED")
    protected Boolean deleted;

    /**
     * The users list.
     */
    @OneToMany(mappedBy = "administration")
    private List<User> usersList;

    /**
     * Instantiates a new administration.
     */
    public Administration() {
    }

    /**
     * Instantiates a new administration.
     *
     * @param id
     */
    public Administration(final Long id) {
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
     * @param id the id to set
     */
    @Override
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

    /**
     * @return the usersList
     */
    public List<User> getUsersList() {
        return usersList;
    }

    /**
     * @param usersList the usersList to set
     */
    public void setUsersList(final List<User> usersList) {
        this.usersList = usersList;
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
        if (!(object instanceof Administration)) {
            return false;
        }
        final Administration other = (Administration) object;
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
