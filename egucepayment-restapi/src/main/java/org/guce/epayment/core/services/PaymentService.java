package org.guce.epayment.core.services;

import java.util.List;
import java.util.Map;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Signature;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;

/**
 *
 * @author tadzotsa
 */
public interface PaymentService {

    void updateInvoices(Payment payment, String status);

    Payment init(List<Map<String, Object>> invoices, PaymentMode paymentMode, Partner commiter,
            String partnerReference, Partner bankGateway, int rate, boolean rateOnInvoiceAmount);

    void save(Payment payment, String seqTable, String prefix);

    void update(Payment payment);

    void setDecision(Payment payment, String status);

    Signature buildUserSignature(String originMessage, String signature, int level, User user, Step step, Payment payment);

    List<PaymentInvoiceVersion> findPivsByBank(String bankCode, String pms, int max);

    List<PaymentInvoiceVersion> findPivsByBenef(String benefCode, String pms, int max);

    List<PaymentInvoiceVersion> findPivsByDecisionMaker(String decisionMakerLogin, String pms, int max);

    List<PaymentMode> findPaymentModesByDirect(boolean direct);

}
