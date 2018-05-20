package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class InvoiceVersionDto {

    private String invoiceNumber;
    private BigDecimal amount;
    private String invoiceTypeCode;
    private String subTypeCode;
    private String taxPayerNumber;
    private String beneficiaryCode;
    private List<InvoiceVersionDto> subInvoices;

}
