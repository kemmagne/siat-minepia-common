package org.guce.siat.common.dao.impl;

import org.guce.siat.common.dao.RecommandationAuthorityDao;
import org.guce.siat.common.model.RecommandationAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class RecommandationAuthorityDaoImpl.
 */
@Repository("RecommandationAuthorityDao")
@Transactional(propagation = Propagation.REQUIRED)
public class RecommandationAuthorityDaoImpl extends AbstractJpaDaoImpl<RecommandationAuthority> implements
		RecommandationAuthorityDao
{
	/**
	 * Instantiates a new recommandation authority dao impl.
	 */
	public RecommandationAuthorityDaoImpl()
	{
		super();
		setClasse(RecommandationAuthority.class);
	}
}
