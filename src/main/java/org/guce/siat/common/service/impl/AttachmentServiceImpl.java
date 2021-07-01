package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AttachmentDao;
import org.guce.siat.common.model.Attachment;
import org.guce.siat.common.model.File;
import org.guce.siat.common.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AttachmentServiceImpl.
 */
@Service("attachmentService")
@Transactional(readOnly = true)
public class AttachmentServiceImpl extends AbstractServiceImpl<Attachment> implements AttachmentService
{

	/** The attachment dao. */
	@Autowired
	private AttachmentDao attachmentDao;



	/**
	 * Instantiates a new attachment service impl.
	 */
	public AttachmentServiceImpl()
	{
		super();
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Attachment> getJpaDao()
	{
		return attachmentDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Attachment> jpaDao)
	{
		this.attachmentDao = (AttachmentDao) jpaDao;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.core.ct.service.AttachmentService#findAttachmentsByFile(org.guce.siat.core.ct.model.File)
	 */
	@Override
	public List<Attachment> findAttachmentsByFile(final File file)
	{
		return attachmentDao.findAttachmentsByFile(file);
	}

        @Override
        public Attachment findAttachmentByFileAndAttachmentTypeAndDocumentNameAndDocumentPath(File file, String attachmentType, String documentName, String documentPath) {
            return attachmentDao.findAttachmentByFileAndAttachmentTypeAndDocumentNameAndDocumentPath(file, attachmentType, documentName, documentPath);
        }

}
