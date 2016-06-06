package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Recommandation;


/**
 * The Interface RecommandationDao.
 */
public interface RecommandationDao extends AbstractJpaDao<Recommandation>
{


	/**
	 * Find recommandation by file.
	 *
	 * @param file
	 *           the file
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
	List<Recommandation> findRecommandationByFileItemAndAuthorties(final FileItem fileItem, final List<Authority> authorities);

}
