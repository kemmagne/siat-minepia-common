package org.guce.siat.common.service.impl;

import java.util.List;

import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FlowDao;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.service.FlowService;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FlowServiceImpl.
 */
@Service("flowService")
@Transactional(readOnly = true)
public class FlowServiceImpl extends AbstractServiceImpl<Flow> implements FlowService {

    /**
     * The flow dao.
     */
    @Autowired
    private FlowDao flowDao;

    /**
     * Instantiates a new flow service impl.
     */
    public FlowServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<Flow> getJpaDao() {
        return flowDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<Flow> jpaDao) {
        this.flowDao = (FlowDao) jpaDao;
    }


    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findFlowsByFromStepAndFileType(org.guce.siat.common.model.Step,
	 * org.guce.siat.common.model.FileType)
     */
    @Override
    public List<Flow> findFlowsByFromStepAndFileType(final Step step, final FileType fileType) {
        return flowDao.findFlowsByFromStepAndFileType(step, fileType);
    }

    @Override
    public List<Flow> findFlowsByFromStepAndFileType2(final Step step, final FileType fileType) {
        return flowDao.findFlowsByFromStepAndFileType2(step, fileType);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FlowService#findByStepsIDs(java.util.List)
     */
    @Override
    public List<Flow> findByStepsIDs(final List<Long> idsSteps) {
        return flowDao.findByStepsIDs(idsSteps);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FlowService#findFlowByLabel(java.lang.String)
     */
    @Override
    public Flow findFlowByLabelSourceAndTarget(final String flowLabel, final String source, final String target, final String local) {
        return flowDao.findFlowByLabelSourceAndTarget(flowLabel, source, target, local);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FlowService#findFlowByCurrentStep(org.guce.siat.core.ct.model.Step)
     */
    @Override
    public Flow findFlowByCurrentStep(final Step step) {
        return flowDao.findFlowByCurrentStep(step);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findFlowBySentFileItem(org.guce.siat.common.model.FileItem)
     */
    @Override
    public Flow findFlowBySentFileItem(final FileItem fileItem) {
        return flowDao.findFlowBySentFileItem(fileItem);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findFlowByCode(java.lang.String)
     */
    @Override
    public Flow findFlowByCode(final String flowCode) {
        return flowDao.findFlowByCode(flowCode);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findFlowListByFlowCodeList(java.util.List)
     */
    @Override
    public List<Flow> findFlowListByFlowCodeList(final List<String> flowCodeList) {
        return flowDao.findFlowListByFlowCodeList(flowCodeList);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findFinalDecisions()
     */
    @Override
    public List<Flow> findFinalDecisions() {
        return flowDao.findFinalDecisions();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findFlowByFileType(org.guce.siat.common.utils.enums.FileTypeCode)
     */
    @Override
    public List<Flow> findFlowByFileType(final FileTypeCode fileType) {
        return flowDao.findFlowByFileType(fileType);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findFlowByFileTypes(java.util.List)
     */
    @Override
    public List<Flow> findFlowByFileTypes(final List<FileTypeCode> fileTypeCodes) {
        return flowDao.findFlowByFileTypes(fileTypeCodes);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findByToStep(org.guce.siat.common.model.Step)
     */
    @Override
    public Flow findByToStep(final Step step) {
        return flowDao.findByToStep(step);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FlowService#findByToStep(org.guce.siat.common.model.Step, org.guce.siat.common.model.FileType)
     */
    @Override
    public Flow findByToStep(Step step, FileType fileType) {
        return flowDao.findByToStep(step, fileType);
    }

}
