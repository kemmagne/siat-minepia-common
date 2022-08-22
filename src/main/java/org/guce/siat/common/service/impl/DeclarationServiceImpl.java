/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AdditionnalDeclarationDao;
import org.guce.siat.common.dao.DeclarationDao;
import org.guce.siat.common.model.AdditionnalDeclaration;
import org.guce.siat.common.model.AdditionnalDeclaration;
import org.guce.siat.common.model.Declaration;
import org.guce.siat.common.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boris.tomfeu
 */
@Service("declarationService")
@Transactional(readOnly = true)
public class DeclarationServiceImpl extends AbstractServiceImpl<Declaration> implements DeclarationService {

    
    @Autowired
    private DeclarationDao declarationDao;

    /**
     * Instantiates a new ippc treatment service impl.
     */
    public DeclarationServiceImpl() {
        super();
    }

    @Override
    public AbstractJpaDao<Declaration> getJpaDao() {
        return declarationDao;
    }

    @Override
    public void setJpaDao(AbstractJpaDao<Declaration> ajd) {
        declarationDao = (DeclarationDao) ajd;
    }

}
