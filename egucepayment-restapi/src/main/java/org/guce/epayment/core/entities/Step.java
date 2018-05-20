package org.guce.epayment.core.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "STEP")
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class Step implements Serializable {

    private static final long serialVersionUID = 1099700858599817317L;

    public static final String STEP_ASSET_CLEARANCE_INIT = "A11";
    public static final String STEP_CANCELED = "CANCELED";
    public static final String STEP_REJECTED = "REJECTED";
    public static final String STEP_TO_PRINCIPAL = "T11";
    public static final String STEP_TO_PRINCIPAL_BANK = "T21";
    public static final String STEP_TO_BENEFICIARY_BANK = "T31";
    public static final String STEP_TO_BENEFICIARY = "T41";

    @Id
    @SequenceGenerator(name = "STEP_SEQ", sequenceName = "STEP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STEP_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE", nullable = false, unique = true, length = 20)
    private String code;
    @Column(name = "LABEL", nullable = false)
    private String label;

}
