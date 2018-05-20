package org.guce.epayment.core.repositories;

import java.util.Optional;
import org.guce.epayment.core.entities.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface AssetTypeRepository extends JpaRepository<AssetType, Integer> {

    Optional<AssetType> findByCode(String code);

}
