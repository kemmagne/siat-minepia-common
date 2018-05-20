package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author hyacinthe
 */
@Data
public class CampostMobileSecondReq {

    private String paymentReference;
    private String pacCodeOne;
    private String pacCodeTwo;
    private boolean cancel;

}
