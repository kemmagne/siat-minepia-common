/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.FileFieldDao;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileFieldValueDaoImpl.
 */
@Repository("fileFieldDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileFieldDaoImpl extends AbstractJpaDaoImpl<FileField> implements FileFieldDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileFieldDaoImpl.class);

    /**
     * Instantiates a new file field dao impl.
     */
    public FileFieldDaoImpl() {
        super();
        setClasse(FileField.class);
    }


    /* (non-Javadoc)
	 * @see org.guce.siat.common.dao.FileFieldDao#findFileFieldByCodeAndFileType(java.lang.String, org.guce.siat.common.utils.enums.FileTypeCode)
     */
    @Override
    public FileField findFileFieldByCodeAndFileType(final String fileFieldCode, final FileTypeCode fileTypeCode) {
        try {
            final String hqlString = "SELECT f FROM FileField f WHERE f.code = :fileFieldCode AND f.fileType.code = :fileTypeCode ";
            final TypedQuery<FileField> query = super.entityManager.createQuery(hqlString, FileField.class);
            query.setParameter("fileFieldCode", fileFieldCode);
            query.setParameter("fileTypeCode", fileTypeCode);
            return query.getSingleResult();

        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }
    
    

    /* (non-Javadoc)
	 * @see org.guce.siat.common.dao.FileFieldDao#findFileFieldByCodeAndFileType(java.lang.String, org.guce.siat.common.utils.enums.FileTypeCode)
     */
    @Override
    public List<FileField> findFileFieldByFileType( final FileTypeCode fileTypeCode) {
        try {
            final String hqlString = "SELECT f FROM FileField f WHERE f.fileType.code = :fileTypeCode ";
            final TypedQuery<FileField> query = super.entityManager.createQuery(hqlString, FileField.class);
            
            query.setParameter("fileTypeCode", fileTypeCode);
            return query.getResultList();

        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

}
