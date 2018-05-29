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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "SIGNATURE")
@Data
@EqualsAndHashCode(of = {"id"})
public class Signature implements Serializable {

    private static final long serialVersionUID = 9142524288064471098L;

    @Id
    @SequenceGenerator(name = "SIGNATURE_SEQ", sequenceName = "SIGNATURE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIGNATURE_SEQ")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @Lob
    @Column(name = "ORIGIN_MESSAGE", nullable = false)
    private String originMessage;
    @Lob
    @Column(name = "SIGNATURE", nullable = false)
    private String signature;
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "LEVEL_VALUE", nullable = false)
    private int level;
    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "STEP_ID", nullable = false)
    @ManyToOne
    private Step step;
    @JoinColumn(name = "PAYMENT_ID", nullable = false)
    @ManyToOne
    private Payment payment;
    @Column(name = "CHILD")
    private boolean child;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
        child = null != user.getPartner().getParent();
    }

}
