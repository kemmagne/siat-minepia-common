package org.guce.epayment.rest.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class DefaultDto implements Serializable {

    private static final long serialVersionUID = -4742576942655136226L;

    private String data;

    private DefaultDto(String data) {
        this.data = data;
    }

    public static DefaultDto of(String data) {

        return new DefaultDto(data);
    }

}

