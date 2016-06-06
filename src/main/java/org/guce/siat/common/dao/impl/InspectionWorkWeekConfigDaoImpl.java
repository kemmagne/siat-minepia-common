package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.InspectionWorkWeekConfigDao;
import org.guce.siat.common.model.HourlyType;
import org.guce.siat.common.model.InspectionWorkWeekConfig;
import org.guce.siat.common.model.Organism;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class InspectionWorkWeekConfigDaoImpl.
 */
@Repository("inspectionWorkWeekConfigDao")
@Transactional(propagation = Propagation.REQUIRED)
public class InspectionWorkWeekConfigDaoImpl extends AbstractJpaDaoImpl<InspectionWorkWeekConfig>
		implements InspectionWorkWeekConfigDao
{

	/**
	 * Instantiates a new inspection work week config dao impl.
	 */
	public InspectionWorkWeekConfigDaoImpl()
	{
		super();
		setClasse(InspectionWorkWeekConfig.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.guce.siat.common.dao.InspectionWorkWeekConfigDao#findIWWConfigByOrganism(org.guce.siat.common.model.Organism)
	 */
	@Override
	public List<InspectionWorkWeekConfig> findIWWConfigByOrganism(final Organism organism)
	{
		if (organism != null)
		{
			final String queryString = "FROM InspectionWorkWeekConfig i WHERE i.organism.id = :organismId";
			final TypedQuery<InspectionWorkWeekConfig> query = super.entityManager.createQuery(queryString,
					InspectionWorkWeekConfig.class);
			query.setParameter("organismId", organism.getId());

			return query.getResultList();
		}
		return Collections.emptyList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.InspectionWorkWeekConfigDao#findByHorlyType(org.guce.siat.common.model.HourlyType)
	 */
	/**
	 * Find by hourly type.
	 *
	 * @param hourlyType
	 *           the hourly type
	 * @return the list
	 */
	@Override
	public List<InspectionWorkWeekConfig> findByHourlyType(final HourlyType hourlyType)
	{
		StringBuilder hqlBuilder = new StringBuilder();

		hqlBuilder.append("SELECT w FROM InspectionWorkWeekConfig w");
		hqlBuilder.append(" WHERE w.hourlyTypeId.id = :hourlyTypeId ");

		final TypedQuery<InspectionWorkWeekConfig> query = super.entityManager.createQuery(hqlBuilder.toString(),
				InspectionWorkWeekConfig.class);
		query.setParameter("hourlyTypeId", hourlyType.getId());
		return query.getResultList();
	}

}
