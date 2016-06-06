package org.guce.siat.common.service;

import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;





/**
 * The Interface FileItemFieldValueService.
 */
public interface FileItemFieldValueService extends AbstractService<FileItemFieldValue>
{

	/**
	 * Find value by file item field and file item.
	 *
	 * @param FileItemFieldCode
	 *           the file item field code
	 * @param fileItem
	 *           the file item
	 * @return the file item field value
	 */
	FileItemFieldValue findValueByFileItemFieldAndFileItem(String FileItemFieldCode, FileItem fileItem);

}
