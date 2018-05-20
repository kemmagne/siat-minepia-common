package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"code"})
public class PartnerGroupDto {

    private String code;
    private String name;

    public PartnerGroupDto() {
    }

    private PartnerGroupDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PartnerGroupDto of(String code, String name) {

        return new PartnerGroupDto(code, name);
    }

}
