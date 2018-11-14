package org.guce.epayment.rest.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class FilterTransferOrderDto implements Serializable {

    private static final long serialVersionUID = 5531347083515182720L;

    private String toReference;
    private String taxPayerNumber;
    private String invoiceTypeCode;
    private String beneficiaryCode;
    private String minAmount;
    private String maxAmount;
    private String status;
    private String minDate;
    private String maxDate;
    private String bank;

}

