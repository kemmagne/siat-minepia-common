package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.Container;
import org.guce.siat.common.model.File;

/**
 *
 * @author ht
 */
public interface CoreDao {

    <T> T save(T entity);

    <T> void save(List<T> entities);

    <T> void update(T entity);

    <T> List<T> update(List<T> entities);

    /**
     * Delete merged entity
     *
     * @param <T>
     * @param entity
     */
    <T> void delete(T entity);

    /**
     * Delete without merge the entity
     *
     * @param <T>
     * @param entity
     */
    <T> void deleteNoMerge(T entity);

    /**
     * Delete merged list of entities
     *
     * @param <T>
     * @param entities
     */
    <T> void delete(List<T> entities);

    /**
     * Delete without merge the list of entities
     *
     * @param <T>
     * @param entities
     */
    <T> void deleteNoMerge(List<T> entities);

    List<Container> findContainersByFile(File file);

    Container findContainerByFileAndNumber(File file, String contNumber);

}
