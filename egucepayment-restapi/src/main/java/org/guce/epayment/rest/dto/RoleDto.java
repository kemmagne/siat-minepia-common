package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import org.guce.epayment.core.entities.Role;
import org.guce.epayment.core.entities.User;

/**
 *
 * @author tadzotsa
 */
public class RoleDto extends Role {

    private static final long serialVersionUID = -39609454138762285L;

    @JsonIgnore
    @Override
    public List<User> getUsers() {
        return super.getUsers();
    }

}
