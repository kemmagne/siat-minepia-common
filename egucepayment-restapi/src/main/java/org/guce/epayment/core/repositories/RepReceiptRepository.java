package org.guce.epayment.core.repositories;

import java.util.List;
import org.guce.epayment.core.entities.RepReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface RepReceiptRepository extends JpaRepository<RepReceipt, String> {

    @Query("SELECT r FROM RepReceipt r WHERE r.invoice.type.code = ?1")
    List<RepReceipt> findByInvoiceType(String invoiceTypeCode);

}
