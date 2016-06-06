package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.RecommandationDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Recommandation;
import org.guce.siat.common.service.RecommandationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class RecommandationServiceImpl.
 */
@Service("recommandationService")
@Transactional(readOnly = true)
public class RecommandationServiceImpl extends AbstractServiceImpl<Recommandation> implements RecommandationService
{

	/** The recommandation dao. */
	@Autowired
	private RecommandationDao recommandationDao;


	/**
	 * Instantiates a new recommandation service impl.
	 */
	public RecommandationServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Recommandation> getJpaDao()
	{
		return recommandationDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Recommandation> jpaDao)
	{
		this.recommandationDao = (RecommandationDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.core.ct.service.RecommandationService#findRecommandationByFile(org.guce.siat.core.ct.model.File)
	 */
	@Override
	public List<Recommandation> findRecommandationByFileAndAuthorties(final File file, final List<Authority> authorities)
	{
		return recommandationDao.findRecommandationByFileAndAuthorties(file, authorities);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.core.ct.service.RecommandationService#findRecommandationByFileItem(org.guce.siat.core.ct.model.FileItem
	 * )
	 */
	@Override
	public List<Recommandation> findRecommandationByFileItemAndAuthorties(final FileItem fileItem,
			final List<Authority> authorities)
	{
		return recommandationDao.findRecommandationByFileItemAndAuthorties(fileItem, authorities);
	}
}
