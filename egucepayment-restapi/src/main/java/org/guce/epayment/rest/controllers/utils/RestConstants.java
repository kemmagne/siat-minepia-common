package org.guce.epayment.rest.controllers.utils;

/**
 *
 * @author tadzotsa
 */
public interface RestConstants {

    static final String MEDIA_TYPE_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".intern();

    static final String MEDIA_TYPE_PDF = "application/pdf".intern();

    static final String CONTENT_DISPOSITION = "Content-Disposition".intern();

    static final String LOCATION = "Location".intern();

    static final String CONTENT_TYPE = "Content-Type".intern();

    static final String X_REQUEST_WITH = "X-Requested-With".intern();

    static final String ORIGIN = "Origin".intern();

    static final String FILE_DOWNLOAD_HEADER_VALUE_FORMAT = "attachment;filename=%s".intern();

    static final String LOGIN = "login".intern();

    static final String TOKEN = "token".intern();

    static final String PASSWORD = "password".intern();

    static final String BROWSER_FINGER_PRINT = "browser_finger_print".intern();

    static final String REMOTE_ADR = "remote_address".intern();

    static final String APPLICATION_ISSUER = "application_issuer".intern();

    static final String ROLES = "roles".intern();

    static final String LOCALE = "locale".intern();

    static final String UNAUTHORIZED = "Unauthorized".intern();

    static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".intern();

    static final String DEFAULT_PASSWORD = "0123456789".intern();

    static final String DEFAULT_RESPONSE_BODY = "OK".intern();

}
