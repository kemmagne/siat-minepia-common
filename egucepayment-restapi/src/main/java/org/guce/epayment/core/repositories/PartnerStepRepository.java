package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface PartnerStepRepository extends JpaRepository<PartnerStep, BigDecimal> {

    @Query("SELECT ps FROM PartnerStep ps WHERE ps.partner = ?1 AND ps.step = ?2 AND ps.paymentMode = ?3 AND ps.highLevel = ?4")
    Optional<PartnerStep> find(Partner partner, Step step, PaymentMode paymentMode, int highLevel);

    @Query("SELECT ps FROM PartnerStep ps WHERE ps.partner = ?1 AND ps.step = ?2 AND ps.paymentMode = ?3")
    Optional<PartnerStep> find(Partner partner, Step step, PaymentMode paymentMode);

    @Query("SELECT ps FROM PartnerStep ps WHERE ps.partner = ?1 AND ps.step = ?2 AND ps.highLevel = ?3")
    Optional<PartnerStep> find(Partner partner, Step step, int highLevel);

    @Query("SELECT ps FROM PartnerStep ps WHERE ps.partner = ?1 AND ps.step = ?2")
    Optional<PartnerStep> find(Partner partner, Step step);

}
