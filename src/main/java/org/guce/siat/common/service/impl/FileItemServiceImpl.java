/**
 *
 */
package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.dao.FileTypeStepDao;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.dao.UserAuthorityFileTypeDao;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthorityFileType;
import org.guce.siat.common.service.FileItemService;
import org.guce.siat.common.utils.SiatUtils;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.InformationSystemCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.guce.siat.common.utils.filter.RetrieveSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FileItemServiceImpl.
 */
@Service("fileItemService")
@Transactional(readOnly = true)
public class FileItemServiceImpl extends AbstractServiceImpl<FileItem> implements FileItemService {

    /**
     * The file item dao.
     */
    @Autowired
    private FileItemDao fileItemDao;

    /**
     * The item flow dao.
     */
    @Autowired
    private ItemFlowDao itemFlowDao;

    /**
     * The user authority file type dao.
     */
    @Autowired
    private UserAuthorityFileTypeDao userAuthorityFileTypeDao;

    /**
     * The file type step dao.
     */
    @Autowired
    private FileTypeStepDao fileTypeStepDao;

    /**
     * The Constant FILETYPE_CCT_CODE_LIST.
     */
    private static final List<FileTypeCode> FILETYPE_CCT_CODE_LIST = Arrays.asList(FileTypeCode.CCT_CT, FileTypeCode.CCT_CT_E,
            FileTypeCode.CC_CT, FileTypeCode.CQ_CT, FileTypeCode.CCT_CT_E_PVI, FileTypeCode.CCT_CT_E_ATP, FileTypeCode.CCT_CT_E_FSTP, FileTypeCode.CCT_CT_E_PVE);

    /**
     * The Constant FILETYPE_AP_CODE_LIST.
     */
    public static final List<FileTypeCode> FILETYPE_AP_CODE_LIST = Arrays.asList(FileTypeCode.AIE_MINADER,
            FileTypeCode.AE_MINADER, FileTypeCode.EH_MINADER, FileTypeCode.AS_MINADER, FileTypeCode.CAT_MINADER,
            FileTypeCode.PIVPSRP_MINADER, FileTypeCode.DI_MINADER, FileTypeCode.AT_MINSANTE, FileTypeCode.VTP_MINSANTE,
            FileTypeCode.VTD_MINSANTE, FileTypeCode.AI_MINSANTE, FileTypeCode.AI_MINMIDT, FileTypeCode.AE_MINMIDT,
            FileTypeCode.CEA_MINMIDT, FileTypeCode.AT_MINEPIA, FileTypeCode.VT_MINEPIA, FileTypeCode.VT_MINEPDED,
            FileTypeCode.CP_MINEPDED, FileTypeCode.AS_MINFOF, FileTypeCode.AS_MINCOMMERCE, FileTypeCode.CO_MINFOF_FORET,
            FileTypeCode.CO_MINFOF_FAUNE, FileTypeCode.BSBE_MINFOF, FileTypeCode.FIMEX_WF);

    /**
     * The Constant FILETYPE_CO_CODE_LIST.
     */
    private static final List<FileTypeCode> FILETYPE_CO_CODE_LIST = Arrays.asList(FileTypeCode.CO_GCO, FileTypeCode.CO_GFC);

    /**
     * The Constant FILETYPE_AM_CODE_LIST.
     */
    private static final List<FileTypeCode> FILETYPE_AM_CODE_LIST = Arrays.asList(FileTypeCode.AM_DOI, FileTypeCode.AM_DOE);

    /**
     * The Constant FILETYPE_FT_CODE_LIST.
     */
    private static final List<FileTypeCode> FILETYPE_FT_CODE_LIST = Arrays.asList(FileTypeCode.FT_DF, FileTypeCode.FT_LVI);

    /**
     * The Constant FILETYPE_SF_CODE_LIST.
     */
    private static final List<FileTypeCode> FILETYPE_SF_CODE_LIST = Arrays.asList(FileTypeCode.SF_GVE, FileTypeCode.SF_QF,
            FileTypeCode.SF_AF, FileTypeCode.SF_AR);

