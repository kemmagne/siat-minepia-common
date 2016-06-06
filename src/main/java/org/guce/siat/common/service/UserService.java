package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.PositionType;



/**
 * The Interface UserService.
 */
public interface UserService extends AbstractService<User>
{
	/**
	 * Creates the user.
	 *
	 * @param user
	 *           the user
	 */
	void createUser(User user);

	/**
	 * Update user.
	 *
	 * @param user
	 *           the user
	 */
	void updateUser(User user);

	/**
	 * Find by login.
	 *
	 * @param userName
	 *           the user name
	 * @return the user
	 */
	User findByLogin(String userName);

	/**
	 * Find all active admins notaffected.
	 *
	 * @return the list
	 */
	List<User> findAllActiveAdminsNotaffected();

	/**
	 * Find by mail.
	 *
	 * @param email
	 *           the email
	 * @return the user
	 */
	User findByMail(String email);

	/**
	 * Find controleurs by service.
	 *
	 * @param service
	 *           the service
	 * @return the list
	 */
	List<User> findControleursByService(Service service);

	/**
	 * Find inspectors by service.
	 *
	 * @param service
	 *           the service
	 * @return the list
	 */
	List<User> findInspectorsByService(Service service);

	/**
	 * Update fail attempts.
	 *
	 * @param user
	 *           the user
	 * @return the user
	 */
	User updateFailAttempts(User user);

	/**
	 * Find users by organism and authorities.
	 *
	 * @param organism
	 *           the organism
	 * @param authoritiesList
	 *           the authorities list
	 * @return the list
	 */
	List<User> findUsersByOrganismAndAuthorities(final Organism organism, final String... authoritiesList);

	/**
	 * Find users by administration and positions.
	 *
	 * @param administration
	 *           the administration
	 * @param positionList
	 *           the position list
	 * @return the list
	 */
	List<User> findUsersByAdministrationAndPositions(Administration administration, final PositionType... positionList);

	/**
	 * Find users by authorities.
	 *
	 * @param authoritiesList
	 *           the authorities list
	 * @return the list
	 */
	List<User> findUsersByAuthorities(final String... authoritiesList);

	/**
	 * Find authorities by administration.
	 *
	 * @param administration
	 *           the administration
	 * @param authorities
	 *           the authorities
	 * @return the list
	 */
	List<User> findUsersByAdministrationAndAuthorities(Administration administration, String... authorities);

	/**
	 * Find by step and file type and administration.
	 *
	 * @param stepId
	 *           the step id
	 * @param fileTypeId
	 *           the file type id
	 * @param bureauList
	 *           the bureau list
	 * @return the list
	 */
	List<User> findByStepAndFileTypeAndAdministration(Long stepId, Long fileTypeId, List<Bureau> bureauList);

	/**
	 * Find super user by file type.
	 *
	 * @param fileTypeCode
	 *           the file type code
	 * @param bureauId
	 *           the bureau id
	 * @return the list
	 */
	List<User> findSuperUserByFileType(FileTypeCode fileTypeCode, Long bureauId);

}
