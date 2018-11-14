package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.Signature;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class SignatureDto extends Signature {

    private static final long serialVersionUID = 8820843427787729974L;

    @Override
    public StepDto getStep() {
        return RestUtils.downCast(Step.class, StepDto.class, super.getStep());
    }

    @Override
    public UserDto getUser() {
        return RestUtils.downCast(User.class, UserDto.class, super.getUser());
    }

    @JsonIgnore
    @Override
    public String getOriginMessage() {
        return super.getOriginMessage();
    }

    @JsonIgnore
    @Override
    public String getSignature() {
        return super.getSignature();
    }

    @JsonIgnore
    @Override
    public Payment getPayment() {
        return super.getPayment();
    }

}
