/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.FileFieldValueDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileFieldValueDaoImpl.
 */
@Repository("fileFieldValueDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileFieldValueDaoImpl extends AbstractJpaDaoImpl<FileFieldValue> implements FileFieldValueDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FileFieldValueDaoImpl.class);

	/**
	 * Instantiates a new file field value dao impl.
	 */
	public FileFieldValueDaoImpl() {
		super();
		setClasse(FileFieldValue.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileFieldValueDao#retriveFileByFileFieldCodeAndValue (java.lang.String,
	 * java.lang.String, org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	@Override
	public File retriveFileByFileFieldCodeAndValue(final String fileFieldCode, final String value, final FileTypeCode fileTypeCode) {

		try {

			final StringBuilder hqlQuery = new StringBuilder();
			hqlQuery.append("SELECT ffv.primaryKey.file FROM FileFieldValue ffv ");
			hqlQuery.append(" WHERE ffv.primaryKey.fileField.code=:fileFieldCode");
			hqlQuery.append(" AND ffv.value=:value");
			hqlQuery.append(" AND ffv.primaryKey.file.fileType.code=:fileTypeCode");

			final TypedQuery<File> query = super.entityManager.createQuery(hqlQuery.toString(), File.class);

			query.setParameter("fileFieldCode", fileFieldCode);
			query.setParameter("value", value);
			query.setParameter("fileTypeCode", fileTypeCode);

			return query.getSingleResult();

		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileFieldValueDao#findFieldByCode(java.lang. String)
	 */
	@Override
	public FileField findFieldByCode(final String fieldCode) {

		try {

			final TypedQuery<FileField> query = entityManager.createQuery("SELECT f FROM FileField f WHERE f.code= :fieldCode",
					FileField.class);
			query.setParameter("fieldCode".intern(), fieldCode);
			return query.getSingleResult();

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileFieldValueDao#findFileItemFieldValueByCodeAndFileItem(java.lang.String,
	 * org.guce.siat.common.model.FileItem)
	 */
	@Override
	public FileItemFieldValue findFileItemFieldValueByCodeAndFileItem(final String code, final FileItem fileItem) {

		try {

			final TypedQuery<FileItemFieldValue> query = entityManager
					.createQuery(
							"SELECT f FROM FileItemFieldValue f WHERE f.primaryKey.fileItemField.code= :fieldItemCode AND f.primaryKey.fileItem =:fileItem",
							FileItemFieldValue.class);
			query.setParameter("fieldItemCode".intern(), code);
			query.setParameter("fileItem".intern(), fileItem);
			return query.getSingleResult();

		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileFieldValueDao#findValueByFileFieldAndFile(java.lang.String,
	 * org.guce.siat.common.model.File)
	 */
	@Override
	public FileFieldValue findValueByFileFieldAndFile(final String fileFieldCode, final File file) {
		try {
			final String hqlString = "FROM FileFieldValue f WHERE f.primaryKey.fileField.code = :fileFieldCode AND f.primaryKey.file.id = :fileId ";
			final TypedQuery<FileFieldValue> query = super.entityManager.createQuery(hqlString, FileFieldValue.class);
			query.setParameter("fileFieldCode", fileFieldCode);
			query.setParameter("fileId", file.getId());
			return query.getSingleResult();

		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}

	}

}
