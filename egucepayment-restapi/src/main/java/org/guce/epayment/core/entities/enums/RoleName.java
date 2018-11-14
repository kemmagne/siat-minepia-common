package org.guce.epayment.core.entities.enums;

import java.io.Serializable;

/**
 *
 * @author tadzotsa
 */
public enum RoleName implements Serializable {

    ADMIN, PRINCIPAL, PRINCIPAL_CONTROLLER, PRINCIPAL_AUDITOR, CASHIER, AGENCY_CONTROLLER,
    BANK_CONTROLLER, BANK_AUDITOR, BENEFICIARY, DECISION_MAKER;

}
