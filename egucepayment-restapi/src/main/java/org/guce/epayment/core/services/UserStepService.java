package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;

/**
 *
 * @author tadzotsa
 */
public interface UserStepService {

    List<UserStep> findByPartner(BigDecimal partnerId);

    Optional<UserStep> find(User user, Step step, PaymentMode paymentMode, int level);

    Optional<UserStep> find(User user, Step step, int level);

    void addValidationLevels(BigDecimal partnerId, List<UserStep> userSteps);

    void removeValidationLevels(BigDecimal partnerId, List<UserStep> userSteps);

}
