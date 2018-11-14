package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "REP_RECEIPT")
@Data
@EqualsAndHashCode(of = {"id", "number"})
public class Receipt implements Serializable {

    private static final long serialVersionUID = -5911581576367380906L;

    @Id
    @Column(name = "ID", precision = 38)
    @SequenceGenerator(name = "RECEIPT_SEQ", sequenceName = "RECEIPT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEIPT_SEQ")
    private BigDecimal id;

    @NotNull
    @Column(name = "RECEIPT_NUMBER")
    private String number;
    @NotNull
    @Column(name = "RECEIPT_DATE")
    private LocalDate receiptDate;
    @NotNull
    @JoinColumn(name = "INVOICE_LINE_ID")
    @ManyToOne
    private InvoiceLine invoiceLine;
    @NotNull
    @JoinColumn(name = "PAYMENT_ID")
    @OneToOne
    private Payment payment;
    @Column(name = "METADATA", length = 500)
    private String metadata;

}

