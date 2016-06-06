package org.guce.siat.common.service.impl;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.ItemDao;
import org.guce.siat.common.model.Item;
import org.guce.siat.common.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class CarServiceImpl.
 */
@Service("itemService")
@Transactional(readOnly = true)
public class ItemServiceImpl extends AbstractServiceImpl<Item> implements ItemService
{

	/** The item dao. */
	@Autowired
	private ItemDao itemDao;

	/**
	 * Instantiates a new car service impl.
	 */
	public ItemServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<Item> getJpaDao()
	{
		return itemDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<Item> jpaDao)
	{
		this.itemDao = (ItemDao) jpaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.ItemService#findByGoodsItemCode(java.lang.String)
	 */
	public Item findByGoodsItemCode(final String goodsItemCode)
	{
		return itemDao.findByGoodsItemCode(goodsItemCode);
	}

}
