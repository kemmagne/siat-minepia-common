package org.guce.siat.common.dao.impl;

import org.guce.siat.common.dao.HourlyTypeDao;
import org.guce.siat.common.model.HourlyType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class HourlyTypeDaoImpl.
 */
@Repository("hourlyTypeDao")
@Transactional(propagation = Propagation.REQUIRED)
public class HourlyTypeDaoImpl extends AbstractJpaDaoImpl<HourlyType> implements HourlyTypeDao
{
	/**
	 * Instantiates a new hourly type dao impl.
	 */
	public HourlyTypeDaoImpl()
	{
		super();
		setClasse(HourlyType.class);
	}


}
