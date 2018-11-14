package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceService {

    List<Invoice> findByNumber(String number);

    Optional<Invoice> findByNumberAndType(String number, Integer invoiceTypeId);

    Optional<Invoice> findByNumberAndType(String number, String invoiceTypeCode);

    Optional<InvoiceVersion> findByInvoiceAndNumber(BigDecimal invoiceId, int number);

    List<InvoiceLine> find(InvoiceType invoiceType, Partner beneficiary, Invoice invoice);

}

