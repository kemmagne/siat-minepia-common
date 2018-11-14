package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerType;

/**
 *
 * @author tadzotsa
 */
public class PartnerTypeDto extends PartnerType {

    private static final long serialVersionUID = 5399176189055456616L;

    @JsonIgnore
    @Override
    public List<Partner> getPartners() {
        return super.getPartners();
    }

}
