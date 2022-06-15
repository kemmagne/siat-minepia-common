package org.guce.siat.common.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.dao.FlowDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.FlowCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FlowDaoImpl.
 */
@Repository("flowDao")
@Transactional
public class FlowDaoImpl extends AbstractJpaDaoImpl<Flow> implements FlowDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FlowDaoImpl.class);

    /**
     * Instantiates a new flow dao impl.
     */
    public FlowDaoImpl() {
        super();
        setClasse(Flow.class);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findFlowsByFromStepAndFileType(org.guce.siat.common.model.Step,
	 * org.guce.siat.common.model.FileType)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Flow> findFlowsByFromStepAndFileType(final Step step, final FileType fileType) {
        if (step != null) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("SELECT f FROM Flow f ");
            hqlBuilder.append("WHERE f.fromStep.id =:stepId ");
            hqlBuilder.append("AND ( ");
            hqlBuilder.append("f.toStep.id IN ");
            hqlBuilder.append('(');
            hqlBuilder.append("SELECT fts.primaryKey.step.id ");
            hqlBuilder.append("FROM FileTypeStep fts ");
            hqlBuilder.append("WHERE fts.primaryKey.fileType.id = :fileTypeId");
            hqlBuilder.append(") ");
            hqlBuilder.append("OR ");
            hqlBuilder.append("f.toStep is null ");
            hqlBuilder.append(')');

            final TypedQuery<Flow> query = super.entityManager.createQuery(hqlBuilder.toString(), Flow.class);
            query.setParameter("stepId", step.getId());
            query.setParameter("fileTypeId", fileType.getId());

            List<Flow> flows = query.getResultList();

            return flows;
        }

        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Flow> findFlowsByFromStepAndFileType2(Step step, FileType fileType) {

        if (step == null) {
            return Collections.emptyList();
        }

        final TypedQuery<Flow> query = super.entityManager.createQuery("SELECT ftf.pk.flow FROM FileTypeFlow ftf, Flow f WHERE ftf.pk.flow.id = f.id AND f.fromStep.id = :stepId AND ftf.pk.fileType.id = :fileTypeId AND (f.toStep.id IN (SELECT fts.primaryKey.step.id FROM FileTypeStep fts WHERE fts.primaryKey.fileType.id = :fileTypeId) OR f.toStep is null)", Flow.class);
        query.setParameter("stepId", step.getId());
        query.setParameter("fileTypeId", fileType.getId());
        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FlowDao#findFlowBySentFileItems(java.util.List)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findFlowBySentFileItem(final FileItem fileItem) {
        if (fileItem != null) {
            try {
                final String hqlString = "SELECT f.flow FROM ItemFlow f  WHERE f.fileItem.id = :fileItemId AND f.sent = false ";
                final TypedQuery<Flow> query = super.entityManager.createQuery(hqlString, Flow.class);
                query.setParameter("fileItemId", fileItem.getId());

                return query.getSingleResult();
            } catch (final NoResultException | NonUniqueResultException e) {
                LOG.info(Objects.toString(e));
            }
        }

        return null;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FlowDao#findByStepsIDs(java.util.List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Flow> findByStepsIDs(final List<Long> idsSteps) {
        if (CollectionUtils.isNotEmpty(idsSteps)) {
            final String hqlString = " FROM Flow f WHERE f.toStep.id IN (:idsSteps)  OR f.fromStep.id  IN (:idsSteps)";
            final TypedQuery<Flow> query = super.entityManager.createQuery(hqlString, Flow.class);
            query.setParameter("idsSteps", idsSteps);
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FlowDao#findFlowByLabel(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findFlowByLabelSourceAndTarget(final String flowLabel, final String source, final String target, final String local) {
        try {
            if (StringUtils.isNotEmpty(source) && StringUtils.isNotEmpty(target)) {
                String hqlString;
                if ("fr".equals(local)) {
                    hqlString = "SELECT f FROM Flow f WHERE f.labelFr = :flowLabel AND f.fromStep.id = :source  AND f.toStep.id = :target";
                } else {
                    hqlString = "SELECT f FROM Flow f WHERE f.labelEn = :flowLabel AND f.fromStep.id = :source  AND f.toStep.id = :target";
                }
                final TypedQuery<Flow> query = super.entityManager.createQuery(hqlString, Flow.class);
                query.setParameter("flowLabel", flowLabel);
                query.setParameter("source", new Long(source));
                query.setParameter("target", new Long(target));
                return query.getSingleResult();
            } else {
                return null;
            }
        } catch (final NumberFormatException e) {
            LOG.info(Objects.toString(e));
            throw new DAOException(e);
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.dao.FlowDao#findFlowByCode(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findFlowByCode(final String flowCode) {
        try {
            final String hqlString = " FROM Flow f WHERE f.code = :flowCode)";
            final TypedQuery<Flow> query = super.entityManager.createQuery(hqlString, Flow.class);
            query.setParameter("flowCode", flowCode);
            return query.getSingleResult();
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findFlowListByFlowCodeList(java.util.List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Flow> findFlowListByFlowCodeList(final List<String> flowCodeList) {
        if (CollectionUtils.isNotEmpty(flowCodeList)) {
            final String hqlString = " FROM Flow f WHERE f.code IN (:flowCodeList)";
            final TypedQuery<Flow> query = super.entityManager.createQuery(hqlString, Flow.class);
            query.setParameter("flowCodeList", flowCodeList);
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findFlowByCurrentStep(org.guce.siat.common.model.Step)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findFlowByCurrentStep(final Step step) {
        try {
            if (step != null) {
                final String hqlString = " FROM Flow f WHERE f.fromStep.id = :currentStepId AND f.isCota = 1";
                final TypedQuery<Flow> query = super.entityManager.createQuery(hqlString, Flow.class);
                query.setParameter("currentStepId", step.getId());
                return query.getSingleResult();
            } else {
                return null;
            }
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findCotationFlowByCurrentStep(org.guce.siat.common.model.Step)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findCotationFlowByCurrentStep(final Step step) {
        try {
            if (step != null) {
                final String hqlString = " FROM Flow f WHERE f.fromStep.id = :currentStepId AND f.isCota = 1";
                final TypedQuery<Flow> query = super.entityManager.createQuery(hqlString, Flow.class);
                query.setParameter("currentStepId", step.getId());
                return query.getSingleResult();
            } else {
                return null;
            }
        } catch (final NoResultException | NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findCiResponseFlow(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findCiResponseFlow(final String flowSiatCode) {
        FlowCode flowCode = FlowCode.valueOf(flowSiatCode);
        switch (flowCode) {
            //AP
            case FL_AP_86: {
                return findFlowByCode(FlowCode.FL_AP_154.name());
            }
            case FL_AP_113: {
                return findFlowByCode(FlowCode.FL_AP_141.name());
            }
            case FL_AP_114: {
                return findFlowByCode(FlowCode.FL_AP_142.name());
            }
            case FL_AP_115: {
                return findFlowByCode(FlowCode.FL_AP_143.name());
            }
            case FL_AP_116: {
                return findFlowByCode(FlowCode.FL_AP_144.name());
            }
            case FL_AP_117: {
                return findFlowByCode(FlowCode.FL_AP_145.name());
            }
            case FL_AP_118: {
                return findFlowByCode(FlowCode.FL_AP_146.name());
            }
            case FL_AP_176: {
                return findFlowByCode(FlowCode.FL_AP_187.name());
            }
            case FL_AP_178: {
                return findFlowByCode(FlowCode.FL_AP_188.name());
            }
            case FL_AP_180: {
                return findFlowByCode(FlowCode.FL_AP_189.name());
            }
            case FL_AP_182: {
                return findFlowByCode(FlowCode.FL_AP_190.name());
            }
            case FL_AP_184: {
                return findFlowByCode(FlowCode.FL_AP_191.name());
            }
            case FL_AP_186: {
                return findFlowByCode(FlowCode.FL_AP_192.name());
            }

            // CO
            case FL_CO_86: {
                return findFlowByCode(FlowCode.FL_CO_154.name());
            }
            case FL_CO_113: {
                return findFlowByCode(FlowCode.FL_CO_141.name());

            }
            case FL_CO_114: {
                return findFlowByCode(FlowCode.FL_CO_142.name());

            }
            case FL_CO_115: {
                return findFlowByCode(FlowCode.FL_CO_143.name());

            }
            case FL_CO_116: {
                return findFlowByCode(FlowCode.FL_CO_144.name());

            }
            case FL_CO_117: {
                return findFlowByCode(FlowCode.FL_CO_145.name());

            }
            case FL_CO_118: {
                return findFlowByCode(FlowCode.FL_CO_146.name());
            }

            // AM
            case FL_AM_86: {
                return findFlowByCode(FlowCode.FL_AM_154.name());
            }
            case FL_AM_113: {
                return findFlowByCode(FlowCode.FL_AM_141.name());

            }
            case FL_AM_114: {
                return findFlowByCode(FlowCode.FL_AM_142.name());

            }
            case FL_AM_115: {
                return findFlowByCode(FlowCode.FL_AM_143.name());

            }
            case FL_AM_116: {
                return findFlowByCode(FlowCode.FL_AM_144.name());

            }
            case FL_AM_117: {
                return findFlowByCode(FlowCode.FL_AM_145.name());

            }
            case FL_AM_118: {
                return findFlowByCode(FlowCode.FL_AM_146.name());
            }

            // CCT
            case FL_CT_02: {
                return findFlowByCode(FlowCode.FL_CT_03.name());
            }
            case FL_CT_22: {
                return findFlowByCode(FlowCode.FL_CT_38.name());
            }
            case FL_CT_24: {
                return findFlowByCode(FlowCode.FL_CT_68.name());
            }
            case FL_CT_25: {
                return findFlowByCode(FlowCode.FL_CT_69.name());
            }
            case FL_CT_30: {
                return findFlowByCode(FlowCode.FL_CT_70.name());
            }
            case FL_CT_37: {
                return findFlowByCode(FlowCode.FL_CT_39.name());
            }
            case FL_CT_46: {
                return findFlowByCode(FlowCode.FL_CT_47.name());
            }
            case FL_CT_50: {
                return findFlowByCode(FlowCode.FL_CT_51.name());
            }
            case FL_CT_55: {
                return findFlowByCode(FlowCode.FL_CT_56.name());
            }
            case FL_CT_58: {
                return findFlowByCode(FlowCode.FL_CT_71.name());
            }
            case FL_CT_96: {
                return findFlowByCode(FlowCode.FL_CT_99.name());
            }
            case FL_CT_101: {
                return findFlowByCode(FlowCode.FL_CT_99.name());
            }
            case FL_CT_127: {
                return findFlowByCode(FlowCode.FL_CT_128.name());
            }
            case FL_CT_137: {
                return findFlowByCode(FlowCode.FL_CT_139.name());
            }
            case FL_CT_155: {
                return findFlowByCode(FlowCode.FL_CT_156.name());
            }
            case FL_CT_146: {
                return findFlowByCode(FlowCode.FL_CT_147.name());
            }
            case FL_CT_148: {
                return findFlowByCode(FlowCode.FL_CT_130.name());
            }
//            case FL_CT_E_AIP_02: {
//                return findFlowByCode(FlowCode.FL_CT_E_AIP_11.name());
//            }
//            case FL_CT_E_AIP_07: {
//                return findFlowByCode(FlowCode.FL_CT_E_AIP_12.name());
//            }

            // FT
            case FL_FT_86: {
                return findFlowByCode(FlowCode.FL_FT_154.name());
            }

            // SF
            case FL_SF_86: {
                return findFlowByCode(FlowCode.FL_SF_154.name());
            }
            case FL_SF_113: {
                return findFlowByCode(FlowCode.FL_SF_141.name());
            }

            // CC
            case FL_CC_86: {
                return findFlowByCode(FlowCode.FL_CC_154.name());
            }
            case FL_CC_113: {
                return findFlowByCode(FlowCode.FL_CC_141.name());
            }
            case FL_CC_160: {
                return findFlowByCode(FlowCode.FL_CC_161.name());
            }

            //
            default:
                return null;
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findFinalDecisions()
     */
    @Transactional(readOnly = true)
    @Override
    public List<Flow> findFinalDecisions() {
        final TypedQuery<Flow> query = super.entityManager.createQuery("FROM Flow f WHERE f.toStep.isFinal = true", Flow.class);
        return query.getResultList();
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findFlowByFileType(org.guce.siat.common.utils.enums.FileTypeCode)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Flow> findFlowByFileType(final FileTypeCode fileType) {
        final TypedQuery<Flow> query = super.entityManager
                .createQuery(
                        "SELECT DISTINCT f FROM FileTypeStep fl JOIN fl.primaryKey.step.fromStepFlowsList f WHERE fl.primaryKey.fileType.code=:code AND f.toStep.isFinal=:etat ORDER BY f.code ASC",
                        Flow.class);
        query.setParameter("code", fileType).setParameter("etat", true);

        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findFlowByFileTypes(java.util.List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Flow> findFlowByFileTypes(final List<FileTypeCode> fileTypeCodes) {
        final TypedQuery<Flow> query = super.entityManager
                .createQuery(
                        "SELECT DISTINCT f FROM FileTypeStep fl JOIN fl.primaryKey.step.fromStepFlowsList f WHERE fl.primaryKey.fileType.code IN :code AND f.toStep.isFinal=:etat ORDER BY f.code ASC",
                        Flow.class);
        query.setParameter("code", fileTypeCodes).setParameter("etat", true);

        return query.getResultList();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findByToStep(org.guce.siat.common.model.Step)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findByToStep(final Step step) {

        TypedQuery<Flow> query = null;
        try {
            if (step != null) {
                final String hqlString = " FROM Flow f WHERE f.toStep.id = :currentStepId ";
                query = super.entityManager.createQuery(hqlString, Flow.class);
                query.setParameter("currentStepId", step.getId());
                return query.getSingleResult();
            }
            return null;
        } catch (final NonUniqueResultException e) {
            LOG.info(Objects.toString(e));
            //Cas du CCT (step FinCCT)
            if (query != null) {
                return query.getResultList().get(0);
            } else {
                return null;
            }
        } catch (final NoResultException nre) {
            LOG.info(Objects.toString(nre));
            return null;
        }

    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findByToStep(org.guce.siat.common.model.Step, org.guce.siat.common.model.FileType)
     */
    @Transactional(readOnly = true)
    @Override
    public Flow findByToStep(Step step, FileType fileType) {

        if (fileType == null || step == null) {
            return null;
        }

//        Query query = super.entityManager.createNativeQuery("SELECT F.ID FROM FLOW F JOIN STEP S ON S.ID = F.TO_STEP AND S.ID = ? JOIN FILE_TYPE_STEP FS ON FS.STEP_ID = F.FROM_STEP AND FS.FILE_TYPE_ID = ?");
        TypedQuery<Flow> query = super.entityManager.createQuery("SELECT ftf.pk.flow FROM FileTypeFlow ftf WHERE ftf.pk.fileType = :fileType AND ftf.pk.flow.toStep = :toStep", Flow.class);

        query.setParameter("fileType", fileType);
        query.setParameter("toStep", step);

        query.setMaxResults(1);

        try {

            return query.getSingleResult();
        } catch (NoResultException ex) {
            LOG.warn(Objects.toString(ex), ex);
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Flow> findBeforeCotationStepFlows(File currentFile) {

        List<FileTypeCode> fileTypeCodes = Arrays.asList(currentFile.getFileType().getCode());

        TypedQuery<Step> stepQuery = super.entityManager.createQuery("SELECT DISTINCT ftf.pk.flow.fromStep FROM FileTypeFlow ftf WHERE ftf.pk.fileType.code IN (:fileTypeCodes) AND ftf.pk.flow.isCota = true", Step.class);
        stepQuery.setParameter("fileTypeCodes", fileTypeCodes);
        List<Step> cotationSteps = stepQuery.getResultList();
        if (cotationSteps.isEmpty()) {
            return new ArrayList<>();
        }

        TypedQuery<Flow> flowQuery = super.entityManager.createQuery("SELECT DISTINCT ftf.pk.flow FROM FileTypeFlow ftf WHERE ftf.pk.fileType.code IN (:fileTypeCodes) AND ftf.pk.flow.toStep IN (:cotationSteps)", Flow.class);
        flowQuery.setParameter("fileTypeCodes", fileTypeCodes);
        flowQuery.setParameter("cotationSteps", cotationSteps);
        List<Flow> flows = flowQuery.getResultList();

        return flows;
    }
    
    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.FlowDao#findFlowsByFromStepAndFileType(org.guce.siat.common.model.Step,
	 * org.guce.siat.common.model.FileType)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Flow> findFlowsByToStepAndFileType(final Step step, final FileType fileType) {
        if (step != null) {
            final StringBuilder hqlBuilder = new StringBuilder();

            hqlBuilder.append("SELECT f FROM Flow f ");
            hqlBuilder.append("WHERE f.toStep.id =:stepId ");
            hqlBuilder.append("AND ( ");
            hqlBuilder.append("f.fromStep.id IN ");
            hqlBuilder.append('(');
            hqlBuilder.append("SELECT fts.primaryKey.step.id ");
            hqlBuilder.append("FROM FileTypeStep fts ");
            hqlBuilder.append("WHERE fts.primaryKey.fileType.id = :fileTypeId");
            hqlBuilder.append(") ");
            hqlBuilder.append("OR ");
            hqlBuilder.append("f.fromStep is not null ");
            hqlBuilder.append(')');

            final TypedQuery<Flow> query = super.entityManager.createQuery(hqlBuilder.toString(), Flow.class);
            query.setParameter("stepId", step.getId());
            query.setParameter("fileTypeId", fileType.getId());

            List<Flow> flows = query.getResultList();

            return flows;
        }

        return Collections.emptyList();
    }


}
