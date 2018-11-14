package org.guce.epayment.core.entities;

import org.guce.epayment.core.entities.enums.InvoiceStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
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

    @Id
    @SequenceGenerator(name = "INVOICE_SEQ_GEN", sequenceName = "INVOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_SEQ_GEN")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @NotNull
    @Column(name = "INVOICE_NUMBER", length = 50)
    private String number;
    @NotNull
    @Column(name = "AMOUNT", precision = 38, scale = 4)
    private BigDecimal amount;
    @NotNull
    @Column(name = "PAID_AMOUNT", precision = 38, scale = 4)
    private BigDecimal paidAmount;
    @Transient
    private BigDecimal amountToPay;
    @NotNull
    @Column(name = "LAST_VERSION_NUMBER")
    private int lastVersionNumber;
    @NotNull
    @Column(name = "LAST_VERSION_DATE")
    private LocalDateTime lastVersionDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    private InvoiceStatus status;
    @NotNull
    @JoinColumn(name = "TYPE_ID")
    @ManyToOne
    private InvoiceType type;
    @JoinColumn(name = "SUB_TYPE_ID")
    @ManyToOne
    private InvoiceType subType;
    @NotNull
    @JoinColumn(name = "OWNER_ID")
    @ManyToOne
    private Partner owner;
    @OneToMany(mappedBy = "invoice", cascade = {CascadeType.PERSIST})
    @OrderBy("number DESC")
    private List<InvoiceVersion> invoiceVersions;
    @Column(name = "BENEF_REFERENCE", length = 30)
    private String benefReference;

    @PrePersist
    private void prePersist() {

        paidAmount = BigDecimal.ZERO;
        status = InvoiceStatus.UNPAID;
        lastVersionDate = LocalDateTime.now();
        lastVersionNumber = 0;
    }

    @PreUpdate
    private void preUpdate() {
        if (InvoiceStatus.UNPAID.equals(status)) {
            lastVersionDate = LocalDateTime.now();
            lastVersionNumber = invoiceVersions.size();
        }
    }

}

