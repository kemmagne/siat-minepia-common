/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.FileItemFieldDao;
import org.guce.siat.common.model.FileItemField;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileFieldValueDaoImpl.
 */
@Repository("fileItemFieldDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileItemFieldDaoImpl extends AbstractJpaDaoImpl<FileItemField> implements FileItemFieldDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileItemFieldDaoImpl.class);

    /**
     * Instantiates a new file field dao impl.
     */
    public FileItemFieldDaoImpl() {
        super();
        setClasse(FileItemField.class);
    }


    /* (non-Javadoc)
	 * @see org.guce.siat.common.dao.FileFieldDao#findFileFieldByCodeAndFileType(java.lang.String, org.guce.siat.common.utils.enums.FileTypeCode)
     */
    @Override
    public FileItemField findFileItemFieldByCodeAndFileType(final String fileItemFieldCode, final FileTypeCode fileTypeCode) {
        try {
            final String hqlString = "SELECT f FROM FileItemField f WHERE f.code = :fileItemFieldCode AND f.fileType.code = :fileTypeCode ";
            final TypedQuery<FileItemField> query = super.entityManager.createQuery(hqlString, FileItemField.class);
            query.setParameter("fileItemFieldCode", fileItemFieldCode);
            query.setParameter("fileTypeCode", fileTypeCode);
            return query.getSingleResult();

        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

}
