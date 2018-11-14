package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.entities.Credentials;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.Role;
import org.guce.epayment.core.entities.Signature;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.UserStep;
import org.guce.epayment.rest.controllers.utils.RestUtils;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends User {

    private static final long serialVersionUID = 4293732427338321904L;

    private String password;
    private String token;

    @Override
    public PartnerDto getPartner() {
        return RestUtils.downCast(Partner.class, PartnerDto.class, super.getPartner());
    }

    @Override
    public List<Role> getRoles() {
        return super.getRoles().stream()
                .map(role -> {
                    RoleDto roleDto = RestUtils.downCast(Role.class, RoleDto.class, role);
                    return roleDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LocalDateTime getCreationDate() {
        return super.getCreationDate();
    }

    @JsonIgnore
    @Override
    public List<Credentials> getCredentialses() {
        return super.getCredentialses();
    }

    @JsonIgnore
    @Override
    public List<InvoiceType> getInvoiceTypes() {
        return super.getInvoiceTypes();
    }

    @JsonIgnore
    @Override
    public List<Signature> getSignatures() {
        return super.getSignatures();
    }

    @JsonIgnore
    @Override
    public List<UserStep> getUserSteps() {
        return super.getUserSteps();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

}
