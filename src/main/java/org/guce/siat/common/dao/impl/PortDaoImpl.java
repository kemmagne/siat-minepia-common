package org.guce.siat.common.dao.impl;

import org.guce.siat.common.dao.PortDao;
import org.guce.siat.common.model.Port;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class PortDaoImpl.
 */
@Repository("portDao")
@Transactional(propagation = Propagation.REQUIRED)
public class PortDaoImpl extends AbstractJpaDaoImpl<Port> implements PortDao
{

	/**
	 * Instantiates a new file dao impl.
	 */
	public PortDaoImpl()
	{
		super();
		setClasse(Port.class);
	}

}
