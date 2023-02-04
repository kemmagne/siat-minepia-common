package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.map.HashedMap;
import org.guce.siat.common.dao.AnalyseTypeDao;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.AnalyseType;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AnalyseTypeDaoImpl.
 */
@Repository("analyseTypeDao")
@Transactional(propagation = Propagation.REQUIRED)
public class AnalyseTypeDaoImpl extends AbstractJpaDaoImpl<AnalyseType> implements AnalyseTypeDao
{

	/**
	 * Instantiates a new analyse type dao impl.
	 */
	public AnalyseTypeDaoImpl()
	{
		super();
		setClasse(AnalyseType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.AnalyseTypeDao#findByAdministration(org.guce.siat.common.model.Administration)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AnalyseType> findByAdministration(final Administration administration)
	{
		if (administration != null)
		{
			final StringBuilder hqlQuery = new StringBuilder();

			final Map<String, Object> params = new HashedMap();

			hqlQuery.append("SELECT a FROM AnalyseType a WHERE ");

			if (administration instanceof Ministry)
			{
				hqlQuery.append("a.organism.ministry.id = :ministryId ");
				params.put("ministryId", administration.getId());
			}
			else if (administration instanceof Organism)
			{
				hqlQuery.append("a.organism.id = :organismId ");
				params.put("organismId", administration.getId());
			}
			else if (administration instanceof SubDepartment)
			{
				hqlQuery.append("a.organism.id = :organismId");
				params.put("organismId", ((SubDepartment) administration).getOrganism().getId());
			}
			else if (administration instanceof Service)
			{
				hqlQuery.append("a.organism.id = :organismId");
				params.put("organismId", ((Service) administration).getSubDepartment().getOrganism().getId());
			}
			else if (administration instanceof Entity)
			{
				hqlQuery.append("a.organism.id = :organismId");
				params.put("organismId", ((Entity) administration).getService().getSubDepartment().getOrganism().getId());
			}

			final TypedQuery<AnalyseType> query = super.entityManager.createQuery(hqlQuery.toString(), AnalyseType.class);

			// Set the parameters into the query
			for (final Entry<String, Object> entry : params.entrySet())
			{
				query.setParameter(entry.getKey(), entry.getValue());
			}

			return query.getResultList();
		}

		return Collections.emptyList();
	}
}
