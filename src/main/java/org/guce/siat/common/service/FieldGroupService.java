package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.FieldGroup;
import org.guce.siat.common.model.FileType;


/**
 * The Interface FieldGroupService.
 */
public interface FieldGroupService extends AbstractService<FieldGroup>
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
List<FieldGroup>findAllByFileType(FileType fileType,String type);
}
