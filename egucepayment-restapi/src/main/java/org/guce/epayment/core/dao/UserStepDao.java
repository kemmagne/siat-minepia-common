package org.guce.epayment.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.UserStep;

/**
 *
 * @author tadzotsa
 */
public interface UserStepDao {

    void removeValidationLevels(BigDecimal partnerId, List<UserStep> userSteps);

}
