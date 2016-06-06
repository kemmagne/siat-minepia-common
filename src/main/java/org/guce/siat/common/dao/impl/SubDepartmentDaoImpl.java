package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.SubDepartmentDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.SubDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class SubDepartmentDaoImpl.
 */
@Repository("subDepartmentDao")
@Transactional(propagation = Propagation.REQUIRED)
public class SubDepartmentDaoImpl extends AbstractJpaDaoImpl<SubDepartment> implements SubDepartmentDao
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SubDepartmentDaoImpl.class);


	/**
	 * Instantiates a new sub department dao impl.
	 */
	public SubDepartmentDaoImpl()
	{
		super();
		setClasse(SubDepartment.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.SubDepartmentDao#findSubDepartmentsByOrganism(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<SubDepartment> findSubDepartmentsByOrganism(final Organism organism)
	{
		if (organism != null)
		{
			final String hqlString = "SELECT s FROM SubDepartment s WHERE s.organism.id = :organismId  ";
			final TypedQuery<SubDepartment> query = super.entityManager.createQuery(hqlString, SubDepartment.class);
			query.setParameter("organismId", organism.getId());
			return query.getResultList();
		}
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.SubDepartmentDao#findNonAffectedByOrganisme(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<SubDepartment> findNonAffectedByOrganism(final Organism organism)
	{
		try
		{
			if (organism != null)
			{
				final StringBuilder hqlQuery = new StringBuilder();
				hqlQuery
						.append("SELECT s FROM SubDepartment s WHERE s.organism.id = :organismId AND s.id NOT IN (SELECT DISTINCT(u.administration.id) FROM User u WHERE u.position = 'SOUS_DIRECTEUR' AND u.administration.id IS NOT NULL) ");
				final TypedQuery<SubDepartment> query = entityManager.createQuery(hqlQuery.toString(), SubDepartment.class);
				query.setParameter("organismId", organism.getId());
				return query.getResultList();
			}
		}
		catch (final Exception e)
		{
			LOG.info(e.getMessage(), e);
			throw new DAOException(e);
		}
		return Collections.emptyList();
	}
}
