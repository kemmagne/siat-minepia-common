package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.AdministrationDao;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.service.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AdministrationServiceImpl.
 */
@org.springframework.stereotype.Service("administrationService")
@Transactional(readOnly = true)
public class AdministrationServiceImpl extends AbstractServiceImpl<Administration> implements AdministrationService
{


	/** The administration dao. */
	@Autowired
	private AdministrationDao administrationDao;



	/**
	 * Instantiates a new administration service impl.
	 */
	public AdministrationServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Administration> getJpaDao()
	{
		return administrationDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Administration> jpaDao)
	{
		this.administrationDao = (AdministrationDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.AdministrationService#getSubAdministrationByAdministration(org.guce.siat.common.model
	 * .Administration)
	 */
	@Override
	public List<Administration> getSubAdministrationByAdministration(final Administration administration)
	{
		final List<Administration> subAdministrations = new ArrayList<Administration>();

		if (administration instanceof Ministry)
		{
			subAdministrations.addAll(((Ministry) administration).getOrganismsList());
		}
		else if (administration instanceof Organism)
		{
			subAdministrations.addAll(((Organism) administration).getSubDepartmentsList());
		}
		else if (administration instanceof SubDepartment)
		{
			subAdministrations.addAll(((SubDepartment) administration).getServicesList());
		}
		else if (administration instanceof Service)
		{
			subAdministrations.addAll(((Service) administration).getEntityList());
		}
		return subAdministrations;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.AdministrationService#getRecursiveSubAdministrationByAdministration(org.guce.siat
	 * .common.model.Administration)
	 */
	@Override
	public List<Administration> getRecursiveSubAdministrationByAdministration(final Administration administration)
	{
		final List<Administration> rSubAdministrations = new ArrayList<Administration>();

		if (administration instanceof Ministry)
		{
			rSubAdministrations.add(administration);
			rSubAdministrations.addAll(((Ministry) administration).getOrganismsList());

			for (final Organism organism : ((Ministry) administration).getOrganismsList())
			{
				rSubAdministrations.addAll(organism.getSubDepartmentsList());

				for (final SubDepartment subDep : organism.getSubDepartmentsList())
				{
					rSubAdministrations.addAll(subDep.getServicesList());

					for (final Service service : subDep.getServicesList())
					{
						rSubAdministrations.addAll(service.getEntityList());
					}
				}
			}
		}
		else if (administration instanceof Organism)
		{
			rSubAdministrations.add(administration);
			rSubAdministrations.addAll(((Organism) administration).getSubDepartmentsList());

			for (final SubDepartment subDep : ((Organism) administration).getSubDepartmentsList())
			{
				rSubAdministrations.addAll(subDep.getServicesList());

				for (final Service service : subDep.getServicesList())
				{
					rSubAdministrations.addAll(service.getEntityList());
				}
			}
		}
		else if (administration instanceof SubDepartment)
		{
			rSubAdministrations.add(administration);
			rSubAdministrations.addAll(((SubDepartment) administration).getServicesList());

			for (final Service service : ((SubDepartment) administration).getServicesList())
			{
				rSubAdministrations.addAll(service.getEntityList());
			}
		}
		else if (administration instanceof Service)
		{
			rSubAdministrations.add(administration);
			rSubAdministrations.addAll(((Service) administration).getEntityList());
		}
		else if (administration instanceof Entity)
		{
			rSubAdministrations.add(administration);
		}
		return rSubAdministrations;
	}


}
