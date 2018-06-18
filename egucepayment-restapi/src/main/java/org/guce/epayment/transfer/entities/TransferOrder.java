package org.guce.epayment.transfer.entities;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Payment;

@Entity
@Table(name = "TRANSFER_ORDER")
@DiscriminatorValue("TRANSFER_ORDER")
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class TransferOrder extends Payment {

    private static final long serialVersionUID = -1493948559807779437L;

    @JoinColumn(name = "DEBIT_ACCOUNT_ID", nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private BankAccount debitAccount;
    @JoinColumn(name = "RECEPTIVE_AGENCY_ID")
    @ManyToOne
    private Partner receptiveAgency;
    @JoinColumn(name = "INVOICE_TYPE_ID", nullable = false)
    @OneToOne
    private InvoiceType invoiceType;
    @JoinColumn(name = "CREDIT_ACCOUNT_ID")
    @OneToOne
    private BankAccount creditAccount;
    @JoinColumn(name = "BENEFICIARY_AGENCY_ID")
    @ManyToOne
    private Partner beneficiaryAgency;
    @JoinColumn(name = "BENEFICIARY_BANK_ID")
    @ManyToOne
    private Partner beneficiaryBank;
    @JoinColumn(name = "TAX_PAYER_ID", nullable = false)
    @ManyToOne
    private Partner taxPayer;
    @Column(name = "FLOW_IN_USE", nullable = false)
    private String flowInUse;
    @Column(name = "SITTING_DATE")
    private LocalDateTime sittingDate;
    @Column(name = "OLD_TO_NUMBER", length = 40)
    private String oldToNumber;

}
