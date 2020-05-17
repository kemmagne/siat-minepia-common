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

    Container findContainerByFileAndNumber(File file, String contNumber);

}
