package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.dao.UserStepDao;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.core.repositories.PartnerRepository;
import org.guce.epayment.core.repositories.PartnerStepRepository;
import org.guce.epayment.core.repositories.UserStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStepServiceImpl implements UserStepService {

    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PartnerStepRepository partnerStepRepository;
    @Autowired
    private UserStepRepository userStepRepository;

    @Autowired
    private UserStepDao userStepDao;

    @Autowired
    private PartnerStepService partnerStepService;

    @Override
    public List<UserStep> findByPartner(final BigDecimal partnerId) {

        return userStepRepository.findByPartner(partnerId);
    }

    @Override
    public Optional<UserStep> find(User user, Step step, PaymentMode paymentMode, int level) {

        final Optional<UserStep> userStep = userStepRepository.find(user, step, paymentMode, level);

        if (userStep.isPresent()) {
            return userStep;
        }

        final int levelInf = level - 1;

        if (1 <= levelInf) {

            final Optional<UserStep> userStepInf = userStepRepository.find(user.getPartner().getId(), step, paymentMode, levelInf);

            if (!userStepInf.isPresent()) {
                return Optional.empty();
            }
        }

        return find(user, step, level);
    }

    @Override
    public Optional<UserStep> find(User user, Step step, int level) {

        return userStepRepository.find(user, step, level);
    }

    @Override
    public void addValidationLevels(final BigDecimal partnerId, final List<UserStep> userSteps) {

        final Partner partner = partnerRepository.getOne(partnerId);

        userSteps.forEach(userStep -> {

            final Optional<PaymentMode> paymentMode = Optional.ofNullable(userStep.getPaymentMode());
            final Step step = userStep.getStep();

            Optional<PartnerStep> partnerStep;
            if (!paymentMode.isPresent()) {
                partnerStep = partnerStepService.find(partner, step);
            } else {
                partnerStep = partnerStepService.find(partner, step, paymentMode.get());
            }

            if (!partnerStep.isPresent()) {

                final PartnerStep partnerStep1 = new PartnerStep();

                partnerStep1.setHighLevel(1);
                partnerStep1.setPartner(partner);
                partnerStep1.setPaymentMode(paymentMode.orElse(null));
                partnerStep1.setStep(step);

                partnerStepRepository.save(partnerStep1);
            }

            userStepRepository.save(userStep);
        });
    }

    @Override
    public void removeValidationLevels(BigDecimal partnerId, List<UserStep> userSteps) {
        userStepDao.removeValidationLevels(partnerId, userSteps);
    }

}
