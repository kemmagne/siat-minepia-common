package org.guce.siat.common.service;

import java.util.List;
import org.guce.siat.common.model.Container;
import org.guce.siat.common.model.File;

/**
 *
 * @author ht
 */
public interface CoreService {

    <T> T save(T entity);

    <T> void save(List<T> entities);

    <T> void update(T entity);

    <T> List<T> update(List<T> entities);

    <T> void delete(T entity);

    <T> void delete(List<T> entities);

    Container findContainerByFileAndNumber(File file, String contNumber);

}
