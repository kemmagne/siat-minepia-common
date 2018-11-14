package org.guce.epayment.core.entities.enums;

import java.io.Serializable;

/**
 *
 * @author tadzotsa
 */
public enum PaymentStatus implements Serializable {

    PENDING, VALIDATED, CONFIRMED, ACKNOWLED, CANCELED, REJECTED;

}
