package org.guce.epayment.core.services;

import java.util.List;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceTypeBeneficiary;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceTypeService {

    List<InvoiceType> findByPaymentModes(String pms);

    void setDebitAccount(InvoiceTypeBeneficiary ivtb);

}
