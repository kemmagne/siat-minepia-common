package org.guce.epayment.core.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author tadzotsa
 */
public interface CoreDao {

    <T> List<T> findRange(Class<T> entityClass, int start, int end);

    <T> Optional<T> findById(Object id, Class<T> clazz);

    String generateUniqueValue(String table, String prefix);

    /**
     *
     * @param <E>
     * @param entityClass
     * @param ids
     * @param map
     */
    <E> void updateEntity(Class<E> entityClass, Map<String, ? extends Object> ids, Map<String, ? extends Object> map);

}
