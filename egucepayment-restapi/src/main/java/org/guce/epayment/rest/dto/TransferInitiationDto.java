package org.guce.epayment.rest.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class TransferInitiationDto implements Serializable {

    private static final long serialVersionUID = 3277590058994117049L;

    private List<InvoiceDto> invoices;
    private BankAccountDto debitAccout;
    private String partnerRef;
    private String paymentModeCode;
    private String originMessage;
    private String privateKey;

}

