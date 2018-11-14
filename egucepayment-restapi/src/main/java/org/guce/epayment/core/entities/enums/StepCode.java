package org.guce.epayment.core.entities.enums;

import java.io.Serializable;

/**
 *
 * @author tadzotsa
 */
public enum StepCode implements Serializable {

    /**
     * asset clearing start
     */
    A11,
    //    CANCELED,
    //    REJECTED,
    /**
     * transfer order on principal side
     */
    T11,
    /**
     * transfer order on principal bank side
     */
    T21,
    /**
     * transfer order on benefeciary bank side
     */
    T31,
    /**
     * transfer order on benefeciary side
     */
    T41;

}
