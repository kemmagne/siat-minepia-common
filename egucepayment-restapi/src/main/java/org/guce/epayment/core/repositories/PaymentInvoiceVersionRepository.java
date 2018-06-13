package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.entities.PaymentInvoiceVersion.PaymentInvoiceVersionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface PaymentInvoiceVersionRepository extends JpaRepository<PaymentInvoiceVersion, PaymentInvoiceVersionID> {

    Optional<PaymentInvoiceVersion> findByInvoiceVersiion(InvoiceVersion invoiceVersion);

    @Query("SELECT piv FROM PaymentInvoiceVersion piv WHERE piv.invoiceVersion.id = ?1")
    Optional<PaymentInvoiceVersion> findByInvoiceVersiion(BigDecimal invoiceVersionId);

}
