package org.guce.epayment.transfer.services;

import java.util.List;
import java.util.Map;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.epayment.transfer.models.FilterTransferOrder;

/**
 *
 * @author tadzotsa
 */
public interface TransferOrderService {

    void init(User connectedUser, InvoiceType invoiceType, List<Map<String, Object>> invoices, Partner beneficiary,
            Partner taxPayer, PaymentMode paymentMode, BankAccount debitAccount, String partnerReference,
            String originMessage, String signature);

    void validate(String toReference, User connectedUser, String originMessage, String signature, boolean valid);

    List<TransferOrder> findPartnerTransferOrders(User connectedUser, int start, int end, boolean count);

    List<TransferOrder> findPartnerTransferOrders(User connectedUser, boolean toValidate, int start, int end, boolean count);

    Object filterTransferOrders(FilterTransferOrder filter, String code);

    Object findTransferOrdersPeriodically(int type, int period, String code, int start, int end, boolean count);

    List<TransferOrder> findLastTransferOrders(int type, String code, int number);

    Object findByTosUser(String userLogin, int start, int end, boolean count);

    List getTosStats(int type, boolean bank);

    Object getAcknowledTransferOrders(String benefCode, int start, int end, boolean count);

}
