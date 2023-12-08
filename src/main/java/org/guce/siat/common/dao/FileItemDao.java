package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.guce.siat.common.utils.filter.RetrieveSearchFilter;

/**
 * The Interface FileItemDao.
 */
public interface FileItemDao extends AbstractJpaDao<FileItem> {

    /**
     * Find file items by file.
     *
     * @param file the file
     * @return the list
     */
    List<FileItem> findFileItemsByFile(File file);

    /**
     * Find draft file items by file.
     *
     * @param file the file
     * @return the list
     */
    List<FileItem> findDraftFileItemsByFile(File file);

    /**
     * Find file item by service and authorities and file type.
     *
     * @param bureaus the bureaus
     * @param loggedUser the logged user
     * @param fileTypeCodeList the file type code list
     * @param excludedStepList the excluded step list
     * @return the list
     */
    List<FileItem> findFileItemByServiceAndAuthoritiesAndFileType(List<Bureau> bureaus, User loggedUser,
            List<FileTypeCode> fileTypeCodeList, List<StepCode> excludedStepList);

    /**
     * Find files by service and authorities and file type.
     *
     * @param bureauList
     * @param loggedUser
     * @param fileTypeCodeList
     * @param excludedStepList
     * @return
     */
    List<File> findFilesByServiceAndAuthoritiesAndFileType(List<Bureau> bureauList, User loggedUser,
            List<FileTypeCode> fileTypeCodeList, List<StepCode> excludedStepList);
    
    /**
     * Find files by service and authorities and file type.
     *
     * @param bureauList
     * @param loggedUser
     * @param fileTypeCodeList
     * @param excludedStepList
     * @return
     */
    List<File> findFilesByServiceAndAuthoritiesAndFileTypeUsingFile(List<Bureau> bureauList, User loggedUser,
            List<FileTypeCode> fileTypeCodeList, List<StepCode> excludedStepList);
    
    /**
     * Find files by service and authorities and file type.
     *
     * @param bureauList
     * @param loggedUser
     * @param fileTypeIdList
     * @param excludedStepList
     * @return
     */
    List<File> findFilesByServiceAndAuthoritiesAndFileType2(List<Bureau> bureauList, User loggedUser,
            List<Long> fileTypeIdList, List<StepCode> excludedStepList);

    /**
     * Find file item field value by field code.
     *
     * @param fileItem the file item
     * @param fieldCode the field code
     * @return the file item field value
     */
    FileItemFieldValue findFileItemFieldValueByFieldCode(FileItem fileItem, String fieldCode);

    /**
     * Find file item by num ebms msg.
     *
     * @param numEbmsMsg the num ebms msg
     * @return the list
     */
    List<FileItem> findFileItemByNumEbmsMsg(String numEbmsMsg);

    /**
     * Find file item for retreive by filter.
     *
     * @param bureaus the bureaus
     * @param loggedUser the logged user
     * @param fileTypeCodeList the file type code list
     * @param stepApAcceptation the step ap acceptation
     * @param filter the filter
     * @return the list
     */
    List<FileItem> findFileItemForRetreiveByFilter(List<Bureau> bureaus, User loggedUser, List<FileTypeCode> fileTypeCodeList,
            StepCode stepApAcceptation, RetrieveSearchFilter filter);

    /**
     * Find file item field value by field code.
     *
     * @param idFile the id file
     * @param idItemField
     * @return the file item field value
     */
    FileItemFieldValue findFileItemFieldValueByFieldCode(Long idFile, Long idItemField);

    /**
     * Find by line number and num siat.
     *
     * @param lineNumberList the line number list
     * @param refSiat the ref siat
     * @return the file item
     */
    List<FileItem> findByLineNumberAndNumSiat(List<Integer> lineNumberList, String refSiat);

}

