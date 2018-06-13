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

    void confirmPayment(PaymentInvoiceVersion paymentInvoiceVersion);

    void sendAperak(AperakType aperakType, String fileNumber, String guceReference,
            String service, String errorCode, String aperakErreur);

}
