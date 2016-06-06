package org.guce.siat.common.service;


/**
 * The Interface EbxmlPropertiesService.
 */
public interface EbxmlPropertiesService
{

	/**
	 * Gets the from party id.
	 *
	 * @return the from party id
	 */
	String getFromPartyId();

	/**
	 * Gets the to party id.
	 *
	 * @return the to party id
	 */
	String getToPartyId();

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	String getAction();

	/**
	 * Gets the conversation id.
	 *
	 * @return the conversation id
	 */
	String getConversationId();

	/**
	 * Gets the cpa id.
	 *
	 * @return the cpa id
	 */
	String getCpaId();

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	String getService();

	/**
	 * Gets the service type.
	 *
	 * @return the service type
	 */
	String getServiceType();

	/**
	 * Gets the ref to message id.
	 *
	 * @return the ref to message id
	 */
	String getRefToMessageId();

	/**
	 * Gets the ebxml folder.
	 *
	 * @return the ebxml folder
	 */
	String getEbxmlFolder();

	/**
	 * Gets the xml folder.
	 *
	 * @return the xml folder
	 */
	String getXmlFolder();

	/**
	 * Gets the attachement folder.
	 *
	 * @return the attachement folder
	 */
	String getAttachementFolder();
}
