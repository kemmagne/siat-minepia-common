package org.guce.epayment.transfer.dao;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Signature;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.core.entities.enums.PaymentStatus;
import org.guce.epayment.core.repositories.StepRepository;
import org.guce.epayment.core.repositories.UserRepository;
import org.guce.epayment.core.services.ApplicationService;
import org.guce.epayment.core.services.UserStepService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.epayment.transfer.models.FilterTransferOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransferOrderDaoImpl implements TransferOrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferOrderDaoImpl.class);

    private static final String WHERE = "where".intern();
    private static final String INVOICE_TYPES = "invoiceTypes".intern();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StepRepository stepRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationService appService;
    @Autowired
    private UserStepService userStepService;

    @Override
    public List<TransferOrder> findPartnerTransferOrders(final User connectedUser, final int start, final int end,
            final boolean count) {

        final StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("SELECT to FROM TransferOrder to ")
                .append("WHERE to.commiter.id = :partnerId")
                .append(" OR to.receptiveAgency.id = :partnerId OR to.bankGateway.id = :partnerId")
                .append(" OR (to.beneficiaryAgency IS NOT NULL AND to.beneficiaryAgency.id = :partnerId)")
                .append(" OR (to.beneficiaryBank IS NOT NULL AND to.beneficiaryBank.id = :partnerId) ORDER BY to.reference DESC");

        final TypedQuery<TransferOrder> query = em.createQuery(queryBuilder.toString(), TransferOrder.class);

        query.setParameter("partnerId", connectedUser.getPartner().getId());

        return query.getResultList().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<TransferOrder> findPartnerTransferOrders(final User connectedUser, final boolean toValidate,
            final int start, final int end, final boolean count) {

        final List<TransferOrder> partnerTransferOrders = findPartnerTransferOrders(connectedUser, start, end, count);

        if (!toValidate) {
            return partnerTransferOrders;
        }

        final List<TransferOrder> tosToValidate = new ArrayList<>();

        for (final TransferOrder to : partnerTransferOrders) {

            if (!PaymentStatus.PENDING.equals(to.getStatus())) {
                continue;
            }

            final PaymentMode paymentMode = to.getMode();
            final Signature lastSignature = to.getSignatures().get(0);
            final Step lastStep = lastSignature.getStep();
            final int lastLevel = lastSignature.getLevel();
            /**
             * l'utilisateur voit s'il a niveau + 1 du même statut ou niveau 1
             * du statut suivant
             */
            Optional<UserStep> userStep = userStepService.find(connectedUser, lastStep, paymentMode, lastLevel + Constants.ONE);
            if (userStep.isPresent()) {

                tosToValidate.add(to);
                continue;
            }

            // prendre en compte le parent
            if (!connectedUser.getPartner().getChildren().isEmpty()) {

                userStep = userStepService.find(connectedUser, lastStep, paymentMode, Constants.ONE);
                if (userStep.isPresent()) {

                    tosToValidate.add(to);
                    continue;
                }
            }

            final List<String> stepsList = Arrays.asList(StringUtils.split(to.getFlowInUse(), appService.getColSep()));
            final int lastStepIndex = stepsList.indexOf(lastStep.getCode());

            if (lastStepIndex < stepsList.size() - 1) { // c'est pas le dernier

                final String nextStepCode = stepsList.get(lastStepIndex + Constants.ONE);
                final Step nextStep = stepRepository.findByCode(nextStepCode).get();
                userStep = userStepService.find(connectedUser, nextStep, paymentMode, Constants.ONE);
                if (userStep.isPresent()) {
                    tosToValidate.add(to);
                }
            }
        }

        return tosToValidate;
    }

    @Override
    public Object filterTransferOrders(final FilterTransferOrder filter, final String code) {

        final int start = filter.getStart();
        final int end = filter.getEnd();
        final boolean count = filter.isCount();
        final Integer type = filter.getType();
        final StringBuilder queryBuilder = new StringBuilder("SELECT ");

        queryBuilder.append(count ? "COUNT(to)" : "to").append(" FROM TransferOrder to WHERE ");

        final StringBuilder whereBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(filter.getReference())) {
            whereBuilder.append("to.reference = '").append(filter.getReference()).append("' AND ");
        }

        if (StringUtils.isNotBlank(filter.getTaxPayerNumber())) {
            whereBuilder.append("to.taxPayer.taxPayerNumber = '").append(filter.getTaxPayerNumber()).append("' AND ");
        }

        if (StringUtils.isNotBlank(filter.getBeneficiary())) {
            whereBuilder.append("to.beneficiary.code = '").append(filter.getBeneficiary()).append("' AND ");
        }

        if (StringUtils.isNotBlank(filter.getInvoiceType())) {
            whereBuilder.append("to.invoiceType.code = '").append(filter.getInvoiceType()).append("' AND ");
        }

        if (filter.getMinAmount() != null) {
            whereBuilder.append("to.amount >= ").append(filter.getMinAmount()).append(" AND ");
        }

        if (filter.getMaxAmount() != null) {
            whereBuilder.append("to.amount <= ").append(filter.getMaxAmount()).append(" AND ");
        }

        if (filter.getMinDate() != null) {
            whereBuilder.append("to.startedDate >= ").append(filter.getMinDate()).append(" AND ");
        }

        if (filter.getMaxDate() != null) {
            whereBuilder.append("to.startedDate <= ").append(filter.getMaxDate()).append(" AND ");
        }

        if (!Constants.TWO.equals(type) && StringUtils.isNotBlank(filter.getStatus())) {

            if (Constants.ALL.equalsIgnoreCase(filter.getStatus())) {
                whereBuilder.append("1 = 1 AND ");
            } else {
                whereBuilder.append("to.status = '").append(filter.getStatus()).append("' AND ");
            }
        }

        if (!Constants.ONE.equals(type) && StringUtils.isNotBlank(filter.getBank())) {
            whereBuilder.append("to.bankGateway.code = '").append(filter.getBank()).append("' AND ");
        }

        if (Constants.ZERO.equals(whereBuilder.length())) {
            return count ? 0 : new ArrayList<>();
        }

        whereBuilder.append("1 = 1");
        queryBuilder.append(whereBuilder);

        if (Constants.TWO.equals(type)) {
            queryBuilder.append(" AND to.status = :status");
        }
        queryBuilder.append(" AND ");

        final Map<String, Object> result = getWhereClause(type, code);
        final String otherWhere = result.get(WHERE).toString();

        if (otherWhere.isEmpty()) {
            return count ? 0 : new ArrayList<>();
        }

        queryBuilder.append(otherWhere);
        queryBuilder.append(" ORDER BY to.reference");

        final Query query = em.createQuery(queryBuilder.toString());

        if (Constants.TWO.equals(type)) {
            query.setParameter("status", PaymentStatus.VALIDATED.name());
        }

        final List<InvoiceType> invoiceTypes = (List<InvoiceType>) result.get(INVOICE_TYPES);

        setQueryParams(query, type, code, invoiceTypes);

        if (!count) {

            if (end > start) {

                query.setMaxResults(end - start);
                query.setFirstResult(start);
            }

            return query.getResultList();
        } else {
            return query.getSingleResult();
        }
    }

    @Override
    public Object findTransferOrdersPeriodically(final int type, final int period, final String code,
            final int start, final int end, final boolean count) {

        LocalDateTime dateTime = LocalDateTime.now();

        dateTime = DateUtils.resetTime(dateTime);

        switch (period) {

            case Constants.DAY_OF_WEEK:
                dateTime = dateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                dateTime = dateTime.minusWeeks(Constants.ONE);
                break;
            case Constants.DAY_OF_MONTH:
                dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
                break;
            case Constants.DAY_OF_YEAR:
                dateTime = dateTime.with(TemporalAdjusters.firstDayOfYear());
        }

        // clause supplémentaire
        final Map<String, Object> result = getWhereClause(type, code);

        final String where = result.get(WHERE).toString();
        if (where.isEmpty()) {
            return new ArrayList<>();
        }

        final List<InvoiceType> invoiceTypes = (List<InvoiceType>) result.get(INVOICE_TYPES);

        final Query query = em.createQuery("SELECT " + (count ? "COUNT(to)" : "to") + " FROM TransferOrder to WHERE to.decisionDate IS NOT NULL AND to.decisionDate >= :start AND to.decisionDate <= :now AND to.status = :status AND " + where + " ORDER BY to.reference DESC");
        query.setParameter("start", dateTime);
        query.setParameter("now", LocalDateTime.now());
        query.setParameter("status", "VALIDATED");

        setQueryParams(query, type, code, invoiceTypes);

        if (!count) {

            if (end > start) {

                query.setMaxResults(end - start);
                query.setFirstResult(start);
            }

            return query.getResultList();
        } else {
            return query.getSingleResult();
        }
    }

    @Override
    public List<TransferOrder> findLastTransferOrders(final int type, final String code, final int number) {

        final Map<String, Object> result = getWhereClause(type, code);
        final String where = result.get(WHERE).toString();

        if (where.isEmpty()) {
            return new ArrayList<>();
        }

        final List<InvoiceType> invoiceTypes = (List<InvoiceType>) result.get(INVOICE_TYPES);
        final TypedQuery<TransferOrder> query = em.createQuery("SELECT to FROM TransferOrder to WHERE " + where + " AND to.decisionDate IS NOT NULL ORDER BY to.reference DESC", TransferOrder.class);

        setQueryParams(query, type, code, invoiceTypes);
        query.setMaxResults(number);

        return query.getResultList();
    }

    private Map<String, Object> getWhereClause(int type, String code) {

        final StringBuilder whereBuilder = new StringBuilder();
        final Map<String, Object> result = new HashMap<>();
        switch (type) {
            case 1: // bank
                whereBuilder.append("to.bankGateway.code = :bankCode");
                break;
            case 2: // benef
                whereBuilder.append("to.beneficiary.code = :beneficiaryCode");
                break;
            case 3: // for decision maker

                final User decisionMaker = userRepository.findByLogin(code).get();
                final List<InvoiceType> invoiceTypes = decisionMaker.getInvoiceTypes();
                final int nbIvts = invoiceTypes.size();

                if (Constants.ZERO.equals(nbIvts)) {
                    break;
                }

                result.put(INVOICE_TYPES, invoiceTypes);

                whereBuilder.append("(");
                for (int index = 0; index < nbIvts; index++) {
                    whereBuilder.append("to.invoiceType.code = :invoiceTypeCode").append(index).append(" OR ");
                }
                whereBuilder.append("1 <> 1)");
        }

        result.put(WHERE, whereBuilder.toString());

        return result;
    }

    private void setQueryParams(Query query, int type, String code, List<InvoiceType> invoiceTypes) {
        switch (type) {
            case 1:
                query.setParameter("bankCode", code);
                break;
            case 2:
                query.setParameter("beneficiaryCode", code);
                break;
            case 3:

                final int nbIvts = null != invoiceTypes ? invoiceTypes.size() : Constants.ZERO;

                for (int index = Constants.ZERO; index < nbIvts; index++) {
                    query.setParameter("invoiceTypeCode" + index, invoiceTypes.get(index).getCode());
                }
        }
    }

    @Override
    public Object findByTosUser(final String userLogin, final int start, final int end, boolean count) {
        final Query query = em.createQuery("SELECT " + (count ? "COUNT(s.payment)" : "s.payment") + " FROM Signature s WHERE s.user.login = :userLogin");

        query.setParameter("userLogin", userLogin);

        if (!count) {

            if (end > start) {

                query.setFirstResult(start);
                query.setMaxResults(end - start);
            }

            return query.getResultList();
        } else {
            return query.getSingleResult();
        }
    }

    @Override
    public List getTosStats(final int type, final boolean bank) {

        final Query query = em.createQuery("SELECT to.bankGateway, COUNT(to), SUM(to.amount) FROM TransferOrder to WHERE to.status = :status GROUP BY to.bankGateway");

        switch (type) {
            case 1:
                if (bank) {
                    query.setParameter("status", "REJECTED");
                } else {
                    query.setParameter("status", "CANCELED");
                }
                break;
            case 2:
                query.setParameter("status", "VALIDATED");
                break;
            case 3:
                query.setParameter("status", "ACKNOWLED");
        }

        return query.getResultList();
    }

    @Override
    public Object getAcknowledTransferOrders(final String benefCode, final int start, final int end, final boolean count) {

        final Query query = em.createQuery("SELECT " + (count ? "COUNT(to)" : "to") + " FROM TransferOrder to WHERE to.beneficiary.code = :beneficiaryCode AND to.status = :status");

        query.setParameter("beneficiaryCode", benefCode);
        query.setParameter("status", "ACKNOWLED");

        if (!count) {

            if (end > start) {

                query.setFirstResult(start);
                query.setMaxResults(end - start);
            }

            return query.getResultList();
        } else {
            return query.getSingleResult();
        }
    }

}
