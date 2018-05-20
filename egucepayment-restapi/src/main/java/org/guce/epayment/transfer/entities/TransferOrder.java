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

    @JoinColumn(name = "DEBIT_ACCOUNT", nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private BankAccount debitAccount;
    @JoinColumn(name = "RECEPTIVE_AGENCY")
    @ManyToOne
    private Partner receptiveAgency;
    @JoinColumn(name = "INVOICE_TYPE", nullable = false)
    @OneToOne
    private InvoiceType invoiceType;
    @JoinColumn(name = "CREDIT_ACCOUNT")
    @OneToOne
    private BankAccount creditAccount;
    @JoinColumn(name = "BENEFICIARY_AGENCY")
    @ManyToOne
    private Partner beneficiaryAgency;
    @JoinColumn(name = "BENEFICIARY_BANK")
    @ManyToOne
    private Partner beneficiaryBank;
    @Column(name = "FLOW_IN_USE", nullable = false)
    private String flowInUse;
    @Column(name = "OLD_TRANSFER_NUMBER", length = 40)
    private String oldTransferNumber;
    @Column(name = "SITTING_DATE")
    private LocalDateTime sittingDate;
    @JoinColumn(name = "TAX_PAYER", nullable = false)
    @ManyToOne
    private Partner taxPayer;
    @JoinColumn(name = "BENEFICIARY")
    @ManyToOne
    private Partner beneficiary;

}
