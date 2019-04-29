//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.04.18 à 06:52:02 AM WAT 
//


package org.guce.siat.common.doc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TYPE_DOCUMENT">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MESSAGE">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TYPE_MESSAGE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="6"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DATE_EMISSION">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                         &lt;pattern value="\d{0,8}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ETAT" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="3"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NUMERO_MESSAGE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="61"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NUMERO_MESSAGE_ORIGINE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="61"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DATE_EMISSION_MSG_ORIGINE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="REFERENCE_DOSSIER">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NUMERO_DOSSIER">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NUMERO_DEMANDE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="SERVICE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="REFERENCE_GUCE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="REFERENCE_SIAT" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="10"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DATE_CREATION">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="SI">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="10"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ROUTAGE">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EMETTEUR">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DESTINATAIRE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ERREURS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence minOccurs="0">
 *                   &lt;element name="ERREUR" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CODE_ERREUR" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="8"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="LIBELLE_ERREUR" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="255"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="REFERENCE_DONNEE" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="70"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;any maxOccurs="unbounded" minOccurs="0"/>
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
    "typedocument",
    "message",
    "referencedossier",
    "routage",
    "erreurs",
    "any"
})
@XmlRootElement(name = "DOCUMENT")
public class DOCUMENT {

    @XmlElement(name = "TYPE_DOCUMENT", required = true)
    protected String typedocument;
    @XmlElement(name = "MESSAGE", required = true)
    protected DOCUMENT.MESSAGE message;
    @XmlElement(name = "REFERENCE_DOSSIER", required = true)
    protected DOCUMENT.REFERENCEDOSSIER referencedossier;
    @XmlElement(name = "ROUTAGE", required = true)
    protected DOCUMENT.ROUTAGE routage;
    @XmlElement(name = "ERREURS", required = true)
    protected DOCUMENT.ERREURS erreurs;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Obtient la valeur de la propriété typedocument.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPEDOCUMENT() {
        return typedocument;
    }

    /**
     * Définit la valeur de la propriété typedocument.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPEDOCUMENT(String value) {
        this.typedocument = value;
    }

    /**
     * Obtient la valeur de la propriété message.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.MESSAGE }
     *     
     */
    public DOCUMENT.MESSAGE getMESSAGE() {
        return message;
    }

    /**
     * Définit la valeur de la propriété message.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.MESSAGE }
     *     
     */
    public void setMESSAGE(DOCUMENT.MESSAGE value) {
        this.message = value;
    }

    /**
     * Obtient la valeur de la propriété referencedossier.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.REFERENCEDOSSIER }
     *     
     */
    public DOCUMENT.REFERENCEDOSSIER getREFERENCEDOSSIER() {
        return referencedossier;
    }

    /**
     * Définit la valeur de la propriété referencedossier.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.REFERENCEDOSSIER }
     *     
     */
    public void setREFERENCEDOSSIER(DOCUMENT.REFERENCEDOSSIER value) {
        this.referencedossier = value;
    }

    /**
     * Obtient la valeur de la propriété routage.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.ROUTAGE }
     *     
     */
    public DOCUMENT.ROUTAGE getROUTAGE() {
        return routage;
    }

    /**
     * Définit la valeur de la propriété routage.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.ROUTAGE }
     *     
     */
    public void setROUTAGE(DOCUMENT.ROUTAGE value) {
        this.routage = value;
    }

    /**
     * Obtient la valeur de la propriété erreurs.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.ERREURS }
     *     
     */
    public DOCUMENT.ERREURS getERREURS() {
        return erreurs;
    }

