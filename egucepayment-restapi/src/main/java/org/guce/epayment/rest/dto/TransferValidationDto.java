package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class TransferValidationDto {

    private String reference;
    private String originMessage;
    private String privateKey;
    private boolean valid;

}
