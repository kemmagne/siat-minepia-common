package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
//@XmlRootElement(name = "FACTURE")
public class InvoiceVersionDto {

//    @XmlElement("NUMERO_FACTURE")
    private String invoiceNumber;
//    @XmlElement("MONTANT_FACTURE")
    private BigDecimal amount;
//    @XmlElement("CODE_TYPE_FACTURE")
    private String invoiceTypeCode;
//    @XmlElement("MENTIONS")
    private String subTypeCode;
//    @XmlElement("CONTRIBUABLE")
    private String taxPayerNumber;
//    @XmlElement("CODE_BENEFICIAIRE")
    private String beneficiaryCode;
//    @XmlElement("DOCUMENT_ORIGINE")
    private String benefReference;
//    @XmlElementWrapper(name = "LIGNES_FACTURE")
//    @XmlElements({
//        @XmlElement(name = "LIGNE_FACTURE", type = InvoiceVersionDto.class)
//    })
    private List<InvoiceVersionDto> subInvoices;

    public InvoiceVersionDto() {

    }

    private InvoiceVersionDto(BigDecimal amount, String beneficiaryCode) {
        this.amount = amount;
        this.beneficiaryCode = beneficiaryCode;
    }

    public static InvoiceVersionDto of(BigDecimal amount, String beneficiaryCode) {
        return new InvoiceVersionDto(amount, beneficiaryCode);
    }

}
