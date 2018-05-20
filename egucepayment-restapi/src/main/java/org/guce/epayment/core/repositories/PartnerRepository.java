package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface PartnerRepository extends JpaRepository<Partner, BigDecimal> {

    Optional<Partner> findByCode(String code);

    List<Partner> findByCodeOrTaxPayerNumber(String code, String taxPayerNumber);

}
