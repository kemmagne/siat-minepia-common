package org.guce.epayment.rest.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"reference"})
public class TransferOrderDto {

    private String reference;
    private String partnerReference;
    private String amount;
    private PartnerDto commiter;
    private InvoiceTypeDto invoiceType;
    private String status;
    private String startedDate;
    private String decisionDate;
    private String sittingDate;
    private PartnerDto taxPayer;
    private PartnerDto beneficiary;
    private List<InvoiceDto> invoices;
    private List<SignatureDto> signatures;

}
