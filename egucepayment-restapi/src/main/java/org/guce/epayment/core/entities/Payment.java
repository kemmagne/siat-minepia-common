package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "PAYMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "P_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PAYMENT")
@Data
@EqualsAndHashCode(of = {"id", "reference"})
public class Payment implements Serializable {

    private static final long serialVersionUID = -2802586394469456991L;

    public static final String PAYMENT_PENDING = "PENDING";
    public static final String PAYMENT_VALIDATED = "VALIDATED";
    public static final String PAYMENT_CONFIRMED = "CONFIRMED";
    public static final String PAYMENT_ACKNOWLED = "ACKNOWLED";
    public static final String PAYMENT_CANCELED = "CANCELED";
    public static final String PAYMENT_REJECTED = "REJECTED";

    @Id
    @SequenceGenerator(name = "PAYMENT_SEQ_GEN", sequenceName = "PAYMENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_SEQ_GEN")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @Column(name = "P_REFERENCE", nullable = false, unique = true, length = 30)
    private String reference;
    @Column(name = "PARTNER_REFERENCE", length = 30)
    private String partnerReference;
    @Column(name = "AMOUNT", nullable = false, precision = 38, scale = 4)
    private BigDecimal amount;
    @Column(name = "STATUS", nullable = false, length = 15)
    private String status;
    @JoinColumn(name = "COMMITER_ID")
    @ManyToOne
    private Partner commiter;
    @JoinColumn(name = "MODE_ID", nullable = false)
    @ManyToOne
    private PaymentMode mode;
    @OneToMany(mappedBy = "payment", cascade = CascadeType.PERSIST)
    private List<PaymentInvoiceVersion> invoicesVersions;
    @OneToMany(mappedBy = "payment", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("creationDate DESC")
    private List<Signature> signatures;
    @JoinColumn(name = "BANK_GATEWAY_ID")
    @ManyToOne
    private Partner bankGateway;
    @Column(name = "STARTED_DATE", nullable = false)
    private LocalDateTime startedDate;
    @Column(name = "VALIDATION_DATE")
    private LocalDateTime validationDate;
//    @Column(name = "CONFIRMATION_DATE")
//    private LocalDateTime confirmationDate;

    @PrePersist
    private void prePersist() {
        startedDate = LocalDateTime.now();
    }

}
