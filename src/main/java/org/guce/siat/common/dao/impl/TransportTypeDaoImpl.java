/**
 *
 */
package org.guce.siat.common.dao.impl;

import org.guce.siat.common.dao.TransportTypeDao;
import org.guce.siat.common.model.TransportType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class TransportTypeDaoImpl.
 */
@Repository("transportTypeDao")
@Transactional(propagation = Propagation.REQUIRED)
public class TransportTypeDaoImpl extends AbstractJpaDaoImpl<TransportType> implements TransportTypeDao
{

	/**
	 * Instantiates a new transport type dao impl.
	 */
	public TransportTypeDaoImpl()
	{
		super();
		setClasse(TransportType.class);
	}

}
