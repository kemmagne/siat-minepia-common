package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.guce.epayment.core.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface PaymentRepository extends JpaRepository<Payment, BigDecimal> {

    Optional<Payment> findByReference(String reference);

}
