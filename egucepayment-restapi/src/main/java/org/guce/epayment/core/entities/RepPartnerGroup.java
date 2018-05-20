package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "REP_PARTNER_GROUP")
@Data
@EqualsAndHashCode(of = {"code"})
public class RepPartnerGroup implements Serializable {

    private static final long serialVersionUID = -288718007278303785L;

    @Id
    @Column(name = "CODE", length = 20)
    private String code;
    @Column(name = "NAME", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "groups")
    private List<Partner> partners;

}
