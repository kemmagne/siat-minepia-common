/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.EntityDao;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class EntityDaoImpl.
 */
@Repository("entityDao")
@Transactional(propagation = Propagation.REQUIRED)
public class EntityDaoImpl extends AbstractJpaDaoImpl<Entity>implements EntityDao
{

	/**
	 * Instantiates a new entity dao impl.
	 */
	public EntityDaoImpl()
	{
		super();
		setClasse(Entity.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.EntityDao#findActiveEntitiesByService(org.guce.siat.common.model.Service)
	 */
	@Override
	public List<Entity> findActiveEntitiesByService(final Service service)
	{
		final String queryString = "FROM Entity e WHERE e.service.id = :serviceId AND e.deleted = 0";
		final TypedQuery<Entity> query = super.entityManager.createQuery(queryString, Entity.class);
		query.setParameter("serviceId", service.getId());

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.EntityDao#findNonAffectedEntityByServiceAndPosition(org.guce.siat.common.model.Service,
	 * org.guce.siat.common.utils.enums.PositionType)
	 */
	@Override
	public List<Entity> findNonAffectedEntityByServiceAndPosition(final Service service, final PositionType position)
	{
		if (service != null)
		{
			final StringBuilder hqlQuery = new StringBuilder();
			hqlQuery.append("SELECT e FROM Entity e WHERE e.service.id = :serviceId ");
			if (PositionType.CHEF_BUREAU.equals(position))
			{
				hqlQuery.append(
						"AND e.id NOT IN (SELECT  DISTINCT(u.administration.id) FROM User u WHERE u.position='CHEF_BUREAU' AND u.administration.id IS NOT NULL )");
			}


			final TypedQuery<Entity> query = entityManager.createQuery(hqlQuery.toString(), Entity.class);
			query.setParameter("serviceId", service.getId());
			return query.getResultList();
		}
		return Collections.emptyList();
	}

}
