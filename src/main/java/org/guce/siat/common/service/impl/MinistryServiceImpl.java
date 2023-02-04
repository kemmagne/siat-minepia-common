package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.MinistryDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.MinistryService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class MinistryServiceImpl.
 */
@org.springframework.stereotype.Service("ministryService")
@Transactional(readOnly = true)
public class MinistryServiceImpl extends AbstractServiceImpl<Ministry> implements MinistryService
{

	/** The ministry dao. */
	@Autowired
	private MinistryDao ministryDao;

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/**
	 * Instantiates a new ministry service impl.
	 */
	public MinistryServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Ministry> getJpaDao()
	{
		return ministryDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Ministry> jpaDao)
	{
		this.ministryDao = (MinistryDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.MinistryService#findMinistryByUser(org.guce.siat.common.model.User)
	 */
	@Override
	public Ministry findMinistryByUser(final User user)
	{
		if (user.getAdministration() instanceof Ministry)
		{
			return (Ministry) user.getAdministration();
		}
		else if (user.getAdministration() instanceof Organism)
		{
			return ((Organism) user.getAdministration()).getMinistry();
		}
		else if (user.getAdministration() instanceof SubDepartment)
		{
			return ((SubDepartment) user.getAdministration()).getOrganism().getMinistry();
		}
		else if (user.getAdministration() instanceof Service)
		{
			return ((Service) user.getAdministration()).getSubDepartment().getOrganism().getMinistry();
		}
		else if (user.getAdministration() instanceof Entity)
		{
			return ((Entity) user.getAdministration()).getService().getSubDepartment().getOrganism().getMinistry();
		}
		else
		{
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.MinistryService#hasMinisterAffected(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public boolean hasMinisterAffected(final Ministry ministry)
	{
		try
		{
			return ministryDao.hasMinisterAffected(ministry);
		}
		catch (final DAOException e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_FATAL);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.MinistryService#hasSGAffected(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public boolean hasSGAffected(final Ministry ministry)
	{
		try
		{
			return ministryDao.hasSGAffected(ministry);
		}
		catch (final DAOException e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_FATAL);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#find(java.lang.Long)
	 */
	@Override
	public Ministry find(final Long id)
	{
		final Ministry ministry = getJpaDao().find(id);
		if (ministry != null)
		{
			final List<User> users = userDao.findUsersByAdministrationsIds(ministry.getId());

			for (final User user : users)
			{
				if (PositionType.ADMINISTRATEUR.equals(user.getPosition()))
				{
					ministry.setAdminMinistry(user);
				}
				else if (PositionType.MINISTRE.equals(user.getPosition()))
				{
					ministry.setMinister(user);
				}
				else if (PositionType.SECRETAIRE_GENERAL.equals(user.getPosition()))
				{
					ministry.setGeneralSecretary(user);
				}
			}
		}
		return ministry;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#findAll()
	 */
	@Override
	public List<Ministry> findAll()
	{
		final List<Long> ministryOfficialsIds = new ArrayList<Long>();
		final List<Ministry> ministries = ministryDao.findAll();

		for (final Ministry ministry : ministries)
		{
			ministryOfficialsIds.add(ministry.getId());
		}

		final List<User> users = userDao.findUsersByAdministrationsIds(ministryOfficialsIds.toArray(new Long[ministryOfficialsIds
				.size()]));

		for (final Ministry ministry : ministries)
		{
			for (final User user : users)
			{
				if (ministry.getId().equals(user.getAdministration().getId())
						&& PositionType.ADMINISTRATEUR.equals(user.getPosition()))
				{
					ministry.setAdminMinistry(user);
				}
				else if (ministry.getId().equals(user.getAdministration().getId())
						&& PositionType.MINISTRE.equals(user.getPosition()))
				{
					ministry.setMinister(user);
				}
				else if (ministry.getId().equals(user.getAdministration().getId())
						&& PositionType.SECRETAIRE_GENERAL.equals(user.getPosition()))
				{
					ministry.setGeneralSecretary(user);
				}
			}
		}

		return ministries;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#findActiveItems()
	 */
	@Override
	public List<Ministry> findActiveItems()
	{
		final List<Long> ministryOfficialsIds = new ArrayList<Long>();
		final List<Ministry> ministries = ministryDao.findActiveItems();

		for (final Ministry ministry : ministries)
		{
			ministryOfficialsIds.add(ministry.getId());
		}

		final List<User> users = userDao.findUsersByAdministrationsIds(ministryOfficialsIds.toArray(new Long[ministryOfficialsIds
				.size()]));

		for (final Ministry ministry : ministries)
		{
			for (final User user : users)
			{
				if (ministry.getId().equals(user.getAdministration().getId())
						&& PositionType.ADMINISTRATEUR.equals(user.getPosition()))
				{
					ministry.setAdminMinistry(user);
				}
				else if (ministry.getId().equals(user.getAdministration().getId())
						&& PositionType.MINISTRE.equals(user.getPosition()))
				{
					ministry.setMinister(user);
				}
				else if (ministry.getId().equals(user.getAdministration().getId())
						&& PositionType.SECRETAIRE_GENERAL.equals(user.getPosition()))
				{
					ministry.setGeneralSecretary(user);
				}
			}
		}

		return ministries;
	}

	/**
	 * Find ministry hasnt admin.
	 *
	 * @return the list
	 */
	@Override
	public List<Ministry> findMinistryHasntAdmin()
	{
		try
		{
			return ministryDao.findMinistryHasntAdmin();
		}
		catch (final Exception e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	}

}
