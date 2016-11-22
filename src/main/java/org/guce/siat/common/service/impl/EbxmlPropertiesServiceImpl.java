package org.guce.siat.common.service.impl;

import org.guce.siat.common.service.EbxmlPropertiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class EbxmlProperties.
 */
@Service("ebxmlPropertiesService")
@Transactional(readOnly = true)
@PropertySource("classpath:global-config.properties")
public class EbxmlPropertiesServiceImpl implements EbxmlPropertiesService
{

	/** The from party id. */
	@Value("${ebxml.fromPartyId}")
	private String fromPartyId;

	/** The url atom repo. */
	@Value("${ebxml.toPartyId}")
	private String toPartyId;

	/** The user name repo. */
	@Value("${ebxml.action}")
	private String action;

	/** The pwd repo. */
	@Value("${ebxml.conversationId}")
	private String conversationId;

	/** The id repo. */
	@Value("${ebxml.cpaId}")
	private String cpaId;

	/** The url repo. */
	@Value("${ebxml.service}")
	private String service;

	/** The service type. */
	@Value("${ebxml.serviceType}")
	private String serviceType;

	/** The ref to message id. */
	@Value("${ebxml.refToMessageId}")
	private String refToMessageId;

	/** The ebxml folder. */
	@Value("${ebxml.folder}")
	private String ebxmlFolder;

	@Value("${xml.folder}")
	private String xmlFolder;

	@Value("${attachement.folder}")
	private String attachementFolder;



	/**
	 * Gets the from party id.
	 *
	 * @return the from party id
	 */
	public String getFromPartyId()
	{
		return fromPartyId;
	}

	/**
	 * Sets the from party id.
	 *
	 * @param fromPartyId
	 *           the new from party id
	 */
	public void setFromPartyId(final String fromPartyId)
	{
		this.fromPartyId = fromPartyId;
	}

	/**
	 * Gets the to party id.
	 *
	 * @return the to party id
	 */
	public String getToPartyId()
	{
		return toPartyId;
	}

	/**
	 * Sets the to party id.
	 *
	 * @param toPartyId
	 *           the new to party id
	 */
	public void setToPartyId(final String toPartyId)
	{
		this.toPartyId = toPartyId;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action
	 *           the new action
	 */
	public void setAction(final String action)
	{
		this.action = action;
	}

	/**
	 * Gets the conversation id.
	 *
	 * @return the conversation id
	 */
	public String getConversationId()
	{
		return conversationId;
	}

	/**
	 * Sets the conversation id.
	 *
	 * @param conversationId
	 *           the new conversation id
	 */
	public void setConversationId(final String conversationId)
	{
		this.conversationId = conversationId;
	}

	/**
	 * Gets the cpa id.
	 *
	 * @return the cpa id
	 */
	public String getCpaId()
	{
		return cpaId;
	}

	/**
	 * Sets the cpa id.
	 *
	 * @param cpaId
	 *           the new cpa id
	 */
	public void setCpaId(final String cpaId)
	{
		this.cpaId = cpaId;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public String getService()
	{
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service
	 *           the new service
	 */
	public void setService(final String service)
	{
		this.service = service;
	}

	/**
	 * Gets the service type.
	 *
	 * @return the service type
	 */
	public String getServiceType()
	{
		return serviceType;
	}

	/**
	 * Sets the service type.
	 *
	 * @param serviceType
	 *           the new service type
	 */
	public void setServiceType(final String serviceType)
	{
		this.serviceType = serviceType;
	}

	/**
	 * Gets the ref to message id.
	 *
	 * @return the ref to message id
	 */
	public String getRefToMessageId()
	{
		return refToMessageId;
	}

	/**
	 * Sets the ref to message id.
	 *
	 * @param refToMessageId
	 *           the new ref to message id
	 */
	public void setRefToMessageId(final String refToMessageId)
	{
		this.refToMessageId = refToMessageId;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.EbxmlPropertiesService#getEbxmlFolder()
	 */
	public String getEbxmlFolder()
	{
		return ebxmlFolder;
	}

	/**
	 * Sets the ebxml folder.
	 *
	 * @param ebxmlFolder
	 *           the new ebxml folder
	 */
	public void setEbxmlFolder(final String ebxmlFolder)
	{
		this.ebxmlFolder = ebxmlFolder;
	}

	/**
	 * @return the xmlFolder
	 */
	public String getXmlFolder()
	{
		return xmlFolder;
	}

	/**
	 * @param xmlFolder
	 *           the xmlFolder to set
	 */
	public void setXmlFolder(final String xmlFolder)
	{
		this.xmlFolder = xmlFolder;
	}

	/**
	 * @return the attachementFolder
	 */
	public String getAttachementFolder()
	{
		return attachementFolder;
	}

	/**
	 * @param attachementFolder
	 *           the attachementFolder to set
	 */
	public void setAttachementFolder(final String attachementFolder)
	{
		this.attachementFolder = attachementFolder;
	}




}
