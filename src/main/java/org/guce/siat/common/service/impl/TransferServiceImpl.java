/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service.impl;

import java.util.List;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.TransferDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.Transfer;
import org.guce.siat.common.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yenke
 */

@Service("transferService")
@Transactional(readOnly = true)
public class TransferServiceImpl extends AbstractServiceImpl<Transfer> implements TransferService{

    @Autowired
    private TransferDao transferDao;
    
    public TransferServiceImpl(){
        super();
    }
    
    @Override
    public AbstractJpaDao<Transfer> getJpaDao() {
        return transferDao;
    }

    @Override
    public void setJpaDao(AbstractJpaDao<Transfer> jpaDao) {
        this.transferDao = (TransferDao) jpaDao;
    }

    @Override
    public List<Transfer> findByFile(File file) {
        return transferDao.findByFile(file);
    }
    
}