    /**
     * Définit la valeur de la propriété erreurs.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.ERREURS }
     *     
     */
    public void setERREURS(DOCUMENT.ERREURS value) {
        this.erreurs = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence minOccurs="0">
     *         &lt;element name="ERREUR" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CODE_ERREUR" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="8"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="LIBELLE_ERREUR" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="255"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="REFERENCE_DONNEE" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="70"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "erreur"
    })
    public static class ERREURS {

        @XmlElement(name = "ERREUR")
        protected List<DOCUMENT.ERREURS.ERREUR> erreur;

        /**
         * Gets the value of the erreur property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the erreur property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getERREUR().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DOCUMENT.ERREURS.ERREUR }
         * 
         * 
         */
        public List<DOCUMENT.ERREURS.ERREUR> getERREUR() {
            if (erreur == null) {
                erreur = new ArrayList<DOCUMENT.ERREURS.ERREUR>();
            }
            return this.erreur;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODE_ERREUR" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="8"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="LIBELLE_ERREUR" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="255"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="REFERENCE_DONNEE" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="70"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
            "codeerreur",
            "libelleerreur",
            "referencedonnee"
        })
        public static class ERREUR {

            @XmlElement(name = "CODE_ERREUR")
            protected String codeerreur;
            @XmlElement(name = "LIBELLE_ERREUR")
            protected String libelleerreur;
            @XmlElement(name = "REFERENCE_DONNEE")
            protected String referencedonnee;

            /**
             * Obtient la valeur de la propriété codeerreur.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODEERREUR() {
                return codeerreur;
            }

            /**
             * Définit la valeur de la propriété codeerreur.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODEERREUR(String value) {
                this.codeerreur = value;
            }

            /**
             * Obtient la valeur de la propriété libelleerreur.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLIBELLEERREUR() {
                return libelleerreur;
            }

            /**
             * Définit la valeur de la propriété libelleerreur.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLIBELLEERREUR(String value) {
                this.libelleerreur = value;
            }

            /**
             * Obtient la valeur de la propriété referencedonnee.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getREFERENCEDONNEE() {
                return referencedonnee;
            }

            /**
             * Définit la valeur de la propriété referencedonnee.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setREFERENCEDONNEE(String value) {
                this.referencedonnee = value;
            }

        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TYPE_MESSAGE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="6"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="DATE_EMISSION">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="50"/>
     *               &lt;pattern value="\d{0,8}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ETAT" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="3"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="NUMERO_MESSAGE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="61"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="NUMERO_MESSAGE_ORIGINE" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="61"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="DATE_EMISSION_MSG_ORIGINE" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="50"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
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
        "typemessage",
        "dateemission",
        "etat",
        "numeromessage",
        "numeromessageorigine",
        "dateemissionmsgorigine"
    })
    public static class MESSAGE {

        @XmlElement(name = "TYPE_MESSAGE", required = true)
        protected String typemessage;
        @XmlElement(name = "DATE_EMISSION", required = true, nillable = true)
        protected String dateemission;
        @XmlElement(name = "ETAT")
        protected String etat;
        @XmlElement(name = "NUMERO_MESSAGE", required = true)
        protected String numeromessage;
        @XmlElement(name = "NUMERO_MESSAGE_ORIGINE")
        protected String numeromessageorigine;
        @XmlElement(name = "DATE_EMISSION_MSG_ORIGINE")
        protected String dateemissionmsgorigine;

        /**
         * Obtient la valeur de la propriété typemessage.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTYPEMESSAGE() {
            return typemessage;
        }

        /**
         * Définit la valeur de la propriété typemessage.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTYPEMESSAGE(String value) {
            this.typemessage = value;
        }

        /**
         * Obtient la valeur de la propriété dateemission.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDATEEMISSION() {
            return dateemission;
        }

        /**
         * Définit la valeur de la propriété dateemission.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDATEEMISSION(String value) {
            this.dateemission = value;
        }

        /**
         * Obtient la valeur de la propriété etat.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getETAT() {
            return etat;
        }

        /**
         * Définit la valeur de la propriété etat.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setETAT(String value) {
            this.etat = value;
        }

        /**
         * Obtient la valeur de la propriété numeromessage.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNUMEROMESSAGE() {
            return numeromessage;
        }

        /**
         * Définit la valeur de la propriété numeromessage.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNUMEROMESSAGE(String value) {
            this.numeromessage = value;
        }

        /**
         * Obtient la valeur de la propriété numeromessageorigine.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNUMEROMESSAGEORIGINE() {
            return numeromessageorigine;
        }

        /**
         * Définit la valeur de la propriété numeromessageorigine.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNUMEROMESSAGEORIGINE(String value) {
            this.numeromessageorigine = value;
        }

        /**
         * Obtient la valeur de la propriété dateemissionmsgorigine.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDATEEMISSIONMSGORIGINE() {
            return dateemissionmsgorigine;
        }

        /**
         * Définit la valeur de la propriété dateemissionmsgorigine.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDATEEMISSIONMSGORIGINE(String value) {
            this.dateemissionmsgorigine = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="NUMERO_DOSSIER">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="NUMERO_DEMANDE" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="SERVICE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="REFERENCE_GUCE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="50"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="REFERENCE_SIAT" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="10"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="DATE_CREATION">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="50"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="SI">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="10"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
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
        "numerodossier",
        "numerodemande",
        "service",
        "referenceguce",
        "referencesiat",
        "datecreation",
        "si"
    })
    public static class REFERENCEDOSSIER {

        @XmlElement(name = "NUMERO_DOSSIER", required = true)
        protected String numerodossier;
        @XmlElement(name = "NUMERO_DEMANDE")
        protected String numerodemande;
        @XmlElement(name = "SERVICE", required = true)
        protected String service;
        @XmlElement(name = "REFERENCE_GUCE", required = true)
        protected String referenceguce;
        @XmlElement(name = "REFERENCE_SIAT")
        protected String referencesiat;
        @XmlElement(name = "DATE_CREATION", required = true)
        protected String datecreation;
        @XmlElement(name = "SI", required = true)
        protected String si;

        /**
         * Obtient la valeur de la propriété numerodossier.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNUMERODOSSIER() {
            return numerodossier;
        }

        /**
         * Définit la valeur de la propriété numerodossier.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNUMERODOSSIER(String value) {
            this.numerodossier = value;
        }

        /**
         * Obtient la valeur de la propriété numerodemande.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNUMERODEMANDE() {
            return numerodemande;
        }

        /**
         * Définit la valeur de la propriété numerodemande.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNUMERODEMANDE(String value) {
            this.numerodemande = value;
        }

        /**
         * Obtient la valeur de la propriété service.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSERVICE() {
            return service;
        }

        /**
         * Définit la valeur de la propriété service.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSERVICE(String value) {
            this.service = value;
        }

        /**
         * Obtient la valeur de la propriété referenceguce.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREFERENCEGUCE() {
            return referenceguce;
        }

        /**
         * Définit la valeur de la propriété referenceguce.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREFERENCEGUCE(String value) {
            this.referenceguce = value;
        }

        /**
         * Obtient la valeur de la propriété referencesiat.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREFERENCESIAT() {
            return referencesiat;
        }

        /**
         * Définit la valeur de la propriété referencesiat.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREFERENCESIAT(String value) {
            this.referencesiat = value;
        }

        /**
         * Obtient la valeur de la propriété datecreation.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDATECREATION() {
            return datecreation;
        }

        /**
         * Définit la valeur de la propriété datecreation.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDATECREATION(String value) {
            this.datecreation = value;
        }

        /**
         * Obtient la valeur de la propriété si.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSI() {
            return si;
        }

        /**
         * Définit la valeur de la propriété si.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSI(String value) {
            this.si = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="EMETTEUR">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="DESTINATAIRE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
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
        "emetteur",
        "destinataire"
    })
    public static class ROUTAGE {

        @XmlElement(name = "EMETTEUR", required = true)
        protected String emetteur;
        @XmlElement(name = "DESTINATAIRE", required = true)
        protected String destinataire;

        /**
         * Obtient la valeur de la propriété emetteur.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEMETTEUR() {
            return emetteur;
        }

        /**
         * Définit la valeur de la propriété emetteur.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEMETTEUR(String value) {
            this.emetteur = value;
        }

        /**
         * Obtient la valeur de la propriété destinataire.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDESTINATAIRE() {
            return destinataire;
        }

        /**
         * Définit la valeur de la propriété destinataire.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDESTINATAIRE(String value) {
            this.destinataire = value;
        }

    }

}
