package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.dao.PartnerDao;
import org.guce.epayment.core.entities.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerDao partnerDao;

    @Override
    public List<Partner> findByTypes(final String partnerTypes, final int start, final int end, boolean justActive) {
        return partnerDao.findByTypes(partnerTypes, justActive, start, end);
    }

    @Override
    public long countByTypes(final String partnerTypes, final boolean justActive) {
        return partnerDao.countByTypes(partnerTypes, justActive);
    }

    @Override
    public List<Partner> findByGroups(final String partnerGroups) {
        return null;
    }

    @Override
    public boolean checkTaxPayerNumberInGroups(final String partnerGroups, final String taxPayerNumber) {
        return false;
    }

    @Override
    public boolean belongsToType(final BigDecimal partnerId, final String typeCode) {
        return false;
    }

}
