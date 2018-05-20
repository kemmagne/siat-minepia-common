package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@IdClass(InvoiceTypeBeneficiary.InvoiceTypeBeneficiaryID.class)
@Table(name = "INVOICE_TYPE_BENEFICIARY")
@Data
@EqualsAndHashCode(exclude = "account")
public class InvoiceTypeBeneficiary implements Serializable {

    private static final long serialVersionUID = 3946197693988049886L;

    @Id
    @JoinColumn(name = "INVOICE_TYPE_ID")
    @ManyToOne
    private InvoiceType invoiceType;
    @Id
    @JoinColumn(name = "BENEFICIARY_ID")
    @ManyToOne
    private Partner beneficiary;
    @JoinColumn(name = "BANK_ACCOUNT_ID")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BankAccount account;

    public InvoiceTypeBeneficiary() {
    }

    public InvoiceTypeBeneficiary(final InvoiceType invoiceType, final Partner beneficiary) {
        this.invoiceType = invoiceType;
        this.beneficiary = beneficiary;
    }

    @Data
    public static class InvoiceTypeBeneficiaryID implements Serializable {

        private static final long serialVersionUID = 1992581868549554427L;

        private Integer invoiceType;
        private BigDecimal beneficiary;

        public InvoiceTypeBeneficiaryID() {
        }

        public InvoiceTypeBeneficiaryID(final Integer invoiceType, final BigDecimal beneficiary) {
            this.invoiceType = invoiceType;
            this.beneficiary = beneficiary;
        }

    }

}
