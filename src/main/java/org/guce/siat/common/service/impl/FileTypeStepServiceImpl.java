package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileTypeStepDao;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.service.FileTypeStepService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FileTypeStepServiceImpl.
 */
@Service("fileTypeStepService")
@Transactional(readOnly = true)
public class FileTypeStepServiceImpl extends AbstractServiceImpl<FileTypeStep> implements FileTypeStepService
{


	/** The file type step dao. */
	@Autowired
	private FileTypeStepDao fileTypeStepDao;


	/**
	 * Instantiates a new file type step service impl.
	 */
	public FileTypeStepServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FileTypeStep> getJpaDao()
	{
		return fileTypeStepDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FileTypeStep> jpaDao)
	{
		this.fileTypeStepDao = (FileTypeStepDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileTypeStepService#findAllStepByFileTypes(java.util.List)
	 */
	@Override
	public List<Step> findAllStepByFileTypes(final List<FileTypeCode> fileTypes)
	{
		return fileTypeStepDao.findAllStepByFileTypes(fileTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.FileTypeStepService#findFileTypeStepByFileTypeAndStep(org.guce.siat.common.model.
	 * FileType, org.guce.siat.common.model.Step)
	 */
	@Override
	public FileTypeStep findFileTypeStepByFileTypeAndStep(final FileType fileType, final Step step)
	{

		return fileTypeStepDao.findFileTypeStepByFileTypeAndStep(fileType, step);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.FileTypeStepService#findStepByFileTypeAndLabel(org.guce.siat.common.model.FileType,
	 * java.lang.String)
	 */
	@Override
	public Step findStepByFileTypeAndLabel(final FileType fileType, final String step)
	{
		return fileTypeStepDao.findStepByFileTypeAndLabel(fileType, step);
	}

}
