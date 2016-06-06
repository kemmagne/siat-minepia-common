package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileTypeDao;
import org.guce.siat.common.dao.UserAuthorityFileTypeDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.FileTypeService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FileTypeServiceImpl.
 */
@Service("fileTypeService")
@Transactional(readOnly = true)
public class FileTypeServiceImpl extends AbstractServiceImpl<FileType> implements FileTypeService
{


	/** The file type dao. */
	@Autowired
	private FileTypeDao fileTypeDao;

	/** The user authority file type dao. */
	@Autowired
	private UserAuthorityFileTypeDao userAuthorityFileTypeDao;

	/**
	 * Instantiates a new file type service impl.
	 */
	public FileTypeServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FileType> getJpaDao()
	{
		return fileTypeDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FileType> jpaDao)
	{
		this.fileTypeDao = (FileTypeDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FileTypeService#findFileTypeByMinistry(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public List<FileType> findFileTypeByMinistry(final Ministry ministry)
	{
		try
		{
			return fileTypeDao.findFileTypeByMinistry(ministry);
		}
		catch (final DAOException e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FileTypeService#findDistinctFileTypesByUser(org.guce.siat.common.model.User)
	 */
	@Override
	public List<FileType> findDistinctFileTypesByUser(final User user)
	{
		return userAuthorityFileTypeDao.findFilesTypesByAuthorizedUser(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FileTypeService#findAuthoritiesByFileType(org.guce.siat.common.model.FileType)
	 */
	@Override
	public List<Authority> findAuthoritiesByFileType(final FileType filetype)
	{
		try
		{
			return fileTypeDao.findAuthoritiesByFileType(filetype);
		}
		catch (final DAOException e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	} 

	@Override
	public List<FileType> findFileTypesByCodes(final FileTypeCode... fileTypeCodes)
	{
		return fileTypeDao.findFileTypesByCodes(fileTypeCodes);
	}

	@Override
	public void update(FileType selected, List<FileTypeStep> targetFileTypeStep)
	{
		 fileTypeDao.update(selected, targetFileTypeStep);	
		
	} 

	 

}
