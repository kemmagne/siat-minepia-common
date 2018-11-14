package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.Invoice;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class InvoiceVersionDto extends InvoiceVersion {

    private static final long serialVersionUID = 4418134217879841365L;

    @JsonIgnore
    @Override
    public Invoice getInvoice() {
        return super.getInvoice();
    }

    @JsonIgnore
    @Override
    public BigDecimal getVersionAmount() {
        return super.getVersionAmount();
    }

    @Override
    public List<InvoiceLine> getInvoiceLines() {
        return super.getInvoiceLines().stream().map(il -> {
            final InvoiceLineDto ilDto = RestUtils.downCast(InvoiceLine.class, InvoiceLineDto.class, il);
            return ilDto;
        }).collect(Collectors.toList());
    }

}
