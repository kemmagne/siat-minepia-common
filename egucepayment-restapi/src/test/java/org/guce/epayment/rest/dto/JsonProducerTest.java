package org.guce.epayment.rest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import org.guce.epayment.core.entities.Role;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.entities.enums.RoleName;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tadzotsa
 */
public class JsonProducerTest {

    private ObjectMapper objectMapper;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
    }

    @Ignore
    @Test
    public void testRoleDto() throws IOException {
        final Role role = new Role(RoleName.ADMIN, "Administrateur");
        final User user = new User();
        user.setLogin("admin");
        role.setUsers(Collections.singletonList(user));
        final RoleDto roleDto = RestUtils.downCast(Role.class, RoleDto.class, role);
        objectMapper.writeValue(System.out, roleDto);
    }

    @Ignore
    @Test
    public void testUserDto() throws IOException {
        final User user = new User();
        final Role role = new Role(RoleName.ADMIN, "Administrateur");
        user.setRoles(Collections.singletonList(role));

        final UserDto userDto = RestUtils.downCast(User.class, UserDto.class, user);
        objectMapper.writeValue(System.out, userDto);
    }

}
