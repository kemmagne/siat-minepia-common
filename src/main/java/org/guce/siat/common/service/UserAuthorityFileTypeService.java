package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.Step;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthorityFileType;


/**
 * The Interface UserAuthorityFileTypeService.
 */
public interface UserAuthorityFileTypeService extends AbstractService<UserAuthorityFileType>
{

	/**
	 * Gets the file type and authority by user.
	 *
	 * @param usr
	 *           the usr
	 * @return the file type and authority by user
	 */
	List<UserAuthorityFileType> getFileTypeAndAuthorityByUser(User usr);


	/**
	 * Update list user authority file type.
	 *
	 * @param usr
	 *           the usr
	 * @param uafs
	 *           the uafs
	 */
	void updateListUserAuthorityFileType(User usr, List<UserAuthorityFileType> uafs);

	/**
	 * Save list user authority file type.
	 *
	 * @param userAuthorityFileTypes
	 *           the user authority file types
	 * @param user
	 *           the user
	 */
	void saveListUserAuthorityFileType(List<UserAuthorityFileType> userAuthorityFileTypes, User user);

	/**
	 * Find user authority file type by file type and user list.
	 *
	 * @param fileType
	 *           the file type
	 * @param userList
	 *           the user list
	 * @return the list
	 */
	List<UserAuthorityFileType> findUserAuthorityFileTypeByFileTypeAndUserList(FileType fileType, List<User> userList);

	/**
	 * Find authorities by file type and user.
	 *
	 * @param fileType
	 *           the file type
	 * @param user
	 *           the user
	 * @return the list
	 */
	List<Authority> findAuthoritiesByFileTypeAndUser(FileType fileType, User user);

	/**
	 * Find user authority file type by user list.
	 *
	 * @param usersList
	 *           the users list
	 * @return the list
	 */
	List<UserAuthorityFileType> findUserAuthorityFileTypeByUserList(List<User> usersList);


	/**
	 * Find user by file type and step authorities.
	 *
	 * @param fileType
	 *           the file type
	 * @param toStep
	 *           the to step the file
	 * @param file
	 *           the file
	 * @return the list
	 */
	List<User> findUserByFileTypeAndStepAuthorities(FileType fileType, Step toStep, File file);
}
