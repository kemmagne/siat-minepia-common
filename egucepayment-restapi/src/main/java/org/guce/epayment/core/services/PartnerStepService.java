package org.guce.epayment.core.services;

import java.util.Optional;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;

/**
 *
 * @author tadzotsa
 */
public interface PartnerStepService {

    Optional<PartnerStep> find(Partner partner, Step step, PaymentMode paymentMode, int highLevel);

    Optional<PartnerStep> find(Partner partner, Step step, PaymentMode paymentMode);

    Optional<PartnerStep> find(Partner partner, Step step, int highLevel);

    Optional<PartnerStep> find(Partner partner, Step step);

}
