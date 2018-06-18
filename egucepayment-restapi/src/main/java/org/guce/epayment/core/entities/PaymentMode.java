package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "PAYMENT_MODE")
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class PaymentMode implements Serializable {

    private static final long serialVersionUID = 7793592770019689837L;

    public static final String PM_ASSET = "ASSET";
    public static final String PM_CAMPOST_WALLET = "CAMPOST_WALLET";
    public static final String PM_E_TRANSFER = "E_TRANSFER";
    public static final String PM_BANK_TRANSFER = "BANK_TRANSFER";
    public static final String PM_COUNTER_TRANSFER = "COUNTER_TRANSFER";
    public static final String PM_CASH = "CASH";
    public static final String PM_CHECK = "CHECK";

    @Id
    @SequenceGenerator(name = "PAYMENT_MODE_SEQ", sequenceName = "PAYMENT_MODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_MODE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE", nullable = false, unique = true, length = 20)
    private String code;
    @Column(name = "LABEL", nullable = false)
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
