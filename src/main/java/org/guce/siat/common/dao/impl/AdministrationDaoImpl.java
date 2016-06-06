/**
 *
 */
package org.guce.siat.common.dao.impl;

import org.guce.siat.common.dao.AdministrationDao;
import org.guce.siat.common.model.Administration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AdministrationDaoImpl.
 */
@Repository("administrationDao")
@Transactional(propagation = Propagation.REQUIRED)
public class AdministrationDaoImpl extends AbstractJpaDaoImpl<Administration> implements AdministrationDao
{

	/**
	 * Instantiates a new administration dao impl.
	 */
	public AdministrationDaoImpl()
	{
		super();
		setClasse(Administration.class);
	}
}
