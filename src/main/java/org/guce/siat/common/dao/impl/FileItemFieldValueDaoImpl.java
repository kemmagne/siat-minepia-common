/**
 *
 */
package org.guce.siat.common.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.FileItemFieldValueDao;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;





/**
 * The Class FileItemFieldValueDaoImpl.
 */
@Repository("fileItemFieldValueDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileItemFieldValueDaoImpl extends AbstractJpaDaoImpl<FileItemFieldValue> implements FileItemFieldValueDao
{

	/**
	 * Instantiates a new file field value dao impl.
	 */
	public FileItemFieldValueDaoImpl()
	{
		super();
		setClasse(FileItemFieldValue.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileItemFieldValueDao#findValueByFileItemFieldAndFileItem(java.lang.String,
	 * org.guce.siat.common.model.FileItem)
	 */
	@Override
	public FileItemFieldValue findValueByFileItemFieldAndFileItem(final String FileItemFieldCode, final FileItem fileItem)
	{

		try
		{
			final String hqlString = "SELECT f FROM FileItemFieldValue f WHERE f.primaryKey.fileItemField.code = :FileItemFieldCode AND f.primaryKey.fileItem.id = :fileItemId ";
			final TypedQuery<FileItemFieldValue> query = super.entityManager.createQuery(hqlString, FileItemFieldValue.class);
			query.setParameter("FileItemFieldCode", FileItemFieldCode);
			query.setParameter("fileItemId", fileItem.getId());
			return query.getSingleResult();

		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			return null;
		}
	}

}
