package org.guce.epayment.exceptions;

/**
 *
 * @author tadzotsa
 */
public class NotCreditAccountException extends RuntimeException {

    private static final long serialVersionUID = -116563079049139905L;

    public NotCreditAccountException() {
    }

    public NotCreditAccountException(String message) {
        super(message);
    }

}
