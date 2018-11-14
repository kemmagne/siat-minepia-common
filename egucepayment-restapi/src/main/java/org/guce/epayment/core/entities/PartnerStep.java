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
@Table(name = "PARTNER_STEP")
@Data
@EqualsAndHashCode(of = {"id"})
public class PartnerStep implements Serializable {

    private static final long serialVersionUID = -4200864736089469893L;

    @Id
    @SequenceGenerator(name = "PARTNER_STEP_SEQ", sequenceName = "PARTNER_STEP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_STEP_SEQ")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @NotNull
    @JoinColumn(name = "PARTNER_ID")
    @ManyToOne
    private Partner partner;
    @NotNull
    @JoinColumn(name = "STEP_ID")
    @ManyToOne
    private Step step;
    @JoinColumn(name = "PAYMENT_MODE_ID")
    @ManyToOne
    private PaymentMode paymentMode;
    @NotNull
    @Column(name = "HIGH_LEVEL")
    private int highLevel;

}

