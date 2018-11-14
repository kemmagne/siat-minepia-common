package org.guce.epayment.rest.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class SetPasswordDto implements Serializable {

    private static final long serialVersionUID = -131913585238295094L;

    private String actual;
    private String update;

}

