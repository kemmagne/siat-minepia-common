package org.guce.siat.common.dao.impl;

import org.guce.siat.common.dao.ValidationFlowDao;
import org.guce.siat.common.model.File;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ValidationFlowDaoImpl.
 */
@Repository("validationFlowDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ValidationFlowDaoImpl extends AbstractJpaDaoImpl<File> implements ValidationFlowDao
{

	/**
	 * Instantiates a new validation flow dao impl.
	 */
	public ValidationFlowDaoImpl()
	{
		super();
		setClasse(File.class);
	}

}
