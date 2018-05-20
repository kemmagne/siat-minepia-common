package org.guce.epayment.core.repositories;

import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, Integer> {

    @Query("SELECT ivt FROM InvoiceType ivt WHERE ivt.subType = false")
    @Override
    List<InvoiceType> findAll();

    Optional<InvoiceType> findByCode(String code);

    List<InvoiceType> findByStandaloneAndSubType(boolean standalone, boolean subType);

}
