package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class BankAccountDto {

    private BigDecimal id;

    private String bankCode;
    private String bankLabel;

    private String agencyCode;
    private String agencyLabel;

    private String accountNumber;

    private String accountKey;

    private String accountLabel;

    public BankAccountDto() {
    }

    private BankAccountDto(BigDecimal id, String bankCode, String bankLabel, String agencyCode, String agencyLabel, String accountNumber, String accountKey, String accountLabel) {
        this.id = id;
        this.bankCode = bankCode;
        this.bankLabel = bankLabel;
        this.agencyCode = agencyCode;
        this.agencyLabel = agencyLabel;
        this.accountNumber = accountNumber;
        this.accountKey = accountKey;
        this.accountLabel = accountLabel;
    }

    public static BankAccountDto of(BigDecimal id, String bankCode, String bankLabel, String agencyCode, String agencyLabel, String accountNumber, String accountKey, String accountLabel) {

        return new BankAccountDto(id, bankCode, bankLabel, agencyCode, agencyLabel, accountNumber, accountKey, accountLabel);
    }

}
