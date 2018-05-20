package org.guce.epayment.core.dao;

import java.util.List;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;

/**
 *
 * @author tadzotsa
 */
public interface PaymentDao {

    List<PaymentInvoiceVersion> findPivsByBank(String bankCode, String pms, int max);

    List<PaymentInvoiceVersion> findPivsByBenef(String benefCode, String pms, int max);

    List<PaymentInvoiceVersion> findPivsByDecisionMaker(String decisionMakerLogin, String pms, int max);

}
