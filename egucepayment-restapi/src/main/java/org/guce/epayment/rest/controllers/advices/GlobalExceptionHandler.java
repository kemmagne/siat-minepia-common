package org.guce.epayment.rest.controllers.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author tadzotsa
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseStatus(FORBIDDEN)
//    @ExceptionHandler(SignatureException.class)
    public void failedToVerify() {
    }

}
