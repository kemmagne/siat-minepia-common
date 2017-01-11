/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.SiatUtils;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class FileDaoImpl.
 */
@Repository("fileDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileDaoImpl extends AbstractJpaDaoImpl<File> implements FileDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FileDaoImpl.class);

	/** The Constant NO_RESULT_EXCEPTION. */
	private static final String NO_RESULT_EXCEPTION = "Quick search: No records found with reference: ";


	/**
	 * Instantiates a new file dao impl.
	 */
	public FileDaoImpl()
	{
		super();
		setClasse(File.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FileDao#findFileFieldValueByFieldCode(org.guce.siat.core.ct.model.File,
	 * java.lang.String)
	 */
	@Override
	public FileFieldValue findFileFieldValueByFieldCode(final File file, final String fieldCode)
	{
		if (StringUtils.isNotEmpty(fieldCode))
		{
			for (final FileFieldValue fv : file.getFileFieldValueList())
			{
				if (StringUtils.equals(fieldCode, fv.getFileField().getCode()))
				{
					return fv;
				}
			}
		}
		return null;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#quickSearch(java.lang.String, org.guce.siat.common.model.Administration,
	 * java.util.List)
	 */
	@Override
	public File quickSearch(final String documentNumberFilter, final Administration administration,
			final List<FileTypeCode> fileTypeCodes)
	{
		try
		{
			final StringBuilder hqlQuery = new StringBuilder();
			final List<Bureau> bureausList = SiatUtils.findCombinedBureausByAdministrationList(new ArrayList<Administration>(
					Collections.singletonList(administration)));

			final Map<String, Object> params = new HashMap<String, Object>();

			params.put("documentNumberFilter", documentNumberFilter);

			params.put("fileTypeCodes", fileTypeCodes);

			hqlQuery.append("FROM File fi ");
			hqlQuery.append("WHERE (fi.referenceSiat = :documentNumberFilter ");
			hqlQuery.append("OR fi.numeroDossier  = :documentNumberFilter) ");
			hqlQuery.append("AND fi.fileType.code IN (:fileTypeCodes) ");

			if (CollectionUtils.isNotEmpty(bureausList))
			{
				hqlQuery.append(" AND fi.bureau IN (:bureausList)");
				params.put("bureausList", bureausList);
			}
			final TypedQuery<File> query = super.entityManager.createQuery(hqlQuery.toString(), File.class);

			for (final Entry<String, Object> entry : params.entrySet())
			{
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(NO_RESULT_EXCEPTION.concat(documentNumberFilter));
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FileDao#findByRefSiat(java.lang.String)
	 */
	@Override
	public File findByRefSiat(final String refSiat)
	{
		try
		{
			final String hqlString = "FROM File f WHERE f.referenceSiat=:refSiat";
			final TypedQuery<File> query = super.entityManager.createQuery(hqlString, File.class);
			query.setParameter("refSiat", refSiat);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#findByRefGuce(java.lang.String)
	 */
	@Override
	public File findByNumDossierGuce(final String numDossierGuce)
	{
		try
		{
			final String hqlString = "FROM File f WHERE f.numeroDossier = :numDossierGuce";
			final TypedQuery<File> query = super.entityManager.createQuery(hqlString, File.class);
			query.setParameter("numDossierGuce", numDossierGuce);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.FileDao#findMatchingBetweenManifestAndPriorNotice(org.guce.siat.common.model.Company,
	 * org.guce.siat.common.model.FileFieldValue, org.guce.siat.common.model.FileFieldValue)
	 */
	@Override
	public File findMatchingBetweenManifestAndPriorNotice(final Company client, final List<FileTypeCode> fileTypeCodeList,
			final FileFieldValue fileFieldValue1, final FileFieldValue fileFieldValue2, final String fileFieldCode1,
			final String fileFieldCode2)
	{
		try
		{
			if (fileFieldValue1 != null && fileFieldValue2 != null)
			{
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery.append("SELECT ffv.primaryKey.file FROM FileFieldValue ffv ");
				hqlQuery.append("WHERE EXISTS ");
				hqlQuery.append("(SELECT ffv1.primaryKey.file FROM FileFieldValue ffv1 ");
				hqlQuery.append("WHERE ffv1.primaryKey.file.fileType.code IN (:fileTypeCodeList) ");

				//BUG ID : 320207
				//				if (client != null)
				//				{
				//					hqlQuery.append("AND ffv1.primaryKey.file.client.id=:clientId  ");
				//				}
				hqlQuery.append("AND ffv1.primaryKey.file.id=ffv.primaryKey.file.id ");
				hqlQuery.append("AND ffv1.value=:value1 ");
				hqlQuery.append("AND ffv1.primaryKey.fileField.code=:ffc1) ");
				hqlQuery.append("AND EXISTS ");
				hqlQuery.append("(SELECT ffv2.primaryKey.file FROM FileFieldValue ffv2 ");
				hqlQuery.append("WHERE ffv2.primaryKey.file.fileType.code IN (:fileTypeCodeList)  ");

				//BUG ID : 320207
				//				if (client != null)
				//				{
				//					hqlQuery.append("AND ffv2.primaryKey.file.client.id=:clientId  ");
				//				}
				hqlQuery.append("AND ffv2.primaryKey.file.id=ffv.primaryKey.file.id ");
				hqlQuery.append("AND ffv2.value=:value2 ");
				hqlQuery.append("AND ffv2.primaryKey.fileField.code=:ffc2) ");


				final TypedQuery<File> query = super.entityManager.createQuery(hqlQuery.toString(), File.class);
				//BUG ID : 320207
				//				if (client != null)
				//				{
				//					query.setParameter("clientId", client.getId());
				//				}
				query.setParameter("fileTypeCodeList", fileTypeCodeList);
				query.setParameter("value1", fileFieldValue1.getValue());
				query.setParameter("value2", fileFieldValue2.getValue());
				query.setParameter("ffc1", fileFieldCode1);
				query.setParameter("ffc2", fileFieldCode2);

				return query.getSingleResult();
			}

			return null;
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#findDraftFilesByUsr(org.guce.siat.common.model.User)
	 */
	@Override
	public List<File> findDraftFilesByUsr(final User user)
	{
		final TypedQuery<File> query = entityManager.createQuery(
				"SELECT DISTINCT i.fileItem.file FROM ItemFlow i WHERE i.sent=false AND i.sender= :user", File.class);
		query.setParameter("user", user);
		return query.getResultList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#findFilesByFileTypeCode(org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	@Override
	public List<File> findFilesByFileTypeCode(final FileTypeCode fileTypeCode)
	{
		final TypedQuery<File> query = entityManager.createQuery("SELECT f FROM File f WHERE f.fileType.code= :fileTypeCode",
				File.class);
		query.setParameter("fileTypeCode", fileTypeCode);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#findCountByFileType(org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	@Override
	public Long findCountByFileType(final FileTypeCode fileTypeCode)
	{
		final List<StepCode> excludedSteps = Arrays.asList(StepCode.ST_CC_46, StepCode.ST_CC_47, StepCode.ST_CC_48,
				StepCode.ST_CC_49, StepCode.ST_CC_50, StepCode.ST_CC_51, StepCode.ST_CC_52, StepCode.ST_CC_53);
		final List<StepCode> etudeSteps = Arrays.asList(StepCode.ST_CC_48, StepCode.ST_CC_49, StepCode.ST_CC_50, StepCode.ST_CC_51,
				StepCode.ST_CC_52, StepCode.ST_CC_53);

		final TypedQuery<Long> query = entityManager
				.createQuery(
						"SELECT COUNT(f) FROM File f JOIN f.fileItemsList fil WHERE (fil.step.stepCode NOT IN (:excludedSteps) OR (fil.step.stepCode IN (:etudeSteps) AND fil.draft= true)) AND f.fileType.code= :fileTypeCode",
						Long.class);
		query.setParameter("excludedSteps", excludedSteps);
		query.setParameter("etudeSteps", etudeSteps);
		query.setParameter("fileTypeCode", fileTypeCode);
		return query.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#findByNumContribuable(java.lang.String)
	 */
	@Override
	public File findByNumContribuable(final String numContribuable)
	{
		try
		{
			final String hqlString = "FROM File f WHERE f.client.numContribuable = :numContribuable";
			final TypedQuery<File> query = super.entityManager.createQuery(hqlString, File.class);
			query.setParameter("numContribuable", numContribuable);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#findMatchingBetweenFimexAndPriorNotice(java.lang.String, java.util.List)
	 */
	@Override
	public File findMatchingBetweenFimexAndPriorNotice(final String numContribuable, final List<FileTypeCode> fileTypeCodeList)
	{
		try
		{
			final StringBuilder hqlQuery = new StringBuilder();
			hqlQuery.append("FROM File f WHERE f.client.numContribuable = :numContribuable");
			hqlQuery.append(" AND f.fileType.code IN (:fileTypeCodeList) ");

			final TypedQuery<File> query = super.entityManager.createQuery(hqlQuery.toString(), File.class);
			query.setParameter("numContribuable", numContribuable);
			query.setParameter("fileTypeCodeList", fileTypeCodeList);
			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileDao#updateSpecificColumn(java.util.Map)
	 */
	@Override
	public void updateSpecificColumn(final Map<String, ?> paramsMap, final File file)
	{
		if (MapUtils.isNotEmpty(paramsMap))
		{
			final StringBuilder sqlBuilder = new StringBuilder("UPDATE FILES F SET ");
			for (final Entry<String, ?> entry : paramsMap.entrySet())
			{
				sqlBuilder.append(entry.getKey()).append("=:").append(entry.getKey());
			}
			sqlBuilder.append(" WHERE F.ID=").append(file.getId());

			final Query query = entityManager.createNativeQuery(sqlBuilder.toString());
			for (final Entry<String, ?> entry : paramsMap.entrySet())
			{
				query.setParameter(entry.getKey(), entry.getValue());
			}
			query.executeUpdate();
		}
	}
}
