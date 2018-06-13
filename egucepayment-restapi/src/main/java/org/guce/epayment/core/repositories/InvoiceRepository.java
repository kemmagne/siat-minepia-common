package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceRepository extends JpaRepository<Invoice, BigDecimal> {

    List<Invoice> findByNumber(String number);

    @Query("SELECT i FROM Invoice i WHERE i.number = ?1 AND i.type.id = ?2")
    Optional<Invoice> findByNumberAndType(String number, Integer invoiceTypeId);

    @Query("SELECT i FROM Invoice i WHERE i.number = ?1 AND i.type.code = ?2")
    Optional<Invoice> findByNumberAndType(String number, String invoiceTypeCode);

}
