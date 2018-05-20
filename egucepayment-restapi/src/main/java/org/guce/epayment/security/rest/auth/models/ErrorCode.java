package org.guce.epayment.security.rest.auth.models;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of REST Error types.
 *
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
public enum ErrorCode {

    GLOBAL(2),
    AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11);

    private final int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
