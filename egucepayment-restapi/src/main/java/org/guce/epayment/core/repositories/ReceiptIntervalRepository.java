package org.guce.epayment.core.repositories;

import java.util.List;
import org.guce.epayment.core.entities.ReceiptInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface ReceiptIntervalRepository extends JpaRepository<ReceiptInterval, Integer> {

    @Query("SELECT ri FROM ReceiptInterval ri ORDER BY ri.insertionDate DESC")
    @Override
    List<ReceiptInterval> findAll();

}