    /**
     * The Constant FILETYPE_CC_CODE_LIST.
     */
    private static final List<FileTypeCode> FILETYPE_CC_CODE_LIST = Arrays.asList(FileTypeCode.CC_DV, FileTypeCode.CC_DE,
            /* FileTypeCode.CC_BQ, */ FileTypeCode.CC_COCAC, FileTypeCode.CC_COCAF);

    /**
     * Instantiates a new file item service impl.
     */
    public FileItemServiceImpl() {
        super();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<FileItem> getJpaDao() {
        return fileItemDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<FileItem> jpaDao) {
        this.fileItemDao = (FileItemDao) jpaDao;
    }

    /**
     * Gets the item flow dao.
     *
     * @return the item flow dao
     */
    public ItemFlowDao getItemFlowDao() {
        return itemFlowDao;
    }

    /**
     * Sets the item flow dao.
     *
     * @param itemFlowDao the new item flow dao
     */
    public void setItemFlowDao(final ItemFlowDao itemFlowDao) {
        this.itemFlowDao = itemFlowDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.core.ct.service.FileItemService#findFileItemsByFileAndServiceAndAuthorities(org.guce.siat.core.ct
	 * .model.File, org.guce.siat.common.model.Service, java.util.Collection)
     */
    @Override
    public List<FileItem> filterFileItemsByUserAuthorityFileTypes(final List<FileItem> productInfoItems,
            final List<UserAuthorityFileType> listUserAuthorityFileTypes, final User loggedUser) {
        final List<FileItem> filtredItems = new ArrayList<>();

        for (final FileItem fileItem : productInfoItems) {

            for (final UserAuthorityFileType userAuthorityFileType : listUserAuthorityFileTypes) {

                final boolean userHasRoleOnFileItemStep = fileItem.getStep().getRoleList()
                        .contains(userAuthorityFileType.getUserAuthority().getAuthorityGranted());

                if (userHasRoleOnFileItemStep) {
                    filtredItems.add(fileItem);
                }
            }
        }
        return filtredItems;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FileItemService#findFileItemsByFile(org.guce.siat.core.ct.model.File)
     */
    @Override
    public List<FileItem> findFileItemsByFile(final File file) {
        final List<FileItem> fileItemList = fileItemDao.findFileItemsByFile(file);
        if (CollectionUtils.isNotEmpty(fileItemList)) {
            for (final FileItem fileItem : fileItemList) {

                //personnalisation des labels dans les fileItems par procedure
                FileTypeStep fileTypeStep;

                fileTypeStep = fileTypeStepDao
                        .findFileTypeStepByFileTypeAndStep(fileItem.getFile().getFileType(), fileItem.getStep());
                if (fileTypeStep != null && fileTypeStep.getLabelFr() != null) {
                    fileItem.setRedefinedLabelEn((fileTypeStep.getLabelEn()));
                    fileItem.setRedefinedLabelFr((fileTypeStep.getLabelFr()));
                }

            }
        }
        return fileItemList;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.guce.siat.common.service.FileItemService#findFileItemByServiceAndAuthoritiesAndTypeFileType(org.guce.siat
     * .common.model.Service, org.guce.siat.common.model.User, java.lang.String)
     */
    @Override
    public List<FileItem> findFileItemByServiceAndAuthoritiesAndFileType(final List<Bureau> bureaus, final User loggedUser,
            final InformationSystemCode informationSystemCode, final List<UserAuthorityFileType> listUserAuthorityFileTypes) {

        List<FileTypeCode> fileTypeCodeList = new ArrayList<>();
        final List<StepCode> excludedStepList = new ArrayList<>();
        List<FileItem> fileItemsWithoutDraft;

        if (null != informationSystemCode) {
            switch (informationSystemCode) {
                case CCT:
                    fileTypeCodeList = FILETYPE_CCT_CODE_LIST;
                    excludedStepList.add(StepCode.ST_CT_06);
                    break;
                case AP:
                    fileTypeCodeList = FILETYPE_AP_CODE_LIST;
                    excludedStepList.add(StepCode.ST_AP_44);
                    break;
                case CO:
                    fileTypeCodeList = FILETYPE_CO_CODE_LIST;
                    excludedStepList.add(StepCode.ST_CO_44);
                    break;
                case AM:
                    fileTypeCodeList = FILETYPE_AM_CODE_LIST;
                    excludedStepList.add(StepCode.ST_AM_44);
                    break;
                case FT:
                    fileTypeCodeList = FILETYPE_FT_CODE_LIST;
                    excludedStepList.add(StepCode.ST_FT_44);
                    break;
                case SF:
                    fileTypeCodeList = FILETYPE_SF_CODE_LIST;
                    excludedStepList.add(StepCode.ST_SF_44);
                    break;
                case CC:
                    fileTypeCodeList = FILETYPE_CC_CODE_LIST;
                    excludedStepList.add(StepCode.ST_CC_44);
                    break;
                case BQ:
                    fileTypeCodeList = Arrays.asList(FileTypeCode.CC_BQ);
                    excludedStepList.add(StepCode.ST_CC_44);
                    break;
                default:
                    break;
            }
        }
        fileItemsWithoutDraft = fileItemDao.findFileItemByServiceAndAuthoritiesAndFileType(bureaus, loggedUser, fileTypeCodeList,
                excludedStepList);

        final List<FileItem> returnFileItems = new ArrayList<>();

        for (final FileItem fileItem : fileItemsWithoutDraft) {
            final FileType fileType = fileItem.getFile().getFileType();
            final User assignedUser = fileItem.getFile().getAssignedUser();

            final List<Authority> assignedUserAuthorities = userAuthorityFileTypeDao.findAuthoritiesByFileTypeAndUser(fileType,
                    assignedUser);

            for (final UserAuthorityFileType userAuthorityFileType : listUserAuthorityFileTypes) {
                final boolean loggedUserHasAuthorityOnFileType = fileType.getId().equals(userAuthorityFileType.getFileType().getId());

                final boolean loggedUserHasRoleOnFileItemStep = fileItem.getStep().getRoleList()
                        .contains(userAuthorityFileType.getUserAuthority().getAuthorityGranted());

                final boolean assignedUserAllowed = assignedUser == null
                        // logged user == assigned user
                        || (assignedUser != null && SiatUtils.getUserIds(loggedUser.getMergedDelegatorList()).contains(
                        assignedUser.getId()))
                        // logged user != assigned user AND role of logged user != role of assigned user  AND l'aasignedUser have not an authority on step authorities
                        || (assignedUser != null
                        && !SiatUtils.getUserIds(loggedUser.getMergedDelegatorList()).contains(assignedUser.getId())
                        && !assignedUserAuthorities.contains(userAuthorityFileType.getUserAuthority().getAuthorityGranted()) && !CollectionUtils
                        .containsAny(assignedUserAuthorities, fileItem.getStep().getRoleList()));

                if (loggedUserHasAuthorityOnFileType && loggedUserHasRoleOnFileItemStep && assignedUserAllowed) {
                    returnFileItems.add(fileItem);
                    break;
                }
            }

            //personnalisation des labels dans les fileItems par procedure
            FileTypeStep fileTypeStep;

            fileTypeStep = fileTypeStepDao.findFileTypeStepByFileTypeAndStep(fileItem.getFile().getFileType(), fileItem.getStep());
            if (fileTypeStep != null) {
                fileItem.setRedefinedLabelEn((fileTypeStep.getLabelEn()));
                fileItem.setRedefinedLabelFr((fileTypeStep.getLabelFr()));
            } else {
                fileItem.setRedefinedLabelEn(fileItem.getStep().getLabelEn());
                fileItem.setRedefinedLabelFr(fileItem.getStep().getLabelFr());
            }

        }

        return returnFileItems;
    }

    /**
     * (non-Javadoc)
     *
     * @param bureaus
     * @param loggedUser
     * @param informationSystemCode
     * @param listUserAuthorityFileTypes
     * @return
     * @see
     * org.guce.siat.common.service.FileItemService#findFilesByServiceAndAuthoritiesAndFileType(java.util.List,
     * org.guce.siat.common.model.User,
     * org.guce.siat.common.utils.enums.InformationSystemCode, java.util.List)
     */
    @Override
    public List<File> findFilesByServiceAndAuthoritiesAndFileType(final List<Bureau> bureaus, final User loggedUser,
            final InformationSystemCode informationSystemCode, final List<UserAuthorityFileType> listUserAuthorityFileTypes) {

        List<FileTypeCode> fileTypeCodeList = new ArrayList<>();
        final List<StepCode> excludedStepList = new ArrayList<>();
        List<File> filesWithoutDraft;

        if (null != informationSystemCode) {
            switch (informationSystemCode) {
                case CCT:
                    fileTypeCodeList = FILETYPE_CCT_CODE_LIST;
                    excludedStepList.add(StepCode.ST_CT_06);
                    break;
                case AP:
                    fileTypeCodeList = FILETYPE_AP_CODE_LIST;
                    excludedStepList.add(StepCode.ST_AP_44);
                    break;
                case CO:
                    fileTypeCodeList = FILETYPE_CO_CODE_LIST;
                    excludedStepList.add(StepCode.ST_CO_44);
                    break;
                case AM:
                    fileTypeCodeList = FILETYPE_AM_CODE_LIST;
                    excludedStepList.add(StepCode.ST_AM_44);
                    break;
                case FT:
                    fileTypeCodeList = FILETYPE_FT_CODE_LIST;
                    excludedStepList.add(StepCode.ST_FT_44);
                    break;
                case SF:
                    fileTypeCodeList = FILETYPE_SF_CODE_LIST;
                    excludedStepList.add(StepCode.ST_SF_44);
                    break;
                case CC:
                    fileTypeCodeList = FILETYPE_CC_CODE_LIST;
                    excludedStepList.add(StepCode.ST_CC_44);
                    break;
                case BQ:
                    fileTypeCodeList = Arrays.asList(FileTypeCode.CC_BQ);
                    excludedStepList.add(StepCode.ST_CC_44);
                    break;
                default:
                    break;
            }
        }
        filesWithoutDraft = fileItemDao.findFilesByServiceAndAuthoritiesAndFileType(bureaus, loggedUser, fileTypeCodeList,
                excludedStepList);

        final List<File> returnFiles = new ArrayList<>();

        for (final File file : filesWithoutDraft) {
            final FileType fileType = file.getFileType();
            final User assignedUser = file.getAssignedUser();

            final List<Authority> assignedUserAuthorities = userAuthorityFileTypeDao.findAuthoritiesByFileTypeAndUser(fileType,
                    assignedUser);

            for (final UserAuthorityFileType userAuthorityFileType : listUserAuthorityFileTypes) {
                final boolean loggedUserHasAuthorityOnFileType = fileType.getId().equals(userAuthorityFileType.getFileType().getId());

                final boolean loggedUserHasRoleOnFileItemStep = file.getFileItemsList().get(0).getStep().getRoleList()
                        .contains(userAuthorityFileType.getUserAuthority().getAuthorityGranted());

                final boolean assignedUserAllowed = assignedUser == null
                        // logged user == assigned user
                        || (assignedUser != null && SiatUtils.getUserIds(loggedUser.getMergedDelegatorList()).contains(
                        assignedUser.getId()))
                        // logged user != assigned user AND role of logged user != role of assigned user  AND l'aasignedUser have not an authority on step authorities
                        || (assignedUser != null
                        && !SiatUtils.getUserIds(loggedUser.getMergedDelegatorList()).contains(assignedUser.getId())
                        && !assignedUserAuthorities.contains(userAuthorityFileType.getUserAuthority().getAuthorityGranted()) && !CollectionUtils
                        .containsAny(assignedUserAuthorities, file.getFileItemsList().get(0).getStep().getRoleList()));

                if (loggedUserHasAuthorityOnFileType && loggedUserHasRoleOnFileItemStep && assignedUserAllowed) {
                    returnFiles.add(file);
                    break;
                }
            }

        }

        return returnFiles;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.FileItemService#findFileItemFieldValueByFieldCode(org.guce.siat.core.ct.model.
	 * FileItem , java.lang.String)
     */
    @Override
    public FileItemFieldValue findFileItemFieldValueByFieldCode(final FileItem fileItem, final String fieldCode) {
        return fileItemDao.findFileItemFieldValueByFieldCode(fileItem, fieldCode);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileItemService#verifyCancelRequestInProgress(java.util.List)
     */
    @Override
    public Boolean verifyStepChangedWhileDecisionInProgress(final List<FileItem> fileItemList) {
        for (final FileItem fileItem : fileItemList) {
            final FileItem lastFileItemFromDB = fileItemDao.find(fileItem.getId());

            // The step of the current fileItem was changed by another user while the decision popup is opened
            if (!lastFileItemFromDB.getStep().getStepCode().equals(fileItem.getStep().getStepCode())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.CommonService#findFileItemForRetreiveByFilter(java.util.List,
	 * org.guce.siat.common.model.User, org.guce.siat.common.utils.enums.InformationSystemCode, java.util.List,
	 * org.guce.siat.core.ct.filter.RetrieveSearchFilter, org.guce.siat.common.utils.enums.StepCode)
     */
    @Override
    public List<FileItem> findFileItemForRetreiveByFilter(final List<Bureau> bureaus, final User loggedUser,
            final InformationSystemCode informationSystemCode, final List<UserAuthorityFileType> listUserAuthorityFileTypes,
            final RetrieveSearchFilter filter, final StepCode acceptationStep) {
        List<FileItem> fileItemsWithoutDraft;
        List<FileTypeCode> fileTypeCodeList = new ArrayList<>();

        if (filter.getFileType() == null) {
            if (null != informationSystemCode) {
                switch (informationSystemCode) {
                    case AP:
                        fileTypeCodeList.addAll(FILETYPE_AP_CODE_LIST);
                        break;
                    case CO:
                        fileTypeCodeList.addAll(FILETYPE_CO_CODE_LIST);
                        break;
                    case SF:
                        fileTypeCodeList.addAll(FILETYPE_SF_CODE_LIST);
                        break;
                    case CC:
                        fileTypeCodeList.addAll(FILETYPE_CC_CODE_LIST);
                        break;
                    default:
                        break;
                }
            }
        } else {
            fileTypeCodeList = new ArrayList<>();
            fileTypeCodeList.add(filter.getFileType().getCode());
        }

        fileItemsWithoutDraft = fileItemDao.findFileItemForRetreiveByFilter(bureaus, loggedUser, fileTypeCodeList, acceptationStep, filter);

        final List<FileItem> returnFileItems = new ArrayList<>();

        for (final FileItem fileItem : fileItemsWithoutDraft) {
            final FileType fileType = fileItem.getFile().getFileType();

            for (final UserAuthorityFileType userAuthorityFileType : listUserAuthorityFileTypes) {
                final boolean loggedUserHasAuthorityOnFileType = fileType.getId().equals(userAuthorityFileType.getFileType().getId());

                final boolean userHasRoleOnFileItemStep = fileItem.getStep().getRoleList()
                        .contains(userAuthorityFileType.getUserAuthority().getAuthorityGranted());

                if (loggedUserHasAuthorityOnFileType && userHasRoleOnFileItemStep) {
                    //personnalisation des labels dans les fileItems par procedure
                    FileTypeStep fileTypeStep;

                    fileTypeStep = fileTypeStepDao.findFileTypeStepByFileTypeAndStep(fileItem.getFile().getFileType(), fileItem.getStep());
                    if (fileTypeStep != null && fileTypeStep.getLabelFr() != null) {
                        fileItem.setRedefinedLabelEn((fileTypeStep.getLabelEn()));
                        fileItem.setRedefinedLabelFr((fileTypeStep.getLabelFr()));
                    }
                    returnFileItems.add(fileItem);
                    break;
                }
            }

        }
        return returnFileItems;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileItemService#findFileItemFieldValueByFieldCode(java.lang.Long,
	 * java.lang.Long)
     */
    @Override
    public FileItemFieldValue findFileItemFieldValueByFieldCode(final Long idFile, final Long idItemField) {
        return fileItemDao.findFileItemFieldValueByFieldCode(idFile, idItemField);

    }
}
