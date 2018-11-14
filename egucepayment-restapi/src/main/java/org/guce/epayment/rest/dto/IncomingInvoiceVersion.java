package org.guce.epayment.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class IncomingInvoiceVersion implements Serializable {

    private static final long serialVersionUID = 230087639853969593L;

    private String invoiceNumber;
    private BigDecimal amount;
    private String invoiceTypeCode;
    private String subTypeCode;
    private String taxPayerNumber;
    private String taxPayerName;
    private String beneficiaryCode;
    private String benefReference;
    private int version;
    private String guceReference;
    private List<IncomingInvoiceVersion> subInvoices;

    public IncomingInvoiceVersion() {
    }

    private IncomingInvoiceVersion(BigDecimal amount, String beneficiaryCode, String benefReference) {
        this.amount = amount;
        this.beneficiaryCode = beneficiaryCode;
        this.benefReference = benefReference;
    }

    public static IncomingInvoiceVersion of(BigDecimal amount, String beneficiaryCode, String benefReference) {
        return new IncomingInvoiceVersion(amount, beneficiaryCode, benefReference);
    }

}

