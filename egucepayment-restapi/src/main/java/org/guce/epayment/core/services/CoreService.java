package org.guce.epayment.core.services;

import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.RepPartnerGroup;
import org.guce.epayment.core.entities.RepReceipt;

/**
 *
 * @author tadzotsa
 */
public interface CoreService {

    <T> Optional<T> findByUniqueKey(String uniqueKey, String uniqueValue, Class<T> clazz);

    <T> List<T> findAll(Class<T> clazz);

    <T> Optional<T> findById(final Object id, final Class<T> clazz);

    void save(final Object entity, final Class clazz);

    List<RepPartnerGroup> findAllPartnerGroups();

    List<RepReceipt> findReceiptsByInvoiceType(String invoiceTypeCode);

}
