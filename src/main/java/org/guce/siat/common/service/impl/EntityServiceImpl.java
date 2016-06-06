/**
 *
 */
package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.EntityDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.EntityService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class EntityServiceImpl.
 */
@Service("entityService")
@Transactional(readOnly = true)
public class EntityServiceImpl extends AbstractServiceImpl<Entity> implements EntityService
{

	/** The entity dao. */
	@Autowired
	private EntityDao entityDao;

	/**
	 * Instantiates a new entity service impl.
	 */
	public EntityServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Entity> getJpaDao()
	{
		return entityDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Entity> jpaDao)
	{
		this.entityDao = (EntityDao) jpaDao;
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.EntityService#findActiveEntitiesByService(org.guce.siat.common.model.Service)
	 */
	@Override
	public List<Entity> findActiveEntitiesByService(final org.guce.siat.common.model.Service service)
	{
		return entityDao.findActiveEntitiesByService(service);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.EntityService#findNonAffectedEntityByServiceAndPosition(org.guce.siat.common.model
	 * .Service, org.guce.siat.common.utils.enums.PositionType)
	 */
	@Override
	public List<Entity> findNonAffectedEntityByServiceAndPosition(final org.guce.siat.common.model.Service service,
			final PositionType position)
	{
		try
		{
			return entityDao.findNonAffectedEntityByServiceAndPosition(service, position);
		}
		catch (final DAOException e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.EntityService#findEntityByUser(org.guce.siat.common.model.User)
	 */
	@Override
	public Entity findEntityByUser(final User user)
	{
		if (user.getAdministration() instanceof Entity)
		{
			return (Entity) user.getAdministration();
		}
		else
		{
			return null;
		}
	}

}
