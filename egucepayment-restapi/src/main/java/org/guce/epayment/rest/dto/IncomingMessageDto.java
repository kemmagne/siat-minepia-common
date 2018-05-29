package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class IncomingMessageDto {

    /**
     * the symetric-encryted text
     */
    private String originMessage;
    /**
     * signature
     */
    private String signature;

    /**
     * 1. lorsque le message arrive, on le déchiffre avec la clé secrète
     * partagée avec le client
     *
     * 2. on le hashe en utilisant l'algorithme défini de commun accord avec le
     * client
     *
     * 3. on déchiffre la signature en utilisant la clé publique du client et le
     * compare avec le hash obtenu en 2. En cas d'égalité, le message est
     * authenticatique, sinon, il a le subit une altération ou ne provient pas
     * du client
     */
}
