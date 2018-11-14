package org.guce.epayment.rest.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class TransferValidationDto implements Serializable {

    private static final long serialVersionUID = 4616469207563015537L;

    private String reference;
    private String originMessage;
    private String privateKey;
    private boolean valid;

}

