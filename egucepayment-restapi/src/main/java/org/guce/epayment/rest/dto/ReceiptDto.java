package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.Receipt;

/**
 *
 * @author tadzotsa
 */
public class ReceiptDto extends Receipt {

    private static final long serialVersionUID = 6272926264879212354L;

    @JsonIgnore
    @Override
    public InvoiceLine getInvoiceLine() {
        return super.getInvoiceLine();
    }

    @JsonIgnore
    @Override
    public Payment getPayment() {
        return super.getPayment();
    }

}
