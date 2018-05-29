package org.guce.epayment.core.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.core.repositories.PartnerStepRepository;
import org.guce.epayment.core.repositories.UserStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserStepDaoImpl implements UserStepDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserStepRepository userStepRepository;
    @Autowired
    private PartnerStepRepository partnerStepRepository;

    @Override
    public void removeValidationLevels(BigDecimal partnerId, List<UserStep> userSteps) {

        final Partner partner = userSteps.get(0).getUser().getPartner();
        final PaymentMode paymentMode = userSteps.get(0).getPaymentMode();

        final StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("SELECT us FROM UserStep us WHERE us.user.partner.id = :partnerId AND us.step.id = :statusId AND us.paymentMode");

        if (paymentMode == null) {
            queryBuilder.append(" IS NULL");
        } else {
            queryBuilder.append(".id = :paymentModeId");
        }

        queryBuilder.append(" ORDER BY us.level");

        final TypedQuery<UserStep> query = em.createQuery(queryBuilder.toString(), UserStep.class);

        query.setParameter("partnerId", partnerId);
        if (null != paymentMode) {
            query.setParameter("paymentModeId", paymentMode.getId());
        }

        userSteps.forEach(userStep -> {

            userStepRepository.delete(userStep);

            final Step step = userStep.getStep();

            query.setParameter("statusId", step.getId());

            final List<UserStep> userStepsRemaning = query.getResultList();

            final Optional<PartnerStep> partnerStep = partnerStepRepository.find(partner, step, paymentMode);

            if (userStepsRemaning.isEmpty()) {
                partnerStepRepository.delete(partnerStep.get());
            } else {

                final int highLevel = userStepsRemaning.size();

                partnerStep.get().setHighLevel(highLevel);

                partnerStepRepository.save(partnerStep.get());

                for (int index = 0; index < highLevel; index++) {

                    final UserStep us = userStepsRemaning.get(index);

                    us.setLevel(index + 1);

                    userStepRepository.save(us);
                }
            }
        });
    }

}
