/**
 *
 */
package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.service.impl.AbstractServiceImpl;
import org.guce.siat.common.dao.ProductCategoryDao;
import org.guce.siat.common.model.ProductCategory;
import org.guce.siat.common.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class ProductCategoryServiceImpl.
 */
@Service("productCategoryService")
@Transactional(readOnly = true)
public class ProductCategoryServiceImpl extends AbstractServiceImpl<ProductCategory> implements ProductCategoryService
{


	/** The product category dao. */
	@Autowired
	private ProductCategoryDao productCategoryDao;

	/**
	 * Instantiates a new product category service impl.
	 */
	public ProductCategoryServiceImpl()
	{
		super();
	}

    @Override
    public AbstractJpaDao<ProductCategory> getJpaDao() {
        return productCategoryDao;
    }

    @Override
    public void setJpaDao(AbstractJpaDao<ProductCategory> jpaDao) {
        this.productCategoryDao = (ProductCategoryDao) jpaDao;
    }

    @Override
    public ProductCategory findByCode(String code) {
        return this.productCategoryDao.findByCode(code);
    }

    @Override
    public List<ProductCategory> findActiveCategories() {
        return this.productCategoryDao.findActiveCategories();
    }

	

}
