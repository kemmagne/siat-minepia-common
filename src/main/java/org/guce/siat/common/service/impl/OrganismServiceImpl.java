package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.OrganismDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.OrganismService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class OrganismServiceImpl.
 */
@org.springframework.stereotype.Service("organismService")
@Transactional(readOnly = true)
public class OrganismServiceImpl extends AbstractServiceImpl<Organism> implements OrganismService
{

	/** The organism dao. */
	@Autowired
	private OrganismDao organismDao;

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/**
	 * Instantiates a new organism service impl.
	 */
	public OrganismServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Organism> getJpaDao()
	{
		return organismDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Organism> jpaDao)
	{
		this.organismDao = (OrganismDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.OrganismService#findOrganismByUser(org.guce.siat.common.model.User)
	 */
	@Override
	public Organism findOrganismByUser(final User user)
	{
		Organism organism = null;
		if (user != null)
		{
			if (user.getAdministration() instanceof Organism)
			{
				organism = (Organism) user.getAdministration();
			}
			else if (user.getAdministration() instanceof SubDepartment)
			{
				organism = ((SubDepartment) user.getAdministration()).getOrganism();
			}
			else if (user.getAdministration() instanceof Service)
			{
				organism = ((Service) user.getAdministration()).getSubDepartment().getOrganism();
			}
			else if (user.getAdministration() instanceof Entity)
			{
				organism = ((Entity) user.getAdministration()).getService().getSubDepartment().getOrganism();
			}
		}
		return organism;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.OrganismService#hasDirectorAffected(org.guce.siat.common.model.Organism)
	 */
	@Override
	public Boolean hasDirectorAffected(final Organism organism)
	{
		return organismDao.hasDirectorAffected(organism);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.OrganismService#hasSupervisorAffected(org.guce.siat.common.model.Organism)
	 */
	@Override
	public Boolean hasSupervisorAffected(final Organism organism)
	{
		return organismDao.hasSupervisorAffected(organism);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.OrganismService#findOrganismsByMinistry(org.guce.siat.common.model.Ministry)
	 */
	@Override
	public List<Organism> findOrganismsByMinistry(final Ministry ministry)
	{
		final List<Organism> organisms = organismDao.findOrganismsByMinistry(ministry);
		final List<Long> organismsIds = new ArrayList<Long>();

		for (final Organism organism : organisms)
		{
			organismsIds.add(organism.getId());

		}

		final List<User> organismOfficials = userDao.findUsersByAdministrationsIds(organismsIds.toArray(new Long[organismsIds
				.size()]));


		for (final Organism organism : organisms)
		{

			for (final User user : organismOfficials)
			{

				if (organism.getId().equals(user.getAdministration().getId()) && PositionType.DIRECTEUR.equals(user.getPosition()))
				{
					organism.setManager(user);
				}
				else if (organism.getId().equals(user.getAdministration().getId())
						&& PositionType.ADMINISTRATEUR.equals(user.getPosition()))
				{
					organism.setAdminOrganism(user);
				}
			}
		}
		return organisms;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#find(java.lang.Long)
	 */
	@Override
	public Organism find(final Long id)
	{
		final Organism organism = getJpaDao().find(id);

		if (organism != null)
		{
			final List<User> users = userDao.findUsersByAdministrationsIds(organism.getId());

			for (final User user : users)
			{

				if (PositionType.DIRECTEUR.equals(user.getPosition()))
				{
					organism.setManager(user);
				}
				else if (PositionType.ADMINISTRATEUR.equals(user.getPosition()))
				{
					organism.setAdminOrganism(user);
				}
			}
		}
		return organism;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.service.OrganismService#findOrganismNotHaveAdminByMinistry(org.guce.siat.common.model.Ministry
	 * )
	 */
	@Override
	public List<Organism> findOrganismNotHaveAdminByMinistry(final Ministry ministry)
	{
		try
		{
			return organismDao.findOrganismNotHaveAdminByMinistry(ministry);
		}
		catch (final Exception e)
		{
			throw new BusinessException(e, BusinessExceptionSeverity.SEVERITY_ERROR);
		}
	}
}
