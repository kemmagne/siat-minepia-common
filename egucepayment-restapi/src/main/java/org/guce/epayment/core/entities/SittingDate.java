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
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "SITTING_DATE")
@Data
@EqualsAndHashCode(of = {"id"})
public class SittingDate implements Serializable {

    private static final long serialVersionUID = 4933598685453192805L;

    @Id
    @SequenceGenerator(name = "SITTING_DATE_SEQ", sequenceName = "SITTING_DATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SITTING_DATE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MIN_TIME", updatable = false, nullable = false)
    private LocalTime minTime;
    @Column(name = "MAX_TIME", updatable = false, nullable = false)
    private LocalTime maxTime;
    @Column(name = "INSERTION_DATE", updatable = false, nullable = false)
    private LocalDateTime insertionDate;

    @PrePersist
    private void prePersist() {
        insertionDate = LocalDateTime.now();
    }

}
