/*
 *
 */
package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthorityFileType;
import org.guce.siat.common.utils.enums.InformationSystemCode;
import org.guce.siat.common.utils.enums.StepCode;
import org.guce.siat.common.utils.filter.RetrieveSearchFilter;



/**
 * The Interface FileItemService.
 */
public interface FileItemService extends AbstractService<FileItem>
{

	/**
	 * Find file items by file and service and organism and authorities.
	 *
	 * @param productInfoItems
	 *           the product info items
	 * @param listUserAuthorityFileTypes
	 *           the list user authority file types
	 * @param loggedUser
	 *           the logged user
	 * @return the list
	 */
	List<FileItem> filterFileItemsByUserAuthorityFileTypes(final List<FileItem> productInfoItems,
			List<UserAuthorityFileType> listUserAuthorityFileTypes, User loggedUser);

	/**
	 * Find file items by file.
	 *
	 * @param file
	 *           the file
	 * @return the list
	 */
	List<FileItem> findFileItemsByFile(final File file);

	/**
	 * Find file item by service and authorities and file type.
	 *
	 * @param bureaus
	 *           the bureaus
	 * @param loggedUser
	 *           the logged user
	 * @param informationSystemCode
	 *           the information system code
	 * @param listUserAuthorityFileTypes
	 *           the list user authority file types
	 * @return the list
	 */
	List<FileItem> findFileItemByServiceAndAuthoritiesAndFileType(List<Bureau> bureaus, User loggedUser,
			InformationSystemCode informationSystemCode, List<UserAuthorityFileType> listUserAuthorityFileTypes);

	/**
	 * Find file item field value by code.
	 *
	 * @param fileItem
	 *           the file item
	 * @param fieldCode
	 *           the field code
	 * @return the file item field value
	 */
	FileItemFieldValue findFileItemFieldValueByFieldCode(FileItem fileItem, String fieldCode);


	/**
	 * Verify step changed while decision in progress.
	 *
	 * @param fileItemList
	 *           the file item list
	 * @return the boolean
	 */
	Boolean verifyStepChangedWhileDecisionInProgress(List<FileItem> fileItemList);

	/**
	 * Find file item for retreive by filter.
	 *
	 * @param bureauList
	 *           the bureau list
	 * @param user
	 *           the user
	 * @param informationSystemCode
	 *           the information system code
	 * @param listUserAuthorityFileTypes
	 *           the list user authority file types
	 * @param filter
	 *           the filter
	 * @param stepApAcceptation
	 *           the step ap acceptation
	 * @return the list
	 */
	List<FileItem> findFileItemForRetreiveByFilter(final List<Bureau> bureauList, final User user,
			InformationSystemCode informationSystemCode, final List<UserAuthorityFileType> listUserAuthorityFileTypes,
			final RetrieveSearchFilter filter, StepCode stepApAcceptation);

	/**
	 * Find file item field value by field code.
	 *
	 * @param idFile
	 *           the id file
	 * @param idItemField
	 *           the id item field
	 * @return the file item field value
	 */
	FileItemFieldValue findFileItemFieldValueByFieldCode(Long idFile, Long idItemField);

}
