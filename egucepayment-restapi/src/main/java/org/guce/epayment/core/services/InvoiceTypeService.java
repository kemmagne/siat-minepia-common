package org.guce.epayment.core.services;

import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceTypeBeneficiary;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceTypeService {

    List<InvoiceType> findByPaymentModes(String pms);

    void setDebitAccount(InvoiceTypeBeneficiary ivtb);

    List<InvoiceType> findByStandaloneAndSubType(boolean standalone, boolean subType);

    Optional<BankAccount> findBankAccount(String invoiceTypeCode, String beneficiaryCode);

}
