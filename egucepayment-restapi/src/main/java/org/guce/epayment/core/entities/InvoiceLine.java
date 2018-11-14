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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "INVOICE_LINE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"INVOICE_VERSION_ID", "INVOICE_TYPE_ID", "BENEFICIARY_ID"})
})
@Data
@EqualsAndHashCode(of = {"invoiceVersion", "type", "beneficiary"})
public class InvoiceLine implements Serializable {

    private static final long serialVersionUID = 3044923487400615562L;

    @Id
    @SequenceGenerator(name = "INVOICE_LINE_SEQ_GEN", sequenceName = "INVOICE_LINE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_LINE_SEQ_GEN")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @NotNull
    @JoinColumn(name = "INVOICE_VERSION_ID")
    @ManyToOne
    private InvoiceVersion invoiceVersion;
    @NotNull
    @JoinColumn(name = "INVOICE_TYPE_ID")
    @ManyToOne
    private InvoiceType type;
    @NotNull
    @Column(name = "AMOUNT", precision = 38, scale = 4)
    private BigDecimal amount;
    @Column(name = "AMOUNT_TO_PAY", precision = 38, scale = 4)
    private BigDecimal amountToPay;
    @Transient
    private BigDecimal paidAmount;
    @NotNull
    @JoinColumn(name = "BENEFICIARY_ID")
    @ManyToOne
    private Partner beneficiary;
    @Column(name = "PAY_ACK_DATE")
    private LocalDateTime acknowledgmentDate;
    @Column(name = "BENEFICIARY_REFERENCE")
    private String benefReference;

}
