package org.guce.epayment.rest.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author hyacinthe
 */
@Data
public class CampostMobileSecondReq implements Serializable {

    private static final long serialVersionUID = 7673112543950861409L;

    private String paymentReference;
    private String pacCodeOne;
    private String pacCodeTwo;
    private boolean cancel;

}
