/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.form.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import org.guce.siat.common.model.Item;

//@Entity
//@Table(name = "CORE_GOOD")
//@Cacheable(false)
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "GOOD_TYPE")
public class CoreGood implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @SequenceGenerator(name = "CORE_GOOD_SEQ", sequenceName = "CORE_GOOD_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CORE_GOOD_SEQ")
    private String ID;
    //@Column(name = "LIGNE_NUMBER")
    private Integer lineNumber;
//    @ManyToOne
//    private CoreProcessing coreProcessing;
   //@Size(max = 1500)
    //@Column(name = "GOOD_CODE", length = 1500)
    private String goodCode;
//    @JoinColumn(name = "GOOD_HS_CODE")
//    @ManyToOne
    private Item hSCode;
    //@Column(name = "GOOD_WEIGHT")
    private BigDecimal weight;
    //@Column(name = "GOOD_QUANTITY", scale = 4)
    private BigDecimal quantite;
    //@Column(name = "GOOD_VOLUME", scale = 4)
    private BigDecimal volume;
   //@Size(max = 35)
    //@Column(name = "GOOD_PACKAGING_MODE", length = 35)
    private String modeEmballage;
    //@Column(name = "GOOD_CFA_VALUE")
    private BigDecimal valeurCfa;
    //@Column(name = "GOOD_FOB_VALUE_IN_CUR", scale = 4)
    private BigDecimal fobValueInCurrency;
//    //@Column(name = "GOOD_UNIT", length = 3)
//   //@Size(max = 3)
//    private String unit;
//    @JoinColumn(name = "GOOD_UNIT")
//    @ManyToOne
    private String unit;
    //@Column(name = "GOOD_TRADE_NAME", length = 75)
   //@Size(max = 75)
    private String tradeName;
    //@Column(name = "GOOD_BRAND", length = 75)
   //@Size(max = 75)
    private String brand;
//    @JoinColumn(name = "RECORD_ID", referencedColumnName = "RECORD_ID")
//    @ManyToOne
//    private CoreRecord recordId;
//    @ManyToMany(mappedBy = "listGood")
//    private List<CoreProcessing> coreProcessings;
//    @JoinColumn(name = "GOOD_ID", referencedColumnName = "ID")
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<CoreContainer> conteneurs;
    //@Column(name = "GOOD_PACKAGE_COUNT")
    private Long packageCount;
    
    //@Column(name = "AMM_CODE")
    private String ammCode;

    @Deprecated
    //@XmlTransient
    public String getGoodCode() {
        return goodCode;
    }

    @Deprecated
    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    //@XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return goodCode;
    }

    public void setDescription(String description) {
        this.goodCode = description;
    }

    //@XmlElement(name = "CODE_TARIF")
    public Item gethSCode() {
        return hSCode;
    }

    public void sethSCode(Item hSCode) {
        this.hSCode = hSCode;
    }

    //@XmlElement(name = "POIDS")
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    //@XmlElement(name = "QUANTITE")
    public BigDecimal getQuantiteBigDecimal() {
        return quantite;
    }

    //@XmlTransient
    public Integer getQuantite() {
        return (quantite != null) ? quantite.intValue() : null;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = new BigDecimal(quantite);
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public void setQuantiteBigDecimal(BigDecimal quantite) {
        this.quantite = quantite;
    }

    //@XmlElement(name = "VOLUME")
    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    //@XmlElement(name = "MODE_EMBALAGE")
    public String getModeEmballage() {
        return modeEmballage;
    }

    public void setModeEmballage(String modeEmballage) {
        this.modeEmballage = modeEmballage;
    }

    //@XmlElement(name = "VALEUR_CFA")
    public BigDecimal getValeurCfa() {
        return valeurCfa;
    }

    public void setValeurCfa(BigDecimal valeurCfa) {
        this.valeurCfa = valeurCfa;
    }

    //@XmlElement(name = "VALEUR_FOB_DEVISE")
    public BigDecimal getFobValueInCurrency() {
        return fobValueInCurrency;
    }

    public void setFobValueInCurrency(BigDecimal fobValueInCurrency) {
        this.fobValueInCurrency = fobValueInCurrency;
    }

    //@XmlTransient
    public String getRepUnit() {
        return this.unit;
    }

    //@XmlElement(name = "AMM")
    public String getAmmCode() {
        return ammCode;
    }

    public void setAmmCode(String ammCode) {
        this.ammCode = ammCode;
    }

    //@XmlElement(name = "UNITE")
    public String getUnit() {
        if (unit != null) {
            return unit;
        }
        return null;
    }  

    

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setRepUnit(String unit) {
        this.unit = unit;
    }

    //@XmlElement(name = "NOM_COMMERCIALE")
    public String getTradeName() {
        return tradeName;
    }

    //@XmlElement(name = "NOMBRE_COLIS")
    public Long getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(Long packageCount) {
        this.packageCount = packageCount;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    //@XmlTransient
//    public CoreRecord getRecordId() {
//        return recordId;
//    }
//
//    public void setRecordId(CoreRecord recordId) {
//        this.recordId = recordId;
//    }

    //@XmlTransient
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //@XmlTransient
//    public List<CoreProcessing> getCoreProcessings() {
//        return coreProcessings;
//    }
//
//    public void setCoreProcessings(List<CoreProcessing> coreProcessings) {
//        this.coreProcessings = coreProcessings;
//    }

    //@XmlElementWrapper(name = "CONTENEURS")
    //@XmlElements(value = {
        //@XmlElement(name = "CONTENEUR", type = CoreContainer.class)})
//    public List<CoreContainer> getConteneurs() {
//        return conteneurs;
//    }
//
//    public void setConteneurs(List<CoreContainer> conteneurs) {
//        this.conteneurs = conteneurs;
//    }

    //@XmlElement(name = "LINE_NUMBER")
    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "org.guce.core.entities.CoreGood[ id=" + goodCode + " ]";
    }

//    @Deprecated
//    //@XmlTransient
//    public CoreProcessing getCoreProcessing() {
//        return coreProcessing;
//    }
//
//    @Deprecated
//    public void setCoreProcessing(CoreProcessing coreProcessing) {
//        this.coreProcessing = coreProcessing;
//    }

    //@XmlTransient
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.ID);
        hash = 83 * hash + Objects.hashCode(this.goodCode);
        hash = 83 * hash + Objects.hashCode(this.hSCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CoreGood other = (CoreGood) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.goodCode, other.goodCode)) {
            return false;
        }
        if (!Objects.equals(this.hSCode, other.hSCode)) {
            return false;
        }
        return true;
    }

    
}