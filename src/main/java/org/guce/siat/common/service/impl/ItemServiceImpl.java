package org.guce.siat.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ItemDao;
import org.guce.siat.common.model.Item;
import org.guce.siat.common.service.ItemService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CarServiceImpl.
 */
@Service("itemService")
@Transactional(readOnly = true)
public class ItemServiceImpl extends AbstractServiceImpl<Item> implements ItemService {

    /**
     * The item dao.
     */
    @Autowired
    private ItemDao itemDao;

    private Map<String, Item> itemsAsMap;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<Item> getJpaDao() {
        return itemDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<Item> jpaDao) {
        this.itemDao = (ItemDao) jpaDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.ItemService#findByGoodsItemCode(java.lang.String)
     */
    @Override
    public Item findByGoodsItemCode(final String goodsItemCode) {
        return itemDao.findByGoodsItemCode(goodsItemCode);
    }

    @Override
    public List<Item> findNSHByFileTypes(FileTypeCode... fileTypeCodes) {
        return itemDao.findNSHByFileTypes(fileTypeCodes);
    }

    @Override
    public List<Item> findNSHByCodeAndDescriptionAndFileTypes(String searchQuery, FileTypeCode... fileTypeCodes) {
        return itemDao.findNSHByCodeAndDescriptionAndFileTypes(searchQuery, fileTypeCodes);
    }

    @Override
    public Map<String, Item> getItemsAsMap() {
        if (itemsAsMap == null) {
            //itemsAsMap = getCountries().stream().collect(Collectors.toMap(Country::getId, country -> country));
            List<Item> listItems = itemDao.findAll();
            itemsAsMap = new HashMap<>();
            for (Item item : listItems) {
                itemsAsMap.put(item.getGoodsItemCode(), item);
            }
        }
        return itemsAsMap;
    }
}
