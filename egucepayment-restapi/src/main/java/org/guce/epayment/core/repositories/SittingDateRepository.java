package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.SittingDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface SittingDateRepository extends JpaRepository<SittingDate, BigDecimal> {

    @Query("SELECT sd FROM SittingDate sd ORDER BY sd.insertionDate DESC")
    @Override
    List<SittingDate> findAll();

}
