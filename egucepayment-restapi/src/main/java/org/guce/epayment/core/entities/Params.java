package org.guce.epayment.core.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "PARAMS")
@Data
public class Params implements Serializable {

    private static final long serialVersionUID = -4567924647984557952L;

    @Id
    @Column(name = "PARAM_NAME")
    private String name;
    @Column(name = "PARAM_VALUE")
    private String value;

    public Params() {
    }

    public Params(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
