package org.guce.epayment.core.services;

import java.util.Map;
import javax.mail.MessagingException;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.utils.enums.AperakType;

/**
 *
 * @author tadzotsa
 */
public interface MessageService {

    void sendMail(Map<String, Object> props) throws MessagingException;

    /**
     * confirme le paiement d'une version de facture. La version de facture est
     * celle de la facture parente
     *
     * @param piv la ligne de paiement pour la version de facture parente
     */
    void confirmPayment(PaymentInvoiceVersion piv);

    void sendAperak(AperakType aperakType, String fileNumber, String guceReference,
            String service, String errorCode, String aperakErreur);

}
