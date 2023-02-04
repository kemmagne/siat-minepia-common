package org.guce.siat.common.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.FileTypeFlowDao;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlow;
import org.guce.siat.common.model.Flow;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FlowDaoImpl.
 */
@Repository("fileTypeFlowDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileTypeFlowDaoImpl extends AbstractJpaDaoImpl<FileTypeFlow> implements FileTypeFlowDao
{

	/**
	 * Instantiates a new flow dao impl.
	 */
	public FileTypeFlowDaoImpl()
	{
		super();
		setClasse(FileTypeFlow.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.FileTypeFlowDao#findByFlowAndFileType(org.guce.siat.common.model.FileType,
	 * org.guce.siat.common.model.Flow)
	 */
	@Override
	public FileTypeFlow findByFlowAndFileType(final FileType fileType, final Flow flow)
	{
		try
		{
			final TypedQuery<FileTypeFlow> query = super.entityManager.createQuery(
					"SELECT f FROM FileTypeFlow f WHERE f.pk.flow= :flow AND f.pk.fileType= :fileType", FileTypeFlow.class);
			query.setParameter("flow", flow);
			query.setParameter("fileType", fileType);

			return query.getSingleResult();
		}
		catch (NonUniqueResultException | NoResultException e)
		{
			return null;
		}
	}

}
