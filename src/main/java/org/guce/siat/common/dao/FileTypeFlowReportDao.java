package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeFlowReport;
import org.guce.siat.common.model.Flow;




/**
 * The Interface FileTypeFlowReportDao.
 */
public interface FileTypeFlowReportDao extends AbstractJpaDao<FileTypeFlowReport>
{



	/**
	 * Find report class name by flow and file type.
	 *
	 * @param flow
	 *           the flow
	 * @param fileType
	 *           the file type
	 * @return the list
	 */
	List<FileTypeFlowReport> findReportClassNameByFlowAndFileType(Flow flow, FileType fileType);
}
