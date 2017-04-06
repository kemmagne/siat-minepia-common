/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.AuthorityDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.utils.enums.AuthorityType;
import org.guce.siat.common.utils.enums.PositionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AuthorityDaoImpl.
 */
@Repository("authorityDao")
@Transactional(propagation = Propagation.REQUIRED)
public class AuthorityDaoImpl extends AbstractJpaDaoImpl<Authority> implements AuthorityDao {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AuthorityDaoImpl.class);

	/**
	 * Instantiates a new authority dao impl.
	 */
	public AuthorityDaoImpl() {
		super();
		setClasse(Authority.class);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AuthorityDao#findByAuthoritiesType()
	 */
	public List<Authority> findByAuthoritiesType() {
		final String hqlString = "SELECT a FROM Authority a WHERE a.authorityType = :cotation OR a.authorityType= :decision ";
		final TypedQuery<Authority> query = super.entityManager.createQuery(hqlString, Authority.class);
		query.setParameter("cotation", AuthorityType.COTATION);
		query.setParameter("decision", AuthorityType.DECISION);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AuthorityDao#findAuthorityByCode(java.lang.String)
	 */
	@Override
	public Authority findAuthorityByCode(final String authorityCode) {
		try {
			final String hqlString = "SELECT a FROM Authority a WHERE a.role = :authorityCode";
			final TypedQuery<Authority> query = super.entityManager.createQuery(hqlString, Authority.class);
			query.setParameter("authorityCode", authorityCode);

			return query.getSingleResult();
		} catch (final NoResultException | NonUniqueResultException e) {
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.AuthorityDao#findDistinctAutoritiesByPosition(org.guce.siat.common.utils.enums.PositionType
	 * )
	 */
	@Override
	public List<Authority> findDistinctAutoritiesByPosition(final PositionType post) {
		try {
			final String hqlString = "SELECT a.primaryKey.authority FROM PositionAuthority a WHERE a.primaryKey.positionType = :positionType";
			final TypedQuery<Authority> query = super.entityManager.createQuery(hqlString, Authority.class);
			query.setParameter("positionType", post);
			return query.getResultList();
		} catch (final Exception e) {
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}

	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.AuthorityDao#findAuthoritiesByAuthorityList(java.util.List)
	 */
	@Override
	public List<Authority> findAuthoritiesByAuthorityList(final List<String> authorityList) {
		try {
			final String hqlString = "SELECT a FROM Authority a WHERE a.role IN (:authorityList) ";
			final TypedQuery<Authority> query = super.entityManager.createQuery(hqlString, Authority.class);
			query.setParameter("authorityList", authorityList);
			return query.getResultList();
		} catch (final Exception e) {
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}

	}
}
