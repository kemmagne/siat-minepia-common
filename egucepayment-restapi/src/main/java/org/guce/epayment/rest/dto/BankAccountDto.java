package org.guce.epayment.rest.dto;

import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
public class BankAccountDto extends BankAccount {

    private static final long serialVersionUID = -2086325639194449708L;

    @Override
    public PartnerDto getAgency() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getAgency());
    }

    @Override
    public PartnerDto getBank() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getBank());
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public PartnerDto getOwner() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getOwner());
    }

}
