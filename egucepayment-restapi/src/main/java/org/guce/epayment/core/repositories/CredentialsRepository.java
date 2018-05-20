package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import org.guce.epayment.core.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface CredentialsRepository extends JpaRepository<Credentials, BigDecimal> {

}
