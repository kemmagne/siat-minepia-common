package org.guce.epayment.exceptions;

/**
 *
 * @author tadzotsa
 */
public class BadStepException extends RuntimeException {

    private static final long serialVersionUID = -116563079049139905L;

    public BadStepException() {
    }

    public BadStepException(String message) {
        super(message);
    }

}
