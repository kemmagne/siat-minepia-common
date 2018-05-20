package org.guce.epayment.core.models;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class FilterInvoice {

    private String invoiceNumber;
    private String taxPayerNumber;
    private String beneficiaryCode;
    private String invoiceTypeCode;
    private String subTypeCode;
    private String invoiceStatus;
    private BigDecimal invoiceMinAmount;
    private BigDecimal invoiceMaxAmount;

    private int start;
    private int end;

    private boolean count;

}
