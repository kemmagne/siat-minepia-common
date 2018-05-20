package org.guce.epayment.core.services;

import java.util.Optional;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.repositories.PartnerStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerStepServiceImpl implements PartnerStepService {

    @Autowired
    private PartnerStepRepository partnerStepRepository;

    @Override
    public Optional<PartnerStep> find(final Partner partner, final Step step, final PaymentMode paymentMode, final int highLevel) {

        final Optional<PartnerStep> partnerStep = partnerStepRepository.find(partner, step, paymentMode, highLevel);

        return Optional.ofNullable(partnerStep.orElse(find(partner, step, highLevel).orElse(null)));
    }

    @Override
    public Optional<PartnerStep> find(final Partner partner, final Step step, final PaymentMode paymentMode) {

        final Optional<PartnerStep> partnerStep = partnerStepRepository.find(partner, step, paymentMode);

        return Optional.ofNullable(partnerStep.orElse(find(partner, step).orElse(null)));
    }

    @Override
    public Optional<PartnerStep> find(final Partner partner, final Step step, final int highLevel) {

        return partnerStepRepository.find(partner, step, highLevel);
    }

    @Override
    public Optional<PartnerStep> find(final Partner partner, final Step step) {

        return partnerStepRepository.find(partner, step);
    }

}
