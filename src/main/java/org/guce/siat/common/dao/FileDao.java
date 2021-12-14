package org.guce.siat.common.dao;

import java.util.List;
import java.util.Map;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Company;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FileDao.
 */
public interface FileDao extends AbstractJpaDao<File> {

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
     * @param fileTypeCodes the file type codes
     * @return the file
     */
    File quickSearch(String documentNumberFilter, Administration administration, List<FileTypeCode> fileTypeCodes);

    public File quickSearch(final String documentNumberFilter, final List<Administration> administrations, final List<FileTypeCode> fileTypeCodes);

    /**
     * Find by ref siat.
     *
     * @param refSiat the ref siat
     * @return the file
     */
    File findByRefSiat(String refSiat);

    /**
     * Find by num dossier guce.
     *
     * @param refGuce the ref guce
     * @return the file
     */
    File findByNumDossierGuce(String refGuce);

    List<File> findByNumeroDossierBase(final String numeroDossierBase);

    /**
     * Find matching between manifest and prior notice.
     *
     * @param client the client
     * @param fileTypeCode the file type code
     * @param fileFieldValue1 the file field value1
     * @param fileFieldValue2 the file field value2
     * @param fileFieldCode1 the file field code1
     * @param fileFieldCode2 the file field code2
     * @return the file
     */
    File findMatchingBetweenManifestAndPriorNotice(Company client, List<FileTypeCode> fileTypeCode,
            FileFieldValue fileFieldValue1, FileFieldValue fileFieldValue2, String fileFieldCode1, String fileFieldCode2);

    /**
     * Find draft files by user.
     *
     * @param user the user
     * @return the list
     */
    List<File> findDraftFilesByUsr(User user);

    /**
     * Find files by file type code.
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
     * Find by num contribuable.
     *
     * @param numContribuable the num contribuable
     * @return the file
     */
    File findByNumContribuable(String numContribuable);

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
    void updateSpecificColumn(Map<String, ?> paramsMap, File file);

    List<File> findByNumeroDemandeAndBureau(String currentFileNumber, String numeroDemande, Bureau bureau, Step cotationStep);

    List<File> findByNumeroDemandeAndFileType(String numeroDemande, FileType fileType);

    List<File> findByNumeroDemandeAndFileType(String numeroDemande, FileTypeCode fileTypeCode);

    List<File> findByNumeroDemande(String numeroDemande);

    List<File> findByNumeroDemandeAndFileTypeWithoutParent(String numeroDemande, FileType fileType);

}
