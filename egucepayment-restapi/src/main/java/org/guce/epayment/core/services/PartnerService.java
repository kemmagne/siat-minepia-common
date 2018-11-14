package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.Partner;

/**
 *
 * @author tadzotsa
 */
public interface PartnerService {

    List<Partner> findByTypes(String partnerTypes, int start, int end, boolean justActive);

    long countByTypes(String partnerTypes, boolean justActive);

    List<Partner> findByGroups(String partnerGroups);

    boolean checkTaxPayerNumberInGroups(String partnerGroups, String taxPayerNumber);

    boolean belongsToType(BigDecimal partnerId, String typeCode);

}

