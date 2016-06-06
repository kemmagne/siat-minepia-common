package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Recommandation;


/**
 * The Interface RecommandationService.
 */
public interface RecommandationService extends AbstractService<Recommandation>
{

	/**
	 * Find recommandation by file.
	 *
	 * @param file
	 *           the file id
	 * @param authorities
	 *           the authorities
	 * @return the list
	 */
	List<Recommandation> findRecommandationByFileAndAuthorties(File file, List<Authority> authorities);

	/**
	 * Find recommandation by file item.
	 *
	 * @param fileItem
	 *           the file item
	 * @param authorities
	 *           the authorities
	 * @return the list
	 */
	List<Recommandation> findRecommandationByFileItemAndAuthorties(FileItem fileItem, List<Authority> authorities);
}
