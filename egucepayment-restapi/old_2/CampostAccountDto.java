package org.guce.epayment.rest.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class CampostAccountDto implements Serializable {

    private static final long serialVersionUID = 4158419283064065273L;

    private Integer id;
    private PartnerDto beneficiary;
    private String merchantid;
    private String izenid;
    private String secretKey;
    private String initVector;

}

