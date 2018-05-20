package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = "name")
public class RoleDto {

    private String name;
    private String description;

    public RoleDto() {
    }

    private RoleDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static RoleDto of(String name, String description) {

        return new RoleDto(name, description);
    }

}
