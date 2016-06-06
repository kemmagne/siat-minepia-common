/**
 *
 */
package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AnalyseTypeDao;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.AnalyseType;
import org.guce.siat.common.service.AnalyseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AnalyseTypeServiceImpl.
 */
@Service("analyseTypeService")
@Transactional(readOnly = true)
public class AnalyseTypeServiceImpl extends AbstractServiceImpl<AnalyseType> implements AnalyseTypeService
{

	/** The AnalyseType dao. */
	@Autowired
	private AnalyseTypeDao analyseTypeDao;

	/**
	 * Instantiates a new AnalyseType service impl.
	 */
	public AnalyseTypeServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<AnalyseType> getJpaDao()
	{
		return analyseTypeDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<AnalyseType> jpaDao)
	{
		this.analyseTypeDao = (AnalyseTypeDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.AnalyseTypeService#findByAdministration(org.guce.siat.common.model.Administration)
	 */
	@Override
	public List<AnalyseType> findByAdministration(final Administration administration)
	{
		return analyseTypeDao.findByAdministration(administration);
	}

}
