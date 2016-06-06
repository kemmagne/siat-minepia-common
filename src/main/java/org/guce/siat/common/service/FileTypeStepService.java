package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.utils.enums.FileTypeCode;




/**
 * The Interface FileTypeStepService.
 */
public interface FileTypeStepService extends AbstractService<FileTypeStep>
{

	/**
	 * Find all step by file types.
	 *
	 * @param fileTypes
	 *           the file types
	 * @return the list
	 */
	List<Step> findAllStepByFileTypes(List<FileTypeCode> fileTypes);

	/**
	 * Find file type step by file type and step.
	 *
	 * @param fileType
	 *           the file type
	 * @param step
	 *           the step
	 * @return the file type step
	 */
	FileTypeStep findFileTypeStepByFileTypeAndStep(FileType fileType, Step step);

	/**
	 * Find step by file type and label.
	 *
	 * @param fileType
	 *           the file type
	 * @param value
	 *           the value
	 * @return the step
	 */
	Step findStepByFileTypeAndLabel(FileType fileType, String value);
}
