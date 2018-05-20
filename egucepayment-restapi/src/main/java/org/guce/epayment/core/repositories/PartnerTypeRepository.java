package org.guce.epayment.core.repositories;

import java.util.Optional;
import org.guce.epayment.core.entities.PartnerType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface PartnerTypeRepository extends JpaRepository<PartnerType, Integer> {

    Optional<PartnerType> findByCode(String code);

}
