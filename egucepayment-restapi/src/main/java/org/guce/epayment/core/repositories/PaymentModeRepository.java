package org.guce.epayment.core.repositories;

import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Integer> {

    Optional<PaymentMode> findByCode(String code);

    List<PaymentMode> findByDirect(boolean direct);

}
