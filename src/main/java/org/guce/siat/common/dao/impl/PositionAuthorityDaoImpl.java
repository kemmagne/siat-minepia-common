package org.guce.siat.common.dao.impl;

import org.guce.siat.common.dao.PositionAuthorityDao;
import org.guce.siat.common.model.PositionAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class PositionAuthorityDaoImpl.
 */
@Repository("PositionAuthorityDao")
@Transactional(propagation = Propagation.REQUIRED)
public class PositionAuthorityDaoImpl extends AbstractJpaDaoImpl<PositionAuthority> implements PositionAuthorityDao
{


	/**
	 * Instantiates a new position authority dao impl.
	 */
	public PositionAuthorityDaoImpl()
	{
		super();
		setClasse(PositionAuthority.class);
	}

}
