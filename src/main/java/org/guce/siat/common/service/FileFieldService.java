package org.guce.siat.common.service;

import java.util.List;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.utils.enums.FileTypeCode;



/**
 * The Interface FileFieldService.
 */
public interface FileFieldService extends AbstractService<FileField>
{

	/**
	 * Find file field by code and file type.
	 *
	 * @param fileFieldCode the file field code
	 * @param fileTypeCode the file type code
	 * @return the file field
	 */
	FileField findFileFieldByCodeAndFileType(String fileFieldCode, FileTypeCode fileTypeCode);
        
        /**
         * 
         * @param fileTypeCode
         * @return 
         */
        List<FileField> findFileFieldByFileType(FileTypeCode fileTypeCode);
}
