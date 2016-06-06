package org.guce.siat.common.service;

import org.guce.siat.common.model.Item;




/**
 * The Interface itemService.
 */
public interface ItemService extends AbstractService<Item>
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
