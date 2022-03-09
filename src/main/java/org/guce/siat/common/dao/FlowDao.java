package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FlowDao.
 */
public interface FlowDao extends AbstractJpaDao<Flow> {

    /**
     * Find flows by from step and file type.
     *
     * @param step the step
     * @param fileType the file type
     * @return the list
     */
    List<Flow> findFlowsByFromStepAndFileType(Step step, FileType fileType);

    List<Flow> findFlowsByFromStepAndFileType2(Step step, FileType fileType);

    /**
     * Find flow by sent file item.
     *
     * @param fileItem the file item
     * @return the flow
     */
    Flow findFlowBySentFileItem(FileItem fileItem);

    /**
     * Find by steps i ds.
     *
     * @param idsSteps the ids steps
     * @return the list
     */
    List<Flow> findByStepsIDs(List<Long> idsSteps);

    /**
     * Find flow by label.
     *
     * @param flowLabel the flow label
     * @param source the source
     * @param target the target
     * @param local the local
     * @return the flow
     */
    Flow findFlowByLabelSourceAndTarget(String flowLabel, String source, String target, String local);

    /**
     * Find flow by current step.
     *
     * @param step the step
     * @return the flow
     */
    Flow findFlowByCurrentStep(Step step);

    /**
     * Find cotation flow by current step.
     *
     * @param step the step
     * @return the flow
     */
    Flow findCotationFlowByCurrentStep(Step step);

    /**
     * Find flow by code.
     *
     * @param flowCode the flow code
     * @return the flow
     */
    Flow findFlowByCode(String flowCode);

    /**
     * Find flow list by flow code list.
     *
     * @param flowCodeList the flow code list
     * @return the list
     */
    List<Flow> findFlowListByFlowCodeList(List<String> flowCodeList);

    /**
     * Find ci response flow.
     *
     * @param flowSiatCode the flow siat code
     * @return the flow
     */
    Flow findCiResponseFlow(String flowSiatCode);

    /**
     * Find final decisions.
     *
     * @return the list
     */
    List<Flow> findFinalDecisions();

    /**
     * Find flow by file type.
     *
     * @param fileType the file type
     * @return the list
     */
    List<Flow> findFlowByFileType(FileTypeCode fileType);

    /**
     * Find flow by file types.
     *
     * @param fileTypeCodes the file type codes
     * @return the list
     */
    List<Flow> findFlowByFileTypes(List<FileTypeCode> fileTypeCodes);

    /**
     * Find by steps id.
     *
     * @param step the step
     * @return the flow
     */
    Flow findByToStep(Step step);

    /**
     * find flow by file type and to step
     *
     * @param step the to step
     * @param fileType the file type
     *
     * @return the flow
     */
    Flow findByToStep(Step step, FileType fileType);

    List<Flow> findBeforeCotationStepFlows(File currentFile);
    
    List<Flow> findFlowsByToStepAndFileType(final Step step, final FileType fileType);

}
