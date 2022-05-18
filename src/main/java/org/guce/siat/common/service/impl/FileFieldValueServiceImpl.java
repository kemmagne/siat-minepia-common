package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileFieldValueDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.service.FileFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileFieldValueServiceImpl.
 */
@Service("fileFieldValueService")
@Transactional(readOnly = true)
public class FileFieldValueServiceImpl extends AbstractServiceImpl<FileFieldValue> implements FileFieldValueService {

    /**
     * The file field value dao.
     */
    @Autowired
    private FileFieldValueDao fileFieldValueDao;


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<FileFieldValue> getJpaDao() {
        return fileFieldValueDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<FileFieldValue> jpaDao) {
        this.fileFieldValueDao = (FileFieldValueDao) jpaDao;
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileFieldValueService#findFieldByCode(java.lang.String)
     */
    @Override
    public FileField findFieldByCode(final String code) {
        return fileFieldValueDao.findFieldByCode(code);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileFieldValueService#findValueByFileFieldAndFile(java.lang.String,
	 * org.guce.siat.common.model.File)
     */
    @Override
    public FileFieldValue findValueByFileFieldAndFile(final String fileFieldCode, final File file) {
        return fileFieldValueDao.findValueByFileFieldAndFile(fileFieldCode, file);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileFieldValueService#findFileItemFieldValueByCodeAndFileItem(java.lang.String,
	 * org.guce.siat.common.model.FileItem)
     */
    @Override
    public FileItemFieldValue findFileItemFieldValueByCodeAndFileItem(final String code, final FileItem fileItem) {
        return fileFieldValueDao.findFileItemFieldValueByCodeAndFileItem(code, fileItem);
    }

}
