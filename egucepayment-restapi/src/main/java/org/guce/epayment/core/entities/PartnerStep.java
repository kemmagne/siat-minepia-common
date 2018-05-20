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
    @Column(name = "ID")
    private BigDecimal id;

    @JoinColumn(name = "PARTNER_ID", nullable = false)
    @ManyToOne
    private Partner partner;
    @JoinColumn(name = "STEP_ID", nullable = false)
    @ManyToOne
    private Step step;
    @JoinColumn(name = "PAYMENT_MODE_ID")
    @ManyToOne
    private PaymentMode paymentMode;
    @Column(name = "HIGH_LEVEL", nullable = false)
    private int highLevel;

}
