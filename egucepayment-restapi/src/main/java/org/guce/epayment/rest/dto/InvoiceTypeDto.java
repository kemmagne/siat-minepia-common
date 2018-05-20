package org.guce.epayment.rest.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class InvoiceTypeDto {

    private Integer id;
    private String code;
    private String label;
    private String params;
    private boolean standalone;
    private List<PaymentModeDto> paymentModes;
    private List<PartnerDto> beneficiaries;
    private List<PartnerDto> banks;
    private List<UserDto> decisionMakers;

    public InvoiceTypeDto() {
    }

    private InvoiceTypeDto(Integer id, String code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;
    }

    public static InvoiceTypeDto of(Integer id, String code, String label) {

        return new InvoiceTypeDto(id, code, label);
    }

}
