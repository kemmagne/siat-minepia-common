package org.guce.siat.common.service;

import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;




/**
 * The Interface FileFieldValueService.
 */
public interface FileFieldValueService extends AbstractService<FileFieldValue>
{


	/**
	 * Find field by code.
	 *
	 * @param code
	 *           the code
	 * @return the file field
	 */
	FileField findFieldByCode(String code);

	/**
	 * Find file item field value by code and file item.
	 *
	 * @param code
	 *           the code
	 * @param fileItem
	 *           the file item
	 * @return the file item field value
	 */
	FileItemFieldValue findFileItemFieldValueByCodeAndFileItem(String code, FileItem fileItem);


	/**
	 * Find value by file field and file.
	 *
	 * @param FileFieldCode
	 *           the file field code
	 * @param file
	 *           the file
	 * @return the file field value
	 */
	FileFieldValue findValueByFileFieldAndFile(String FileFieldCode, File file);


}
