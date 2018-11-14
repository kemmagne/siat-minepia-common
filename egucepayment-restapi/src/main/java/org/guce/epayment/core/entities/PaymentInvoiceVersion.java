package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@IdClass(PaymentInvoiceVersion.PaymentInvoiceVersionID.class)
@Table(name = "PAYMENT_INVOICE_VERSION")
@Data
@EqualsAndHashCode(of = {"payment", "invoiceVersion"})
public class PaymentInvoiceVersion implements Serializable {

    private static final long serialVersionUID = 6958170188901993685L;

    @Id
    @JoinColumn(name = "PAYMENT_ID")
    @ManyToOne
    private Payment payment;
    @Id
    @JoinColumn(name = "INVOICE_VERSION_ID")
    @ManyToOne
    private InvoiceVersion invoiceVersion;
    @NotNull
    @Column(name = "AMOUNT_PAID_FOR_INVOICE", precision = 38, scale = 4)
    private BigDecimal amount;
    @NotNull
    @Column(name = "RATE_USED_FOR_INVOICE")
    private Integer rateUsed;

    public PaymentInvoiceVersion() {
    }

    public PaymentInvoiceVersion(final Payment payment, final InvoiceVersion invoiceVersion, final BigDecimal amount, final Integer rateUsed) {
        this.payment = payment;
        this.invoiceVersion = invoiceVersion;
        this.amount = amount;
        this.rateUsed = rateUsed;
    }

    @PrePersist
    private void prePersist() {
        if (rateUsed == null) {
            rateUsed = 100;
        }
    }

    @Data
    public static class PaymentInvoiceVersionID implements Serializable {

        private static final long serialVersionUID = -1828875036471173305L;

        private BigDecimal payment;
        private BigDecimal invoiceVersion;

        public PaymentInvoiceVersionID() {
        }

        public PaymentInvoiceVersionID(final BigDecimal payment, final BigDecimal invoiceVersion) {
            this.payment = payment;
            this.invoiceVersion = invoiceVersion;
        }

    }
}

