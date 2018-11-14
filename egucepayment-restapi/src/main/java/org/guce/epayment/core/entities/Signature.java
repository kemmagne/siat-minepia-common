package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "SIGNATURE")
@Data
@EqualsAndHashCode(of = {"id"})
public class Signature implements Serializable {

    private static final long serialVersionUID = 9142524288064471098L;

    @Id
    @SequenceGenerator(name = "SIGNATURE_SEQ_GEN", sequenceName = "SIGNATURE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIGNATURE_SEQ_GEN")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @NotNull
    @Lob
    @Column(name = "ORIGIN_MESSAGE")
    private String originMessage;
    @NotNull
    @Lob
    @Column(name = "SIGNATURE")
    private String signature;
    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
    @NotNull
    @Column(name = "LEVEL_VALUE")
    private int level;
    @NotNull
    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;
    @NotNull
    @JoinColumn(name = "STEP_ID")
    @ManyToOne
    private Step step;
    @NotNull
    @JoinColumn(name = "PAYMENT_ID")
    @ManyToOne
    private Payment payment;
    @Column(name = "CHILD")
    private boolean child;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
        child = user.getPartner().getParent() != null;
    }

}

