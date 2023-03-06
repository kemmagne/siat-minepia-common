/**
 *
 */
package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.service.AbstractService;
import org.guce.siat.common.model.ProductCategory;


/**
 * The Interface ProductCategoryService.
 */
public interface ProductCategoryService extends AbstractService<ProductCategory>
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
}
