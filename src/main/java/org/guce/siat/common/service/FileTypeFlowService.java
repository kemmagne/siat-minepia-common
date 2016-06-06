package org.guce.siat.common.service;

import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlow;
import org.guce.siat.common.model.Flow;



/**
 * The Interface FlowService.
 */
public interface FileTypeFlowService extends AbstractService<FileTypeFlow>
{

	/**
	 * Find by flow and file type.
	 *
	 * @param fileType
	 *           the file type
	 * @param flow
	 *           the flow
	 * @return the file type flow
	 */
	FileTypeFlow findByFlowAndFileType(FileType fileType, Flow flow);
}
