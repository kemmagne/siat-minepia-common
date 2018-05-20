package org.guce.epayment.exceptions;

/**
 *
 * @author tadzotsa
 */
public class EntityNotExistException extends RuntimeException {

    private static final long serialVersionUID = 2375403962581362951L;

    public EntityNotExistException(String key) {
        super(String.format("The entity find by key = %s doesn't exist", key));
    }

}
