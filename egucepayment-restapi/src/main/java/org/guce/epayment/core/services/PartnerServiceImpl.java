package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerType;
import org.guce.epayment.core.repositories.InvoiceTypeRepository;
import org.guce.epayment.core.repositories.PartnerRepository;
import org.guce.epayment.core.repositories.PartnerTypeRepository;
import org.guce.epayment.core.repositories.RepPartnerGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerTypeRepository partnerTypeRepository;
    @Autowired
    private RepPartnerGroupRepository partnerGroupRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private InvoiceTypeRepository invoiceTypeRepository;

    @Autowired
    private ApplicationService appService;

    @Override
    public List<Partner> findByTypes(final String partnerTypes, final int start, final int end) {

        final List<Partner> partners = new ArrayList<>();

        Arrays.asList(StringUtils.split(partnerTypes, appService.getColSep())).forEach(partnerType -> {

            partners.addAll(partnerTypeRepository.findByCode(partnerType).get().getPartners());
        });

        return partners.stream().distinct()
                .sorted((partner1, partner2) -> partner2.getId().compareTo(partner1.getId()))
                .collect(Collectors.toList()).subList(start, end);

    }

    @Override
    public List<Partner> findByGroups(final String partnerGroups) {

        final List<Partner> partners = new ArrayList<>();

        Arrays.asList(StringUtils.split(partnerGroups, appService.getColSep())).forEach(partnerGroup -> {

            partners.addAll(partnerGroupRepository.findById(partnerGroup).get().getPartners());
        });

        return partners.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public boolean checkTaxPayerNumberInGroups(final String partnerGroups, final String taxPayerNumber) {

        return findByGroups(partnerGroups).stream()
                .anyMatch(partner -> taxPayerNumber.equalsIgnoreCase(partner.getTaxPayerNumber()));
    }

    @Override
    public List<Partner> findByCodeOrTaxPayerNumber(final String code, final String taxPayerNumber) {

        return partnerRepository.findByCodeOrTaxPayerNumber(code, taxPayerNumber);
    }

    @Override
    public boolean belongsToType(final BigDecimal partnerId, final String typeCode) {

        return partnerRepository.findById(partnerId).get().getTypes()
                .stream().map(partnerType -> partnerType.getCode())
                .collect(Collectors.toList()).contains(typeCode);
    }

    @Override
    public List<Partner> findBeneficiaries(final String invoiceTypeCode) {

        return invoiceTypeRepository.findByCode(invoiceTypeCode).get().getBeneficiaries()
                .stream().map(ivtb -> ivtb.getBeneficiary()).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Partner> findPrincipals(final int start, final int end) {

        return partnerTypeRepository.findByCode(PartnerType.PARTNER_TYPE_PRINCIPAL).get().getPartners().subList(start, end);
    }

    @Override
    public long countPrincipals() {

        return partnerTypeRepository.findByCode(PartnerType.PARTNER_TYPE_PRINCIPAL).get().getPartners().size();
    }

}
