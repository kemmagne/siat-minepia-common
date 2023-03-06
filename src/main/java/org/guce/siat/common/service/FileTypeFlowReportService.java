package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlowReport;
import org.guce.siat.common.model.Flow;

/**
 * The Interface FileTypeFlowReportService.
 */
public interface FileTypeFlowReportService extends AbstractService<FileTypeFlowReport> {

    /**
     * Find report class name by flow and file type.
     *
     * @param flow the flow
     * @param fileType the file type
     * @return the list
     */
    List<FileTypeFlowReport> findReportClassNameByFlowAndFileType(Flow flow, FileType fileType);

    /**
     * Find report class name by file type.
     *
     * @param fileType the file type
     * @return the list
     */
    FileTypeFlowReport findReportClassNameByFileType(FileType fileType);
    
    /**
     * Find report class name by file type.
     *
     * @param fileFieldName  the file field Name
     * @return the list
     */
    FileTypeFlowReport findReportClassNameByFileFieldName(String fileFieldName);
}
