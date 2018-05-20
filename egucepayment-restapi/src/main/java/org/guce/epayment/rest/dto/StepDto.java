package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"code"})
public class StepDto {

    private String code;
    private String label;

    public StepDto() {
    }

    private StepDto(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static StepDto of(String code, String label) {

        return new StepDto(code, label);
    }

}
