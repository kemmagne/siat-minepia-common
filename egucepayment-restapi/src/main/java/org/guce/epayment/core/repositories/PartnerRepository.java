package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.guce.epayment.core.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface PartnerRepository extends JpaRepository<Partner, BigDecimal> {

    @Query("SELECT p FROM Partner p WHERE p.code = ?1 AND p.deleted = false")
    Optional<Partner> findByCode(String code);

}

