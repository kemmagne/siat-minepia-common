package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.ItemFlowData;




/**
 * The Interface ItemFlowDataDao.
 */
public interface ItemFlowDataDao extends AbstractJpaDao<ItemFlowData>
{

	/**
	 * Find by item flows.
	 *
	 * @param itemFlows
	 *           the item flows
	 * @return the list
	 */
	List<ItemFlowData> findByItemFlows(final List<ItemFlow> itemFlows);

}
