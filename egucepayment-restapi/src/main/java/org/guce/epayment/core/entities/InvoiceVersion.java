package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "INVOICE_VERSION", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"INVOICE_ID", "VERSION_NUMBER"})
})
@Data
@EqualsAndHashCode(of = {"id", "invoice", "number"})
public class InvoiceVersion implements Serializable {

    private static final long serialVersionUID = 7717412749558533531L;

    @Id
    @SequenceGenerator(name = "INVOICE_VERSION_SEQ", sequenceName = "INVOICE_VERSION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_VERSION_SEQ")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @JoinColumn(name = "INVOICE_ID", nullable = false)
    @ManyToOne
    private Invoice invoice;
    @Column(name = "VERSION_NUMBER", nullable = false)
    private int number;
    @Column(name = "VERSION_DATE", nullable = false)
    private LocalDateTime date;
    @Column(name = "VERSION_AMOUNT", nullable = false, precision = 38, scale = 4)
    private BigDecimal versionAmount;
    @Column(name = "BALANCE_AMOUNT", nullable = false, precision = 38, scale = 4)
    private BigDecimal balanceAmount;
    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;
    @Column(name = "PAY_CONFIRM_DATE")
    private LocalDateTime confirmationDate;
    @Column(name = "PAY_ACK_DATE")
    private LocalDateTime acknowledgmentDate;
    @Column(name = "E_GUCE_REFERENCE", length = 50)
    private String eGuceReference;
    @Column(name = "CODEDAP", length = 20)
    private Long codedap;
    @Column(name = "OLD_TO_NUMBER", length = 40)
    private String oldToNumber;

    @PrePersist
    private void prePersist() {
        date = LocalDateTime.now();
    }
}
