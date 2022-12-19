package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FileService.
 */
public interface FileTypeService extends AbstractService<FileType> {

	/**
	 * Find file type by ministry.
	 *
	 * @param ministry the ministry
	 * @return the list
	 */
	List<FileType> findFileTypeByMinistry(Ministry ministry);

	/**
	 * Find distinct file types by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<FileType> findDistinctFileTypesByUser(User user);

	/**
	 * Find Authorities by filetype.
	 *
	 * @param filetype the filetype
	 * @return the list
	 */
	List<Authority> findAuthoritiesByFileType(FileType filetype);

	/**
	 * Find file types by codes.
	 *
	 * @param fileTypeCodes the file type codes
	 * @return the list
	 */
	List<FileType> findFileTypesByCodes(FileTypeCode... fileTypeCodes);

	/**
	 * Find file types services by fileType.
	 *
	 * @param fileType the file type
	 * @return the list
	 */
	List<org.guce.siat.common.model.FileTypeService> findFileTypeServiceByFileType(FileType fileType);

	/**
	 * Update.
	 *
	 * @param selected the selected
	 * @param targetFileTypeStep the target file type step
	 */
	void update(FileType selected, List<FileTypeStep> targetFileTypeStep);

	/**
	 * Update.
	 *
	 * @param selected the selected
	 * @param targetFileTypeStep the target file type step
	 * @param targetFileTypeServices the target file type services
	 */
	void update(FileType selected, List<FileTypeStep> targetFileTypeStep, List<org.guce.siat.common.model.FileTypeService> targetFileTypeServices);
}
