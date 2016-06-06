package org.guce.siat.common.dao;

import org.guce.siat.common.model.Item;


/**
 * The Interface ItemDao.
 */
public interface ItemDao extends AbstractJpaDao<Item>
{

	/**
	 * Find by goods item code.
	 *
	 * @param goodsItemCode
	 *           the goods item code
	 * @return the item
	 */
	Item findByGoodsItemCode(final String goodsItemCode);

}
