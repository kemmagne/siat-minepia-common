package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.repositories.InvoiceLineRepository;
import org.guce.epayment.core.repositories.InvoiceRepository;
import org.guce.epayment.core.repositories.InvoiceVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceVersionRepository invoiceVersionRepository;
    @Autowired
    private InvoiceLineRepository invoiceLineRepository;

    @Override
    public List<Invoice> findByNumber(String number) {
        return invoiceRepository.findByNumber(number);
    }

    @Override
    public Optional<Invoice> findByNumberAndType(String number, Integer invoiceTypeId) {
        return invoiceRepository.findByNumberAndType(number, invoiceTypeId);
    }

    @Override
    public Optional<Invoice> findByNumberAndType(String number, String invoiceTypeCode) {
        return invoiceRepository.findByNumberAndType(number, invoiceTypeCode);
    }

    @Override
    public Optional<InvoiceVersion> findByInvoiceAndNumber(BigDecimal invoiceId, int number) {
        return invoiceVersionRepository.findByInvoiceAndNumber(invoiceId, number);
    }

    @Override
    public List<InvoiceLine> find(InvoiceType invoiceType, Partner beneficiary, Invoice invoice) {
        return invoiceLineRepository.find(invoiceType, beneficiary, invoice);
    }

}
