package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class InvoiceDto {

    private BigDecimal id;
    private String invoiceNumber;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal amountToPay;
    private InvoiceTypeDto invoiceType;
    private InvoiceTypeDto subType;
    private List<InvoiceDto> subInvoices;
    private int versionNumber;
    private String status;
    private PartnerDto beneficiary;
    private PartnerDto owner;
    private boolean mended;
    private String versionDate;

}
