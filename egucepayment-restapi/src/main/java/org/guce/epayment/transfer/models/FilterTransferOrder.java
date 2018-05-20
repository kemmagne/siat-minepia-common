package org.guce.epayment.transfer.models;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class FilterTransferOrder {

    private String reference;
    private String taxPayerNumber;
    private String beneficiary;
    private String invoiceType;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    private Date minDate;
    private Date maxDate;

    private String status;

    private String bank;

}
