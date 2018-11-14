package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceTypeBeneficiary;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PaymentMode;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class InvoiceTypeDto extends InvoiceType {

    private static final long serialVersionUID = 2284266659611587809L;

    @JsonIgnore
    @Override
    public List<InvoiceTypeBeneficiary> getBeneficiaries() {
        return super.getBeneficiaries();
    }

    @Override
    public List<User> getDecisionMakers() {
        return super.getDecisionMakers().stream().map(dm -> {
            final UserDto dmDto = RestUtils.downCast(User.class, UserDto.class, dm);
            return dmDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PaymentMode> getPaymentModes() {
        return super.getPaymentModes().stream().map(pm -> {
            final PaymentModeDto pmDto = RestUtils.downCast(PaymentMode.class, PaymentModeDto.class, pm);
            return pmDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Partner> getUnauthorizedBanks() {
        return super.getUnauthorizedBanks().stream().map(bank -> {
            final PartnerDto bankDto = RestUtils.downCast(Partner.class, PartnerDto.class, bank);
            return bankDto;
        }).collect(Collectors.toList());
    }

    public List<PartnerDto> getBenefs() {
        List<InvoiceTypeBeneficiary> beneficiaries = getBeneficiaries();
        return beneficiaries.stream().map(ivtb -> {
            final PartnerDto benefDto = RestUtils.downCast(Partner.class, PartnerDto.class, ivtb.getBeneficiary());
            return benefDto;
        }).collect(Collectors.toList());
    }

}
