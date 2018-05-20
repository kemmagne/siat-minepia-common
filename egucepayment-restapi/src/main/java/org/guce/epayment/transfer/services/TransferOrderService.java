package org.guce.epayment.transfer.services;

import java.util.List;
import java.util.Map;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.User;

/**
 *
 * @author tadzotsa
 */
public interface TransferOrderService {

    void init(User connectedUser, InvoiceType invoiceType, List<Map<String, Object>> invoices, Partner beneficiary,
            Partner taxPayer, PaymentMode paymentMode, BankAccount debitAccount, String partnerReference,
            String originMessage, String signature);

    void validate(String toReference, User connectedUser, String originMessage, String signature, boolean valid);

}
