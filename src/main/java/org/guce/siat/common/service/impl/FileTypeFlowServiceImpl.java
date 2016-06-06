/**
 *
 */
package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileTypeFlowDao;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlow;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.service.FileTypeFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FlowServiceImpl.
 */
@Service("fileTypeFlowService")
@Transactional(readOnly = true)
public class FileTypeFlowServiceImpl extends AbstractServiceImpl<FileTypeFlow> implements FileTypeFlowService
{

	/** The file type flow dao. */
	@Autowired
	private FileTypeFlowDao fileTypeFlowDao;

	/**
	 * Instantiates a new flow service impl.
	 */
	public FileTypeFlowServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FileTypeFlow> getJpaDao()
	{
		return fileTypeFlowDao;
	}

	/**
	 * Gets the file type flow dao.
	 *
	 * @return the file type flow dao
	 */
	public FileTypeFlowDao getFileTypeFlowDao()
	{
		return fileTypeFlowDao;
	}

	/**
	 * Sets the file type flow dao.
	 *
	 * @param fileTypeFlowDao
	 *           the new file type flow dao
	 */
	public void setFileTypeFlowDao(final FileTypeFlowDao fileTypeFlowDao)
	{
		this.fileTypeFlowDao = fileTypeFlowDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileTypeFlowService#findByFlowAndFileType(org.guce.siat.common.model.FileType,
	 * org.guce.siat.common.model.Flow)
	 */
	@Override
	public FileTypeFlow findByFlowAndFileType(final FileType fileType, final Flow flow)
	{
		return fileTypeFlowDao.findByFlowAndFileType(fileType, flow);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FileTypeFlow> jpaDao)
	{
		this.fileTypeFlowDao = (FileTypeFlowDao) jpaDao;
	}


}
