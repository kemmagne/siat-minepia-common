package org.guce.epayment.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@IdClass(InvoiceTypeAsset.InvoiceTypeAssetID.class)
@Table(name = "INVOICE_TYPE_ASSET")
@Data
@EqualsAndHashCode(of = {"invoiceType", "asset"})
public class InvoiceTypeAsset implements Serializable {

    private static final long serialVersionUID = 8357247327277776575L;

    @Id
    @JoinColumn(name = "INVOICE_TYE_ID")
    @ManyToOne
    private InvoiceType invoiceType;
    @Id
    @JoinColumn(name = "ASSET_ID")
    @ManyToOne
    private Asset asset;
    @NotNull
    @Column(name = "APPLICABLE_RATE")
    private int applicableRate;
    @Column(name = "PARAMS", length = 500)
    private String params;

    @Data
    public static class InvoiceTypeAssetID implements Serializable {

        private static final long serialVersionUID = -3599921563337135919L;

        private Integer invoiceType;
        private BigDecimal asset;

        public InvoiceTypeAssetID() {
        }

        public InvoiceTypeAssetID(Integer invoiceType, BigDecimal asset) {
            this.invoiceType = invoiceType;
            this.asset = asset;
        }

    }

}

