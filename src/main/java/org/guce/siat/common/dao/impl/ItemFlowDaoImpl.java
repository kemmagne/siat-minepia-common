/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.model.ItemFlowData;
import org.guce.siat.common.utils.enums.CompanyType;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.FlowCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ItemFlowDaoImpl.
 */
@Repository("itemFlowDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ItemFlowDaoImpl extends AbstractJpaDaoImpl<ItemFlow> implements ItemFlowDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ItemFlowDaoImpl.class);

    /**
     * The Constant FILE_ITEM_QUERY_ATTRIBUTE.
     */
    private static final String FILE_ITEM_QUERY_ATTRIBUTE = "fileItems";

    /**
     * The Constant FILE_ITEMID_ID_QUERY_ATTRIBUTE.
     */
    private static final String FILE_ITEMID_ID_QUERY_ATTRIBUTE = "fileItemId";

    /**
     * Instantiates a new item flow dao impl.
     */
    public ItemFlowDaoImpl() {
        super();
        setClasse(ItemFlow.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.ItemFlowDao#findLastItemFlowByFileItem(java.lang.String)
     */
    @Override
    public ItemFlow findLastItemFlowByFileItem(final FileItem fileItem) {
        if (!Objects.equals(fileItem, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("SELECT it FROM ItemFlow it ");
            hqlBuilder.append("WHERE it.fileItem.id = :fileItemId ");
            hqlBuilder.append("AND it.id = (SELECT Max(it1.id) FROM ItemFlow it1 WHERE it1.fileItem.id = :fileItemId ) ");

            final TypedQuery<ItemFlow> query = super.entityManager.createQuery(hqlBuilder.toString(), ItemFlow.class);
            query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItem.getId());
            final List<ItemFlow> itemFlows = query.getResultList();
            if (CollectionUtils.isNotEmpty(itemFlows)) {
                return itemFlows.get(0);
            }
        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ItemFlowDao#findLastOUtgoingItemFlowByFileItem(org.guce.siat.common.model.FileItem)
     */
    @Override
    public ItemFlow findLastOutgoingItemFlowByFileItem(final FileItem fileItem) {
        if (!Objects.equals(fileItem, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("SELECT it FROM ItemFlow it ");
            hqlBuilder.append("WHERE it.flow.outgoing = 1 ");
            hqlBuilder.append("AND it.fileItem.id = :fileItemId ");
            hqlBuilder
                    .append("AND it.id = (SELECT Max(it1.id) FROM ItemFlow it1 WHERE it1.flow.outgoing = 1 AND it1.fileItem.id = :fileItemId ) ");

            final TypedQuery<ItemFlow> query = super.entityManager.createQuery(hqlBuilder.toString(), ItemFlow.class);
            query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItem.getId());
            final List<ItemFlow> itemFlows = query.getResultList();
            if (CollectionUtils.isNotEmpty(itemFlows)) {
                return itemFlows.get(0);
            }
        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.ItemFlowDao#findLastSentItemFlowByFileItem(java.lang.Long)
     */
    @Override
    public ItemFlow findLastSentItemFlowByFileItem(final FileItem fileItem) {
        if (!Objects.equals(fileItem, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("SELECT i FROM ItemFlow i ");
            hqlBuilder.append("WHERE i.fileItem.id = :fileItemId ");
            hqlBuilder
                    .append("AND i.id = (SELECT MAX(i1.id) FROM ItemFlow i1 WHERE i1.fileItem.id = :fileItemId AND i1.sent = true) ");

            final TypedQuery<ItemFlow> query = super.entityManager.createQuery(hqlBuilder.toString(), ItemFlow.class);
            query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItem.getId());
            final List<ItemFlow> itemFlows = query.getResultList();
            if (CollectionUtils.isNotEmpty(itemFlows)) {
                return itemFlows.get(0);
            }
        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.ItemFlowDao#findItemFlowsByFileItemList(java.util.List)
     */
    @Override
    public List<ItemFlow> findItemFlowsByFileItemList(final List<Long> fileItems) {
        final String qlString = "SELECT i FROM ItemFlow i WHERE  i.sent = false AND i.fileItem.id IN (:fileItems) ";
        final TypedQuery<ItemFlow> query = super.entityManager.createQuery(qlString, ItemFlow.class);
        query.setParameter(FILE_ITEM_QUERY_ATTRIBUTE, fileItems);
        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.ItemFlowDao#findItemFlowByFileItem(org.guce.siat.core.ct.model.FileItem)
     */
    @Override
    public List<ItemFlow> findItemFlowByFileItem(final FileItem fileItem) {
        final String hqlString = "SELECT i FROM ItemFlow i WHERE i.fileItem.id = :fileItemId AND i.sent = true ORDER BY i.id ASC";
        final TypedQuery<ItemFlow> query = super.entityManager.createQuery(hqlString, ItemFlow.class);
        query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItem.getId());
        return query.getResultList();
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ItemFlowDao#findItemFlowByFileItemAndFlow(org.guce.siat.common.model.FileItem,
	 * org.guce.siat.common.utils.enums.FlowCode)
     */
    @Override
    public ItemFlow findItemFlowByFileItemAndFlow(final FileItem fileItem, final FlowCode flowCode) {
        try {
            final String hqlString = "SELECT i FROM ItemFlow i WHERE i.flow.code=:flowCode AND i.fileItem.id= :fileItemId AND i.id = (SELECT MAX(i1.id) FROM ItemFlow i1 WHERE i1.flow.code=:flowCode AND i1.fileItem.id = :fileItemId AND i1.sent = true) ";
            final TypedQuery<ItemFlow> query = super.entityManager.createQuery(hqlString, ItemFlow.class);
            query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItem.getId());
            query.setParameter("flowCode", flowCode.name());

            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.ItemFlowDao#findItemFlowByFileItemAndFlow(org.guce.siat.common.model.FileItem,
	 * java.util.List)
     */
    @Override
    public ItemFlow findItemFlowByFileItemAndFlow(final FileItem fileItem, final List<String> flowCodeList) {
        try {
            final String hqlString = "SELECT i FROM ItemFlow i WHERE i.flow.code IN (:flowCodeList) AND i.fileItem.id= :fileItemId AND i.id = (SELECT MAX(i1.id) FROM ItemFlow i1 WHERE i1.flow.code IN (:flowCodeList) AND i1.fileItem.id = :fileItemId AND i1.sent = true) ";
            final TypedQuery<ItemFlow> query = super.entityManager.createQuery(hqlString, ItemFlow.class);
            query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItem.getId());
            query.setParameter("flowCodeList", flowCodeList);

            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.ItemFlowDao#findNbrDecisionByFileItemHistory(org.guce.siat.common.utils.enums.FlowCode,
	 * org.guce.siat.common.model.FileItem)
     */
    @Override
    public Long findNbrDecisionByFileItemHistory(final List<String> flowCodes, final FileItem fileItem) {
        try {
            final String hqlString = "SELECT COUNT(i) FROM ItemFlow i WHERE i.fileItem.id = :fileItemId AND i.flow.code IN (:flowCodes)";
            final TypedQuery<Long> query = super.entityManager.createQuery(hqlString, Long.class);
            query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItem.getId());
            query.setParameter("flowCodes", flowCodes);

            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    @Override
    public ItemFlow findDraftByFileItem(final FileItem fileItem) {
        if (!Objects.equals(fileItem, null)) {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("SELECT i FROM ItemFlow i ");
            hqlBuilder.append("WHERE i.sent = false AND i.fileItem.id = :fileItemId");

            final TypedQuery<ItemFlow> query = entityManager.createQuery(hqlBuilder.toString(), ItemFlow.class);
            query.setParameter("fileItemId", fileItem.getId());
            query.setMaxResults(1);
            try {
                return query.getSingleResult();
            } catch (NoResultException | NonUniqueResultException e) {
                LOG.error(Objects.toString(e));
            }
        }

        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.ItemFlowDao#findLastSentItemFlowByFileItem(java.lang.Long)
     */
    @Override
    public ItemFlow findLastSentItemFlowByFileItem(final Long fileItemId) {
        final String qlString = "SELECT i FROM ItemFlow i WHERE i.fileItem.id= :fileItemId AND i.id = (SELECT MAX(i1.id) FROM ItemFlow i1 WHERE i1.fileItem.id = :fileItemId AND i1.sent = true) ";
        final TypedQuery<ItemFlow> query = super.entityManager.createQuery(qlString, ItemFlow.class);
        query.setParameter(FILE_ITEMID_ID_QUERY_ATTRIBUTE, fileItemId);
        final List<ItemFlow> itemFlows = query.getResultList();
        if (CollectionUtils.isNotEmpty(itemFlows)) {
            return itemFlows.get(0);
        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.ItemFlowDao#findFileItemsHistoryByNegativeDecisionsAndCompany(org.guce.siat.common.model
	 * .FileItem, java.util.List)
     */
    @Override
    public List<FileItem> findFileItemsHistoryByNegativeDecisionsAndCompany(final FileItem fileItem, final List<StepCode> stepCodes) {
        final StringBuilder hqlString = new StringBuilder();

        hqlString.append("SELECT DISTINCT i.fileItem FROM ItemFlow i ");
        hqlString.append("WHERE i.fileItem.file.client.companyType=:companyType ");
        hqlString.append("AND i.fileItem.file.client.id =:importerId ");
        hqlString.append("AND i.flow.toStep.stepCode IN (:stepCodes) ");

        final TypedQuery<FileItem> query = super.entityManager.createQuery(hqlString.toString(), FileItem.class);
        query.setParameter("companyType", CompanyType.DECLARANT);
        query.setParameter("importerId", fileItem.getFile().getClient().getId());
        query.setParameter("stepCodes", stepCodes);
        return query.getResultList();
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.dao.ItemFlowDao#findFileItemsHistoryByNegativeDecisionsAndProduct(org.guce.siat.common.model
	 * .FileItem, java.util.List)
     */
    @Override
    public List<FileItem> findFileItemsHistoryByNegativeDecisionsAndProduct(final FileItem fileItem, final List<StepCode> stepCodes) {
        final StringBuilder hqlString = new StringBuilder();

        hqlString.append("SELECT DISTINCT i.fileItem FROM ItemFlow i ");
        hqlString.append("WHERE i.fileItem.id =:productId ");
        hqlString.append("AND i.flow.toStep.stepCode IN (:stepCodes) ");

        final TypedQuery<FileItem> query = super.entityManager.createQuery(hqlString.toString(), FileItem.class);
        query.setParameter("productId", fileItem.getId());
        query.setParameter("stepCodes", stepCodes);
        return query.getResultList();
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.core.cc.dao.CommonDao#retriveItemFlowDataDatabyFileTypeAndValue(org.guce.siat.common.utils.enums
	 * .FileTypeCode, java.lang.String)
     */
    @Override
    public ItemFlowData retriveItemFlowDataDatabyFileTypeAndValue(final FileTypeCode fileTypeCode, final String value) {
        try {
            final StringBuilder hqlBuilder = new StringBuilder();
            hqlBuilder.append("FROM ItemFlowData i ");
            hqlBuilder.append("WHERE i.value = :value AND i.itemFlow.fileItem.file.fileType.code= :fileTypeCode");
            final TypedQuery<ItemFlowData> query = super.entityManager.createQuery(hqlBuilder.toString(), ItemFlowData.class);
            query.setParameter("value", value);
            query.setParameter("fileTypeCode", fileTypeCode);

            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

}

