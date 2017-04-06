/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.MinistryDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MinistryDaoImpl.
 */
@Repository("ministryDao")
@Transactional(propagation = Propagation.REQUIRED)
public class MinistryDaoImpl extends AbstractJpaDaoImpl<Ministry> implements MinistryDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MinistryDaoImpl.class);

	/**
	 * Instantiates a new ministry dao impl.
	 */
	public MinistryDaoImpl() {
		super();
		setClasse(Ministry.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.MinistryDao#hasMinisterAffected(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public boolean hasMinisterAffected(final Ministry ministry) {
		try {
			final StringBuilder hqlQuery = new StringBuilder();
			hqlQuery
					.append("SELECT u FROM User u WHERE u.administration.id = :ministryId AND u.position = 'MINISTRE' AND  u.deleted=false");
			final TypedQuery<User> query = entityManager.createQuery(hqlQuery.toString(), User.class);
			query.setParameter("ministryId", ministry.getId());

			final List<User> affectedUser = query.getResultList();
			if (CollectionUtils.isEmpty(affectedUser)) {
				return false;
			}
			return true;
		} catch (final Exception e) {
			LOG.error(e.getMessage());
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.MinistryDao#hasSGAffected(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public boolean hasSGAffected(final Ministry ministry) {
		try {
			final StringBuilder hqlQuery = new StringBuilder();
			hqlQuery
					.append("SELECT u FROM User u WHERE u.administration.id = :ministryId AND u.position = 'SECRETAIRE_GENERAL' AND u.deleted=false");
			final TypedQuery<User> query = entityManager.createQuery(hqlQuery.toString(), User.class);
			query.setParameter("ministryId", ministry.getId());

			final List<User> affectedUser = query.getResultList();
			if (CollectionUtils.isEmpty(affectedUser)) {
				return false;
			}
			return true;
		} catch (final Exception e) {
			LOG.error(e.getMessage());
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.MinistryDao#findMinistryHasntAdmin()
	 */
	@Override
	public List<Ministry> findMinistryHasntAdmin() {
		try {
			final StringBuilder hqlQuery = new StringBuilder();
			hqlQuery
					.append("SELECT m FROM Ministry m WHERE m.id NOT IN (SELECT DISTINCT(u.administration.id) FROM User u WHERE u.position='ADMINISTRATEUR' AND u.deleted=false AND  u.administration.id IS NOT NULL)");
			final TypedQuery<Ministry> query = entityManager.createQuery(hqlQuery.toString(), Ministry.class);
			return query.getResultList();
		} catch (final Exception e) {
			LOG.error(e.getMessage());
			throw new DAOException(e);
		}
	}
}
