package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.RepPartnerGroup;

/**
 *
 * @author tadzotsa
 */
public class PartnerGroupDto extends RepPartnerGroup {

    private static final long serialVersionUID = 5177728856241995086L;

    @JsonIgnore
    @Override
    public List<Partner> getPartners() {
        return super.getPartners();
    }

}
