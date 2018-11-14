package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface UserStepRepository extends JpaRepository<UserStep, BigDecimal> {

    @Query("SELECT us FROM UserStep us WHERE us.step.code <> 'T31' AND us.user.partner.id = ?1 ORDER BY us.level")
    List<UserStep> findByPartner(BigDecimal partnerId);

    @Query("SELECT us FROM UserStep us WHERE us.user = ?1 AND us.step = ?2 AND us.paymentMode = ?3 AND us.level = ?4 ORDER BY us.level")
    Optional<UserStep> find(User user, Step step, PaymentMode paymentMode, int level);

    @Query("SELECT us FROM UserStep us WHERE us.user.partner.id = ?1 AND us.step = ?2 AND us.paymentMode = ?3 AND us.level = ?4 ORDER BY us.level")
    Optional<UserStep> find(BigDecimal partnerId, Step step, PaymentMode paymentMode, int level);

    @Query("SELECT us FROM UserStep us WHERE us.user = ?1 AND us.step = ?2 AND us.level = ?3 ORDER BY us.level")
    Optional<UserStep> find(User user, Step step, int level);

}

