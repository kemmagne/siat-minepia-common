package org.guce.siat.common.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.guce.siat.common.dao.ItemDao;
import org.guce.siat.common.model.Item;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CustomUserDetailsDaoImpl.
 */
@Repository("itemDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ItemDaoImpl extends AbstractJpaDaoImpl<Item> implements ItemDao {

    /**
     * Instantiates a new item dao impl.
     */
    public ItemDaoImpl() {
        setClasse(Item.class);
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ItemDao#findByGoodsItemCode(java.lang.String)
     */
    @Override
    public Item findByGoodsItemCode(final String goodsItemCode) {

        final String hqlString = "SELECT i FROM Item i WHERE i.goodsItemCode = :goodsItemCode";
        final TypedQuery<Item> query = super.entityManager.createQuery(hqlString, Item.class);
        query.setParameter("goodsItemCode", goodsItemCode);
        final List<Item> itemResultList = query.getResultList();

        if (itemResultList != null && !itemResultList.isEmpty()) {
            return itemResultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Item> findNSHByFileTypes(FileTypeCode... fileTypeCodes) {

        TypedQuery<Item> query = super.entityManager.createQuery("SELECT DISTINCT fi.nsh FROM FileItem fi WHERE fi.file.fileType.code IN (:fileTypeCodes) AND fi.file.bureau IS NOT NULL AND fi.file.numeroDemande IS NOT NULL AND fi.nsh IS NOT NULL", Item.class);

        query.setParameter("fileTypeCodes", Arrays.asList(fileTypeCodes));

        return query.getResultList();
    }

}
