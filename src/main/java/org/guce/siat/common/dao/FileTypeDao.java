package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.FileType;
import org.guce.siat.common.model.FileTypeService;
import org.guce.siat.common.model.FileTypeStep;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.utils.enums.FileTypeCode;

/**
 * The Interface FileTypeDao.
 */
public interface FileTypeDao extends AbstractJpaDao<FileType> {

	/**
	 * Find by minestry.
	 *
	 * @param ministry the ministry
	 * @return the list
	 */
	List<FileType> findFileTypeByMinistry(Ministry ministry);

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the file type
	 */
	FileType findByCode(FileTypeCode code);

	/**
	 * Find by filetype.
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
	List<FileType> findFileTypesByCodes(final FileTypeCode... fileTypeCodes);

	/**
	 * Update.
	 *
	 * @param selected the selected
	 * @param targetFileTypeStep the target file type step
	 */
	void update(FileType selected, List<FileTypeStep> targetFileTypeStep);

	public void update(FileType selected, List<FileTypeStep> targetFileTypeStep, List<FileTypeService> targetFileTypeServices);

	public List<FileTypeService> findFileTypeServiceByFileType(FileType fileType);
}
