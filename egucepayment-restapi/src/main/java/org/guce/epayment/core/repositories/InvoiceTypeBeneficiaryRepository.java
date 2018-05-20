package org.guce.epayment.core.repositories;

import java.util.Optional;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.InvoiceTypeBeneficiary;
import org.guce.epayment.core.entities.InvoiceTypeBeneficiary.InvoiceTypeBeneficiaryID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceTypeBeneficiaryRepository extends JpaRepository<InvoiceTypeBeneficiary, InvoiceTypeBeneficiaryID> {

    @Query("SELECT ivtb.account FROM InvoiceTypeBeneficiary ivtb WHERE ivtb.invoiceType.code = ?1 AND ivtb.beneficiary.code = ?2")
    Optional<BankAccount> findBankAccount(String invoiceTypeCode, String beneficiaryCode);

}
