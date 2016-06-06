/*
 *
 */
package org.guce.siat.common.dao;

import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlow;
import org.guce.siat.common.model.Flow;




/**
 * The Interface FlowDao.
 */
public interface FileTypeFlowDao extends AbstractJpaDao<FileTypeFlow>
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
