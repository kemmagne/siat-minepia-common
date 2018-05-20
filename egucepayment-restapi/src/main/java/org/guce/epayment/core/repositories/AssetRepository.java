package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.guce.epayment.core.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface AssetRepository extends JpaRepository<Asset, BigDecimal> {

    Optional<Asset> findByReference(String reference);

}
