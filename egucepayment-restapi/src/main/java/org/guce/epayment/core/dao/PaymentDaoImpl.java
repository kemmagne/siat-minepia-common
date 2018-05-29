package org.guce.epayment.core.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.repositories.UserRepository;
import org.guce.epayment.core.services.ApplicationService;
import org.guce.epayment.core.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationService appService;

    @Override
    public List<PaymentInvoiceVersion> findPivsByBank(final String bankCode, final String pms, final int max) {
        return findByPivsPartner("piv.payment.bankGateway.code", bankCode, pms, max);
    }

    @Override
    public List<PaymentInvoiceVersion> findPivsByBenef(final String benefCode, final String pms, final int max) {
        return findByPivsPartner("piv.invoiceVersion.invoice.beneficiary.code", benefCode, pms, max);
    }

    private List<PaymentInvoiceVersion> findByPivsPartner(final String where, final String partnerCode, final String pms,
            final int max) {

        final StringBuilder whereBuilder = new StringBuilder("(");
        final String[] pmsTab = StringUtils.split(pms, appService.getColSep());
        final int nbPms = pmsTab.length;

        for (Integer index = Constants.ZERO; index < nbPms; index++) {
            whereBuilder.append("piv.payment.paymentMode.code = :paymentModeCode").append(index).append(" OR ");
        }
        whereBuilder.append("1 <> 1)");

        final StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("SELECT piv FROM PaymentInvoiceVersion piv WHERE ")
                .append(where).append(" = :partnerCode AND ")
                .append(whereBuilder).append(" ORDER BY piv.payment.reference DESC");

        final TypedQuery<PaymentInvoiceVersion> query = em.createQuery(queryBuilder.toString(), PaymentInvoiceVersion.class);

        query.setParameter("partnerCode", partnerCode);

        for (Integer index = Constants.ZERO; index < nbPms; index++) {
            query.setParameter("paymentModeCode" + index, pmsTab[index]);
        }

        if (Constants.ZERO != max) {
            query.setMaxResults(max);
        }

        return query.getResultList();
    }

    @Override
    public List<PaymentInvoiceVersion> findPivsByDecisionMaker(final String decisionMakerLogin, final String pms,
            final int max) {

        final User decisionMaker = userRepository.findByLogin(decisionMakerLogin).get();

        final List<InvoiceType> invoiceTypes = decisionMaker.getInvoiceTypes();

        if (invoiceTypes.isEmpty()) {
            return new ArrayList<>();
        }

        final StringBuilder whereBuilder = new StringBuilder("(");
        final int nbIvts = invoiceTypes.size();

        for (Integer index = Constants.ZERO; index < nbIvts; index++) {
            whereBuilder.append("piv.invoiceVersion.invoice.type.code = :invoiceTypeCode")
                    .append(index).append(" OR ");
        }
        whereBuilder.append("1 <> 1)");

        final StringBuilder queryBuilder = new StringBuilder("SELECT piv FROM PaymentInvoiceVersion piv WHERE ");

        queryBuilder.append(whereBuilder);

        final String[] pmsTab = StringUtils.split(pms, appService.getColSep());
        final int nbPms = pmsTab.length;

        whereBuilder.delete(Constants.ZERO, whereBuilder.length());
        whereBuilder.append("(");

        for (Integer index = Constants.ZERO; index < nbPms; index++) {
            whereBuilder.append("piv.payment.paymentMode.code = :paymentModeCode").append(index).append(" OR ");
        }
        whereBuilder.append("1 <> 1)");

        queryBuilder.append(" AND ").append(whereBuilder).append(" ORDER BY piv.payment.reference DESC");

        final TypedQuery<PaymentInvoiceVersion> query = em.createQuery(queryBuilder.toString(), PaymentInvoiceVersion.class);

        for (Integer index = Constants.ZERO; index < nbIvts; index++) {
            query.setParameter("invoiceTypeCode" + index, invoiceTypes.get(index).getCode());
        }

        for (Integer index = Constants.ZERO; index < nbPms; index++) {
            query.setParameter("paymentModeCode" + index, pmsTab[index]);
        }

        if (Constants.ZERO != max) {
            query.setMaxResults(max);
        }

        return query.getResultList();
    }

}
