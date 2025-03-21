package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.ServiceDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ServiceDaoImpl.
 */
@Repository("serviceDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceDaoImpl extends AbstractJpaDaoImpl<Service> implements ServiceDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ServiceDaoImpl.class);

	/**
	 * Instantiates a new service dao impl.
	 */
	public ServiceDaoImpl() {
		super();
		setClasse(Service.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServiceDao#findServicesByOrganism(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<Service> findServicesByOrganism(final Organism organism) {
		if (organism != null) {
			final String hqlString = "FROM Service s WHERE s.subDepartment.organism.id = :organismId ";
			final TypedQuery<Service> query = super.entityManager.createQuery(hqlString, Service.class);
			query.setParameter("organismId", organism.getId());
			return query.getResultList();
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServiceDao#findNonAffectedBySubDepartment(org.guce.siat.common.model.SubDepartment)
	 */
	@Override
	public List<Service> findNonAffectedServicesBySubDepartment(final SubDepartment subDepartment) {
		try {
			if (subDepartment != null) {
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery
						.append("SELECT s FROM Service s WHERE s.subDepartment.id = :subDepartmentId AND s.id NOT IN (SELECT DISTINCT(u.administration.id) FROM User u WHERE u.position = 'CHEF_SERVICE' AND u.administration.id IS NOT NULL)");
				final TypedQuery<Service> query = entityManager.createQuery(hqlQuery.toString(), Service.class);
				query.setParameter("subDepartmentId", subDepartment.getId());
				return query.getResultList();
			}
			return Collections.emptyList();
		} catch (final Exception e) {
			LOG.info(e.getMessage());
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServiceDao#findServiceByFileType(org.guce.siat.common.model.FileType)
	 */
	@Override
	public List<Service> findServiceByFileType(final FileType fileType) {
		if (fileType != null) {
			final TypedQuery<Service> query = super.entityManager.createQuery(
					"SELECT s.service FROM FileTypeService s WHERE s.fileType.id = :fileTypeId ", Service.class);
			query.setParameter("fileTypeId", fileType.getId());
			return query.getResultList();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServiceDao#findServiceByFileTypeAndMinistry(org.guce.siat.common.model.FileType,
	 * org.guce.siat.common.model.Ministry)
	 */
	@Override
	public Service findServiceByFileTypeAndMinistry(final FileType fileType, final String ministryCode) {
		//TODO add Column code in table ministry  (labelFr may be changed)
		if (fileType != null && ministryCode != null) {
			final TypedQuery<Service> query = super.entityManager
					.createQuery(
							"SELECT s.service FROM FileTypeService s WHERE s.fileType.id = :fileTypeId AND s.service.subDepartment.organism.ministry.labelFr = :ministryCode",
							Service.class);
			query.setParameter("fileTypeId", fileType.getId());
			query.setParameter("ministryCode", ministryCode);
			return query.getSingleResult();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServiceDao#findServicesByFileTypeAndMinistry(org.guce.siat.common.model.FileType,
	 * org.guce.siat.common.model.Ministry)
	 */
	@Override
	public List<Service> findServicesByFileTypeAndMinistry(final FileType fileType, final String ministryCode) {
		//TODO add Column code in table ministry  (labelFr may be changed)
		if (fileType != null && ministryCode != null) {
			final TypedQuery<Service> query = super.entityManager
					.createQuery(
							"SELECT s.service FROM FileTypeService s WHERE s.fileType.id = :fileTypeId AND s.service.subDepartment.organism.ministry.labelFr = :ministryCode",
							Service.class);
			query.setParameter("fileTypeId", fileType.getId());
			query.setParameter("ministryCode", ministryCode);
			return query.getResultList();
		}
		return null;
	}
}
