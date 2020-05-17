package org.guce.siat.common.dao;

import java.util.List;

/**
 *
 * @author ht
 */
public interface CoreDao {

    <T> T save(T entity);

    <T> void save(List<T> entities);

    <T> void update(T entity);

    <T> List<T> update(List<T> entities);

}
