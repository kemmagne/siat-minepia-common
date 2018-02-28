package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FlowService.
 */
public interface FlowService extends AbstractService<Flow> {

    /**
     * Find flows by from step and file type.
     *
     * @param step the step
     * @param fileType the file type
     * @return the list
     */
    List<Flow> findFlowsByFromStepAndFileType(Step step, FileType fileType);

    /**
     * Find flows by from step and file type by using the file_type_flow table
     * other than the flow table like usually
     *
     * @param step
     * @param fileType
     * @return
     */
    List<Flow> findFlowsByFromStepAndFileType2(Step step, FileType fileType);

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
     * Find flow by sent file item.
     *
     * @param fileItem the file item
     * @return the flow
     */
    Flow findFlowBySentFileItem(FileItem fileItem);

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
     * Find final decisions.
     *
     * @return the list
     */
    List<Flow> findFinalDecisions();

    /**
     * Find flow by file type.
     *
     * @param coGco the co gco
     * @return the list
     */
    List<Flow> findFlowByFileType(FileTypeCode coGco);

    /**
     * Find flow by file types.
     *
     * @param fileTypeCodes the file type codes
     * @return the list
     */
    List<Flow> findFlowByFileTypes(List<FileTypeCode> fileTypeCodes);

    /**
     * Find by to step.
     *
     * @param step the step
     * @return the flow
     */
    Flow findByToStep(Step step);

}

