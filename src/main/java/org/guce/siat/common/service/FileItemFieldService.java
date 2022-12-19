package org.guce.siat.common.service;

import java.util.List;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileItemField;
import org.guce.siat.common.utils.enums.FileTypeCode;



/**
 * The Interface FileFieldService.
 */
public interface FileItemFieldService extends AbstractService<FileItemField>
{

	/**
	 * Find file field by code and file type.
	 *
	 * @param fileItemFieldCode the file field code
	 * @param fileTypeCode the file type code
	 * @return the file field
	 */
	FileItemField findFileItemFieldByCodeAndFileType(String fileItemFieldCode, FileTypeCode fileTypeCode);
	
        /**
         *
         * @param fileTypeCode
         * @return 
         */
        List<FileItemField> findFileItemFieldByFileType(FileTypeCode fileTypeCode);
}
