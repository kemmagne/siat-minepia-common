/**
 *
 */
package org.guce.siat.common.dao;


import java.util.List;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileProductCategory;
import org.guce.siat.common.model.ProductCategory;


/**
 * The Interface ProductCategoryDao.
 */
public interface FileProductCategoryDao extends AbstractJpaDao<FileProductCategory>
{

	
        List<FileProductCategory> findActiveCategoriesByFile(File file);
        
        List<FileProductCategory> findCategoriesByFile(File file);
        
        List<FileProductCategory> findCategoryByFileAndCategory(File file, ProductCategory productCategory);
}
