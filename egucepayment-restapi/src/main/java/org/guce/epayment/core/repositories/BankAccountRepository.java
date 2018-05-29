package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, BigDecimal> {

    @Query("SELECT ba FROM BankAccount ba WHERE ba.owner IS NOT NULL AND ba.owner.code = :ownerCode")
    List<BankAccount> findByOwner(String ownerCode);

}
