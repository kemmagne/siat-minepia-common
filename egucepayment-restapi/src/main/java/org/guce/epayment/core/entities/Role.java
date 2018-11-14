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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.guce.epayment.core.entities.enums.RoleName;

/**
 *
 * @author tadzotsa
 */
@Entity
@Table(name = "ROLES")
@Data
@EqualsAndHashCode(of = {"id", "name"})
@ToString(exclude = {"users"})
public class Role implements Serializable {

    private static final long serialVersionUID = 5678981891772549444L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "ROLE_SEQ_GEN", sequenceName = "ROLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_GEN")
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", unique = true)
    private RoleName name;
    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public Role(RoleName name, String description) {
        this.name = name;
        this.description = description;
    }

}

