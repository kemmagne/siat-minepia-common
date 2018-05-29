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

@Entity
@Table(name = "PARTNER_TYPE")
@Data
@EqualsAndHashCode(exclude = {"label", "partners"})
public class PartnerType implements Serializable {

    private static final long serialVersionUID = -288718007278303785L;

    public static final String PARTNER_TYPE_PRINCIPAL = "DONNEUR_ORDRE";
    public static final String PARTNER_TYPE_BENEFICIARY = "BENEFICIAIRE";
    public static final String PARTNER_TYPE_BANK = "BANQUE";
    public static final String PARTNER_TYPE_BANK_AGENCY = "AGENCE_BANQUE";

    @Id
    @SequenceGenerator(name = "PARTNER_TYPE_SEQ", sequenceName = "PARTNER_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_TYPE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE", nullable = false, unique = true, length = 20)
    private String code;
    @Column(name = "LABEL", nullable = false)
    private String label;
    @ManyToMany(mappedBy = "types")
    private List<Partner> partners;

}
