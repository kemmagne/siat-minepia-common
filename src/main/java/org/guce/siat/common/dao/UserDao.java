package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * The Interface UserDao.
 */
public interface UserDao extends AbstractJpaDao<User> {

    /**
     * Load user by username.
     *
     * @param username the username
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Creates the user.
     *
     * @param user the user
     */
    void createUser(User user);

    /**
     * Update user.
     *
     * @param user the user
     */
    void updateUser(User user);

    /**
     * Gets the user by login.
     *
     * @param username the username
     * @return the user by login
     */
    User getUserByLogin(String username);

    /**
     * Find by mail.
     *
     * @param email the email
     * @return the user
     */
    User findByMail(String email);

    /**
     * Update fail attempts.
     *
     * @param user the user
     * @return the user
     */
    User updateFailAttempts(User user);

    /**
     * Find users by administrations and positions.
     *
     * @param administrationIds the administrations ids
     * @param positionList the position list
     * @return the list
     */
    List<User> findUsersByAdministrationsAndPositions(List<Long> administrationIds, final PositionType... positionList);

    /**
     * Find users by authorities.
     *
     * @param authoritiesList the authorities list
     * @return the list
     */
    List<User> findUsersByAuthorities(final String... authoritiesList);

    /**
     * Find users by administrations ids.
     *
     * @param administrationsIds the administrations ids
     * @return the list
     */
    List<User> findUsersByAdministrationsIds(Long... administrationsIds);

    /**
     *
     * @param positionType
     * @param administrationIds
     * @return
     */
    List<User> findUsersByAdministrationsIdsAndPosition(PositionType positionType, Long... administrationIds);

    /**
     * Find users by administration and positions.
     *
     * @param administration the administration
     * @param positionList the position list
     * @return the list
     */
    List<User> findUsersByAdministrationsAndPositions(Administration administration, PositionType... positionList);

    /**
     * Removes the all granted authorities.
     *
     * @param user the user
     */
    void removeAllGrantedAuthorities(User user);

    /**
     * Find users by administration and authorities.
     *
     * @param administrationIds the administration ids
     * @param authorities the authorities
     * @return the list
     */
    List<User> findUsersByAdministrationAndAuthorities(List<Long> administrationIds, String... authorities);

    /**
     * Find by step and file type and administration.
     *
     * @param stepId the step id
     * @param fileTypeId the file type id
     * @param bureauList the bureau list
     * @return the list
     */
    List<User> findByStepAndFileTypeAndAdministration(Long stepId, Long fileTypeId, List<Bureau> bureauList);

    
    /**
     * Find by administration.
     *

     * @param bureauList the bureau list
     * @return the list
     */
    List<User> findByAdministration(List<Bureau> bureauList);

    
    /**
     * Find super user by file type.
     *
     * @param fileTypeCode the file type code
     * @param bureauId the bureau id
     * @return the list
     */
    List<User> findSuperUserByFileType(FileTypeCode fileTypeCode, Long bureauId);

}
