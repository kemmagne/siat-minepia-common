package org.guce.epayment.rest.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author hyacinthe
 */
@Data
public class CampostMobileFirstReq {

    private String beneficiaryCode;
    private List<InvoiceDto> invoices;
    private String mobile;

}
