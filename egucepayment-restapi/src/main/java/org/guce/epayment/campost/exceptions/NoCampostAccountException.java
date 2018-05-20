package org.guce.epayment.campost.exceptions;

public class NoCampostAccountException extends Exception {

    public NoCampostAccountException() {
    }

    public NoCampostAccountException(String message) {
        super(message);
    }

}
