package org.guce.siat.common.dao;

import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;





/**
 * The Interface FileItemFieldValueDao.
 */
public interface FileItemFieldValueDao extends AbstractJpaDao<FileItemFieldValue>
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
