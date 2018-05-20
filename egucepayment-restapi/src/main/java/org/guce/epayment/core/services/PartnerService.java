package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.Partner;

/**
 *
 * @author tadzotsa
 */
public interface PartnerService {

    List<Partner> findByTypes(String partnerTypes, int start, int end);

    List<Partner> findByGroups(String partnerGroups);

    boolean checkTaxPayerNumberInGroups(String partnerGroups, String taxPayerNumber);

    List<Partner> findByCodeOrTaxPayerNumber(String code, String taxPayerNumber);

    boolean belongsToType(BigDecimal partnerId, String typeCode);

    List<Partner> findBeneficiaries(String invoiceTypeCode);

    List<Partner> findPrincipals(int start, int end);

    long countPrincipals();

}
