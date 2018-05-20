package org.guce.epayment.core.entities;

import java.io.Serializable;
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
@EqualsAndHashCode(of = {"id", "owner", "password", "salt"})
@ToString(exclude = {"owner"/*, "creationDate"*/})
public class Credentials implements Serializable {

    private static final long serialVersionUID = -5614930456101679007L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "CREDENTIALS_SEQ", sequenceName = "CREDENTIALS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDENTIALS_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID", nullable = false)
    private User owner;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "SALT")
    private String salt;
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }

}
