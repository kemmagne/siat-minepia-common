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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "CREDENTIALS")
@Data
@EqualsAndHashCode(exclude = {"owner", "creationDate"})
@ToString(exclude = {"owner"})
public class Credentials implements Serializable {

    private static final long serialVersionUID = -5614930456101679007L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID", precision = 38)
    @SequenceGenerator(name = "CREDENTIALS_SEQ", sequenceName = "CREDENTIALS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENTIALS_SEQ")
    private BigDecimal id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;
    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "SALT")
    private String salt;
    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }

}

