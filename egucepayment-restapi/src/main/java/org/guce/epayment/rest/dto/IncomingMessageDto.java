package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class IncomingMessageDto implements Serializable {

    private static final long serialVersionUID = 9061630717928558145L;

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

