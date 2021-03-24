package org.guce.siat.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.guce.siat.common.dao.AbstractJpaDao;
import org.guce.siat.common.dao.FileTypeStepDao;
import org.guce.siat.common.dao.UserAuthorityDao;
import org.guce.siat.common.dao.UserAuthorityFileTypeDao;
import org.guce.siat.common.dao.UserDao;
import org.guce.siat.common.dao.exception.DAOException;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileAdministration;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.model.UserAuthorityFileType;
import org.guce.siat.common.service.UserAuthorityFileTypeService;
import org.guce.siat.common.service.exception.BusinessException;
import org.guce.siat.common.service.exception.BusinessExceptionSeverity;
import org.guce.siat.common.utils.enums.BureauType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class UserAuthorityFileTypeServiceImpl.
 */
@Service("userAuthorityFileTypeService")
@Transactional(readOnly = true)
public class UserAuthorityFileTypeServiceImpl extends
        AbstractServiceImpl<UserAuthorityFileType> implements
        UserAuthorityFileTypeService {

    /**
     * The authority file type dao.
     */
    @Autowired
    private UserAuthorityFileTypeDao userAuthorityFileTypeDao;

    /**
     * The user authority dao.
     */
    @Autowired
    private UserAuthorityDao userAuthorityDao;

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * The file type step dao.
     */
    @Autowired
    FileTypeStepDao fileTypeStepDao;

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
     */
    @Override
    public AbstractJpaDao<UserAuthorityFileType> getJpaDao() {
        return userAuthorityFileTypeDao;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce
	 * .siat.common.dao.AbstractJpaDao)
     */
    @Override
    public void setJpaDao(final AbstractJpaDao<UserAuthorityFileType> jpaDao) {
        this.userAuthorityFileTypeDao = (UserAuthorityFileTypeDao) jpaDao;

    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * getFileTypeAndAuthorityByUser(org.guce.siat.common. model.User)
     */
    @Override
    public List<UserAuthorityFileType> getFileTypeAndAuthorityByUser(
            final User usr) {
        try {
            return userAuthorityFileTypeDao.getFileTypeAndAuthorityByUser(usr);
        } catch (final DAOException e) {
            throw new BusinessException(e,
                    BusinessExceptionSeverity.SEVERITY_ERROR);
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * updateListUserAuthorityFileType(org.guce.siat.common .model.User,
	 * java.util.List)
     */
    @Override
    public void updateListUserAuthorityFileType(final User user,
            final List<UserAuthorityFileType> userAuthFileTypes) {
        try {
            final List<UserAuthorityFileType> authorityFileTypes = userAuthorityFileTypeDao
                    .getFileTypeAndAuthorityByUser(user);
            final List<UserAuthority> userAuthorities = new ArrayList<UserAuthority>();
            userDao.updateUser(user);
            if (CollectionUtils.isNotEmpty(authorityFileTypes)) {
                for (final UserAuthorityFileType userAuthorityFileType : authorityFileTypes) {
                    userAuthorityFileTypeDao.delete(userAuthorityFileType);
                }
            }
            if (CollectionUtils.isNotEmpty(userAuthFileTypes)) {
                for (final UserAuthorityFileType userAuthorityFileType : userAuthFileTypes) {

                    final UserAuthority userAuthority = userAuthorityDao
                            .findByUserAndAuthoriy(user, userAuthorityFileType
                                    .getUserAuthority().getAuthorityGranted());
                    if (userAuthority != null) {
                        userAuthorityFileType.setUserAuthority(userAuthority);
                        userAuthorities.add(userAuthority);
                    } else {
                        userAuthorityDao.save(userAuthorityFileType
                                .getUserAuthority());
                        userAuthorities.add(userAuthorityFileType
                                .getUserAuthority());
                    }
                    userAuthorityFileTypeDao.save(userAuthorityFileType);
                }
                userAuthorityDao.removeUnusedAuthrities(user, userAuthorities);
            }
        } catch (final DAOException e) {
            throw new BusinessException(e,
                    BusinessExceptionSeverity.SEVERITY_ERROR);
        }

    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * saveListUserAuthorityFileType(java.util.List)
     */
    @Override
    public void saveListUserAuthorityFileType(
            final List<UserAuthorityFileType> userAuthorityFileTypes,
            final User user) {
        try {
            final List<UserAuthority> userAuthorities = user
                    .getUserAuthorityList();
            userDao.createUser(user);
            for (final UserAuthority userAuthority : userAuthorities) {
                userAuthorityDao.save(userAuthority);
            }
            if (CollectionUtils.isNotEmpty(userAuthorityFileTypes)) {
                for (final UserAuthorityFileType userAuthorityFileType : userAuthorityFileTypes) {
                    final UserAuthority userAuthority = userAuthorityDao
                            .findByUserAndAuthoriy(user, userAuthorityFileType
                                    .getUserAuthority().getAuthorityGranted());
                    if (userAuthority != null) {
                        userAuthorityFileType.setUserAuthority(userAuthority);
                    }
                    userAuthorityFileTypeDao.save(userAuthorityFileType);
                }

            }
        } catch (final DAOException e) {
            throw new BusinessException(e,
                    BusinessExceptionSeverity.SEVERITY_ERROR);
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserAuthorityFileTypeService#
	 * findUserAuthorityFileTypeByFileTypeAndUserList(org.guce
	 * .siat.common.model.FileType, java.util.List)
     */
    @Override
    public List<UserAuthorityFileType> findUserAuthorityFileTypeByFileTypeAndUserList(
            final FileType fileType, final List<User> userList) {
        return userAuthorityFileTypeDao
                .findUserAuthorityFileTypeByFileTypeAndUserList(fileType,
                        userList);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserAuthorityFileTypeService#
	 * findAuthoritiesByFileTypeAndUser(org.guce.siat.common .model.FileType,
	 * org.guce.siat.common.model.User)
     */
    @Override
    public List<Authority> findAuthoritiesByFileTypeAndUser(
            final FileType fileType, final User user) {
        return userAuthorityFileTypeDao.findAuthoritiesByFileTypeAndUser(
                fileType, user);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserAuthorityFileTypeService#
	 * findUserAuthorityFileTypeByUserList(java.util.List)
     */
    @Override
    public List<UserAuthorityFileType> findUserAuthorityFileTypeByUserList(
            final List<User> usersList) {
        return userAuthorityFileTypeDao
                .findUserAuthorityFileTypeByUserList(usersList);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * findUserByFileTypeAndStepAuthorities(org.guce.siat.
	 * core.ct.model.FileType, org.guce.siat.core.ct.model.Step,
	 * org.guce.siat.core.ct.model.File)
     */
    @Override
    public List<User> findUserByFileTypeAndStepAuthorities(
            final FileType fileType, final Step toStep, final File file) {
        return this.findUserByFileTypeAndStepAuthorities(fileType, toStep, file, null);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.core.ct.service.UserAuthorityFileTypeService#
	 * findUserByFileTypeAndStepAuthorities(org.guce.siat.
	 * core.ct.model.FileType, org.guce.siat.core.ct.model.Step,
	 * org.guce.siat.core.ct.model.File)
     */
    @Override
    public List<User> findUserByFileTypeAndStepAuthorities(final FileType fileType, final Step toStep, final File file, final User loggedUser) {
        final List<User> returnedList = new ArrayList<>();

        final List<User> userByFtStAut = new ArrayList<>();

        final Boolean isStepApDecision = fileTypeStepDao.isApDecisionByFileTypeAndStep(fileType, toStep);

        List<UserAuthorityFileType> userAuthorities = userAuthorityFileTypeDao.findUserByFileTypeAndStepAuthorities(fileType, toStep, isStepApDecision);

        for (final UserAuthorityFileType authorityFileType : userAuthorities) {
            userByFtStAut.add(authorityFileType.getUserAuthority().getUser());
        }

        for (final User user : userByFtStAut) {
            List<Administration> administrations = Arrays.asList(new Administration[]{user.getAdministration(), user.getAdministrationExtendRoles()});
            for (Administration adm : administrations) {
                if (adm instanceof Ministry) {
                    if (adm.getId().equals(file.getBureau().getService().getSubDepartment().getOrganism().getMinistry().getId())) {
                        returnedList.add(user);
                    } else if (userInCandidateAdministration(user, file, loggedUser)) {
                        returnedList.add(user);
                    }
                } else if (adm instanceof Organism) {
                    if (adm.getId().equals(file.getBureau().getService().getSubDepartment().getOrganism().getId())) {
                        returnedList.add(user);
                    } else if (userInCandidateAdministration(user, file, loggedUser)) {
                        returnedList.add(user);
                    }
                } else if (adm instanceof SubDepartment) {
                    if (adm.getId().equals(file.getBureau().getService().getSubDepartment().getId())) {
                        returnedList.add(user);
                    } else if (userInCandidateAdministration(user, file, loggedUser)) {
                        returnedList.add(user);
                    }
                } else if (adm instanceof org.guce.siat.common.model.Service) {
                    if (adm.getId().equals(file.getBureau().getService().getId())) {
                        returnedList.add(user);
                    } else if (userInCandidateAdministration(user, file, loggedUser)) {
                        returnedList.add(user);
                    }
                } else if (adm instanceof Bureau
                        && (((Bureau) adm).getService().getId().equals(file.getBureau().getService().getId())
                        && (((Bureau) adm).getBureauType().equals(BureauType.BUREAU_CENTRAL)) || ((((Bureau) user.getAdministration()).getId().equals(file.getBureau().getId())))
                        && ((Bureau) adm).getBureauType().equals(BureauType.BUREAU_REGIONAL))) {
                    returnedList.add(user);
                }
            }
        }

        return returnedList;

    }

    @Override
    public UserAuthorityFileType findByCurrentStepFileAndLoggedUser(Step currentStep, File currentFile, User loggedUser) {
        return userAuthorityFileTypeDao.findByCurrentStepFileAndLoggedUser(currentStep, currentFile, loggedUser);
    }

    private boolean userInCandidateAdministration(User user, File file, User loggedUser) {
        boolean res = false;
        List<FileAdministration> list = file.getFileAdministrationsList();
        if (list != null && !list.isEmpty()) {
            List<Administration> admList = new ArrayList<>();
            for (FileAdministration fa : list) {
                admList.add(fa.getAdministration());
            }
            List<Administration> administrations = getRecursiveParentAdministations(admList, user.getAdministration().getClass());
            /*
			//recherche les administrations de même type que ceux de User mais faisant partir de l'arborescence du bureau actuellement affecté au dossier
			List<? extends Administration> fileAdminList = new ArrayList<Administration>();
			if (user.getAdministration() instanceof Ministry) {
			} else if (user.getAdministration() instanceof Organism) {
			fileAdminList = file.getBureau().getService().getSubDepartment().getOrganism().getMinistry().getOrganismsList();
			} else if (user.getAdministration() instanceof SubDepartment) {
			fileAdminList = file.getBureau().getService().getSubDepartment().getOrganism().getSubDepartmentsList();
			} else if (user.getAdministration() instanceof org.guce.siat.common.model.Service) {
			fileAdminList = file.getBureau().getService().getSubDepartment().getServicesList();
			} else if (user.getAdministration() instanceof Bureau) {
			fileAdminList = file.getBureau().getService().getEntityList();
			}*/
            //On recherche les administrations qui sons succeptible de traiter le dossier actuel.
            //En principe, si assignedUser est indiqué, on prend toutes les administration qui sont sous lui
            //On prend les administrations qui sont sous l'utilisateur actuellement connecté
            List<Administration> fileAdminList = administrations;
            if (file.getAssignedUser() != null || loggedUser != null) {
                List<Administration> listAdm = new ArrayList<>();
                if (file.getAssignedUser() != null) {
                    listAdm.add(file.getAssignedUser().getAdministration());
                }
                if (loggedUser != null) {
                    listAdm.add(loggedUser.getAdministration());
                }
                fileAdminList = getRecursiveSubAdministations(listAdm);
            }
            //filtre les administrations pour ne garder que ceux qui matchent la liste
            List<Administration> filteredAdms = new ArrayList<>();
            for (Administration a : administrations) {
                if (fileAdminList.contains(a)) {
                    filteredAdms.add(a);
                }
            }
            res = filteredAdms.contains(user.getAdministration());
        }
        return res;
    }

    /**
     * Gets the recursive parent administations of type specified.
     *
     * @param administrations the administrations
     * @param expectedType the expected administration type
     * @return the recursive administations
     */
    public static List<Administration> getRecursiveParentAdministations(List<? extends Administration> administrations, Class<? extends Administration> expectedType) {

        List<Administration> administrationsList = new ArrayList<>();

        for (final Administration adm : administrations) {
            if (adm.getClass().equals(expectedType)) {
                administrationsList.add(adm);
            } else {
                if (adm.getClass().equals(Organism.class)) {
                    administrationsList.addAll(getRecursiveParentAdministations(Arrays.asList(((Organism) adm).getMinistry()), expectedType));
                } else if (adm.getClass().equals(SubDepartment.class)) {
                    administrationsList.addAll(getRecursiveParentAdministations(Arrays.asList(((SubDepartment) adm).getOrganism()), expectedType));
                } else if (adm.getClass().equals(org.guce.siat.common.model.Service.class)) {
                    administrationsList.addAll(getRecursiveParentAdministations(Arrays.asList(((org.guce.siat.common.model.Service) adm).getSubDepartment()), expectedType));
                } else if (adm.getClass().equals(Bureau.class)) {
                    administrationsList.addAll(getRecursiveParentAdministations(Arrays.asList(((Bureau) adm).getService()), expectedType));
                }
            }
        }
        return administrationsList;
    }

    /**
     * Gets the recursive sub administations.
     *
     * @param administrations the administrations
     * @return the recursive sub administations
     */
    public static List<Administration> getRecursiveSubAdministations(List<? extends Administration> administrations) {
        List<Administration> subAdministrationsList = new ArrayList<>();
        for (final Object administration : administrations) {
            subAdministrationsList.add((Administration) administration);
            switch (administration.getClass().getSimpleName()) {
                case "Ministry":
                    subAdministrationsList.addAll(getRecursiveSubAdministations(((Ministry) administration).getOrganismsList()));
                    break;
                case "Organism":
                    subAdministrationsList.addAll(getRecursiveSubAdministations(((Organism) administration).getSubDepartmentsList()));
                    break;
                case "SubDepartment":
                    subAdministrationsList.addAll(getRecursiveSubAdministations(((SubDepartment) administration).getServicesList()));
                    break;
                case "Service":
                    subAdministrationsList.addAll(getRecursiveSubAdministations(((org.guce.siat.common.model.Service) administration).getEntityList()));
                    break;
                default:
                    break;
            }
        }
        return subAdministrationsList;
    }
}
