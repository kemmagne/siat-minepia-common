package org.guce.epayment.core.entities;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "INVOICE_TYPE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODE", "DELETED"})
})
@Data
@EqualsAndHashCode(of = {"code", "deleted"})
public class InvoiceType implements Serializable {

    private static final long serialVersionUID = 5778626176562521049L;

    public static final String INVOICE_TYPE_CUSDEC = "CUSDEC";

    @Id
    @SequenceGenerator(name = "INVOICE_TYPE_SEQ", sequenceName = "INVOICE_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_TYPE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "CODE", length = 30)
    private String code;
    @NotNull
    @Column(name = "LABEL")
    private String label;

    @Column(name = "DELETED")
    private boolean deleted;

    /**
     * dernière étape pour les factures de ce type : transfer.last.step
     *
     * agence de réception (bénéficiaire) des virements pour le paiement des
     * factures de ce type : beneficiary.agency ; le code d'une agence est
     * formaté de la façon suivante code_banque#code_agence
     *
     * reliquat ou pas pour les redressements : balance (true or false) si non
     * définie la valeur est considérée comme étant égal à false
     *
     * taux applicable pour l'apurement des factures : asset.rate : entier
     * compris entre 1 et 100
     *
     * le séparateur est \n
     */
    @Column(name = "PARAMS", length = 500)
    private String parameters;

    @JoinTable(name = "INVOICE_TYPE_PAYMENT_MODE", joinColumns = {
        @JoinColumn(name = "INVOICE_TYPE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PAYMENT_MODE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<PaymentMode> paymentModes;

    @OneToMany(mappedBy = "invoiceType", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<InvoiceTypeBeneficiary> beneficiaries;

    @JoinTable(name = "INVOICE_TYPE_EXCLUDED_BANKS", joinColumns = {
        @JoinColumn(name = "INVOICE_TYPE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EXCLUDED_BANK_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Partner> unauthorizedBanks;

    @JoinTable(name = "INVOICE_TYPE_DECISION_MAKER", joinColumns = {
        @JoinColumn(name = "INVOICE_TYPE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "DECISION_MAKER_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<User> decisionMakers;

    @Column(name = "STANDALONE")
    private boolean standalone;
    @Column(name = "SUB_TYPE")
    private boolean subType;

}

