package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class PartnerDto {

    private BigDecimal id;
    private String code;
    private String taxPayerNumber;
    private String name;
    private PartnerDto parent;
    private List<PartnerTypeDto> types;
    private List<PartnerGroupDto> groups;

    public PartnerDto() {
    }

    private PartnerDto(final BigDecimal id, final String code, final String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    private PartnerDto(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static PartnerDto of(final BigDecimal id, final String code, final String name) {
        return new PartnerDto(id, code, name);
    }

    public static PartnerDto of(final String code, final String name) {
        return new PartnerDto(code, name);
    }

}
