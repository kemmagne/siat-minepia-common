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
import org.guce.epayment.core.entities.enums.PartnerTypeCode;

@Entity
@Table(name = "PARTNER_TYPE")
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class PartnerType implements Serializable {

    private static final long serialVersionUID = -288718007278303785L;

    @Id
    @SequenceGenerator(name = "PARTNER_TYPE_SEQ", sequenceName = "PARTNER_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_TYPE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CODE", unique = true, length = 20)
    private PartnerTypeCode code;
    @NotNull
    @Column(name = "LABEL")
    private String label;
    @ManyToMany(mappedBy = "types")
    private List<Partner> partners;

}

