package org.guce.siat.common.service;

import java.util.Map;


/**
 * The Interface FileProducer.
 */
public interface FileProducer
{

	/**
	 * Send file.
	 *
	 * @param file
	 *           the file
	 */
	public void sendFile(Map<String, Object> file);

}
