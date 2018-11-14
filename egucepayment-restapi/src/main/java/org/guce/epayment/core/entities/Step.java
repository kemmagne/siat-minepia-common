package org.guce.epayment.core.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.entities.enums.StepCode;

@Entity
@Table(name = "STEP")
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class Step implements Serializable {

    private static final long serialVersionUID = 1099700858599817317L;

    @Id
    @SequenceGenerator(name = "STEP_SEQ_GEN", sequenceName = "STEP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STEP_SEQ_GEN")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CODE", unique = true, length = 20)
    private StepCode code;
    @NotNull
    @Column(name = "LABEL")
    private String label;

}

