package org.guce.siat.common.service.impl;

import java.util.Date;
import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ControllerHolidayDao;
import org.guce.siat.common.model.ControllerHoliday;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.User;
import org.guce.siat.common.service.ControllerHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ControllerHolidayServiceImpl.
 */
@Service("controllerHolidayService")
@Transactional(readOnly = true)
public class ControllerHolidayServiceImpl extends AbstractServiceImpl<ControllerHoliday> implements ControllerHolidayService
{

	/** The inspector holiday dao. */
	@Autowired
	private ControllerHolidayDao controllerHolidayDao;

	/**
	 * Instantiates a new inspector holiday service impl.
	 */
	public ControllerHolidayServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<ControllerHoliday> getJpaDao()
	{
		return controllerHolidayDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<ControllerHoliday> jpaDao)
	{
		this.controllerHolidayDao = (ControllerHolidayDao) jpaDao;
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ControllerHolidayService#findHolidayByControllerAndDates(java.lang.Long,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<ControllerHoliday> findHolidayByControllerAndDates(final User user, final Date beginDate, final Date endDate)
	{
		return controllerHolidayDao.findHolidayByControllerAndDates(user, beginDate, endDate);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.ControllerHolidayService#findHolidaysByListController(java.util.List)
	 */
	@Override
	public List<ControllerHoliday> findHolidaysByListController(final List<User> inspectorList)
	{
		return controllerHolidayDao.findHolidaysByListController(inspectorList);
	}

	@Override
	public ControllerHoliday findHolidayByControllerAndDay(final User controller, final Date date)
	{
		return controllerHolidayDao.findHolidayByControllerAndDay(controller, date);
	}

	@Override
	public List<ControllerHoliday> findByOrganism(Organism organism)
	{
		return controllerHolidayDao.findByOrganism(organism);
	}
        
        @Override
	public  ControllerHoliday findHolidayByController(User user)
	{
		return controllerHolidayDao.findHolidayByController(user);
	}

}
