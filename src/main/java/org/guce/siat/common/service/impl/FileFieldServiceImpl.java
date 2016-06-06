package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileFieldDao;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.service.FileFieldService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FileFieldValueServiceImpl.
 */
@Service("fileFieldService")
@Transactional(readOnly = true)
public class FileFieldServiceImpl extends AbstractServiceImpl<FileField> implements FileFieldService
{

	/** The file field value dao. */
	@Autowired
	private FileFieldDao fileFieldDao;


	/**
	 * Instantiates a new file service impl.
	 */
	public FileFieldServiceImpl()
	{
		super();
	}


	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FileField> getJpaDao()
	{
		return fileFieldDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FileField> jpaDao)
	{
		this.fileFieldDao = (FileFieldDao) jpaDao;
	}


	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.FileFieldService#findFileFieldByCodeAndFileType(java.lang.String, org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	@Override
	public FileField findFileFieldByCodeAndFileType(String fileFieldCode, FileTypeCode fileTypeCode)
	{
		return  fileFieldDao.findFileFieldByCodeAndFileType(fileFieldCode,fileTypeCode);
	}

}
