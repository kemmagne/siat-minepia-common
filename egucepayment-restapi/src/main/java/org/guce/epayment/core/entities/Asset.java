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

    @Column(name = "REFERENCE", nullable = false, length = 50)
    private String reference;
    @Column(name = "ASSET_DATE", nullable = false)
    private LocalDate assetDate;
    @Column(name = "AMOUNT", nullable = false, precision = 38, scale = 4)
    private BigDecimal amount;
    @JoinColumn(name = "DECLARER_ID", nullable = false)
    @ManyToOne
    private Partner declarer;
    @JoinColumn(name = "OWNER_ID", nullable = false)
    @ManyToOne
    private Partner owner;
    @JoinColumn(name = "CURRENT_USER_ID", nullable = false)
    @ManyToOne
    private Partner currentUser;
//    @OneToMany(mappedBy = "assetUsed")
//    private List<CoreAssetClearance> assetUtilizations;
    @JoinColumn(name = "TYPE_ID", nullable = false)
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
