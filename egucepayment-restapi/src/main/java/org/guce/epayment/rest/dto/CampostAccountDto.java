package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class CampostAccountDto {

    private Integer id;
    private PartnerDto beneficiary;
    private String merchantid;
    private String izenid;
    private String secretKey;
    private String initVector;

}
