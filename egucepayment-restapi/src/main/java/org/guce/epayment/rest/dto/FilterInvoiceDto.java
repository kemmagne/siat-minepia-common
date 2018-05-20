package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class FilterInvoiceDto {

    private String invoiceNumber;
    private String taxPayerNumber;
    private String invoiceTypeCode;
    private String beneficiaryCode;
    private String invoiceMinAmount;
    private String invoiceMaxAmount;
    private String invoiceStatus;
    private String subTypeCode;
    /**
     * si ce champ est true on ne cherche que les factures qui ont les autres
     * critères et dont les owners sont dans les mêmes groupes que le partenaire
     * dont l'utilisateur est connecté
     */
    private boolean groups;

}
