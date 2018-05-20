package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class UserStepDto {

    private BigDecimal id;
    private UserDto user;
    private StepDto step;
    private PaymentModeDto paymentMode;
    private int level;

    public UserStepDto() {
    }

    private UserStepDto(BigDecimal id, UserDto user, StepDto step, PaymentModeDto paymentMode, int level) {
        this.id = id;
        this.user = user;
        this.step = step;
        this.paymentMode = paymentMode;
        this.level = level;
    }

    public static UserStepDto of(BigDecimal id, UserDto user, StepDto step, PaymentModeDto paymentMode, int level) {

        return new UserStepDto(id, user, step, paymentMode, level);
    }

}
