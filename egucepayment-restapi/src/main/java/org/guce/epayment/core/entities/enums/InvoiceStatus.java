package org.guce.epayment.core.entities.enums;

import java.io.Serializable;

/**
 *
 * @author tadzotsa
 */
public enum InvoiceStatus implements Serializable {

    UNPAID, PAYMENT_IN_PROCESS, PAYMENT_CANCELED, PAYMENT_REJECTED, PAID, CLEARED;

}
