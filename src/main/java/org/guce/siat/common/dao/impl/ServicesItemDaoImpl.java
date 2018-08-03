/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.dao.ServicesItemDao;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.ServicesItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CustomUserDetailsDaoImpl.
 */
@Repository("serviceItemDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ServicesItemDaoImpl extends AbstractJpaDaoImpl<ServicesItem> implements ServicesItemDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ServicesItemDaoImpl.class);

	/**
	 * Instantiates a new service items dao impl.
	 */
	public ServicesItemDaoImpl() {
		super();
		setClasse(ServicesItem.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServicesItemDao#findServicesItemByService(java.lang.String)
	 */
	@Override
	public List<ServicesItem> loadServicesItemByService(final Service service) {
		try {
			if (service != null) {
				final String qlString = "SELECT s FROM ServicesItem s WHERE s.service.id = :serviceId";
				final TypedQuery<ServicesItem> query = super.entityManager.createQuery(qlString, ServicesItem.class);
				query.setParameter("serviceId", service.getId());

				return query.getResultList();
			}

		} catch (final NoResultException e) {
			LOG.info(e.getMessage(), e);
		}

		return Collections.emptyList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServicesItemDao#findByNSH(java.lang.String)
	 */
	public List<ServicesItem> findByNSH(final String nsh) {
		if (StringUtils.isNotEmpty(nsh)) {
			try {
				final String qlString = "SELECT s FROM ServicesItem s WHERE s.nsh.goodsItemCode = :nsh";
				final TypedQuery<ServicesItem> query = super.entityManager.createQuery(qlString, ServicesItem.class);

				query.setParameter("nsh", nsh);

				return query.getResultList();
			} catch (final NoResultException e) {
				LOG.info(e.getMessage(), e);
			}
		}
		return Collections.emptyList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServicesItemDao#findByOrganism(org.guce.siat.common.model.Organism)
	 */
	public List<ServicesItem> findByOrganism(final Organism organism) {
		if (organism != null) {
			try {
				final String qlString = "SELECT s FROM ServicesItem s WHERE s.service.subDepartment.organism.id = :organismId";
				final TypedQuery<ServicesItem> query = super.entityManager.createQuery(qlString, ServicesItem.class);
				query.setParameter("organismId", organism.getId());

				return query.getResultList();
			} catch (final NoResultException e) {
				LOG.info(e.getMessage(), e);
			}
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServicesItemDao#findActiveItemByOrganisme(java.lang.Long)
	 */
	@Override
	public List<ServicesItem> findActiveItemByOrganism(final Organism organism) {
		try {
			if (organism != null) {
				final String qlString = "SELECT s FROM ServicesItem s WHERE s.service.subDepartment.organism.id = :organismId AND s.deleted = false";
				final TypedQuery<ServicesItem> query = super.entityManager.createQuery(qlString, ServicesItem.class);
				query.setParameter("organismId", organism.getId());

				return query.getResultList();
			}
		} catch (final NoResultException e) {
			LOG.info(e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServicesItemDao#fetchMaxCodeByNsh(java.lang.String)
	 */
	public Integer fetchMaxCodeByNsh(final String nsh) {
		try {
			final String qlString = "SELECT cast(s.code, integer) FROM ServicesItem  s WHERE cast(s.code, integer) = (SELECT MAX(CAST(s1.code, integer)) FROM ServicesItem s1 WHERE :nsh =s1.nsh.goodsItemCode)";
			final TypedQuery<Integer> query = super.entityManager.createQuery(qlString, Integer.class);
			query.setParameter("nsh", nsh);
			return query.getSingleResult();
		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ServicesItemDao#findByNshAndCode(java.lang.String, java.lang.String)
	 */
	@Override
	public ServicesItem findByNshAndCode(final String nsh, final String code) {
		try {
			final String qlString = "SELECT s FROM ServicesItem s WHERE s.nsh.goodsItemCode = :nsh AND s.code = :code";
			final TypedQuery<ServicesItem> query = super.entityManager.createQuery(qlString, ServicesItem.class);
			query.setParameter("nsh", nsh);
			query.setParameter("code", code);
			return query.getSingleResult();
		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	@Override
	public ServicesItem findNativeServiceItemByNSH(final String nsh) {

		try {
			final String qlString = "SELECT s FROM ServicesItem s WHERE s.nsh.goodsItemCode = :nsh AND s.type='N'";
			final TypedQuery<ServicesItem> query = super.entityManager.createQuery(qlString, ServicesItem.class);

			query.setParameter("nsh", nsh);

			return query.getSingleResult();
		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	@Override
	public List<ServicesItem> findActiveItemsByNshAndService(final String nsh, final Service service) {
		try {
			final String qlString = "SELECT s FROM ServicesItem s WHERE s.nsh.goodsItemCode = :nsh AND s.service =:service AND s.deleted = :deleted";
			final TypedQuery<ServicesItem> query = super.entityManager.createQuery(qlString, ServicesItem.class);
			query.setParameter("nsh", nsh);
			query.setParameter("service", service);
			query.setParameter("deleted", false);
			return query.getResultList();
		} catch (final NoResultException e) {
			LOG.info(e.getMessage(), e);
		}
		return Collections.emptyList();
	}
}
