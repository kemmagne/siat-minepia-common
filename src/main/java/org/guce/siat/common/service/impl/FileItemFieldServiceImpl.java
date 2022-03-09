package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileItemFieldDao;
import org.guce.siat.common.model.FileItemField;
import org.guce.siat.common.service.FileItemFieldService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FileFieldValueServiceImpl.
 */
@Service("fileItemFieldService")
@Transactional(readOnly = true)
public class FileItemFieldServiceImpl extends AbstractServiceImpl<FileItemField> implements FileItemFieldService
{

	/** The file field value dao. */
	@Autowired
	private FileItemFieldDao fileItemFieldDao;


	/**
	 * Instantiates a new file service impl.
	 */
	public FileItemFieldServiceImpl()
	{
		super();
	}


	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FileItemField> getJpaDao()
	{
		return fileItemFieldDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FileItemField> jpaDao)
	{
		this.fileItemFieldDao = (FileItemFieldDao) jpaDao;
	}


	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.FileFieldService#findFileFieldByCodeAndFileType(java.lang.String, org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	@Override
	public FileItemField findFileItemFieldByCodeAndFileType(String fileFieldCode, FileTypeCode fileTypeCode)
	{
		return  fileItemFieldDao.findFileItemFieldByCodeAndFileType(fileFieldCode,fileTypeCode);
	}

}
