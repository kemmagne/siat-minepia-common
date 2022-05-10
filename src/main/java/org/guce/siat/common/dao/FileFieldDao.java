package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FileFieldDao.
 */
public interface FileFieldDao extends AbstractJpaDao<FileField> {

    /**
     * Find file field by code and file type.
     *
     * @param fileFieldCode the file field code
     * @param fileTypeCode the file type code
     * @return the file field
     */
    FileField findFileFieldByCodeAndFileType(String fileFieldCode, FileTypeCode fileTypeCode);
    
    /**
     * Find file field by file type.
     * @param fileTypeCode
     * @return 
     */
    List<FileField> findFileFieldByFileType(FileTypeCode fileTypeCode);
    

}
