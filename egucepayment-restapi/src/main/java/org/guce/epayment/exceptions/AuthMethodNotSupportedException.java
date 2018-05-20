package org.guce.epayment.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 *
 * @author tadzotsa
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {

    private static final long serialVersionUID = 2925828909935931795L;

    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }

}
