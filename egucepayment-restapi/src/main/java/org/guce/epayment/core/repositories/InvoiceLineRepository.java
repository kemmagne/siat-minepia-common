package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, BigDecimal> {

    @Query("SELECT il FROM InvoiceLine il WHERE il.type = ?1 AND il.beneficiary = ?2 AND il.invoiceVersion.invoice = ?3 ORDER BY il.invoiceVersion.number DESC")
    List<InvoiceLine> find(InvoiceType invoiceType, Partner beneficiary, Invoice invoice);

}
