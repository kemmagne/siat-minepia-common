package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"reference"})
public class StatisticBanqueDto {

    private String reference;
    private Date startedDate;
    private PartnerDto taxPayer;
    private PartnerDto beneficiary;
    private BigDecimal amount;

    public StatisticBanqueDto() {
    }

    private StatisticBanqueDto(String reference, Date startedDate, PartnerDto taxPayer, PartnerDto beneficiary, BigDecimal amount) {
        this.reference = reference;
        this.startedDate = startedDate;
        this.taxPayer = taxPayer;
        this.beneficiary = beneficiary;
        this.amount = amount;
    }

    public static StatisticBanqueDto of(String reference, Date startedDate, PartnerDto taxPayer, PartnerDto beneficiary, BigDecimal amount) {
        return new StatisticBanqueDto(reference, startedDate, taxPayer, beneficiary, amount);
    }

}
