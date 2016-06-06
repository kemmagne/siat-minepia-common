package org.guce.siat.common.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.FileTypeStepDao;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class FileTypeStepDaoImpl.
 */
@Repository("fileTypeStepDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileTypeStepDaoImpl extends AbstractJpaDaoImpl<FileTypeStep> implements FileTypeStepDao
{


	/**
	 * Instantiates a new file type step dao impl.
	 */
	public FileTypeStepDaoImpl()
	{
		super();
		setClasse(FileTypeStep.class);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FileTypeStepDao#isApDecisionByFileTypeAndStep(org.guce.siat.core.ct.model.FileType,
	 * org.guce.siat.core.ct.model.Step)
	 */
	@Override
	public Boolean isApDecisionByFileTypeAndStep(final FileType fileType, final Step step)
	{
		final String hqlString = "FROM FileTypeStep fts WHERE fts.primaryKey.step = :step AND fts.primaryKey.fileType = :fileType AND fts.isApDecision = true";
		final TypedQuery<FileTypeStep> query = super.entityManager.createQuery(hqlString, FileTypeStep.class);
		query.setParameter("step", step);
		query.setParameter("fileType", fileType);
		return CollectionUtils.isNotEmpty(query.getResultList());
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileTypeStepDao#findAllStepByFileTypes(java.util.List)
	 */
	@Override
	public List<Step> findAllStepByFileTypes(final List<FileTypeCode> fileTypes)
	{
		final String jpqlString = "SELECT DISTINCT fts.primaryKey.step FROM FileTypeStep fts WHERE fts.primaryKey.fileType.code IN :fileTypes AND fts.primaryKey.step.isFinal=:etat ";
		final TypedQuery<Step> query = super.entityManager.createQuery(jpqlString, Step.class);
		query.setParameter("fileTypes", fileTypes).setParameter("etat", false);
		return query.getResultList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.FileTypeStepDao#findFileTypeStepByFileTypeAndStep(org.guce.siat.common.model.FileType,
	 * org.guce.siat.common.model.Step)
	 */
	@Override
	public FileTypeStep findFileTypeStepByFileTypeAndStep(final FileType fileType, final Step step)
	{
		try
		{
			final String hqlString = "FROM FileTypeStep fts WHERE fts.primaryKey.step = :step AND fts.primaryKey.fileType = :fileType";
			final TypedQuery<FileTypeStep> query = super.entityManager.createQuery(hqlString, FileTypeStep.class);
			query.setParameter("step", step);
			query.setParameter("fileType", fileType);

			return query.getSingleResult();
		}
		catch (NoResultException | NonUniqueResultException e)
		{
			return null;
		}
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileTypeStepDao#findStepByFileTypeAndLabel(org.guce.siat.common.model.FileType,
	 * java.lang.String)
	 */
	@Override
	public Step findStepByFileTypeAndLabel(final FileType fileType, final String labelStep)
	{
		Step returnedStep = null;
		try
		{
			final String hqlString = "FROM FileTypeStep fts WHERE fts.primaryKey.step.id=  :step  AND fts.primaryKey.fileType = :fileType";
			final TypedQuery<FileTypeStep> query = super.entityManager.createQuery(hqlString, FileTypeStep.class);
			query.setParameter("step", Long.valueOf(labelStep));
			query.setParameter("fileType", fileType);
			final List<FileTypeStep> fileTypeSteps = query.getResultList();

			if (CollectionUtils.isNotEmpty(fileTypeSteps))
			{
				returnedStep = fileTypeSteps.get(0).getStep();
			}
			else
			{
				if (CollectionUtils.isNotEmpty(fileType.getStepList()))
				{

					for (final Step step : fileType.getStepList())
					{
						if (labelStep.equals(step.getLabelEn()) || labelStep.equals(step.getLabelFr()))
						{
							returnedStep = step;
						}
					}
				}
			}
			return returnedStep;
		}
		catch (NoResultException | NonUniqueResultException e)
		{
			return null;
		}
	}

}
