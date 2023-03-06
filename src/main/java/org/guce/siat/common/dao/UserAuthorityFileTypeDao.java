package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.model.UserAuthorityFileType;

/**
 * The Interface UserAuthorityFileTypeDao.
 */
public interface UserAuthorityFileTypeDao extends AbstractJpaDao<UserAuthorityFileType> {

    /**
     * Gets the file type and authority by user.
     *
     * @param usr the usr
     * @return the file type and authority by user
     */
    List<UserAuthorityFileType> getFileTypeAndAuthorityByUser(final User usr);

    /**
     * Find user authority file type by file type and user list.
     *
     * @param fileType the file type
     * @param userList the user list
     * @return the list
     */
    List<UserAuthorityFileType> findUserAuthorityFileTypeByFileTypeAndUserList(FileType fileType, List<User> userList);

    /**
     * Find authorities by file type and user.
     *
     * @param fileType the file type
     * @param user the user
     * @return the list
     */
    List<Authority> findAuthoritiesByFileTypeAndUser(FileType fileType, User user);

    /**
     * Find user authority file type by user list.
     *
     * @param usersList the users list
     * @return the list
     */
    List<UserAuthorityFileType> findUserAuthorityFileTypeByUserList(List<User> usersList);

    /**
     * Find user by file type and step authorities.
     *
     * @param fileType the file type
     * @param toStep the to step
     * @param isStepApDecesion the is step ap decesion
     * @return the list
     */
    List<UserAuthorityFileType> findUserByFileTypeAndStepAuthorities(FileType fileType, Step toStep, Boolean isStepApDecesion);

    UserAuthorityFileType findByCurrentStepFileAndLoggedUser(Step currentStep, File currentFile, User loggedUser);

    /**
     * Find files types by authorized user.
     *
     * @param user the user
     * @return the list
     */
    List<FileType> findFilesTypesByAuthorizedUser(User user);

    /**
     * Removes the used authorities.
     *
     * @param userAuthoritiesIds the user authorities ids
     */
    void removeUsedAuthritiesFileTypeByUserAuthorities(final List<Long> userAuthoritiesIds);
}
