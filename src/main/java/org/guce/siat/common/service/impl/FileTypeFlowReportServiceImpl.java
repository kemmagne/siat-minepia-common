package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileTypeFlowReportDao;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlowReport;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.service.FileTypeFlowReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class FileTypeFlowReportServiceImpl.
 */
@Service("fileTypeFlowReportService")
@Transactional(readOnly = true)
public class FileTypeFlowReportServiceImpl extends AbstractServiceImpl<FileTypeFlowReport> implements FileTypeFlowReportService
{


	/** The file type flow report dao. */
	@Autowired
	private FileTypeFlowReportDao fileTypeFlowReportDao;


	/**
	 * Instantiates a new file type flow report service impl.
	 */
	public FileTypeFlowReportServiceImpl()
	{
		super();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FileTypeFlowReport> getJpaDao()
	{
		return fileTypeFlowReportDao;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FileTypeFlowReport> jpaDao)
	{
		this.fileTypeFlowReportDao = (FileTypeFlowReportDao) jpaDao;
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.FileTypeFlowReportService#findReportClassNameByFlowAndFileType(org.guce.siat.common
	 * .model.Flow, org.guce.siat.common.model.FileType)
	 */
	@Override
	public List<FileTypeFlowReport> findReportClassNameByFlowAndFileType(final Flow flow, final FileType fileType)
	{
		return fileTypeFlowReportDao.findReportClassNameByFlowAndFileType(flow, fileType);
	}
}
