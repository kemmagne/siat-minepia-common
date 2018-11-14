package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.guce.epayment.core.entities.InvoiceLine;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class InvoiceLineDto extends InvoiceLine {

    private static final long serialVersionUID = 2088039403870159476L;

    @JsonIgnore
    @Override
    public LocalDateTime getAcknowledgmentDate() {
        return super.getAcknowledgmentDate();
    }

    @JsonIgnore
    @Override
    public BigDecimal getAmount() {
        return super.getAmount();
    }

    @JsonIgnore
    @Override
    public InvoiceVersion getInvoiceVersion() {
        return super.getInvoiceVersion();
    }

    @Override
    public PartnerDto getBeneficiary() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getBeneficiary());
    }

    @Override
    public BigDecimal getPaidAmount() {
        if (super.getPaidAmount() != null) {
            return super.getPaidAmount();
        } else {
            return getInvoiceVersion().getPaymentDate() == null
                    ? BigDecimal.ZERO
                    : super.getAmountToPay();
        }
    }

    @Override
    public InvoiceTypeDto getType() {
        return RestUtils.downCast(InvoiceType.class, InvoiceTypeDto.class, super.getType());
    }

}
