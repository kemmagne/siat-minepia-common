package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Attachment;
import org.guce.siat.common.model.File;


/**
 * The Interface AttachmentDao.
 */
public interface AttachmentDao extends AbstractJpaDao<Attachment>
{

	/**
	 * Find attachments by file.
	 *
	 * @param file
	 *           the file
	 * @return the list
	 */
	List<Attachment> findAttachmentsByFile(File file);
}
