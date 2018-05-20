package org.guce.epayment.core.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ASSET_TYPE")
@Data
@EqualsAndHashCode(of = {"id", "code"})
public class AssetType implements Serializable {

    private static final long serialVersionUID = -3215240065470698874L;

    @Id
    @SequenceGenerator(name = "ASSET_TYPE_SEQ", sequenceName = "ASSET_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSET_TYPE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE", nullable = false, unique = true, length = 20)
    private String code;
    @Column(name = "LABEL", nullable = false)
    private String label;
    @Column(name = "PARAMS")
    private String params;

}
