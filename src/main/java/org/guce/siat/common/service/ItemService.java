package org.guce.siat.common.service;

import java.util.List;
import java.util.Map;
import org.guce.siat.common.model.Item;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface itemService.
 */
public interface ItemService extends AbstractService<Item> {

    /**
     * Find by goods item code.
     *
     * @param goodsItemCode the goods item code
     * @return the item
     */
    Item findByGoodsItemCode(final String goodsItemCode);

    List<Item> findNSHByFileTypes(FileTypeCode... fileTypeCodes);
    
    List<Item> findNSHByCodeAndDescriptionAndFileTypes(String searchQuery, FileTypeCode... fileTypeCodes);
    Map<String, Item> getItemsAsMap(); 
}
