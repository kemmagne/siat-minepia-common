package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FlowGuceSiat;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FlowGuceSiatDao.
 */
public interface FlowGuceSiatDao extends AbstractJpaDao<FlowGuceSiat> {

    /**
     *
     * @param flowGuce the flow guce
     * @return the list
     */
    FlowGuceSiat findFlowGuceSiatByFlowGuce(String flowGuce);

    /**
     *
     * @param flowGuce
     * @param fileType
     * @return
     */
    FlowGuceSiat findFlowGuceSiatByFlowGuceAndFileType(final String flowGuce, final FileType fileType);

    List<FlowGuceSiat> findAllFlowGuceSiatsByFlowGuceAndFileType(final String flowGuce, final FileType fileType);

    /**
     * Find flow guce siat by flow siat.
     *
     * @param flowSiat the flow siat
     * @param fileTypeId the file type id
     * @return the flow guce siat
     */
    FlowGuceSiat findFlowGuceSiatByFlowSiatAndFileType(String flowSiat, Long fileTypeId);
    
    FlowGuceSiat findFlowGuceSiatFlowGuceAndFileType(final String flowGuce, final FileTypeCode fileType);
}
