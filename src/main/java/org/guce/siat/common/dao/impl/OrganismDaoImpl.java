/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.OrganismDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class OrganismDaoImpl.
 */
@Repository("organismDao")
@Transactional(propagation = Propagation.REQUIRED)
public class OrganismDaoImpl extends AbstractJpaDaoImpl<Organism> implements OrganismDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganismDaoImpl.class);

	/**
	 * Instantiates a new organism dao impl.
	 */
	public OrganismDaoImpl()
	{
		super();
		setClasse(Organism.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.OrganismDao#hasDirectorAffected(org.guce.siat.common.model.Organism)
	 */
	@Override
	public Boolean hasDirectorAffected(final Organism organism)
	{
		boolean state = false;
		try
		{
			if (organism != null)
			{
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery
						.append("FROM User u WHERE u.administration.id = :organismId AND u.position = 'DIRECTEUR' AND u.deleted=false");
				final TypedQuery<User> query = entityManager.createQuery(hqlQuery.toString(), User.class);
				query.setParameter("organismId", organism.getId());
				final List<User> result = query.getResultList();

				if (CollectionUtils.isNotEmpty(result))
				{
					state = true;
				}
			}
			return state;
		}
		catch (final Exception e)
		{
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.OrganismDao#hasSupervisorAffected(org.guce.siat.common.model.Organism)
	 */
	@Override
	public Boolean hasSupervisorAffected(final Organism organism)
	{
		boolean state = false;
		try
		{
			if (organism != null)
			{
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery
						.append("FROM User u WHERE u.administration.id = :organismId AND u.position = 'SUPERVISEUR' AND u.deleted=false");
				final TypedQuery<User> query = entityManager.createQuery(hqlQuery.toString(), User.class);
				query.setParameter("organismId", organism.getId());
				final List<User> result = query.getResultList();

				if (CollectionUtils.isNotEmpty(result))
				{
					state = true;
				}
			}
			return state;
		}
		catch (final Exception e)
		{
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.OrganismDao#findOrganismsByMinistry(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public List<Organism> findOrganismsByMinistry(final Ministry ministry)
	{
		if (ministry != null)
		{
			final String hqlString = "FROM Organism o WHERE o.ministry.id = :ministryId ";
			final TypedQuery<Organism> query = super.entityManager.createQuery(hqlString, Organism.class);
			query.setParameter("ministryId", ministry.getId());
			return query.getResultList();
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.OrganismDao#findOrganismNotHaveAdminByMinistry(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public List<Organism> findOrganismNotHaveAdminByMinistry(final Ministry ministry)
	{
		try
		{
			if (ministry != null)
			{
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery
						.append("SELECT o FROM Organism o WHERE o.ministry.id= :ministryId AND o.id NOT IN (SELECT DISTINCT(u.administration.id) FROM User u WHERE u.position='ADMINISTRATEUR' AND u.deleted=false AND  u.administration.id IS NOT NULL)");
				final TypedQuery<Organism> query = entityManager.createQuery(hqlQuery.toString(), Organism.class);
				query.setParameter("ministryId", ministry.getId());

				return query.getResultList();
			}
		}
		catch (final Exception e)
		{
			LOG.error(e.getMessage());
			throw new DAOException(e);
		}
		return Collections.emptyList();
	}
}
