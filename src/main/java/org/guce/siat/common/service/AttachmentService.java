package org.guce.siat.common.service;

import java.util.List;

import org.guce.siat.common.model.Attachment;
import org.guce.siat.common.model.File;


/**
 * The Interface AttachmentService.
 */
public interface AttachmentService extends AbstractService<Attachment>
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
