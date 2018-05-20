package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import org.guce.epayment.core.entities.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface SignatureRepository extends JpaRepository<Signature, BigDecimal> {

}
