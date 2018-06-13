//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2018.06.12 � 02:54:50 PM WAT 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TYPE_DOCUMENT">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
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
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ETAT">
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
 *                   &lt;element name="NUMERO_MESSAGE_ORIGINE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="61"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DATE_EMISSION_MSG_ORIGINE">
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
 *                   &lt;element name="NUMERO_DEMANDE">
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
 *                   &lt;element name="REFERENCE_SIAT">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
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
 *         &lt;element name="ERREURS" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ERREUR" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CODE_ERREUR" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
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
 *                                   &lt;maxLength value="255"/>
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
 *         &lt;element name="CONTENT" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence minOccurs="0">
 *                   &lt;element name="OBESERVATION" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
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
    "typedocument",
    "message",
    "referencedossier",
    "routage",
    "erreurs",
    "content"
})
@XmlRootElement(name = "DOCUMENT")
public class AperakDocument {

    @XmlElement(name = "TYPE_DOCUMENT", required = true)
    protected String typedocument;
    @XmlElement(name = "MESSAGE", required = true)
    protected AperakDocument.MESSAGE message;
    @XmlElement(name = "REFERENCE_DOSSIER", required = true)
    protected AperakDocument.REFERENCEDOSSIER referencedossier;
    @XmlElement(name = "ROUTAGE", required = true)
    protected AperakDocument.ROUTAGE routage;
    @XmlElement(name = "ERREURS")
    protected AperakDocument.ERREURS erreurs;
    @XmlElement(name = "CONTENT")
    protected AperakDocument.CONTENT content;

    /**
     * Obtient la valeur de la propri�t� typedocument.
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
     * D�finit la valeur de la propri�t� typedocument.
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
     * Obtient la valeur de la propri�t� message.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.MESSAGE }
     *     
     */
    public AperakDocument.MESSAGE getMESSAGE() {
        return message;
    }

    /**
     * D�finit la valeur de la propri�t� message.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.MESSAGE }
     *     
     */
    public void setMESSAGE(AperakDocument.MESSAGE value) {
        this.message = value;
    }

    /**
     * Obtient la valeur de la propri�t� referencedossier.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.REFERENCEDOSSIER }
     *     
     */
    public AperakDocument.REFERENCEDOSSIER getREFERENCEDOSSIER() {
        return referencedossier;
    }

    /**
     * D�finit la valeur de la propri�t� referencedossier.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.REFERENCEDOSSIER }
     *     
     */
    public void setREFERENCEDOSSIER(AperakDocument.REFERENCEDOSSIER value) {
        this.referencedossier = value;
    }

    /**
     * Obtient la valeur de la propri�t� routage.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.ROUTAGE }
     *     
     */
    public AperakDocument.ROUTAGE getROUTAGE() {
        return routage;
    }

    /**
     * D�finit la valeur de la propri�t� routage.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.ROUTAGE }
     *     
     */
    public void setROUTAGE(AperakDocument.ROUTAGE value) {
        this.routage = value;
    }

    /**
     * Obtient la valeur de la propri�t� erreurs.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.ERREURS }
     *     
     */
    public AperakDocument.ERREURS getERREURS() {
        return erreurs;
    }

    /**
     * D�finit la valeur de la propri�t� erreurs.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.ERREURS }
     *     
     */
    public void setERREURS(AperakDocument.ERREURS value) {
        this.erreurs = value;
    }

    /**
     * Obtient la valeur de la propri�t� content.
     * 
     * @return
     *     possible object is
     *     {@link DOCUMENT.CONTENT }
     *     
     */
    public AperakDocument.CONTENT getCONTENT() {
        return content;
    }

