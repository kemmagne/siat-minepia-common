package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceVersionRepository extends JpaRepository<InvoiceVersion, BigDecimal> {

    @Query("SELECT iv FROM InvoiceVersion iv WHERE iv.invoice.id = ?1 AND iv.number = ?2")
    Optional<InvoiceVersion> findByInvoiceAndNumber(BigDecimal invoiceId, int number);

}
