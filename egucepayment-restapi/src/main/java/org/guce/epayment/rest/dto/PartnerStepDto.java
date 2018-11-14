package org.guce.epayment.rest.dto;

import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class PartnerStepDto extends PartnerStep {

    private static final long serialVersionUID = 3837726726562541542L;

    @Override
    public PartnerDto getPartner() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getPartner());
    }

    @Override
    public PaymentModeDto getPaymentMode() {
        return RestUtils.downCast(PaymentMode.class, PaymentModeDto.class, super.getPaymentMode());
    }

    @Override
    public StepDto getStep() {
        return RestUtils.downCast(Step.class, StepDto.class, super.getStep());
    }

}