    /**
     * D�finit la valeur de la propri�t� content.
     * 
     * @param value
     *     allowed object is
     *     {@link DOCUMENT.CONTENT }
     *     
     */
    public void setCONTENT(AperakDocument.CONTENT value) {
        this.content = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence minOccurs="0">
     *         &lt;element name="OBESERVATION" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
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
        "obeservation"
    })
    public static class CONTENT {

        @XmlElement(name = "OBESERVATION")
        protected String obeservation;

        /**
         * Obtient la valeur de la propri�t� obeservation.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOBESERVATION() {
            return obeservation;
        }

        /**
         * D�finit la valeur de la propri�t� obeservation.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOBESERVATION(String value) {
            this.obeservation = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ERREUR" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CODE_ERREUR" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
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
     *                         &lt;maxLength value="255"/>
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
        protected AperakDocument.ERREURS.ERREUR erreur;

        /**
         * Obtient la valeur de la propri�t� erreur.
         * 
         * @return
         *     possible object is
         *     {@link DOCUMENT.ERREURS.ERREUR }
         *     
         */
        public AperakDocument.ERREURS.ERREUR getERREUR() {
            return erreur;
        }

        /**
         * D�finit la valeur de la propri�t� erreur.
         * 
         * @param value
         *     allowed object is
         *     {@link DOCUMENT.ERREURS.ERREUR }
         *     
         */
        public void setERREUR(AperakDocument.ERREURS.ERREUR value) {
            this.erreur = value;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODE_ERREUR" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
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
         *               &lt;maxLength value="255"/>
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
             * Obtient la valeur de la propri�t� codeerreur.
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
             * D�finit la valeur de la propri�t� codeerreur.
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
             * Obtient la valeur de la propri�t� libelleerreur.
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
             * D�finit la valeur de la propri�t� libelleerreur.
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
             * Obtient la valeur de la propri�t� referencedonnee.
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
             * D�finit la valeur de la propri�t� referencedonnee.
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
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ETAT">
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
     *         &lt;element name="NUMERO_MESSAGE_ORIGINE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="61"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="DATE_EMISSION_MSG_ORIGINE">
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
        @XmlElement(name = "DATE_EMISSION", required = true)
        protected String dateemission;
        @XmlElement(name = "ETAT", required = true)
        protected String etat;
        @XmlElement(name = "NUMERO_MESSAGE", required = true)
        protected String numeromessage;
        @XmlElement(name = "NUMERO_MESSAGE_ORIGINE", required = true)
        protected String numeromessageorigine;
        @XmlElement(name = "DATE_EMISSION_MSG_ORIGINE", required = true)
        protected String dateemissionmsgorigine;

        /**
         * Obtient la valeur de la propri�t� typemessage.
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
         * D�finit la valeur de la propri�t� typemessage.
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
         * Obtient la valeur de la propri�t� dateemission.
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
         * D�finit la valeur de la propri�t� dateemission.
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
         * Obtient la valeur de la propri�t� etat.
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
         * D�finit la valeur de la propri�t� etat.
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
         * Obtient la valeur de la propri�t� numeromessage.
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
         * D�finit la valeur de la propri�t� numeromessage.
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
         * Obtient la valeur de la propri�t� numeromessageorigine.
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
         * D�finit la valeur de la propri�t� numeromessageorigine.
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
         * Obtient la valeur de la propri�t� dateemissionmsgorigine.
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
         * D�finit la valeur de la propri�t� dateemissionmsgorigine.
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
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     *         &lt;element name="NUMERO_DEMANDE">
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
     *         &lt;element name="REFERENCE_SIAT">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
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
        @XmlElement(name = "NUMERO_DEMANDE", required = true)
        protected String numerodemande;
        @XmlElement(name = "SERVICE", required = true)
        protected String service;
        @XmlElement(name = "REFERENCE_GUCE", required = true)
        protected String referenceguce;
        @XmlElement(name = "REFERENCE_SIAT", required = true)
        protected String referencesiat;
        @XmlElement(name = "DATE_CREATION", required = true)
        protected String datecreation;
        @XmlElement(name = "SI", required = true)
        protected String si;

        /**
         * Obtient la valeur de la propri�t� numerodossier.
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
         * D�finit la valeur de la propri�t� numerodossier.
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
         * Obtient la valeur de la propri�t� numerodemande.
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
         * D�finit la valeur de la propri�t� numerodemande.
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
         * Obtient la valeur de la propri�t� service.
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
         * D�finit la valeur de la propri�t� service.
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
         * Obtient la valeur de la propri�t� referenceguce.
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
         * D�finit la valeur de la propri�t� referenceguce.
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
         * Obtient la valeur de la propri�t� referencesiat.
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
         * D�finit la valeur de la propri�t� referencesiat.
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
         * Obtient la valeur de la propri�t� datecreation.
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
         * D�finit la valeur de la propri�t� datecreation.
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
         * Obtient la valeur de la propri�t� si.
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
         * D�finit la valeur de la propri�t� si.
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
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
         * Obtient la valeur de la propri�t� emetteur.
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
         * D�finit la valeur de la propri�t� emetteur.
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
         * Obtient la valeur de la propri�t� destinataire.
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
         * D�finit la valeur de la propri�t� destinataire.
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
