package org.guce.siat.common.dao;

import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.utils.enums.FileTypeCode;





/**
 * The Interface FileFieldValueDao.
 */
public interface FileFieldValueDao extends AbstractJpaDao<FileFieldValue>
{

	/**
	 * Retrive file from file field code.
	 *
	 * @param fileFieldCode
	 *           the file field code
	 * @param value
	 *           the value
	 * @param fileTypeCode
	 *           the file type code
	 * @return the file
	 */
	File retriveFileByFileFieldCodeAndValue(String fileFieldCode, String value, FileTypeCode fileTypeCode);

	/**
	 * Find value by file field and file.
	 *
	 * @param fileFieldCode
	 *           the file field code
	 * @param file
	 *           the file
	 * @return the file field value
	 */
	FileFieldValue findValueByFileFieldAndFile(String fileFieldCode, File file);

	/**
	 * Find field by code.
	 *
	 * @param fieldCode
	 *           the field code
	 * @return the file field
	 */
	FileField findFieldByCode(String fieldCode);


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

}
