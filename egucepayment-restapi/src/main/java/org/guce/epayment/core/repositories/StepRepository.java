package org.guce.epayment.core.repositories;

import java.util.Optional;
import org.guce.epayment.core.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface StepRepository extends JpaRepository<Step, Integer> {

    Optional<Step> findByCode(String code);

}
