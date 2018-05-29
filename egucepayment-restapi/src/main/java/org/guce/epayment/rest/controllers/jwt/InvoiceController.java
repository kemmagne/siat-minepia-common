package org.guce.epayment.rest.controllers.jwt;

import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.models.FilterInvoice;
import org.guce.epayment.core.services.InvoiceService;
import org.guce.epayment.core.services.SearchService;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("jwt")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private SearchService searchService;

    @ResponseBody
    @RequestMapping(path = "public/invoices/by-number/{invoiceNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceDto>> findInvoiceByNumber(@PathVariable String invoiceNumber) {

        final List<Invoice> invoicesFound = invoiceService.findByNumber(invoiceNumber);

        return ResponseEntity.ok(invoicesFound.stream().map(
                invoice -> RestUtils.getInvoiceDto(invoice)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "invoices/filter", method = RequestMethod.POST)
    public ResponseEntity findInvoices(@RequestBody FilterInvoice filter) {

        if (!filter.isCount()) {

            final List<Invoice> invoicesFound = (List<Invoice>) searchService.searchInvoices(filter);

            return ResponseEntity.ok(invoicesFound.stream().map(
                    invoice -> RestUtils.getInvoiceDto(invoice)
            ).collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(DefaultDto.of(searchService.searchInvoices(filter).toString()));
        }
    }

}
