package org.guce.epayment.core.repositories;

import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.entities.PaymentInvoiceVersion.PaymentInvoiceVersionID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface PaymentInvoiceVersionRepository extends JpaRepository<PaymentInvoiceVersion, PaymentInvoiceVersionID> {

}
