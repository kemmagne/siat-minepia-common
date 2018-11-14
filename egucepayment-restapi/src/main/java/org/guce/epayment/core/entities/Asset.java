package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ASSETS")
@Data
@EqualsAndHashCode(of = {"id", "reference"})
public class Asset implements Serializable {

    private static final long serialVersionUID = -5092065880275410339L;

    @Id
    @SequenceGenerator(name = "ASSET_SEQ", sequenceName = "ASSET_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSET_SEQ")
    @Column(name = "ID", precision = 38)
    private BigDecimal id;

    @NotNull
    @Column(name = "REFERENCE", length = 50)
    private String reference;
    @NotNull
    @Column(name = "ASSET_DATE")
    private LocalDate assetDate;
    @NotNull
    @Column(name = "AMOUNT", precision = 38, scale = 4)
    private BigDecimal amount;
    @NotNull
    @JoinColumn(name = "DECLARER_ID")
    @ManyToOne
    private Partner declarer;
    @NotNull
    @JoinColumn(name = "OWNER_ID")
    @ManyToOne
    private Partner owner;
    @NotNull
    @JoinColumn(name = "CURRENT_USER_ID")
    @ManyToOne
    private Partner currentUser;
    @NotNull
    @JoinColumn(name = "TYPE_ID")
    @ManyToOne
    private AssetType type;
    @OneToMany(mappedBy = "asset", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<InvoiceTypeAsset> invoiceTypes;

    @PrePersist
    private void prePersist() {
        if (currentUser == null) {
            currentUser = owner;
        }
    }

}

