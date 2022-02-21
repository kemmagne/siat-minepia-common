package org.guce.siat.common.service;

import java.util.List;
import java.util.Map;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FileService.
 */
public interface FileService extends AbstractService<File> {

    /**
     * Find file item field value by field code.
     *
     * @param file the file
     * @param fieldCode the field code
     * @return the file item field value
     */
    FileFieldValue findFileFieldValueByFieldCode(File file, String fieldCode);

    /**
     * Quick search.
     *
     * @param documentNumberFilter the document number filter
     * @param administration the administration
     * @param loggedUser the logged user
     * @return the file
     */
    File quickSearch(final String documentNumberFilter, final Administration administration, User loggedUser);

    /**
     * Find matching between manifest and prior notice.
     *
     * @param client the client
     * @param fileTypeCodes the file type codes
     * @param fileFieldValue1 the file field value1
     * @param fileFieldValue2 the file field value2
     * @param fileFieldCode1 the file field code1
     * @param fileFieldCode2 the file field code2
     * @return the file
     */
    File findMatchingBetweenManifestAndPriorNotice(final Company client, List<FileTypeCode> fileTypeCodes,
            final FileFieldValue fileFieldValue1, final FileFieldValue fileFieldValue2, String fileFieldCode1, String fileFieldCode2);

    /**
     * Find draft files by usr.
     *
     * @param user the user
     * @return the list
     */
    List<File> findDraftFilesByUsr(User user);

    /**
     * Find fimex filesby bureaux.
     *
     * @param fileTypeCode the file type code
     * @return the list
     */
    List<File> findFilesByFileTypeCode(FileTypeCode fileTypeCode);

    /**
     * Find count by file type.
     *
     * @param fileTypeCode the file type code
     * @return the integer
     */
    Long findCountByFileType(FileTypeCode fileTypeCode);

    /**
     * Find matching between fimex and prior notice.
     *
     * @param numContribuable the num contribuable
     * @param fileTypeCodeList the file type code list
     * @return the file
     */
    File findMatchingBetweenFimexAndPriorNotice(String numContribuable, List<FileTypeCode> fileTypeCodeList);

    /**
     * Find file if any, corresponding the following parameters.
     *
     * @param numeroDemande the NUMERO_DEMANDE from GUCE
     * @param fileTypeGuce the file type code
     * @return the file
     */
    File findByNumeroDemandeAndFileTypeGuce(String numeroDemande, String fileTypeGuce);

    /**
     * Update specific column.
     *
     * @param paramsMap the params map
     * @param file the file
     */
    void updateSpecificColumn(final Map<String, ?> paramsMap, final File file);

    List<File> findByNumeroDemandeAndBureau(File currentFile, Step treatmentStep);

    List<File> findByNumeroDemandeAndFileType(String numeroDemande, FileType fileType);

    List<File> findByNumeroDemandeAndFileTypeWithoutParent(String numeroDemande, FileType fileType);

    List<File> findByNumeroDemandeAndFileType(String numeroDemande, FileTypeCode fileTypeCode);

    /**
     * Find by num dossier guce.
     *
     * @param refGuce the ref guce
     * @return the file
     */
    File findByNumDossierGuce(String refGuce);

    /**
     * Find by ref siat.
     *
     * @param refSiat the ref siat
     * @return the file
     */
    File findByRefSiat(String refSiat);

    /**
     * Find by ref siat.
     *
     * @param fileFieldCode the ref siat
     * @param fileTypeCode the ref siat
     * @return the list of file
     */
    List<File> findFileByFileFieldCode(String fileFieldCode , String fileTypeCode);
}
