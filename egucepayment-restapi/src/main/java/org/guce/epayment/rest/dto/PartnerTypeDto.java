package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(exclude = {"label"})
public class PartnerTypeDto {

    private Integer id;
    private String code;
    private String label;

    public PartnerTypeDto() {
    }

    private PartnerTypeDto(Integer id, String code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;
    }

    public static PartnerTypeDto of(Integer id, String code, String label) {
        return new PartnerTypeDto(id, code, label);
    }

}
