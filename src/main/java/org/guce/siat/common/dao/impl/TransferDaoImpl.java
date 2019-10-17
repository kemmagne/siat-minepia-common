/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.TransferDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.Transfer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yenke
 */
@Repository("transferDao")
@Transactional(propagation = Propagation.REQUIRED)
public class TransferDaoImpl extends AbstractJpaDaoImpl<Transfer> implements TransferDao{

    
    public TransferDaoImpl(){
        super();
        setClasse(Transfer.class);
    }
    
    @Override
    public List<Transfer> findByFile(final File file) {
        
        if (file != null){
            final String hqlString = "SELECT t FROM Transfer WHERE t.file.id = :fileId";
            final TypedQuery<Transfer> query = super.entityManager.createQuery(hqlString, Transfer.class);
            query.setParameter("fileId", file.getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }
    
}
