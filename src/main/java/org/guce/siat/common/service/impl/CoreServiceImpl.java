package org.guce.siat.common.service.impl;

import java.util.List;
import org.guce.siat.common.dao.CoreDao;
import org.guce.siat.common.model.Container;
import org.guce.siat.common.model.File;
import org.guce.siat.common.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ht
 */
@Service("coreService")
@Transactional
public class CoreServiceImpl implements CoreService {

    @Autowired
    private CoreDao dao;

    @Override
    public <T> T save(T entity) {
        return dao.save(entity);
    }

    @Override
    public <T> void save(List<T> entities) {
        dao.save(entities);
    }

    @Override
    public <T> void update(T entity) {
        dao.update(entity);
    }

    @Override
    public <T> List<T> update(List<T> entities) {
        return dao.update(entities);
    }

    @Override
    public <T> void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public <T> void delete(List<T> entities) {
        dao.delete(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public Container findContainerByFileAndNumber(File file, String contNumber) {
        return dao.findContainerByFileAndNumber(file, contNumber);
    }

}
