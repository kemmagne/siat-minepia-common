package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.Item;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface ItemDao.
 */
public interface ItemDao extends AbstractJpaDao<Item> {

    /**
     * Find by goods item code.
     *
     * @param goodsItemCode the goods item code
     * @return the item
     */
    Item findByGoodsItemCode(final String goodsItemCode);

    List<Item> findNSHByFileTypes(FileTypeCode... fileTypeCodes);
    
    List<Item> findNSHByCodeAndDescriptionAndFileTypes(String searchQuery, FileTypeCode... fileTypeCodes);

}
