package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class PartnerStepDto {

    private BigDecimal id;
    private UserDto user;
    private StepDto step;
    private InvoiceTypeDto invoiceType;
    private PaymentModeDto paymentMode;
    private int highLevel;

    public PartnerStepDto() {
    }

    private PartnerStepDto(BigDecimal id, UserDto user, StepDto step, InvoiceTypeDto invoiceType, PaymentModeDto paymentMode, int highLevel) {
        this.id = id;
        this.user = user;
        this.step = step;
        this.invoiceType = invoiceType;
        this.paymentMode = paymentMode;
        this.highLevel = highLevel;
    }

    public static PartnerStepDto of(BigDecimal id, UserDto user, StepDto step, InvoiceTypeDto invoiceType, PaymentModeDto paymentMode, int highLevel) {

        return new PartnerStepDto(id, user, step, invoiceType, paymentMode, highLevel);
    }

}
