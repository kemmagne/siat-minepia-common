package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yenke
 */
@Entity
@Table(name = "TRANSFER")
public class Transfer extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 2977636569762088374L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSFER_SEQ")
    @SequenceGenerator(name = "TRANSFER_SEQ", sequenceName = "TRANSFER_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;

    /**
     * The file.
     */
    @ManyToOne
    @JoinColumn(name = "FILE_ID", referencedColumnName = "ID", nullable = false)
    private File file;

    @Column(name = "NUMERO_DEMANDE", length = 35)
    private String numeroDemande;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_USER_ID", referencedColumnName = "ID", nullable = false)
    private User assignedUser;

    /**
     * The created date.
     */
    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    private void prePersist() {
        if (createdDate == null) {
            createdDate = Calendar.getInstance().getTime();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Transfer other = (Transfer) obj;
        return Objects.equals(this.id, other.id);
    }

}
