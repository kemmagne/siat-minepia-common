package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class IncomingMessageDto {

    /**
     * clé secrète chiffré avec ma clé publique
     */
    @JsonProperty("CLE_SECRETE_CHIFFREE")
    private String cipheredSecretKey;

    /**
     * message chiffré avec la clé secrètre
     */
    @JsonProperty("MESSAGE_ORIGINE_CHIFFRE")
    private String cipheredOriginMessage;

    /**
     * signature
     */
    @JsonProperty("SIGNATURE")
    private String signature;
}
