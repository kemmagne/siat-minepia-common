package org.guce.epayment.core.dao;

import java.util.Optional;

/**
 *
 * @author tadzotsa
 */
public interface CoreDao {

    <T> Optional<T> findById(Object id, Class<T> clazz) throws Exception;

    String generateUniqueValue(String table, String prefix);

}
