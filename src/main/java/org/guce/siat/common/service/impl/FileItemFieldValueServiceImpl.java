package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileItemFieldValueDao;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.service.FileItemFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




/**
 * The Class FileItemFieldValueServiceImpl.
 */
@Service("fileItemFieldValueService")
@Transactional(readOnly = true)
public class FileItemFieldValueServiceImpl extends AbstractServiceImpl<FileItemFieldValue>implements FileItemFieldValueService
{

	/** The file field value dao. */
	@Autowired
	private FileItemFieldValueDao fileItemFieldValueDao;


	/**
	 * Instantiates a new file service impl.
	 */
	public FileItemFieldValueServiceImpl()
	{
		super();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<FileItemFieldValue> getJpaDao()
	{
		return fileItemFieldValueDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<FileItemFieldValue> jpaDao)
	{
		this.fileItemFieldValueDao = (FileItemFieldValueDao) jpaDao;
	}


	/* (non-Javadoc)
	 * @see org.guce.siat.common.service.FileItemFieldValueService#findValueByFileItemFieldAndFileItem(java.lang.String, org.guce.siat.common.model.FileItem)
	 */
	@Override
	public FileItemFieldValue findValueByFileItemFieldAndFileItem(String FileItemFieldCode, FileItem fileItem) {
		// TODO Auto-generated method stub
		return fileItemFieldValueDao.findValueByFileItemFieldAndFileItem(FileItemFieldCode,fileItem);
	}

}
