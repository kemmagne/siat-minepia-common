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
    private boolean child;
    /**
     * si ce champ est true on ne cherche que les factures qui ont les autres
     * critères et dont les owners sont dans les mêmes groupes que le partenaire
     * dont l'utilisateur est connecté
     */
    private boolean groups;

    private int start;
    private int end;

    private boolean count;

}
