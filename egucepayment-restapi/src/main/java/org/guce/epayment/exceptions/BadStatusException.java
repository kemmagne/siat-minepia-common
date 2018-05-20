package org.guce.epayment.exceptions;

/**
 *
 * @author tadzotsa
 */
public class BadStatusException extends RuntimeException {

    private static final long serialVersionUID = -116563079049139905L;

    public BadStatusException() {
    }

    public BadStatusException(String message) {
        super(message);
    }

}
