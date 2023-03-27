/**
 *
 */
package org.guce.siat.common.dao;


import java.util.List;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.ProductCategory;


/**
 * The Interface ProductCategoryDao.
 */
public interface ProductCategoryDao extends AbstractJpaDao<ProductCategory>
{

	/**
	 * Find by code.
	 *
	 * @param code
	 *           the code of product category
	 * @return ProductCategory
	 */
	ProductCategory findByCode(String code);
        
        List<ProductCategory> findActiveCategories();
        
        List<ProductCategory> findActiveCategoriesByFile(File file);
}
