package org.guce.siat.common.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.InspectionWorkDayConfigDao;
import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.InspectionWorkDayConfig;
import org.guce.siat.common.model.Organism;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class InspectionWorkDayConfigDaoImpl.
 */
@Repository("inspectionWorkDayConfigDao")
@Transactional(propagation = Propagation.REQUIRED)
public class InspectionWorkDayConfigDaoImpl extends AbstractJpaDaoImpl<InspectionWorkDayConfig> implements
		InspectionWorkDayConfigDao
{

	/** The Constant HOURLY_TYPE_ID_LABEL. */
	private static final String HOURLY_TYPE_ID_LABEL = "hourlyTypeId";

	/**
	 * Instantiates a new inspection work day config dao impl.
	 */
	public InspectionWorkDayConfigDaoImpl()
	{
		super();
		setClasse(InspectionWorkDayConfig.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.InspectionWorkDayConfigDao#findInspectionWorkDayConfigByHourlyType(org.guce.siat.common
	 * .model.HourlyType)
	 */
	@Override
	public List<InspectionWorkDayConfig> findInspectionWorkDayConfigByHourlyType(final HourlyType hourlyType)
	{

		String hqlQuery = null;
		TypedQuery<InspectionWorkDayConfig> query = null;

		hqlQuery = "SELECT insp FROM InspectionWorkDayConfig insp WHERE insp.hourlyType.id = :hourlyTypeId";
		query = super.entityManager.createQuery(hqlQuery, InspectionWorkDayConfig.class);
		query.setParameter(HOURLY_TYPE_ID_LABEL, hourlyType.getId());
		return query.getResultList();
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.InspectionWorkDayConfigDao#findInspectionWorkDayConfigByHourlyTypeAndOrganism(org.guce
	 * .siat.common.model.HourlyType, org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<InspectionWorkDayConfig> findInspectionWorkDayConfigByHourlyTypeAndOrganism(final HourlyType hourlyType,
			final Organism organism)
	{
		String hqlQuery = null;
		TypedQuery<InspectionWorkDayConfig> query = null;

		hqlQuery = "SELECT insp FROM InspectionWorkDayConfig insp WHERE insp.hourlyType.id = :hourlyTypeId AND insp.organism.id = :organismId";
		query = super.entityManager.createQuery(hqlQuery, InspectionWorkDayConfig.class);
		query.setParameter(HOURLY_TYPE_ID_LABEL, hourlyType.getId());
		query.setParameter("organismId", organism.getId());
		return query.getResultList();
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.InspectionWorkDayConfigDao#findByHorlyType(org.guce.siat.common.model.HourlyType)
	 */
	/**
	 * Find by hourly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	@Override
	public List<InspectionWorkDayConfig> findByHourlyType(final HourlyType hourlyType)
	{
		final String hqlString = "SELECT w FROM InspectionWorkDayConfig w WHERE w.hourlyType.id = :hourlyTypeId ";
		final TypedQuery<InspectionWorkDayConfig> query = super.entityManager.createQuery(hqlString, InspectionWorkDayConfig.class);
		query.setParameter(HOURLY_TYPE_ID_LABEL, hourlyType.getId());
		return query.getResultList();
	}


}
