package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class DefaultDto {

    private String data;

    public DefaultDto() {
    }

    private DefaultDto(String data) {
        this.data = data;
    }

    public static DefaultDto of(String data) {

        return new DefaultDto(data);
    }

}
