package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class PaymentModeDto {

    private Integer id;
    private String code;
    private String label;
    private boolean direct;

    public PaymentModeDto() {
    }

    private PaymentModeDto(Integer id, String code, String label, boolean direct) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.direct = direct;
    }

    private PaymentModeDto(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static PaymentModeDto of(Integer id, String code, String label, boolean direct) {
        return new PaymentModeDto(id, code, label, direct);
    }

    public static PaymentModeDto of(String code, String label) {
        return new PaymentModeDto(code, label);
    }

}
