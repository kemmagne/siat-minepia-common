package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface ReceiptRepository extends JpaRepository<Receipt, BigDecimal> {

    @Query("SELECT r FROM Receipt r WHERE r.invoiceLine.invoiceVersion.invoice.type.code = ?1")
    List<Receipt> findByInvoiceType(String invoiceTypeCode);

}

