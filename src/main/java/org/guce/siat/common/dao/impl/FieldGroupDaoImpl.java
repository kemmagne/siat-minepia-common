/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.FieldGroupDao;
import org.guce.siat.common.model.FieldGroup;
import org.guce.siat.common.model.FileType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FieldGroupDaoImpl.
 */
@Repository("fieldGroupDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FieldGroupDaoImpl extends AbstractJpaDaoImpl<FieldGroup> implements FieldGroupDao
{

	/**
	 * Instantiates a new field group dao impl.
	 */
	public FieldGroupDaoImpl()
	{
		super();
		setClasse(FieldGroup.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FieldGroupDao#findAllByFileType(org.guce.siat.common.model.FileType,
	 * java.lang.String)
	 */
	@Override
	public List<FieldGroup> findAllByFileType(final FileType fileType, final String type)
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT DISTINCT f FROM FieldGroup f");
		if (type.equals("01"))
		{
			builder.append(" JOIN f.fileFieldList field");
		}
		else
		{
			builder.append(" JOIN f.fileItemFieldList field");
		}
		builder.append(" WHERE field.fileType=:fileType ORDER BY f.id ASC");
		final TypedQuery<FieldGroup> query = super.entityManager.createQuery(builder.toString(), FieldGroup.class);
		query.setParameter("fileType", fileType);
		return query.getResultList();
	}

}
