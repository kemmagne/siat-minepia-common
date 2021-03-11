package org.guce.siat.common.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import org.guce.siat.common.dao.FileItemFieldValueDao;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemField;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileItemFieldValueDaoImpl.
 */
@Repository("fileItemFieldValueDao")
@Transactional
public class FileItemFieldValueDaoImpl extends AbstractJpaDaoImpl<FileItemFieldValue> implements FileItemFieldValueDao {

    /**
     * Instantiates a new file field value dao impl.
     */
    public FileItemFieldValueDaoImpl() {
        setClasse(FileItemFieldValue.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileItemFieldValueDao#findValueByFileItemFieldAndFile(java.lang.String,
	 * org.guce.siat.common.model.FileItem)
     */
    @Override
    public FileItemFieldValue findValueByFileItemFieldAndFileItem(final String fileItemFieldCode, final FileItem fileItem) {

        try {
            final String hqlString = "SELECT f FROM FileItemFieldValue f WHERE f.primaryKey.fileItemField.code = :FileItemFieldCode AND f.primaryKey.fileItem.id = :fileItemId ";
            final TypedQuery<FileItemFieldValue> query = super.entityManager.createQuery(hqlString, FileItemFieldValue.class);
            query.setParameter("FileItemFieldCode", fileItemFieldCode);
            query.setParameter("fileItemId", fileItem.getId());
            return query.getSingleResult();

        } catch (final NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @SuppressWarnings("JPQLValidation")
    @Override
    public FileItemFieldValue findValueByFileItemFieldAndFile(String fileItemFieldCode, FileTypeCode fileTypeCode, String numeroDemande) {

        TypedQuery<FileItemFieldValue> query = super.entityManager
                .createQuery("SELECT f FROM FileItemFieldValue f WHERE f.primaryKey.fileItemField.code = :fileItemFieldCode AND f.primaryKey.fileItem.file.fileType.code = :fileTypeCode AND f.primaryKey.fileItem.file.numeroDemande = :numeroDemande", FileItemFieldValue.class);

        query.setParameter("fileItemFieldCode", fileItemFieldCode);
        query.setParameter("fileTypeCode", fileTypeCode);
        query.setParameter("numeroDemande", numeroDemande);

        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (final NoResultException ex) {
            return null;
        }
    }

    @Override
    public FileItemField findByFileTypeAndCode(FileTypeCode fileTypeCode, String code) {

        TypedQuery<FileItemField> query = super.entityManager.createQuery("SELECT fif FROM FileItemField fif WHERE fif.fileType.code = :fileTypeCode AND fif.code = :fifCode", FileItemField.class);

        query.setParameter("fileTypeCode", fileTypeCode);
        query.setParameter("fifCode", code);

        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
