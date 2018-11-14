package org.guce.epayment.core.repositories;

import java.util.Collection;
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

    @Query("SELECT ivt FROM InvoiceType ivt WHERE ivt.subType = false AND ivt.deleted = false")
    @Override
    List<InvoiceType> findAll();

    @Query("SELECT ivt FROM InvoiceType ivt WHERE ivt.code = ?1 AND ivt.deleted = false")
    Optional<InvoiceType> findByCode(String code);

    @Query("SELECT ivt FROM InvoiceType ivt WHERE ivt.standalone = ?1 AND ivt.subType = ?2 AND ivt.deleted = false")
    List<InvoiceType> findByStandaloneAndSubType(boolean standalone, boolean subType);

    @Query("SELECT ivt FROM InvoiceType ivt JOIN ivt.paymentModes pm WHERE pm.code IN ?1 AND ivt.deleted = false")
    List<InvoiceType> findByPaymentModes(Collection pms);

}

