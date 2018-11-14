package org.guce.epayment.rest.dto;

import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class UserStepDto extends UserStep {

    private static final long serialVersionUID = -7096785670995084364L;

    @Override
    public PaymentModeDto getPaymentMode() {
        return RestUtils.downCast(PaymentMode.class, PaymentModeDto.class, super.getPaymentMode());
    }

    @Override
    public StepDto getStep() {
        return RestUtils.downCast(Step.class, StepDto.class, super.getStep());
    }

    @Override
    public UserDto getUser() {
        return RestUtils.downCast(User.class, UserDto.class, super.getUser());
    }

}
