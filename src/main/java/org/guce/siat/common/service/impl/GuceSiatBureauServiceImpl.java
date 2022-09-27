package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.GuceSiatBureauDao;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.GuceSiatBureau;
import org.guce.siat.common.service.GuceSiatBureauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class GuceSiatBureauServiceImpl.
 */
@org.springframework.stereotype.Service("guceSiatBureauService")
@Transactional(readOnly = true)
public class GuceSiatBureauServiceImpl extends AbstractServiceImpl<GuceSiatBureau> implements GuceSiatBureauService
{

	/** The guce siat bureau dao. */
	@Autowired
	private GuceSiatBureauDao guceSiatBureauDao;

	/**
	 * Instantiates a new guce siat bureau service impl.
	 */
	public GuceSiatBureauServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<GuceSiatBureau> getJpaDao()
	{
		return guceSiatBureauDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<GuceSiatBureau> jpaDao)
	{
		this.guceSiatBureauDao = (GuceSiatBureauDao) jpaDao;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.GuceSiatBureauService#findGuceSiatBureauBySiatBureau(org.guce.siat.common.model.Bureau
	 * )
	 */
	@Override
	public GuceSiatBureau findGuceSiatBureauBySiatBureau(final Bureau siatBureau)
	{
		return guceSiatBureauDao.findByBureauSiat(siatBureau);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.GuceSiatBureauService#findGuceSiatBureauByGuceBureau(java.lang.String)
	 */
	@Override
	public GuceSiatBureau findGuceSiatBureauByGuceBureau(final String guceBureau)
	{
		return guceSiatBureauDao.findByBureauGuce(guceBureau);
	}

}
