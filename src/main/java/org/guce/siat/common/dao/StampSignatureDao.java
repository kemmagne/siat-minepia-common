/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.StampSignature;

/**
 *
 * @author mbezele
 */



/**
 * The Interface StampSignatureDao.
 */
public interface StampSignatureDao extends AbstractJpaDao<StampSignature>
{

	/**
	 * Find by Id.
	 *
	 * @return the StampSignature
	 */
	StampSignature findById(Long id);
	
	StampSignature findByName(String name);
}

