package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"number"})
public class ReceiptDto {

    private String number;
    private InvoiceDto invoice;
    private String date;

    public ReceiptDto() {
    }

    private ReceiptDto(String number, InvoiceDto invoice, String date) {
        this.number = number;
        this.invoice = invoice;
        this.date = date;
    }

    public static ReceiptDto of(String number, InvoiceDto invoice, String date) {
        return new ReceiptDto(number, invoice, date);
    }

}
