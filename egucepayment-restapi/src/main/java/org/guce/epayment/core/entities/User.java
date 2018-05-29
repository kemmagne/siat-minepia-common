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
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.utils.Constants;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "USERS")
@Data
@EqualsAndHashCode(of = {"id", "login"})
public class User implements Serializable {

    private static final long serialVersionUID = 6669430757032785372L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID", precision = 38)
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    private BigDecimal id;

    @Column(name = "LOGIN", unique = true, nullable = false, length = 100)
    private String login;
    @Column(name = "TITLE", nullable = false, length = 5)
    private String title;
    @Column(name = "LOCALE", nullable = false, length = 2)
    private String locale;
    @Column(name = "FIRST_NAME", length = 100)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;
    @Column(name = "RESET_PASSWORD")
    private boolean resetPassword;
    @Column(name = "LOCKED")
    private boolean locked;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;
    @JoinColumn(name = "PARTNER_ID", nullable = false)
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
//    @OneToMany(mappedBy = "connectedUser")
//    private List<CoreConnection> connections;
//    @OneToMany(mappedBy = "userOwner", cascade = CascadeType.PERSIST)
//    @OrderBy("active DESC")
//    private List<CoreUserCertificate> certificates;

    @PrePersist
    private void prePersist() {

        creationDate = LocalDateTime.now();
        if (locale == null) {
            locale = Constants.LOCALE_FR;
        }
    }

}
