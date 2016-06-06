package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.SubDepartmentDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.SubDepartmentService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class SubDepartmentServiceImpl.
 */
@org.springframework.stereotype.Service("subDepartmentService")
@Transactional(readOnly = true)
public class SubDepartmentServiceImpl extends AbstractServiceImpl<SubDepartment> implements SubDepartmentService
{

	/** The sub department dao. */
	@Autowired
	private SubDepartmentDao subDepartmentDao;

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/**
	 * Instantiates a new sub department service impl.
	 */
	public SubDepartmentServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<SubDepartment> getJpaDao()
	{

		return subDepartmentDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<SubDepartment> jpaDao)
	{
		this.subDepartmentDao = (SubDepartmentDao) jpaDao;

	}


	/**
	 * Find sub departments by organism.
	 *
	 * @param organism
	 *           the organism
	 * @return the list
	 */
	@Override
	public List<SubDepartment> findSubDepartmentsByOrganism(final Organism organism)
	{
		final List<SubDepartment> subDepartments = subDepartmentDao.findSubDepartmentsByOrganism(organism);
		final List<Long> assistantDirectorsIds = new ArrayList<Long>();

		for (final SubDepartment subDepartment : subDepartments)
		{
			assistantDirectorsIds.add(subDepartment.getId());
		}

		final List<User> users = userDao.findUsersByAdministrationsIds(assistantDirectorsIds.toArray(new Long[assistantDirectorsIds
				.size()]));

		for (final SubDepartment subDepartment : subDepartments)
		{
			for (final User user : users)
			{

				if (subDepartment.getId().equals(user.getAdministration().getId()))
				{
					subDepartment.setAssistantDirector(user);
					break;
				}
			}
		}
		return subDepartments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#find(java.lang.Long)
	 */
	@Override
	public SubDepartment find(final Long id)
	{
		final SubDepartment subDepartment = getJpaDao().find(id);

		if (subDepartment != null)
		{
			final List<User> users = userDao.findUsersByAdministrationsIds(id);

			if (CollectionUtils.isNotEmpty(users))
			{
				subDepartment.setAssistantDirector(users.get(Constants.ZERO));
			}
		}
		return subDepartment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.SubDepartmentService#findSubDepartmentByUser(org.guce.siat.common.model.User)
	 */
	@Override
	public SubDepartment findSubDepartmentByUser(final User user)
	{
		if (user.getAdministration() instanceof SubDepartment)
		{
			return (SubDepartment) user.getAdministration();
		}
		else if (user.getAdministration() instanceof Service)
		{
			return ((Service) user.getAdministration()).getSubDepartment();
		}
		else if (user.getAdministration() instanceof Entity)
		{
			return ((Entity) user.getAdministration()).getService().getSubDepartment();
		}
		else
		{
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.SubDepartmentService#findNonAffectedByOrganisme(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<SubDepartment> findNonAffectedByOrganism(final Organism organism)
	{
		try
		{
			return subDepartmentDao.findNonAffectedByOrganism(organism);
		}
		catch (final DAOException e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	}
}
