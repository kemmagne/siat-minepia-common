package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.guce.epayment.core.entities.enums.PaymentModeCode;

@Entity
@Table(name = "PAYMENT_MODE")
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class PaymentMode implements Serializable {

    private static final long serialVersionUID = 7793592770019689837L;

    @Id
    @SequenceGenerator(name = "PAYMENT_MODE_SEQ", sequenceName = "PAYMENT_MODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_MODE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CODE", unique = true, length = 20)
    private PaymentModeCode code;
    @NotNull
    @Column(name = "LABEL")
    private String label;
    @Column(name = "GLOBAL_FLOW")
    private String globalFlow;
    @Column(name = "DIRECT")
    private boolean direct;

    @ManyToMany(mappedBy = "paymentModes")
    private List<InvoiceType> invoiceTypes;
    @OneToMany(mappedBy = "mode")
    private List<Payment> payments;

}

