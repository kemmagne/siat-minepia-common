/**
 *
 */
package org.guce.siat.common.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.DateUtils;
import org.guce.siat.common.utils.SiatUtils;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.guce.siat.common.utils.filter.RetrieveSearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileItemDaoImpl.
 */
@Repository("fileItemDao")
@Transactional
public class FileItemDaoImpl extends AbstractJpaDaoImpl<FileItem> implements FileItemDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileItemDaoImpl.class);

    /**
     * Instantiates a new file item dao impl.
     */
    public FileItemDaoImpl() {
        setClasse(FileItem.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FileItemDao#findFileItemsByFile(org.guce.siat.core.ct.model.File)
     */
    @Transactional(readOnly = true)
    @Override
    public List<FileItem> findFileItemsByFile(final File file) {
        if (file != null) {
            final String hqlString = "FROM FileItem fi WHERE fi.file.id = :fileId";
            final TypedQuery<FileItem> query = super.entityManager.createQuery(hqlString, FileItem.class);
            query.setParameter("fileId", file.getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.ItemFlowDao#findDraftFileItemsByFile(org.guce.siat.core.ct.model.File)
     */
    @Transactional(readOnly = true)
    @Override
    public List<FileItem> findDraftFileItemsByFile(final File file) {
        if (file != null) {
            final String qlString = "SELECT i.fileItem FROM ItemFlow i WHERE i.fileItem.file.id= :fileItemId  AND i.sent = false";
            final TypedQuery<FileItem> query = super.entityManager.createQuery(qlString, FileItem.class);
            query.setParameter("fileItemId", file.getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.guce.siat.common.dao.FileItemDao#findFileItemByServiceAndAuthoritiesAndFileType(java.util.List,
     * org.guce.siat.common.model.User, java.util.List, java.util.List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<FileItem> findFileItemByServiceAndAuthoritiesAndFileType(final List<Bureau> bureauList, final User loggedUser, final List<FileTypeCode> fileTypeCodeList, final List<StepCode> excludedStepList) {
        final StringBuilder hqlBuilder = new StringBuilder();
        hqlBuilder.append("FROM FileItem fi ");
        hqlBuilder.append("WHERE fi.file.bureau IN (:bureauList) ");
        hqlBuilder.append("AND fi.step.stepCode NOT IN (:excludedStepList) ");
        hqlBuilder.append("AND fi.file.fileType.id = ");

        hqlBuilder.append('(');
        hqlBuilder.append("SELECT DISTINCT authFi.primaryKey.fileType.id ");
        hqlBuilder.append("FROM UserAuthorityFileType authFi ");
        hqlBuilder.append("WHERE authFi.primaryKey.userAuthority.user.id IN (:userListIds) ");
        hqlBuilder.append("AND authFi.primaryKey.fileType.id = fi.file.fileType.id ");
        hqlBuilder.append("AND authFi.primaryKey.fileType.code IN (:listFileTypeCode) ");
        hqlBuilder.append(')');

        final TypedQuery<FileItem> query = super.entityManager.createQuery(hqlBuilder.toString(), FileItem.class);

        query.setParameter("bureauList", bureauList);
        //The ids of the logged user combined with their delegator users
        query.setParameter("userListIds", SiatUtils.getUserIds(loggedUser.getMergedDelegatorList()));
        query.setParameter("listFileTypeCode", fileTypeCodeList);
        query.setParameter("excludedStepList", excludedStepList);
        
        return query.getResultList();
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.guce.siat.common.dao.FileItemDao#findFilesByServiceAndAuthoritiesAndFileType(java.util.List,
     * org.guce.siat.common.model.User, java.util.List, java.util.List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<File> findFilesByServiceAndAuthoritiesAndFileType(final List<Bureau> bureauList, final User loggedUser, final List<FileTypeCode> fileTypeCodeList, final List<StepCode> excludedStepList) {
        final StringBuilder hqlBuilder = new StringBuilder();
        hqlBuilder.append("SELECT DISTINCT fi FROM File fi ");
        hqlBuilder.append("WHERE fi.bureau IN (:bureauList) ");
        hqlBuilder.append("AND fi.step.stepCode NOT IN (:excludedStepList) ");
        hqlBuilder.append("AND fi.fileType.id = ");

        hqlBuilder.append("(");
        hqlBuilder.append("SELECT DISTINCT authFi.primaryKey.fileType.id ");
        hqlBuilder.append("FROM UserAuthorityFileType authFi ");
        hqlBuilder.append("WHERE authFi.primaryKey.userAuthority.user.id IN (:userListIds) ");
        hqlBuilder.append("AND authFi.primaryKey.fileType.id = fi.fileType.id ");
        hqlBuilder.append("AND authFi.primaryKey.fileType.code IN (:listFileTypeCode) ");
        hqlBuilder.append(")");
//        hqlBuilder.append(") ORDER BY fi.file.lastDecisionDate DESC");

        final TypedQuery<File> query = super.entityManager.createQuery(hqlBuilder.toString(), File.class);
        
        query.setParameter("bureauList", bureauList);
		//The ids of the logged user combined with their delegator users
		List<Long> listId = SiatUtils.getUserIds(loggedUser.getMergedDelegatorList());
		System.out.println(" liste : " + listId);
        query.setParameter("userListIds", SiatUtils.getUserIds(loggedUser.getMergedDelegatorList()));
        query.setParameter("listFileTypeCode", fileTypeCodeList);
        query.setParameter("excludedStepList", excludedStepList);
        
        return query.getResultList();
    }
    
    @Override
    public List<File> findFilesByServiceAndAuthoritiesAndFileType2(List<Bureau> bureauList, User loggedUser, List<Long> fileTypeIdLIst, List<StepCode> excludedStepList) {
        final StringBuilder hqlBuilder = new StringBuilder();
        hqlBuilder.append("SELECT DISTINCT fi FROM File fi ");
        hqlBuilder.append("WHERE fi.bureau IN (:bureauList) ");
        hqlBuilder.append("AND fi.step.stepCode NOT IN (:excludedStepList) ");
        hqlBuilder.append("AND fi.fileType.id IN (:fileTypeIdList)");
        final TypedQuery<File> query = super.entityManager.createQuery(hqlBuilder.toString(), File.class);
        
        query.setParameter("bureauList", bureauList);
        query.setParameter("fileTypeIdList", fileTypeIdLIst);
        query.setParameter("excludedStepList", excludedStepList);
        
        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FileItemDao#findFileItemFieldValueByFieldCode(org.guce.siat.core.ct.model.FileItem,
	 * java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public FileItemFieldValue findFileItemFieldValueByFieldCode(final FileItem fileItem, final String fieldCode) {
        if (StringUtils.isNotEmpty(fieldCode)) {
            for (final FileItemFieldValue fv : fileItem.getFileItemFieldValueList()) {
                if (StringUtils.equals(fieldCode, fv.getFileItemField().getCode())) {
                    return fv;
                }
            }
        }
        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileItemDao#findFileItemByNumEbmsMsg(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<FileItem> findFileItemByNumEbmsMsg(final String numEbmsMsg) {
        if (StringUtils.isNotBlank(numEbmsMsg)) {
            final String qlString = "SELECT fi FROM FileItem fi WHERE fi.numEbmsMessage = :numEbmsMsg";
            final TypedQuery<FileItem> query = super.entityManager.createQuery(qlString, FileItem.class);
            query.setParameter("numEbmsMsg", numEbmsMsg);
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.CommonDao#findFileItemForRetreiveByFilter(java.util.List,
	 * org.guce.siat.common.model.User, java.util.List, org.guce.siat.common.utils.enums.StepCode,
	 * org.guce.siat.core.ct.filter.RetrieveSearchFilter)
     */
    @Transactional(readOnly = true)
    @Override
    public List<FileItem> findFileItemForRetreiveByFilter(final List<Bureau> bureaus, final User loggedUser,
            final List<FileTypeCode> fileTypeCodeList, final StepCode stepApAcceptation, final RetrieveSearchFilter filter) {
        final Map<String, Object> params = new HashMap<>();
        final StringBuilder hqlBuilder = new StringBuilder();

        hqlBuilder.append("FROM FileItem fi ");
        hqlBuilder.append("WHERE fi.file.bureau IN (:bureauList) ");
        hqlBuilder.append("AND fi.step.stepCode = :stepApAcceptation ");
        hqlBuilder.append("AND fi.file.fileType.id = ");

        hqlBuilder.append('(');
        hqlBuilder.append("SELECT DISTINCT authFi.primaryKey.fileType.id ");
        hqlBuilder.append("FROM UserAuthorityFileType authFi ");
        hqlBuilder.append("WHERE authFi.primaryKey.userAuthority.user.id IN (:userListIds) ");
        hqlBuilder.append("AND authFi.primaryKey.fileType.id = fi.file.fileType.id ");
        hqlBuilder.append("AND authFi.primaryKey.fileType.code IN (:listFileTypeCode) ");
        hqlBuilder.append(')');

        if (filter.getFromDate() != null && filter.getToDate() == null) {
            hqlBuilder.append(" AND fi.file.createdDate >= TO_DATE(:createdDate,'");
            hqlBuilder.append(DateUtils.PATTERN_YYYY_MM_DD_HH24_MI_SS);
            hqlBuilder.append("')");
            params.put("createdDate", DateUtils.formatSimpleDateForOracle(filter.getFromDate()));
        }

        if (filter.getFromDate() == null && filter.getToDate() != null) {
            hqlBuilder.append(" AND fi.file.createdDate <:createDate");
            params.put("createDate", DateUtils.addDays(filter.getToDate(), 1));
        }
        if (filter.getFromDate() != null && filter.getToDate() != null) {
            hqlBuilder.append(" AND fi.file.createdDate >= :fromDate");
            hqlBuilder.append(" AND fi.file.createdDate <:toDate");
            params.put("fromDate", filter.getFromDate());
            params.put("toDate", DateUtils.addDays(filter.getToDate(), 1));
        }

        final TypedQuery<FileItem> query = super.entityManager.createQuery(hqlBuilder.toString(), FileItem.class);

        query.setParameter("bureauList", bureaus);
        //The ids of the logged user combined with their delegator users
        query.setParameter("userListIds", SiatUtils.getUserIds(loggedUser.getMergedDelegatorList()));
        query.setParameter("listFileTypeCode", fileTypeCodeList);
        query.setParameter("stepApAcceptation", stepApAcceptation);

        for (final Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileItemDao#findFileItemFieldValueByFieldCode(java.lang.Long, java.lang.Long)
     */
    @Transactional(readOnly = true)
    @Override
    public FileItemFieldValue findFileItemFieldValueByFieldCode(final Long idFileItem, final Long idItemField) {
        try {
            final String jpql = "FROM FileItemFieldValue f WHERE f.primaryKey.fileItem.id = :fileItem AND f.primaryKey.fileItemField.id=:itemField";
            final TypedQuery<FileItemFieldValue> query = super.entityManager.createQuery(jpql, FileItemFieldValue.class);
            query.setParameter("fileItem", idFileItem).setParameter("itemField", idItemField);
            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FileItemDao#findByLineNumberAndNumSiat(java.util.List, java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<FileItem> findByLineNumberAndNumSiat(final List<Integer> lineNumberList, final String refSiat) {
        TypedQuery<FileItem> query;
        if (CollectionUtils.isEmpty(lineNumberList)) {
            final String jpql = "SELECT f FROM FileItem f WHERE f.file.referenceSiat=:refSiat";
            query = super.entityManager.createQuery(jpql, FileItem.class);
            query.setParameter("refSiat", refSiat);
        } else {
            final String jpql = "SELECT f FROM FileItem f WHERE f.lineNumber IN (:lineNumberList) AND f.file.referenceSiat=:refSiat";
            query = super.entityManager.createQuery(jpql, FileItem.class);
            query.setParameter("lineNumberList", lineNumberList).setParameter("refSiat", refSiat);
        }
        if (CollectionUtils.isNotEmpty(query.getResultList())) {
            return query.getResultList();
        } else {
            return null;
        }
    }

}
