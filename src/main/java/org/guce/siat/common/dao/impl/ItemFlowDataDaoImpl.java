/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.ItemFlowDataDao;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.ItemFlowData;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class ItemFlowDataDaoImpl.
 */
@Repository("itemFlowDataDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ItemFlowDataDaoImpl extends AbstractJpaDaoImpl<ItemFlowData>implements ItemFlowDataDao
{

	/**
	 * Instantiates a new item flow data dao impl.
	 */
	public ItemFlowDataDaoImpl()
	{
		super();
		setClasse(ItemFlowData.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.dao.ItemFlowDataDao#findByItemFlows(java.util.List)
	 */
	@Override
	public List<ItemFlowData> findByItemFlows(final List<ItemFlow> itemFlows)
	{
		if (CollectionUtils.isNotEmpty(itemFlows))
		{
			StringBuilder hqlBuilder = new StringBuilder();

			hqlBuilder.append("SELECT i FROM ItemFlowData i ");
			hqlBuilder.append("WHERE i.itemFlow IN (:itemFlowsList)");

			TypedQuery<ItemFlowData> query = entityManager.createQuery(hqlBuilder.toString(), ItemFlowData.class);
			query.setParameter("itemFlowsList", itemFlows);
			return query.getResultList();
		}
		return Collections.emptyList();
	}


}
