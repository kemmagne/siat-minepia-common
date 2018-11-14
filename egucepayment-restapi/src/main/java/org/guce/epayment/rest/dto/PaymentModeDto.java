package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.PaymentMode;

/**
 *
 * @author tadzotsa
 */
public class PaymentModeDto extends PaymentMode {

    private static final long serialVersionUID = 5657923715986584158L;

    @JsonIgnore
    @Override
    public String getGlobalFlow() {
        return super.getGlobalFlow();
    }

    @JsonIgnore
    @Override
    public List<InvoiceType> getInvoiceTypes() {
        return super.getInvoiceTypes();
    }

    @JsonIgnore
    @Override
    public List<Payment> getPayments() {
        return super.getPayments();
    }

}
