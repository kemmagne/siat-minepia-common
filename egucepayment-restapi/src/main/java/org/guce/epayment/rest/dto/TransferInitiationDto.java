package org.guce.epayment.rest.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class TransferInitiationDto {

    private List<InvoiceDto> invoices;
    private BankAccountDto debitAccout;
    private String partnerRef;
    private String paymentModeCode;
    private String originMessage;
    private String privateKey;

}
