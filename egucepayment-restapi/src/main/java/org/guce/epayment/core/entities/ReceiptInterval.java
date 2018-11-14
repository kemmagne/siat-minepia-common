package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "RECEIPT_INTERVAL")
@Data
@EqualsAndHashCode(of = {"id"})
public class ReceiptInterval implements Serializable {

    private static final long serialVersionUID = 4933598685453192805L;

    @Id
    @SequenceGenerator(name = "RECEIPT_INTERVAL_SEQ_GEN", sequenceName = "RECEIPT_INTERVAL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEIPT_INTERVAL_SEQ_GEN")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "MIN_TIME", updatable = false)
    private LocalTime minTime;
    @NotNull
    @Column(name = "MAX_TIME", updatable = false)
    private LocalTime maxTime;
    @NotNull
    @Column(name = "INSERTION_DATE", updatable = false)
    private LocalDateTime insertionDate;

    @PrePersist
    private void prePersist() {
        insertionDate = LocalDateTime.now();
    }

}

