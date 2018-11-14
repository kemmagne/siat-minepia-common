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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "INVOICE_VERSION", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"INVOICE_ID", "VERSION_NUMBER"})
})
@Data
@EqualsAndHashCode(of = {"invoice", "number"})
public class InvoiceVersion implements Serializable {

    private static final long serialVersionUID = 7717412749558533531L;

    @Id
    @SequenceGenerator(name = "INVOICE_VERSION_SEQ", sequenceName = "INVOICE_VERSION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_VERSION_SEQ")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @NotNull
    @JoinColumn(name = "INVOICE_ID")
    @ManyToOne
    private Invoice invoice;
    @NotNull
    @Column(name = "VERSION_NUMBER")
    private int number;
    @NotNull
    @Column(name = "VERSION_DATE")
    private LocalDateTime date;
    @NotNull
    @Column(name = "VERSION_AMOUNT", precision = 38, scale = 4)
    private BigDecimal versionAmount;
    @NotNull
    @Column(name = "BALANCE_AMOUNT", precision = 38, scale = 4)
    private BigDecimal balanceAmount;
    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;
    @NotNull
    @Column(name = "E_GUCE_REFERENCE", length = 50)
    private String eGuceReference;
    @Column(name = "PAY_CONFIRM_DATE")
    private LocalDateTime confirmationDate;
    @OneToMany(mappedBy = "invoiceVersion", cascade = CascadeType.PERSIST)
    private List<InvoiceLine> invoiceLines;

    @PrePersist
    private void prePersist() {
        date = LocalDateTime.now();
    }
}

