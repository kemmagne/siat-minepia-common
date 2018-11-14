package org.guce.epayment.core.dao;

import java.util.List;
import org.guce.epayment.core.entities.Partner;

/**
 *
 * @author tadzotsa
 */
public interface PartnerDao {

    List<Partner> findByTypes(String partnerTypes, boolean justActive, int start, int end);

    long countByTypes(String partnerTypes, boolean justActive);

}
