package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PaymentInvoiceVersion;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.Signature;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.transfer.entities.TransferOrder;

/**
 *
 * @author tadzotsa
 */
public class TransferOrderDto extends TransferOrder {

    private static final long serialVersionUID = -6925144546275022123L;

    @Override
    public PartnerDto getCommiter() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getCommiter());
    }

    @Override
    public PartnerDto getBankGateway() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getBankGateway());
    }

    @Override
    public PaymentModeDto getMode() {
        return RestUtils.downCast(PaymentMode.class, PaymentModeDto.class, super.getMode());
    }

    @Override
    public PartnerDto getTaxPayer() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getTaxPayer());
    }

    @Override
    public List<Signature> getSignatures() {
        return super.getSignatures().stream().map(sign -> {
            final SignatureDto signDto = RestUtils.downCast(Signature.class, SignatureDto.class, sign);
            return signDto;
        }).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public List<PaymentInvoiceVersion> getInvoicesVersions() {
        return super.getInvoicesVersions();
    }

    @JsonIgnore
    @Override
    public String getFlowInUse() {
        return super.getFlowInUse();
    }

    @JsonIgnore
    @Override
    public BankAccount getDebitAccount() {
        return super.getDebitAccount();
    }

    @JsonIgnore
    @Override
    public List<BankAccount> getBankAccounts() {
        return super.getBankAccounts();
    }

}
