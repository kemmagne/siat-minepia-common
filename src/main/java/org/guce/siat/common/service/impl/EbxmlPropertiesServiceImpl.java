package org.guce.siat.common.service.impl;

import javax.annotation.PostConstruct;
import org.guce.siat.common.service.EbxmlPropertiesService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EbxmlPropertiesServiceImpl implements EbxmlPropertiesService {

    @Autowired
    private PropertiesLoader propertiesLoader;

    /**
     * The from party id.
     */
    @Value("${ebxml.fromPartyId}")
    private String fromPartyId;

    /**
     * The url atom repo.
     */
    @Value("${ebxml.toPartyId}")
    private String toPartyId;

    /**
     * The user name repo.
     */
    @Value("${ebxml.action}")
    private String action;

    /**
     * The pwd repo.
     */
    @Value("${ebxml.conversationId}")
    private String conversationId;

    /**
     * The id repo.
     */
    @Value("${ebxml.cpaId}")
    private String cpaId;

    /**
     * The url repo.
     */
    @Value("${ebxml.service}")
    private String service;

    /**
     * The service type.
     */
    @Value("${ebxml.serviceType}")
    private String serviceType;

    /**
     * The ref to message id.
     */
    @Value("${ebxml.refToMessageId}")
    private String refToMessageId;

    /**
     * The ebxml folder.
     */
    @Value("${ebxml.folder}")
    private String ebxmlFolder;

    @Value("${xml.folder}")
    private String xmlFolder;

    @Value("${attachement.folder}")
    private String attachementFolder;

    @PostConstruct
    public void init() {
        fromPartyId = propertiesLoader.getProperty(PropertiesConstants.EBXML_FROM_PARTY_ID);
        toPartyId = propertiesLoader.getProperty(PropertiesConstants.EBXML_TO_PARTY_ID);
        action = propertiesLoader.getProperty(PropertiesConstants.EBXML_ACTION);
        conversationId = propertiesLoader.getProperty(PropertiesConstants.EBXML_CONVERSATION_ID);
        cpaId = propertiesLoader.getProperty(PropertiesConstants.EBXML_CPA_ID);
        service = propertiesLoader.getProperty(PropertiesConstants.EBXML_SERVICE);
        serviceType = propertiesLoader.getProperty(PropertiesConstants.EBXML_SERVICE_TYPE);
        refToMessageId = propertiesLoader.getProperty(PropertiesConstants.EBXML_REF_TO_MESSAGE_ID);
        ebxmlFolder = propertiesLoader.getProperty(PropertiesConstants.EBXML_FOLDER);
        xmlFolder = propertiesLoader.getProperty(PropertiesConstants.XML_FOLDER);
        attachementFolder = propertiesLoader.getProperty(PropertiesConstants.ATTACHMENT_FOLDER);
    }

    /**
     * Gets the from party id.
     *
     * @return the from party id
     */
    @Override
    public String getFromPartyId() {
        return fromPartyId;
    }

    /**
     * Sets the from party id.
     *
     * @param fromPartyId the new from party id
     */
    public void setFromPartyId(final String fromPartyId) {
        this.fromPartyId = fromPartyId;
    }

    /**
     * Gets the to party id.
     *
     * @return the to party id
     */
    @Override
    public String getToPartyId() {
        return toPartyId;
    }

    /**
     * Sets the to party id.
     *
     * @param toPartyId the new to party id
     */
    public void setToPartyId(final String toPartyId) {
        this.toPartyId = toPartyId;
    }

    /**
     * Gets the action.
     *
     * @return the action
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * Sets the action.
     *
     * @param action the new action
     */
    public void setAction(final String action) {
        this.action = action;
    }

    /**
     * Gets the conversation id.
     *
     * @return the conversation id
     */
    @Override
    public String getConversationId() {
        return conversationId;
    }

    /**
     * Sets the conversation id.
     *
     * @param conversationId the new conversation id
     */
    public void setConversationId(final String conversationId) {
        this.conversationId = conversationId;
    }

    /**
     * Gets the cpa id.
     *
     * @return the cpa id
     */
    @Override
    public String getCpaId() {
        return cpaId;
    }

    /**
     * Sets the cpa id.
     *
     * @param cpaId the new cpa id
     */
    public void setCpaId(final String cpaId) {
        this.cpaId = cpaId;
    }

    /**
     * Gets the service.
     *
     * @return the service
     */
    @Override
    public String getService() {
        return service;
    }

    /**
     * Sets the service.
     *
     * @param service the new service
     */
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * Gets the service type.
     *
     * @return the service type
     */
    @Override
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the service type.
     *
     * @param serviceType the new service type
     */
    public void setServiceType(final String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Gets the ref to message id.
     *
     * @return the ref to message id
     */
    @Override
    public String getRefToMessageId() {
        return refToMessageId;
    }

    /**
     * Sets the ref to message id.
     *
     * @param refToMessageId the new ref to message id
     */
    public void setRefToMessageId(final String refToMessageId) {
        this.refToMessageId = refToMessageId;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.EbxmlPropertiesService#getEbxmlFolder()
     */
    @Override
    public String getEbxmlFolder() {
        return ebxmlFolder;
    }

    /**
     * Sets the ebxml folder.
     *
     * @param ebxmlFolder the new ebxml folder
     */
    public void setEbxmlFolder(final String ebxmlFolder) {
        this.ebxmlFolder = ebxmlFolder;
    }

    /**
     * @return the xmlFolder
     */
    @Override
    public String getXmlFolder() {
        return xmlFolder;
    }

    /**
     * @param xmlFolder the xmlFolder to set
     */
    public void setXmlFolder(final String xmlFolder) {
        this.xmlFolder = xmlFolder;
    }

    /**
     * @return the attachementFolder
     */
    @Override
    public String getAttachementFolder() {
        return attachementFolder;
    }

    /**
     * @param attachementFolder the attachementFolder to set
     */
    public void setAttachementFolder(final String attachementFolder) {
        this.attachementFolder = attachementFolder;
    }

}
