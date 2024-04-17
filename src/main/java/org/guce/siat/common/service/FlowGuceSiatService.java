package org.guce.siat.common.service;

import org.guce.siat.common.model.FlowGuceSiat;
import org.guce.siat.common.utils.enums.FileTypeCode;


/**
 * The Interface CountryService.
 */
public interface FlowGuceSiatService extends AbstractService<FlowGuceSiat>
{
	/**
	 * Find bureau by type and organism.
	 *
	 * @param flowGuce
	 *           the flow guce
	 * @return the list
	 */
	FlowGuceSiat findFlowGuceSiatByFlowGuce(String flowGuce);


	/**
	 * Find flow guce siat by flow siat.
	 *
	 * @param flowSiat
	 *           the flow siat
	 * @param fileTypeId
	 *           the file type id
	 * @return the flow guce siat
	 */
	FlowGuceSiat findFlowGuceSiatByFlowSiatAndFileType(String flowSiat, Long fileTypeId);
        
        
	/**
	 * Find flow-guce-siat by flow guce and file type .
	 *
	 * @param flowGuce
	 *           the flow guce
	 * @param fileType
	 *           the file type code
	 * @return FlowGuceSiat
	 */
	FlowGuceSiat findFlowGuceSiatFlowGuceAndFileType(final String flowGuce, final FileTypeCode fileType);
}
