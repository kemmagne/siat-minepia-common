/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.StampSignatureDao;
import org.guce.siat.common.model.Car;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.StampSignature;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mbezele
 */


/**
 * The Class StampSignatureImpl.
 */
@Repository("stampSignatureDao")
@Transactional(propagation = Propagation.REQUIRED)
public class StampSignatureDaoImpl extends AbstractJpaDaoImpl<StampSignature> implements StampSignatureDao
{

	/**
	 * Instantiates a new stampSignature dao impl.
	 */
	public StampSignatureDaoImpl()
	{
		super();
		setClasse(StampSignature.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.CarDao#findByOrganism(org.guce.siat.common.model.Organism)
	 */
//	@Override
//	public List<Car> findByOrganism(final Organism organism)
//	{
//		if (organism != null)
//		{
//			final String hqlString = "SELECT c FROM Car c WHERE c.organism.id = :organismId AND c.deleted=false";
//			final TypedQuery<Car> query = super.entityManager.createQuery(hqlString, Car.class);
//			query.setParameter("organismId", organism.getId());
//			return query.getResultList();
//		}
//		return Collections.emptyList();
//	}

	@Override
	public StampSignature findById(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public StampSignature findByName(String name) {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	
	if (name != null)
		{
			final String hqlString = "SELECT s FROM StampSignature s WHERE s.name = :imageName";
			final TypedQuery<StampSignature> query = super.entityManager.createQuery(hqlString, StampSignature.class);
			query.setParameter("imageName", name);
			return query.getSingleResult();
		}
		//return Collections.emptyList();
		return null;
	
	}

}

