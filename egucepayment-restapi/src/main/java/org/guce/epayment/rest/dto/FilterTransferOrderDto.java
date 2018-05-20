package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class FilterTransferOrderDto {

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
