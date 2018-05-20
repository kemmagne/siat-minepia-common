package org.guce.epayment.campost.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.guce.epayment.campost.entities.CampostAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface CampostAccountRepository extends JpaRepository<CampostAccount, BigDecimal> {

    @Query("SELECT a FROM CampostAccount a WHERE a.beneficiary.code = ?1")
    Optional<CampostAccount> findByBeneficiary(String beneficiary);

}
