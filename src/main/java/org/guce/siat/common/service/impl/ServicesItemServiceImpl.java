package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ServicesItemDao;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.ServicesItem;
import org.guce.siat.common.service.ServicesItemService;
import org.guce.siat.common.utils.enums.ServiceItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ServiceServiceImpl.
 */
@org.springframework.stereotype.Service("servicesItemService")
@Transactional(readOnly = true)
public class ServicesItemServiceImpl extends AbstractServiceImpl<ServicesItem> implements ServicesItemService
{

	/** The service Item dao. */
	@Autowired
	private ServicesItemDao servicesItemDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<ServicesItem> getJpaDao()
	{
		return servicesItemDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<ServicesItem> jpaDao)
	{
		this.servicesItemDao = (ServicesItemDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ServicesItemService#findServicesItemByService(java.lang.Long)
	 */
	@Override
	public List<ServicesItem> findServicesItemByService(final Service service)
	{
		return servicesItemDao.loadServicesItemByService(service);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.ServicesItemService#findServicesItemByNsh(java.lang.String)
	 */
	@Override
	public List<ServicesItem> findServicesItemByNsh(final String nsh)
	{
		return servicesItemDao.findByNSH(nsh);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ServicesItemService#findServicesItemByOrganisme(java.lang.Long)
	 */
	@Override
	public List<ServicesItem> findServicesItemByOrganism(final Organism organism)
	{
		return servicesItemDao.findByOrganism(organism);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.ServicesItemService#findActiveServicesItemByOrganisme(org.guce.siat.common.model.
	 * Organism)
	 */
	@Override
	public List<ServicesItem> findActiveServicesItemByOrganism(final Organism organism)
	{
		return servicesItemDao.findActiveItemByOrganism(organism);
	}


	@Override
	public List<ServicesItem> findAllActiveServicesItemByOrganism(final Organism organism)
	{
		final List<ServicesItem> servicesItemList = servicesItemDao.findActiveItemByOrganism(organism);
		final List<ServicesItem> filtredServicesItemList = new ArrayList<ServicesItem>();
		final Set<String> subFamilyNSH = new HashSet<String>();

		for (final ServicesItem servicesItem : servicesItemList)
		{
			if (ServiceItemType.SUBFAMILY.getCode().equals(servicesItem.getType().toString()))
			{
				subFamilyNSH.add(servicesItem.getNsh().getGoodsItemCode());
			}
		}

		for (final ServicesItem servicesItem : servicesItemList)
		{
			if (ServiceItemType.SUBFAMILY.getCode().equals(servicesItem.getType().toString()))
			{
				filtredServicesItemList.add(servicesItem);
			}
			else if (!subFamilyNSH.contains(servicesItem.getNsh().getGoodsItemCode()))
			{
				filtredServicesItemList.add(servicesItem);
			}
		}

		return filtredServicesItemList;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.ServicesItemService#fetchMaxCodeByNsh(java.lang.String)
	 */
	@Override
	public Integer fetchMaxCodeByNsh(final String nsh)
	{
		return servicesItemDao.fetchMaxCodeByNsh(nsh);
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ServicesItemService#findServicesItemByNshAndCode(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ServicesItem findServicesItemByNshAndCode(final String nsh, final String code)
	{

		return servicesItemDao.findByNshAndCode(nsh, code);
	}

}
