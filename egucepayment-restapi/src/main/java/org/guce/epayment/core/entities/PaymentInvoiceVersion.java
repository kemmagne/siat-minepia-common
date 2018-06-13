package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@IdClass(PaymentInvoiceVersion.PaymentInvoiceVersionID.class)
@Table(name = "PAYMENT_INVOICE_VERSION", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"INVOICE_VERSION_ID"})
})
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
    @Column(name = "AMOUNT_PAID_FOR_INVOICE", nullable = false, precision = 38, scale = 4)
    private BigDecimal amountForInvoice;
    @Column(name = "RATE_USED_FOR_INVOICE", nullable = false)
    private Integer rateUsedForInvoice;

    public PaymentInvoiceVersion() {
    }

    public PaymentInvoiceVersion(final Payment payment, final InvoiceVersion invoiceVersion, final BigDecimal amountForInvoice, final Integer rateUsedForInvoice) {
        this.payment = payment;
        this.invoiceVersion = invoiceVersion;
        this.amountForInvoice = amountForInvoice;
        this.rateUsedForInvoice = rateUsedForInvoice;
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
