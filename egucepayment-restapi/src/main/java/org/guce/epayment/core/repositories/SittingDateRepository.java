package org.guce.epayment.core.repositories;

import java.util.List;
import org.guce.epayment.core.entities.SittingDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface SittingDateRepository extends JpaRepository<SittingDate, Integer> {

    @Query("SELECT sd FROM SittingDate sd ORDER BY sd.insertionDate DESC")
    @Override
    List<SittingDate> findAll();

}
