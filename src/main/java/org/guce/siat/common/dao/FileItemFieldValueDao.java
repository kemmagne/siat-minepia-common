package org.guce.siat.common.dao;

import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemField;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FileItemFieldValueDao.
 */
public interface FileItemFieldValueDao extends AbstractJpaDao<FileItemFieldValue> {

    /**
     * Find value by file item field and file item.
     *
     * @param fileItemFieldCode the file item field code
     * @param fileItem the file item
     * @return the file item field value
     */
    FileItemFieldValue findValueByFileItemFieldAndFileItem(String fileItemFieldCode, FileItem fileItem);

    FileItemFieldValue findValueByFileItemFieldAndFile(String fileItemFieldCode, FileTypeCode fileTypeCode, String numeroDemande);

    FileItemField findByFileTypeAndCode(FileTypeCode fileTypeCode, String code);

}
