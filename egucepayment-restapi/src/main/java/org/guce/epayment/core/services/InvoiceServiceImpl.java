package org.guce.epayment.core.services;

import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findByNumber(String number) {
        return invoiceRepository.findByNumber(number);
    }

    @Override
    public Optional<Invoice> findByNumberAndType(String number, Integer invoiceTypeId) {
        return invoiceRepository.findByNumberAndType(number, invoiceTypeId);
    }

}
