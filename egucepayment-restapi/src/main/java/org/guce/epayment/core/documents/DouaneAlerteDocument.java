//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source.
// G�n�r� le : 2018.06.12 � 08:36:14 AM WAT
//
package org.guce.epayment.core.documents;

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
 *         &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DONNEE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BLOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NIVEAU" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "message",
    "donnee",
    "bloc",
    "niveau"
})
@XmlRootElement(name = "LISTE_DES_ERREURS")
public class DouaneAlerteDocument {

    @XmlElement(name = "MESSAGE", required = true)
    protected String message;
    @XmlElement(name = "DONNEE")
    protected String donnee;
    @XmlElement(name = "BLOC")
    protected String bloc;
    @XmlElement(name = "NIVEAU")
    protected Integer niveau;

    /**
     * Obtient la valeur de la propri�t� message.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMESSAGE() {
        return message;
    }

    /**
     * D�finit la valeur de la propri�t� message.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setMESSAGE(String value) {
        this.message = value;
    }

    /**
     * Obtient la valeur de la propri�t� donnee.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDONNEE() {
        return donnee;
    }

    /**
     * D�finit la valeur de la propri�t� donnee.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDONNEE(String value) {
        this.donnee = value;
    }

    /**
     * Obtient la valeur de la propri�t� bloc.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBLOC() {
        return bloc;
    }

    /**
     * D�finit la valeur de la propri�t� bloc.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBLOC(String value) {
        this.bloc = value;
    }

    /**
     * Obtient la valeur de la propri�t� niveau.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getNIVEAU() {
        return niveau;
    }

    /**
     * D�finit la valeur de la propri�t� niveau.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNIVEAU(Integer value) {
        this.niveau = value;
    }

}
