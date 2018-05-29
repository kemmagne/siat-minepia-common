package org.guce.epayment.core.services;

import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.Invoice;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceService {

    List<Invoice> findByNumber(String number);

    Optional<Invoice> findByNumberAndType(String number, Integer invoiceTypeId);

}
