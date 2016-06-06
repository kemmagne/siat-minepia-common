package org.guce.siat.common.service;

import org.guce.siat.common.model.File;


/**
 * The Interface AlfrescoDirectoryCreator.
 */
public interface AlfrescoDirectoryCreator
{

	/**
	 * Creates the directory.
	 *
	 * @param siatFile
	 *           the SIAT file
	 */
	void createDirectory(File siatFile);

	/**
	 * Generate alfresco path.
	 *
	 * @param siatFile
	 *           the SIAT file
	 * @return the Alfresco path
	 */
	String generateAlfrescoPath(File siatFile);


	/**
	 * Attachment exist.
	 *
	 * @param path
	 *           the path
	 * @return true, if exist
	 */
	boolean attachmentExist(String path);
}
