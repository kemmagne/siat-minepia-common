package org.guce.epayment.rest.controllers;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.RepReceipt;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.ReceiptDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("admin")
public class ReceiptController {

    @Autowired
    private CoreService coreService;

    @ResponseBody
    @RequestMapping("receipts/by-invoice-type/{invoiceTypeCode}")
    public ResponseEntity<List<ReceiptDto>> findReceiptByInvoiceType(@PathVariable String invoiceTypeCode) throws Exception {

        final List<RepReceipt> receiptsByInvoiceType = coreService.findReceiptsByInvoiceType(invoiceTypeCode);

        return ResponseEntity.ok(receiptsByInvoiceType.stream().map(
                receipt -> {

                    final ReceiptDto receiptDto = new ReceiptDto();

                    receiptDto.setDate(receipt.getReceiptDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)));
                    receiptDto.setInvoice(RestUtils.getInvoiceDto(receipt.getInvoice()));

                    return receiptDto;
                }
        ).collect(Collectors.toList()));
    }

}
