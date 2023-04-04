/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.model.File;
import org.guce.siat.common.dao.ProductCategoryDao;
import org.guce.siat.common.model.ProductCategory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ProductCategoryDaoImpl.
 */
@Repository("productCategoryDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ProductCategoryDaoImpl extends AbstractJpaDaoImpl<ProductCategory> implements ProductCategoryDao {

    /**
     * Instantiates a new product category dao impl.
     */
    public ProductCategoryDaoImpl() {
        super();
        setClasse(ProductCategory.class);
    }

    @Override
    public ProductCategory findByCode(String code) {
        if (code != null) {
            final String hqlString = "SELECT p FROM ProductCategory p WHERE p.code = :code AND p.active = true";
            final TypedQuery<ProductCategory> query = super.entityManager.createQuery(hqlString, ProductCategory.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        }
        return null;
    }

    @Override
    public List<ProductCategory> findActiveCategories() {
        final String hqlString = "SELECT p FROM ProductCategory p WHERE p.active = true";
        final TypedQuery<ProductCategory> query = super.entityManager.createQuery(hqlString, ProductCategory.class);
        return query.getResultList();

    }

    @Override
    public List<ProductCategory> findActiveCategoriesByFile(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
