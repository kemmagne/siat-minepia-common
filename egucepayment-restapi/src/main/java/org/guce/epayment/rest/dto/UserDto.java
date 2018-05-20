package org.guce.epayment.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id", "login"})
public class UserDto {

    private BigDecimal id;
    private String login;
    private String title;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean locked;
    private boolean active;
    private boolean resetPassword;
    private List<String> roles;
    private PartnerDto partner;
    private List<RoleDto> roleDtos;
    private String token;
    private String locale;

}
