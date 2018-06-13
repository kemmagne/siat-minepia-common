//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source.
// G�n�r� le : 2018.06.12 � 08:36:50 AM WAT
//
package org.guce.epayment.core.documents;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour anonymous complex type.
 *
 * <p>
 * Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette
 * classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KEY_CUO">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DATE_LIQUIDATION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SERIE_LIQUIDATION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUMERO_LIQUIDATION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VERSION_LIQUIDATION">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MONTANT_LIQUIDATION" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ANNEE_QUITTANCE">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BUREAU_QUITTANCE">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SERIE_QUITTANCE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUMERO_QUITTANCE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DATE_QUITTANCE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "keycuo",
    "dateliquidation",
    "serieliquidation",
    "numeroliquidation",
    "versionliquidation",
    "montantliquidation",
    "anneequittance",
    "bureauquittance",
    "seriequittance",
    "numeroquittance",
    "datequittance"
})
@XmlRootElement(name = "ENREGISTREMENT_PAIEMENT")
public class QuittanceDouaneDocument {

    @XmlElement(name = "KEY_CUO", required = true)
    protected String keycuo;
    @XmlElement(name = "DATE_LIQUIDATION", required = true)
    protected String dateliquidation;
    @XmlElement(name = "SERIE_LIQUIDATION", required = true)
    protected String serieliquidation;
    @XmlElement(name = "NUMERO_LIQUIDATION", required = true)
    protected String numeroliquidation;
    @XmlElement(name = "VERSION_LIQUIDATION", required = true)
    protected String versionliquidation;
    @XmlElement(name = "MONTANT_LIQUIDATION", required = true)
    protected BigDecimal montantliquidation;
    @XmlElement(name = "ANNEE_QUITTANCE", required = true)
    protected String anneequittance;
    @XmlElement(name = "BUREAU_QUITTANCE", required = true)
    protected String bureauquittance;
    @XmlElement(name = "SERIE_QUITTANCE", required = true)
    protected String seriequittance;
    @XmlElement(name = "NUMERO_QUITTANCE", required = true)
    protected String numeroquittance;
    @XmlElement(name = "DATE_QUITTANCE", required = true)
    protected String datequittance;

    /**
     * Obtient la valeur de la propri�t� keycuo.
     *
     * @return possible object is {@link String }
     *
     */
    public String getKEYCUO() {
        return keycuo;
    }

    /**
     * D�finit la valeur de la propri�t� keycuo.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setKEYCUO(String value) {
        this.keycuo = value;
    }

    /**
     * Obtient la valeur de la propri�t� dateliquidation.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDATELIQUIDATION() {
        return dateliquidation;
    }

    /**
     * D�finit la valeur de la propri�t� dateliquidation.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDATELIQUIDATION(String value) {
        this.dateliquidation = value;
    }

    /**
     * Obtient la valeur de la propri�t� serieliquidation.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSERIELIQUIDATION() {
        return serieliquidation;
    }

    /**
     * D�finit la valeur de la propri�t� serieliquidation.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSERIELIQUIDATION(String value) {
        this.serieliquidation = value;
    }

    /**
     * Obtient la valeur de la propri�t� numeroliquidation.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNUMEROLIQUIDATION() {
        return numeroliquidation;
    }

    /**
     * D�finit la valeur de la propri�t� numeroliquidation.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNUMEROLIQUIDATION(String value) {
        this.numeroliquidation = value;
    }

    /**
     * Obtient la valeur de la propri�t� versionliquidation.
     *
     * @return possible object is {@link String }
     *
     */
    public String getVERSIONLIQUIDATION() {
        return versionliquidation;
    }

    /**
     * D�finit la valeur de la propri�t� versionliquidation.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setVERSIONLIQUIDATION(String value) {
        this.versionliquidation = value;
    }

    /**
     * Obtient la valeur de la propri�t� montantliquidation.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getMONTANTLIQUIDATION() {
        return montantliquidation;
    }

    /**
     * D�finit la valeur de la propri�t� montantliquidation.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setMONTANTLIQUIDATION(BigDecimal value) {
        this.montantliquidation = value;
    }

    /**
     * Obtient la valeur de la propri�t� anneequittance.
     *
     * @return possible object is {@link String }
     *
     */
    public String getANNEEQUITTANCE() {
        return anneequittance;
    }

    /**
     * D�finit la valeur de la propri�t� anneequittance.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setANNEEQUITTANCE(String value) {
        this.anneequittance = value;
    }

    /**
     * Obtient la valeur de la propri�t� bureauquittance.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBUREAUQUITTANCE() {
        return bureauquittance;
    }

    /**
     * D�finit la valeur de la propri�t� bureauquittance.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBUREAUQUITTANCE(String value) {
        this.bureauquittance = value;
    }

    /**
     * Obtient la valeur de la propri�t� seriequittance.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSERIEQUITTANCE() {
        return seriequittance;
    }

    /**
     * D�finit la valeur de la propri�t� seriequittance.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSERIEQUITTANCE(String value) {
        this.seriequittance = value;
    }

    /**
     * Obtient la valeur de la propri�t� numeroquittance.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNUMEROQUITTANCE() {
        return numeroquittance;
    }

    /**
     * D�finit la valeur de la propri�t� numeroquittance.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNUMEROQUITTANCE(String value) {
        this.numeroquittance = value;
    }

    /**
     * Obtient la valeur de la propri�t� datequittance.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDATEQUITTANCE() {
        return datequittance;
    }

    /**
     * D�finit la valeur de la propri�t� datequittance.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDATEQUITTANCE(String value) {
        this.datequittance = value;
    }

}
