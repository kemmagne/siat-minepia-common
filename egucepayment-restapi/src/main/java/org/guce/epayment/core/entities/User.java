package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.utils.Constants;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "USERS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"LOGIN", "DELETED"})
})
@Data
@EqualsAndHashCode(of = {"login", "deleted"})
public class User implements Serializable {

    private static final long serialVersionUID = 6669430757032785372L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID", precision = 38)
    @SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
    private BigDecimal id;

    @NotNull
    @Column(name = "LOGIN", length = 100)
    private String login;
    @NotNull
    @Column(name = "TITLE", length = 5)
    private String title;
    @NotNull
    @Column(name = "LOCALE", length = 2)
    private String locale;
    @Column(name = "FIRST_NAME", length = 100)
    private String firstName;
    @NotNull
    @Column(name = "LAST_NAME", length = 100)
    private String lastName;
    @NotNull
    @Column(name = "EMAIL", length = 100)
    private String email;
    @Column(name = "RESET_PASSWORD")
    private boolean resetPassword;
    @Column(name = "LOCKED")
    private boolean locked;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "DELETED")
    private boolean deleted;
    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
    @NotNull
    @JoinColumn(name = "PARTNER_ID")
    @ManyToOne
    private Partner partner;

    @JoinTable(name = "USER_ROLE", joinColumns = {
        @JoinColumn(name = "USER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Role> roles;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.ALL})
    @OrderBy("creationDate DESC")
    private List<Credentials> credentialses;

    @OneToMany(mappedBy = "user")
    private List<Signature> signatures;
    @OneToMany(mappedBy = "user")
    private List<UserStep> userSteps;
    @ManyToMany(mappedBy = "decisionMakers")
    private List<InvoiceType> invoiceTypes;

    @PrePersist
    private void prePersist() {

        creationDate = LocalDateTime.now();
        if (locale == null) {
            locale = Constants.LOCALE_FR;
        }
    }

}

