/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.StampSignatureDao;
import org.guce.siat.common.model.StampSignature;
import org.guce.siat.common.service.StampSignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mbezele
 */

/**
 * The Class BureauServiceImpl.
 */
@org.springframework.stereotype.Service("stampSignatureService")
@Transactional(readOnly = true)
public class StampSignatureServiceImpl extends AbstractServiceImpl<StampSignature> implements StampSignatureService {

    /**
     * The stamp signature dao.
     */
    @Autowired
    private StampSignatureDao stampSignatureDao;

   
    /**
     * Instantiates a new bureau service impl.
     */
    public StampSignatureServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.BureauService#findBureauByTypeAndOrganism(org.guce.siat.common.utils.enums.BureauType
	 * , org.guce.siat.common.model.Organism)
     */
    @Override
    public StampSignature findByName(String name) {

        StampSignature stampSignature = stampSignatureDao.findByName(name);
        return stampSignature;
    }
 
	@Override
	public AbstractJpaDao<StampSignature> getJpaDao() {
		return stampSignatureDao;
	}

	@Override
	public void setJpaDao(AbstractJpaDao<StampSignature> jpaDao) {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	 this.stampSignatureDao = (StampSignatureDao) jpaDao;
	}

}
