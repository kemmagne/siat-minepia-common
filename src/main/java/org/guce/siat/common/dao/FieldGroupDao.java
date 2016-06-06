package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.FieldGroup;
import org.guce.siat.common.model.FileType;


/**
 * The Interface FieldGroupDao.
 */
public interface FieldGroupDao extends AbstractJpaDao<FieldGroup>
{

	/**
	 * Find all by file type.
	 *
	 * @param fileType
	 *           the file type
	 * @param type
	 *           the type
	 * @return the list
	 */
	List<FieldGroup> findAllByFileType(FileType fileType,String type);



}
