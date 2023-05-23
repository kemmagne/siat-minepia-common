/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.model.File;
import org.guce.siat.common.dao.FileProductCategoryDao;
import org.guce.siat.common.model.FileProductCategory;
import org.guce.siat.common.model.ProductCategory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ProductCategoryDaoImpl.
 */
@Repository("fileProductCategoryDao")
@Transactional(propagation = Propagation.REQUIRED)
public class FileProductCategoryDaoImpl extends AbstractJpaDaoImpl<FileProductCategory> implements FileProductCategoryDao {

    /**
     * Instantiates a new product category dao impl.
     */
    public FileProductCategoryDaoImpl() {
        super();
        setClasse(FileProductCategory.class);
    }

    

    @Override
    public List<FileProductCategory> findActiveCategoriesByFile(File file) {
        final String hqlString = "SELECT fp FROM FileProductCategory fp WHERE fp.file.id = :id AND fp.productCategory.active = true";
        final TypedQuery<FileProductCategory> query = super.entityManager.createQuery(hqlString, FileProductCategory.class);
        query.setParameter("id", file.getId());
        return query.getResultList();
    }

    @Override
    public List<FileProductCategory> findCategoriesByFile(File file) {
        final String hqlString = "SELECT fp FROM FileProductCategory fp WHERE fp.file.id = :id";
        final TypedQuery<FileProductCategory> query = super.entityManager.createQuery(hqlString, FileProductCategory.class);
        query.setParameter("id", file.getId());
        return query.getResultList();
    }

    @Override
    public List<FileProductCategory> findCategoryByFileAndCategory(File file, ProductCategory productCategory) {
        final String hqlString = "SELECT fp FROM FileProductCategory fp WHERE fp.file.id = :id AND fp.productCategory.code = :code";
        final TypedQuery<FileProductCategory> query = super.entityManager.createQuery(hqlString, FileProductCategory.class);
        query.setParameter("id", file.getId());
        query.setParameter("code", productCategory.getCode());
        return query.getResultList();
    }
}
