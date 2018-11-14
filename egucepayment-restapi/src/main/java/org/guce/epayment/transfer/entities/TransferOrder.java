package org.guce.epayment.transfer.entities;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Payment;

@Entity
@Table(name = "TRANSFER_ORDER")
@DiscriminatorValue("TRANSFER_ORDER")
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class TransferOrder extends Payment {

    private static final long serialVersionUID = -1493948559807779437L;

    @NotNull
    @JoinColumn(name = "DEBIT_ACCOUNT_ID")
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private BankAccount debitAccount;
    @NotNull
    @JoinColumn(name = "TAX_PAYER_ID")
    @ManyToOne
    private Partner taxPayer;
    @NotNull
    @Column(name = "FLOW_IN_USE")
    private String flowInUse;
    @Column(name = "RECEIPT_DATE")
    private LocalDateTime receiptDate;

    @JoinTable(name = "TRANSFER_ORDER_BANK_ACCOUNT", joinColumns = {
        @JoinColumn(name = "TRANSFER_ORDER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "BANK_ACCOUNT_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<BankAccount> bankAccounts;

}

