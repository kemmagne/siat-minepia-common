package org.guce.epayment.core.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.guce.epayment.core.entities.RepPartnerGroup;
import org.guce.epayment.core.entities.Receipt;

/**
 *
 * @author tadzotsa
 */
public interface CoreService {

    <T> Optional<T> findByUniqueKey(String uniqueKey, String uniqueValue, Class<T> entityClass);

    <T> List<T> findAll(Class<T> entityClass);

    <T> List<T> findRange(Class<T> entityClass, int start, int end);

    <T> Optional<T> findById(Object id, Class<T> entityClass);

    /**
     * save the entity in param and fush instantly
     *
     * @param <T>
     * @param entity the entity to save
     * @param entityClass the entity class
     * @return the saved entity
     */
    <T> T save(Object entity, Class<T> entityClass);

    /**
     * delete entity in DB
     *
     * @param <T>
     * @param entity the entity to delete or the id of this entity
     * @param entityClass the entity class
     * @param byId specify if we are going to delete by id or by the entity
     * @param idClass the id class
     */
    <T> void delete(T entity, Class<T> entityClass);

    /**
     * delete entity by id in DB
     *
     * @param id the id of the entity
     * @param entityClass the entity class
     * @param idClass the id class
     */
    void deleteById(Object id, Class entityClass, Class idClass);

    List<RepPartnerGroup> findAllPartnerGroups();

    List<Receipt> findReceiptsByInvoiceType(String invoiceTypeCode);

    /**
     *
     * @param <E>
     * @param entityClass
     * @param ids
     * @param map
     */
    <E> void updateEntity(Class<E> entityClass, Map<String, ? extends Object> ids, Map<String, ? extends Object> map);

}
