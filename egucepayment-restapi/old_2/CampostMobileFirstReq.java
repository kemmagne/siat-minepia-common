package org.guce.epayment.rest.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author hyacinthe
 */
@Data
public class CampostMobileFirstReq implements Serializable {

    private static final long serialVersionUID = -5350609587460001156L;

    private String beneficiaryCode;
    private List<InvoiceDto> invoices;
    private String mobile;

}
