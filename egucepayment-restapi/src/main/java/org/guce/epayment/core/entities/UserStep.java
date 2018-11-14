package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "USER_STEP")
@Data
@EqualsAndHashCode(of = {"id"})
public class UserStep implements Serializable {

    private static final long serialVersionUID = -4200864736089469893L;

    @Id
    @SequenceGenerator(name = "USER_STEP_SEQ_GEN", sequenceName = "USER_STEP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_STEP_SEQ_GEN")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @NotNull
    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;
    @NotNull
    @JoinColumn(name = "STEP_ID")
    @ManyToOne
    private Step step;
    @JoinColumn(name = "PAYMENT_MODE_ID")
    @ManyToOne
    private PaymentMode paymentMode;
    @NotNull
    @Column(name = "LEVEL_VALUE")
    private int level;

}

