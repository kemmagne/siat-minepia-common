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
public class SignatureDto {

    private BigDecimal id;
    private UserDto user;
    private StepDto step;
    private int level;
    private String date;

    public SignatureDto() {
    }

    private SignatureDto(BigDecimal id, UserDto user, StepDto step, int level, String date) {
        this.id = id;
        this.user = user;
        this.step = step;
        this.level = level;
        this.date = date;
    }

    public static SignatureDto of(BigDecimal id, UserDto user, StepDto step, int level, String date) {
        return new SignatureDto(id, user, step, level, date);
    }

}
