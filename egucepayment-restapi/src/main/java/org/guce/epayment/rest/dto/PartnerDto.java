package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.Asset;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerStep;
import org.guce.epayment.core.entities.PartnerType;
import org.guce.epayment.core.entities.Payment;
import org.guce.epayment.core.entities.RepPartnerGroup;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class PartnerDto extends Partner {

    private static final long serialVersionUID = 53838630055756932L;

    @Override
    public PartnerDto getParent() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getParent());
    }

    @Override
    public List<PartnerType> getTypes() {
        return super.getTypes().stream().map(pt -> {
            final PartnerTypeDto ptDto = RestUtils.downCast(PartnerType.class, PartnerTypeDto.class, pt);
            return ptDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<RepPartnerGroup> getGroups() {
        return super.getGroups().stream().map(pg -> {
            final PartnerGroupDto pgDto = RestUtils.downCast(RepPartnerGroup.class, PartnerGroupDto.class, pg);
            return pgDto;
        }).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public List<Asset> getAssetsClaimed() {
        return super.getAssetsClaimed();
    }

    @JsonIgnore
    @Override
    public List<Asset> getAssetsDeclared() {
        return super.getAssetsDeclared();
    }

    @JsonIgnore
    @Override
    public List<BankAccount> getBankAccounts() {
        return super.getBankAccounts();
    }

    @JsonIgnore
    @Override
    public List<Partner> getChildren() {
        return super.getChildren();
    }

    @JsonIgnore
    @Override
    public List<Asset> getOwnAssets() {
        return super.getOwnAssets();
    }

    @JsonIgnore
    @Override
    public List<PartnerStep> getPartnerSteps() {
        return super.getPartnerSteps();
    }

    @JsonIgnore
    @Override
    public List<Payment> getPaymentsCommited() {
        return super.getPaymentsCommited();
    }

    @JsonIgnore
    @Override
    public BankAccount getPrincipalBankAccount() {
        return super.getPrincipalBankAccount();
    }

    @JsonIgnore
    @Override
    public List<User> getUsers() {
        return super.getUsers();
    }

}
