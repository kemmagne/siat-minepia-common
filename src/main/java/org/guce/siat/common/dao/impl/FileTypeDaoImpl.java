package org.guce.siat.common.dao.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.ArrayUtils;
import org.guce.siat.common.dao.FileTypeDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeService;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileDaoImpl.
 */
@Repository("fileTypeDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileTypeDaoImpl extends AbstractJpaDaoImpl<FileType> implements FileTypeDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FileTypeDaoImpl.class);

	/**
	 * Instantiates a new file type dao impl.
	 */
	public FileTypeDaoImpl() {
		super();
		setClasse(FileType.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FileTypeDao#findFileTypeByMinestry(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public List<FileType> findFileTypeByMinistry(final Ministry ministry) {
		try {
			if (ministry != null) {
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery.append("SELECT f.primaryKey.fileType FROM MinistryFileType f WHERE f.primaryKey.ministry.id = :minestiryId");

				final TypedQuery<FileType> query = entityManager.createQuery(hqlQuery.toString(), FileType.class);
				query.setParameter("minestiryId", ministry.getId());
				return query.getResultList();
			}
		} catch (final Exception e) {
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileTypeDao#findByCode(org.guce.siat.common.utils.enums.FileTypeCode)
	 */
	@Override
	public FileType findByCode(final FileTypeCode code) {
		try {
			final String hqlQuery = "SELECT ft FROM FileType ft WHERE ft.code = :code ";
			TypedQuery<FileType> query = entityManager.createQuery(hqlQuery.toString(), FileType.class);
			query = super.entityManager.createQuery(hqlQuery, FileType.class);
			query.setParameter("code", code);
			return query.getSingleResult();
		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}

	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileTypeDao#findAuthoritiesByFileType(org.guce.siat.common.model.FileType)
	 */
	@Override
	public List<Authority> findAuthoritiesByFileType(final FileType filetype) {
		try {
			if (filetype != null) {
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery.append("SELECT a FROM FileType f LEFT OUTER JOIN f.roleList a WHERE f=:filetype");
				final TypedQuery<Authority> query = entityManager.createQuery(hqlQuery.toString(), Authority.class);
				query.setParameter("filetype", filetype);
				return query.getResultList();
			}
		} catch (final Exception e) {
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileTypeDao#findFileTypesByCodes(java.lang.String[])
	 */
	@Override
	public List<FileType> findFileTypesByCodes(final FileTypeCode... fileTypeCodes) {
		final StringBuilder hqlQuery = new StringBuilder();
		try {
			hqlQuery.append("SELECT ft FROM FileType ft ");
			if (!ArrayUtils.isEmpty(fileTypeCodes)) {
				hqlQuery.append("WHERE ft.code IN ( :codes )");
			}
			TypedQuery<FileType> query = entityManager.createQuery(hqlQuery.toString(), FileType.class);
			query = super.entityManager.createQuery(hqlQuery.toString(), FileType.class);
			if (!ArrayUtils.isEmpty(fileTypeCodes)) {
				query.setParameter("codes", Arrays.asList(fileTypeCodes));
			}
			return query.getResultList();
		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}

	}

	@Override
	public void update(final FileType selected, final List<FileTypeStep> targetFileTypeStep) {
		try {
			entityManager.merge(selected);
			for (final FileTypeStep fileTypeStep : selected.getFileTypeStepList()) {
				entityManager.remove(entityManager.merge(fileTypeStep));
			}
			for (final FileTypeStep fileTypeStep : targetFileTypeStep) {
				fileTypeStep.setIsApDecision(false);
				entityManager.persist(fileTypeStep);
				entityManager.flush();
			}
		} catch (final Exception e) {
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	@Override
	public void update(FileType selected, List<FileTypeStep> targetFileTypeStep, List<FileTypeService> targetFileTypeServices) {
		try {
			entityManager.merge(selected);
			if (targetFileTypeStep != null) {
				for (final FileTypeStep fileTypeStep : selected.getFileTypeStepList()) {
					entityManager.remove(entityManager.merge(fileTypeStep));
				}
				for (final FileTypeStep fileTypeStep : targetFileTypeStep) {
					fileTypeStep.setIsApDecision(false);
					entityManager.persist(fileTypeStep);
					entityManager.flush();
				}
			}
			if (targetFileTypeServices != null) {
				List<FileTypeService> fileTypeServiceList = selected.getFileTypeServiceList();
				for (final FileTypeService fileTypeService : fileTypeServiceList) {
					entityManager.remove(entityManager.merge(fileTypeService));
				}
				for (final FileTypeService fileTypeService : targetFileTypeServices) {
					entityManager.persist(fileTypeService);
					entityManager.flush();
				}
			}
		} catch (final Exception e) {
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<FileTypeService> findFileTypeServiceByFileType(FileType fileType) {
		final StringBuilder hqlQuery = new StringBuilder();
		try {
			hqlQuery.append("SELECT ft FROM FileTypeService ft WHERE ft.fileType = :fileType");
			TypedQuery<FileTypeService> query = entityManager.createQuery(hqlQuery.toString(), FileTypeService.class);
			return query.getResultList();
		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}
	}
}
