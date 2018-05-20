package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import org.guce.epayment.core.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, BigDecimal> {

}
