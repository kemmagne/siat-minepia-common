package org.guce.siat.common.utils;

/**
 *
 * @author ht
 */
public interface PropertiesConstants {

    String MAILS_FOLDER = "mails.folder".intern();
    String MAIL_FROM = "mailSender.from".intern();
    String MAIL_REPLY_TO = "mailSender.replyTo".intern();
    String APPLICATION_ENV = "application.environment".intern();

    String MESSAGES_FOLDER = "messages.folder".intern();

    String GED_CONNECTION_NAME = "connectionName".intern();
    String GED_USERNAME_REPO = "userNameRepo".intern();
    String GED_PASSWORD_REPO = "pwdRepo".intern();
    String GED_IP_REPO = "ipRepo".intern();
    String GED_URL_ATOM_REPO = "urlAtomRepo".intern();
    String GED_URL_REPO = "urlRepo".intern();
    String GED_ID_REPO = "idRepo".intern();
    String APP_NAME = "appName".intern();

    String WEBSERVICE_URL = "webservice.url".intern();

    String EBXML_FROM_PARTY_ID = "ebxml.fromPartyId".intern();
    String EBXML_TO_PARTY_ID = "ebxml.toPartyId".intern();
    String EBXML_ACTION = "ebxml.action".intern();
    String EBXML_CONVERSATION_ID = "ebxml.conversationId".intern();
    String EBXML_CPA_ID = "ebxml.cpaId".intern();
    String EBXML_SERVICE = "ebxml.service".intern();
    String EBXML_SERVICE_TYPE = "ebxml.serviceType".intern();
    String EBXML_REF_TO_MESSAGE_ID = "ebxml.refToMessageId".intern();
    String EBXML_FOLDER = "ebxml.folder".intern();

    String XML_FOLDER = "xml.folder".intern();
    String ATTACHMENT_FOLDER = "attachement.folder".intern();

}
