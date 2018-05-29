package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "ROLES")
@Data
@EqualsAndHashCode(of = {"id", "name"})
@ToString(exclude = {"users"})
public class Role implements Serializable {

    private static final long serialVersionUID = 5678981891772549444L;

    public static final String ROLE_ADMIN = "ADMIN".intern();
    public static final String ROLE_DONNEUR_ORDRE = "DONNEUR_ORDRE".intern();
    public static final String ROLE_CONTROLEUR_DONNEUR_ORDRE = "CONTROLEUR_DONNEUR_ORDRE".intern();
    public static final String ROLE_AUDITEUR_DONNEUR_ORDRE = "AUDITEUR_DONNEUR_ORDRE".intern();
    public static final String ROLE_CAISSIER = "CAISSIER".intern();
    public static final String ROLE_CONTROLEUR_AGENCE = "CONTROLEUR_AGENCE".intern();
    public static final String ROLE_CONTROLEUR_BANQUE = "CONTROLEUR_BANQUE".intern();
    public static final String ROLE_AUDITEUR_BANQUE = "AUDITEUR_BANQUE".intern();
    public static final String ROLE_BENEFICIAIRE = "BENEFICIAIRE".intern();
    public static final String ROLE_DECIDEUR = "DECIDEUR".intern();

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
