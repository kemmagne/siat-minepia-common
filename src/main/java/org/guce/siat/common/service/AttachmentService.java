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
        
        /**
	 * Find attachments by file and attachment_type and document_name and document_path.
	 *
         * @param file 
	 * @param attachmentType the file
         * @param documentName  the document name
         * @param documentPath the document path
	 * @return the Attachment
	 */
	Attachment findAttachmentByFileAndAttachmentTypeAndDocumentNameAndDocumentPath(File file, String attachmentType, String documentName, String documentPath);
}
