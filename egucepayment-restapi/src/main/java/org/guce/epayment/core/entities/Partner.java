package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "PARTNER", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODE", "DELETED"})
})
@Data
@EqualsAndHashCode(of = {"code", "deleted"})
public class Partner implements Serializable {

    private static final long serialVersionUID = 1501487064137944532L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID", precision = 38)
    @SequenceGenerator(name = "PARTNER_SEQ", sequenceName = "PARTNER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_SEQ")
    private BigDecimal id;

    @NotNull
    @Column(name = "CODE", length = 50)
    private String code;
    @NotNull
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "PARENT_ID")
    @ManyToOne
    private Partner parent;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "DELETED")
    private boolean deleted;
    @JoinColumn(name = "MAIN_BANK_ACCOUNT_ID")
    @OneToOne
    private BankAccount principalBankAccount;

    @OneToMany(mappedBy = "partner")
    private List<User> users;
    @OneToMany(mappedBy = "parent")
    private List<Partner> children;
    @OneToMany(mappedBy = "commiter")
    private List<Payment> paymentsCommited;
    @JoinTable(name = "PARTNER_PARTNER_TYPE", joinColumns = {
        @JoinColumn(name = "PARTNER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PARTNER_TYPE_ID", referencedColumnName = "ID")})
    @ManyToMany
    @OrderBy(value = "code ASC")
    private List<PartnerType> types;
    @JoinTable(name = "PARTNER_PARTNER_GROUP", joinColumns = {
        @JoinColumn(name = "PARTNER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PARTNER_GROUP_ID", referencedColumnName = "CODE")})
    @ManyToMany
    private List<RepPartnerGroup> groups;
    @OneToMany(mappedBy = "partner")
    private List<PartnerStep> partnerSteps;
    @OneToMany(mappedBy = "owner")
    private List<Asset> ownAssets;
    @OneToMany(mappedBy = "currentUser")
    private List<Asset> assetsClaimed;
    @OneToMany(mappedBy = "declarer")
    private List<Asset> assetsDeclared;
    @OneToMany(mappedBy = "owner")
    private List<BankAccount> bankAccounts;

}

