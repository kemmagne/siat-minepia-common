package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "INVOICE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"INVOICE_NUMBER", "TYPE_ID"})
})
@Data
@EqualsAndHashCode(of = {"id"})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1947287922496518906L;

    public static final String INVOICE_UNPAID = "UNPAID";
    public static final String INVOICE_PAYMENT_STARTED = "PAYMENT_STARTED";
    public static final String INVOICE_PAYMENT_CANCELED = "PAYMENT_CANCELED";
    public static final String INVOICE_PAYMENT_REJECTED = "PAYMENT_REJECTED";
    public static final String INVOICE_PAID = "PAID";
    public static final String INVOICE_CLEARED = "CLEARED";

    @Id
    @SequenceGenerator(name = "INVOICE_SEQ_GEN", sequenceName = "INVOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_SEQ_GEN")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @Column(name = "INVOICE_NUMBER", nullable = false, length = 50)
    private String number;
    @Column(name = "AMOUNT", nullable = false, precision = 38, scale = 4)
    private BigDecimal amount;
    @Column(name = "PAID_AMOUNT", nullable = false, precision = 38, scale = 4)
    private BigDecimal paidAmount;
    @Column(name = "LAST_VERSION_NUMBER", nullable = false)
    private int lastVersionNumber;
    @Column(name = "LAST_VERSION_DATE", nullable = false)
    private LocalDateTime lastVersionDate;
    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;
    @JoinColumn(name = "TYPE_ID", nullable = false)
    @ManyToOne
    private InvoiceType type;
    @JoinColumn(name = "SUB_TYPE_ID")
    @ManyToOne
    private InvoiceType subType;
    @JoinColumn(name = "OWNER_ID", nullable = false)
    @ManyToOne
    private Partner owner;
    @JoinColumn(name = "BENEFICIARY_ID")
    @ManyToOne
    private Partner beneficiary;
    @OneToMany(mappedBy = "invoice", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<InvoiceVersion> invoiceVersions;
    @JoinColumn(name = "PARENT_ID")
    @ManyToOne
    private Invoice parent;
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Invoice> subInvoices;
    @Column(name = "BENEF_REFERENCE", length = 30)
    private String benefReference;
    @Column(name = "OLD_NUMBER", length = 100)
    private String oldNumber;

    @PrePersist
    private void prePersist() {

        paidAmount = BigDecimal.ZERO;
        status = INVOICE_UNPAID;
        lastVersionDate = LocalDateTime.now();
        lastVersionNumber = 0;

        forSubInvoice();
    }

    @PreUpdate
    private void preUpdate() {
        forSubInvoice();
    }

    private void forSubInvoice() {

        if (parent != null) {

            if (!paidAmount.equals(amount)) {
                status = parent.getStatus();
            }

            lastVersionDate = parent.getLastVersionDate();
            lastVersionNumber = parent.getLastVersionNumber();
        }
    }

}
