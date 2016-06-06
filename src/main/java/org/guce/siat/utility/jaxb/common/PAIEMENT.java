package org.guce.siat.utility.jaxb.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Classe Java pour anonymous complex type.
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FACTURE" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="REFERENCE_FACTURE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DETAIL_FACTURES" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence minOccurs="0">
 *                             &lt;element name="DETAIL_FACTURE" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence minOccurs="0">
 *                                       &lt;element name="NUMERO_LIGNE" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="255"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="CODE_ARTICLE" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="35"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="LIBELLE_ARTICLE" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="255"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="MONTANT_HT" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="35"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="MONTANT_TVA" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="35"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="MONTANT_TTC" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="35"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MONTANT_HT" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="MONTANT_TVA" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="MONTANT_TTC" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="AUTRE_MONTANT" minOccurs="0">
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
 *         &lt;element name="SIGNATAIRE" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NOM" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="100"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="QUALITE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="100"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="LIEU" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="100"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DATE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="SOCIETE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="100"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ENCAISSEMENT" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NUMERO_RECU" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NATURE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DATE_ENCAISSEMENT" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="8"/>
 *                         &lt;pattern value="((20|19)[0-9][0-9])(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="MONTANT" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="OBSERVATIONS" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="CANAL_ENCAISSEMENT" minOccurs="0">
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
 *         &lt;element name="FICHE_RECETTE" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NUMERO_ROLE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="IMPUTATION" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ARTICLE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NUMERO_QUITTANCE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NATURE_RECETTE_RUBRIQUE" minOccurs="0">
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
 *         &lt;element name="PARTIE_VERSANTE" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NUMERO_CONTRIBUABLE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="RAISON_SOCIALE" minOccurs="0">
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
 *         &lt;element name="CHARGEUR" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NUMERO_CONTRIBUABLE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="RAISON_SOCIALE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ADRESSE" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ADRESSE1" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="255"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="ADRESSE2" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="255"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="BP" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="10"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="PAYS_ADRESSE" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CODE_PAYS" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="3"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NOM_PAYS" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="35"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="VILLE" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="35"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="EMAIL" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SITE_WEB" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="TELEPHONE_FIXE" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="INDICATIF_PAYS" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="5"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUMERO" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="15"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="TELEPHONE_MOBILE" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="INDICATIF_PAYS" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="5"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUMERO" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="15"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="FAX" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="INDICATIF_PAYS" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="5"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUMERO" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="15"/>
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
 *         &lt;element name="BENEFICIAIRE" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="LIBELLE" minOccurs="0">
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "facture", "signataire", "encaissement", "ficherecette", "partieversante", "chargeur", "beneficiaire" })
public class PAIEMENT
{

	@XmlElement(name = "FACTURE")
	protected PAIEMENT.FACTURE facture;
	@XmlElement(name = "SIGNATAIRE")
	protected PAIEMENT.SIGNATAIRE signataire;
	@XmlElement(name = "ENCAISSEMENT")
	protected PAIEMENT.ENCAISSEMENT encaissement;
	@XmlElement(name = "FICHE_RECETTE")
	protected PAIEMENT.FICHERECETTE ficherecette;
	@XmlElement(name = "PARTIE_VERSANTE")
	protected PAIEMENT.PARTIEVERSANTE partieversante;
	@XmlElement(name = "CHARGEUR")
	protected PAIEMENT.CHARGEUR chargeur;
	@XmlElement(name = "BENEFICIAIRE")
	protected PAIEMENT.BENEFICIAIRE beneficiaire;

	/**
	 * Obtient la valeur de la propriété facture.
	 *
	 * @return possible object is {@link PAIEMENT.FACTURE }
	 *
	 */
	public PAIEMENT.FACTURE getFACTURE()
	{
		return facture;
	}

	/**
	 * Définit la valeur de la propriété facture.
	 *
	 * @param value
	 *           allowed object is {@link PAIEMENT.FACTURE }
	 *
	 */
	public void setFACTURE(final PAIEMENT.FACTURE value)
	{
		this.facture = value;
	}

	/**
	 * Obtient la valeur de la propriété signataire.
	 *
	 * @return possible object is {@link PAIEMENT.SIGNATAIRE }
	 *
	 */
	public PAIEMENT.SIGNATAIRE getSIGNATAIRE()
	{
		return signataire;
	}

	/**
	 * Définit la valeur de la propriété signataire.
	 *
	 * @param value
	 *           allowed object is {@link PAIEMENT.SIGNATAIRE }
	 *
	 */
	public void setSIGNATAIRE(final PAIEMENT.SIGNATAIRE value)
	{
		this.signataire = value;
	}

	/**
	 * Obtient la valeur de la propriété encaissement.
	 *
	 * @return possible object is {@link PAIEMENT.ENCAISSEMENT }
	 *
	 */
	public PAIEMENT.ENCAISSEMENT getENCAISSEMENT()
	{
		return encaissement;
	}

	/**
	 * Définit la valeur de la propriété encaissement.
	 *
	 * @param value
	 *           allowed object is {@link PAIEMENT.ENCAISSEMENT }
	 *
	 */
	public void setENCAISSEMENT(final PAIEMENT.ENCAISSEMENT value)
	{
		this.encaissement = value;
	}

	/**
	 * Obtient la valeur de la propriété ficherecette.
	 *
	 * @return possible object is {@link PAIEMENT.FICHERECETTE }
	 *
	 */
	public PAIEMENT.FICHERECETTE getFICHERECETTE()
	{
		return ficherecette;
	}

	/**
	 * Définit la valeur de la propriété ficherecette.
	 *
	 * @param value
	 *           allowed object is {@link PAIEMENT.FICHERECETTE }
	 *
	 */
	public void setFICHERECETTE(final PAIEMENT.FICHERECETTE value)
	{
		this.ficherecette = value;
	}

	/**
	 * Obtient la valeur de la propriété partieversante.
	 *
	 * @return possible object is {@link PAIEMENT.PARTIEVERSANTE }
	 *
	 */
	public PAIEMENT.PARTIEVERSANTE getPARTIEVERSANTE()
	{
		return partieversante;
	}

	/**
	 * Définit la valeur de la propriété partieversante.
	 *
	 * @param value
	 *           allowed object is {@link PAIEMENT.PARTIEVERSANTE }
	 *
	 */
	public void setPARTIEVERSANTE(final PAIEMENT.PARTIEVERSANTE value)
	{
		this.partieversante = value;
	}

	/**
	 * Obtient la valeur de la propriété chargeur.
	 *
	 * @return possible object is {@link PAIEMENT.CHARGEUR }
	 *
	 */
	public PAIEMENT.CHARGEUR getCHARGEUR()
	{
		return chargeur;
	}

	/**
	 * Définit la valeur de la propriété chargeur.
	 *
	 * @param value
	 *           allowed object is {@link PAIEMENT.CHARGEUR }
	 *
	 */
	public void setCHARGEUR(final PAIEMENT.CHARGEUR value)
	{
		this.chargeur = value;
	}

	/**
	 * Obtient la valeur de la propriété beneficiaire.
	 *
	 * @return possible object is {@link PAIEMENT.BENEFICIAIRE }
	 *
	 */
	public PAIEMENT.BENEFICIAIRE getBENEFICIAIRE()
	{
		return beneficiaire;
	}

	/**
	 * Définit la valeur de la propriété beneficiaire.
	 *
	 * @param value
	 *           allowed object is {@link PAIEMENT.BENEFICIAIRE }
	 *
	 */
	public void setBENEFICIAIRE(final PAIEMENT.BENEFICIAIRE value)
	{
		this.beneficiaire = value;
	}


	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 *
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="CODE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="LIBELLE" minOccurs="0">
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
	@XmlType(name = "", propOrder =
	{ "code", "libelle" })
	public static class BENEFICIAIRE implements Serializable
	{

		@XmlElement(name = "CODE")
		protected String code;
		@XmlElement(name = "LIBELLE")
		protected String libelle;

		/**
		 * Obtient la valeur de la propriété code.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getCODE()
		{
			return code;
		}

		/**
		 * Définit la valeur de la propriété code.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setCODE(final String value)
		{
			this.code = value;
		}

		/**
		 * Obtient la valeur de la propriété libelle.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getLIBELLE()
		{
			return libelle;
		}

		/**
		 * Définit la valeur de la propriété libelle.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setLIBELLE(final String value)
		{
			this.libelle = value;
		}

	}


	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 *
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="NUMERO_CONTRIBUABLE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="RAISON_SOCIALE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="50"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="ADRESSE" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="ADRESSE1" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="255"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="ADRESSE2" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="255"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="BP" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="10"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="PAYS_ADRESSE" minOccurs="0">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence>
	 *                             &lt;element name="CODE_PAYS" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="3"/>
	 *                                 &lt;/restriction>
	 *                               &lt;/simpleType>
	 *                             &lt;/element>
	 *                             &lt;element name="NOM_PAYS" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="35"/>
	 *                                 &lt;/restriction>
	 *                               &lt;/simpleType>
	 *                             &lt;/element>
	 *                           &lt;/sequence>
	 *                         &lt;/restriction>
	 *                       &lt;/complexContent>
	 *                     &lt;/complexType>
	 *                   &lt;/element>
	 *                   &lt;element name="VILLE" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="35"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="EMAIL" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="50"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="SITE_WEB" minOccurs="0">
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
	 *         &lt;element name="TELEPHONE_FIXE" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="INDICATIF_PAYS" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="5"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="NUMERO" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="15"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="TELEPHONE_MOBILE" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="INDICATIF_PAYS" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="5"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="NUMERO" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="15"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="FAX" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="INDICATIF_PAYS" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="5"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                   &lt;element name="NUMERO" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;maxLength value="15"/>
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
	@XmlType(name = "", propOrder =
	{ "numerocontribuable", "raisonsociale", "adresse", "telephonefixe", "telephonemobile", "fax" })
	public static class CHARGEUR implements Serializable
	{

		@XmlElement(name = "NUMERO_CONTRIBUABLE")
		protected String numerocontribuable;
		@XmlElement(name = "RAISON_SOCIALE")
		protected String raisonsociale;
		@XmlElement(name = "ADRESSE")
		protected PAIEMENT.CHARGEUR.ADRESSE adresse;
		@XmlElement(name = "TELEPHONE_FIXE")
		protected PAIEMENT.CHARGEUR.TELEPHONEFIXE telephonefixe;
		@XmlElement(name = "TELEPHONE_MOBILE")
		protected PAIEMENT.CHARGEUR.TELEPHONEMOBILE telephonemobile;
		@XmlElement(name = "FAX")
		protected PAIEMENT.CHARGEUR.FAX fax;

		/**
		 * Obtient la valeur de la propriété numerocontribuable.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNUMEROCONTRIBUABLE()
		{
			return numerocontribuable;
		}

		/**
		 * Définit la valeur de la propriété numerocontribuable.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNUMEROCONTRIBUABLE(final String value)
		{
			this.numerocontribuable = value;
		}

		/**
		 * Obtient la valeur de la propriété raisonsociale.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getRAISONSOCIALE()
		{
			return raisonsociale;
		}

		/**
		 * Définit la valeur de la propriété raisonsociale.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setRAISONSOCIALE(final String value)
		{
			this.raisonsociale = value;
		}

		/**
		 * Obtient la valeur de la propriété adresse.
		 *
		 * @return possible object is {@link PAIEMENT.CHARGEUR.ADRESSE }
		 *
		 */
		public PAIEMENT.CHARGEUR.ADRESSE getADRESSE()
		{
			return adresse;
		}

		/**
		 * Définit la valeur de la propriété adresse.
		 *
		 * @param value
		 *           allowed object is {@link PAIEMENT.CHARGEUR.ADRESSE }
		 *
		 */
		public void setADRESSE(final PAIEMENT.CHARGEUR.ADRESSE value)
		{
			this.adresse = value;
		}

		/**
		 * Obtient la valeur de la propriété telephonefixe.
		 *
		 * @return possible object is {@link PAIEMENT.CHARGEUR.TELEPHONEFIXE }
		 *
		 */
		public PAIEMENT.CHARGEUR.TELEPHONEFIXE getTELEPHONEFIXE()
		{
			return telephonefixe;
		}

		/**
		 * Définit la valeur de la propriété telephonefixe.
		 *
		 * @param value
		 *           allowed object is {@link PAIEMENT.CHARGEUR.TELEPHONEFIXE }
		 *
		 */
		public void setTELEPHONEFIXE(final PAIEMENT.CHARGEUR.TELEPHONEFIXE value)
		{
			this.telephonefixe = value;
		}

		/**
		 * Obtient la valeur de la propriété telephonemobile.
		 *
		 * @return possible object is {@link PAIEMENT.CHARGEUR.TELEPHONEMOBILE }
		 *
		 */
		public PAIEMENT.CHARGEUR.TELEPHONEMOBILE getTELEPHONEMOBILE()
		{
			return telephonemobile;
		}

		/**
		 * Définit la valeur de la propriété telephonemobile.
		 *
		 * @param value
		 *           allowed object is {@link PAIEMENT.CHARGEUR.TELEPHONEMOBILE }
		 *
		 */
		public void setTELEPHONEMOBILE(final PAIEMENT.CHARGEUR.TELEPHONEMOBILE value)
		{
			this.telephonemobile = value;
		}

		/**
		 * Obtient la valeur de la propriété fax.
		 *
		 * @return possible object is {@link PAIEMENT.CHARGEUR.FAX }
		 *
		 */
		public PAIEMENT.CHARGEUR.FAX getFAX()
		{
			return fax;
		}

		/**
		 * Définit la valeur de la propriété fax.
		 *
		 * @param value
		 *           allowed object is {@link PAIEMENT.CHARGEUR.FAX }
		 *
		 */
		public void setFAX(final PAIEMENT.CHARGEUR.FAX value)
		{
			this.fax = value;
		}


		/**
		 * <p>
		 * Classe Java pour anonymous complex type.
		 *
		 * <p>
		 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
		 *
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="ADRESSE1" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="255"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="ADRESSE2" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="255"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="BP" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="10"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="PAYS_ADRESSE" minOccurs="0">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence>
		 *                   &lt;element name="CODE_PAYS" minOccurs="0">
		 *                     &lt;simpleType>
		 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *                         &lt;maxLength value="3"/>
		 *                       &lt;/restriction>
		 *                     &lt;/simpleType>
		 *                   &lt;/element>
		 *                   &lt;element name="NOM_PAYS" minOccurs="0">
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
		 *         &lt;element name="VILLE" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="35"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="EMAIL" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="50"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="SITE_WEB" minOccurs="0">
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
		@XmlType(name = "", propOrder =
		{ "adresse1", "adresse2", "bp", "paysadresse", "ville", "email", "siteweb" })
		public static class ADRESSE implements Serializable
		{

			@XmlElement(name = "ADRESSE1")
			protected String adresse1;
			@XmlElement(name = "ADRESSE2")
			protected String adresse2;
			@XmlElement(name = "BP")
			protected String bp;
			@XmlElement(name = "PAYS_ADRESSE")
			protected PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE paysadresse;
			@XmlElement(name = "VILLE")
			protected String ville;
			@XmlElement(name = "EMAIL")
			protected String email;
			@XmlElement(name = "SITE_WEB")
			protected String siteweb;

			/**
			 * Obtient la valeur de la propriété adresse1.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getADRESSE1()
			{
				return adresse1;
			}

			/**
			 * Définit la valeur de la propriété adresse1.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setADRESSE1(final String value)
			{
				this.adresse1 = value;
			}

			/**
			 * Obtient la valeur de la propriété adresse2.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getADRESSE2()
			{
				return adresse2;
			}

			/**
			 * Définit la valeur de la propriété adresse2.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setADRESSE2(final String value)
			{
				this.adresse2 = value;
			}

			/**
			 * Obtient la valeur de la propriété bp.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getBP()
			{
				return bp;
			}

			/**
			 * Définit la valeur de la propriété bp.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setBP(final String value)
			{
				this.bp = value;
			}

			/**
			 * Obtient la valeur de la propriété paysadresse.
			 *
			 * @return possible object is {@link PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE }
			 *
			 */
			public PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE getPAYSADRESSE()
			{
				return paysadresse;
			}

			/**
			 * Définit la valeur de la propriété paysadresse.
			 *
			 * @param value
			 *           allowed object is {@link PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE }
			 *
			 */
			public void setPAYSADRESSE(final PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE value)
			{
				this.paysadresse = value;
			}

			/**
			 * Obtient la valeur de la propriété ville.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getVILLE()
			{
				return ville;
			}

			/**
			 * Définit la valeur de la propriété ville.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setVILLE(final String value)
			{
				this.ville = value;
			}

			/**
			 * Obtient la valeur de la propriété email.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getEMAIL()
			{
				return email;
			}

			/**
			 * Définit la valeur de la propriété email.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setEMAIL(final String value)
			{
				this.email = value;
			}

			/**
			 * Obtient la valeur de la propriété siteweb.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getSITEWEB()
			{
				return siteweb;
			}

			/**
			 * Définit la valeur de la propriété siteweb.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setSITEWEB(final String value)
			{
				this.siteweb = value;
			}


			/**
			 * <p>
			 * Classe Java pour anonymous complex type.
			 *
			 * <p>
			 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
			 *
			 * <pre>
			 * &lt;complexType>
			 *   &lt;complexContent>
			 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       &lt;sequence>
			 *         &lt;element name="CODE_PAYS" minOccurs="0">
			 *           &lt;simpleType>
			 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
			 *               &lt;maxLength value="3"/>
			 *             &lt;/restriction>
			 *           &lt;/simpleType>
			 *         &lt;/element>
			 *         &lt;element name="NOM_PAYS" minOccurs="0">
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
			@XmlType(name = "", propOrder =
			{ "codepays", "nompays" })
			public static class PAYSADRESSE implements Serializable
			{

				@XmlElement(name = "CODE_PAYS")
				protected String codepays;
				@XmlElement(name = "NOM_PAYS")
				protected String nompays;

				/**
				 * Obtient la valeur de la propriété codepays.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getCODEPAYS()
				{
					return codepays;
				}

				/**
				 * Définit la valeur de la propriété codepays.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setCODEPAYS(final String value)
				{
					this.codepays = value;
				}

				/**
				 * Obtient la valeur de la propriété nompays.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getNOMPAYS()
				{
					return nompays;
				}

				/**
				 * Définit la valeur de la propriété nompays.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setNOMPAYS(final String value)
				{
					this.nompays = value;
				}

			}

		}


		/**
		 * <p>
		 * Classe Java pour anonymous complex type.
		 *
		 * <p>
		 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
		 *
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="INDICATIF_PAYS" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="5"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="NUMERO" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="15"/>
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
		@XmlType(name = "", propOrder =
		{ "indicatifpays", "numero" })
		public static class FAX implements Serializable
		{

			@XmlElement(name = "INDICATIF_PAYS")
			protected String indicatifpays;
			@XmlElement(name = "NUMERO")
			protected String numero;

			/**
			 * Obtient la valeur de la propriété indicatifpays.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getINDICATIFPAYS()
			{
				return indicatifpays;
			}

			/**
			 * Définit la valeur de la propriété indicatifpays.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setINDICATIFPAYS(final String value)
			{
				this.indicatifpays = value;
			}

			/**
			 * Obtient la valeur de la propriété numero.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getNUMERO()
			{
				return numero;
			}

			/**
			 * Définit la valeur de la propriété numero.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setNUMERO(final String value)
			{
				this.numero = value;
			}

		}


		/**
		 * <p>
		 * Classe Java pour anonymous complex type.
		 *
		 * <p>
		 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
		 *
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="INDICATIF_PAYS" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="5"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="NUMERO" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="15"/>
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
		@XmlType(name = "", propOrder =
		{ "indicatifpays", "numero" })
		public static class TELEPHONEFIXE implements Serializable
		{

			@XmlElement(name = "INDICATIF_PAYS")
			protected String indicatifpays;
			@XmlElement(name = "NUMERO")
			protected String numero;

			/**
			 * Obtient la valeur de la propriété indicatifpays.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getINDICATIFPAYS()
			{
				return indicatifpays;
			}

			/**
			 * Définit la valeur de la propriété indicatifpays.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setINDICATIFPAYS(final String value)
			{
				this.indicatifpays = value;
			}

			/**
			 * Obtient la valeur de la propriété numero.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getNUMERO()
			{
				return numero;
			}

			/**
			 * Définit la valeur de la propriété numero.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setNUMERO(final String value)
			{
				this.numero = value;
			}

		}


		/**
		 * <p>
		 * Classe Java pour anonymous complex type.
		 *
		 * <p>
		 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
		 *
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="INDICATIF_PAYS" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="5"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *         &lt;element name="NUMERO" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;maxLength value="15"/>
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
		@XmlType(name = "", propOrder =
		{ "indicatifpays", "numero" })
		public static class TELEPHONEMOBILE implements Serializable
		{

			@XmlElement(name = "INDICATIF_PAYS")
			protected String indicatifpays;
			@XmlElement(name = "NUMERO")
			protected String numero;

			/**
			 * Obtient la valeur de la propriété indicatifpays.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getINDICATIFPAYS()
			{
				return indicatifpays;
			}

			/**
			 * Définit la valeur de la propriété indicatifpays.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setINDICATIFPAYS(final String value)
			{
				this.indicatifpays = value;
			}

			/**
			 * Obtient la valeur de la propriété numero.
			 *
			 * @return possible object is {@link String }
			 *
			 */
			public String getNUMERO()
			{
				return numero;
			}

			/**
			 * Définit la valeur de la propriété numero.
			 *
			 * @param value
			 *           allowed object is {@link String }
			 *
			 */
			public void setNUMERO(final String value)
			{
				this.numero = value;
			}

		}

	}


	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 *
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="NUMERO_RECU" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="NATURE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="DATE_ENCAISSEMENT" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;length value="8"/>
	 *               &lt;pattern value="((20|19)[0-9][0-9])(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="MONTANT" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="OBSERVATIONS" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="255"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="CANAL_ENCAISSEMENT" minOccurs="0">
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
	@XmlType(name = "", propOrder =
	{ "numerorecu", "nature", "dateencaissement", "montant", "observations", "canalencaissement" })
	public static class ENCAISSEMENT implements Serializable
	{

		@XmlElement(name = "NUMERO_RECU")
		protected String numerorecu;
		@XmlElement(name = "NATURE")
		protected String nature;
		@XmlElement(name = "DATE_ENCAISSEMENT")
		protected String dateencaissement;
		@XmlElement(name = "MONTANT")
		protected String montant;
		@XmlElement(name = "OBSERVATIONS")
		protected String observations;
		@XmlElement(name = "CANAL_ENCAISSEMENT")
		protected String canalencaissement;

		/**
		 * Obtient la valeur de la propriété numerorecu.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNUMERORECU()
		{
			return numerorecu;
		}

		/**
		 * Définit la valeur de la propriété numerorecu.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNUMERORECU(final String value)
		{
			this.numerorecu = value;
		}

		/**
		 * Obtient la valeur de la propriété nature.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNATURE()
		{
			return nature;
		}

		/**
		 * Définit la valeur de la propriété nature.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNATURE(final String value)
		{
			this.nature = value;
		}

		/**
		 * Obtient la valeur de la propriété dateencaissement.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getDATEENCAISSEMENT()
		{
			return dateencaissement;
		}

		/**
		 * Définit la valeur de la propriété dateencaissement.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setDATEENCAISSEMENT(final String value)
		{
			this.dateencaissement = value;
		}

		/**
		 * Obtient la valeur de la propriété montant.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getMONTANT()
		{
			return montant;
		}

		/**
		 * Définit la valeur de la propriété montant.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setMONTANT(final String value)
		{
			this.montant = value;
		}

		/**
		 * Obtient la valeur de la propriété observations.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getOBSERVATIONS()
		{
			return observations;
		}

		/**
		 * Définit la valeur de la propriété observations.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setOBSERVATIONS(final String value)
		{
			this.observations = value;
		}

		/**
		 * Obtient la valeur de la propriété canalencaissement.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getCANALENCAISSEMENT()
		{
			return canalencaissement;
		}

		/**
		 * Définit la valeur de la propriété canalencaissement.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setCANALENCAISSEMENT(final String value)
		{
			this.canalencaissement = value;
		}

	}


	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 *
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="REFERENCE_FACTURE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="DETAIL_FACTURES" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence minOccurs="0">
	 *                   &lt;element name="DETAIL_FACTURE" maxOccurs="unbounded" minOccurs="0">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence minOccurs="0">
	 *                             &lt;element name="NUMERO_LIGNE" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="255"/>
	 *                                 &lt;/restriction>
	 *                               &lt;/simpleType>
	 *                             &lt;/element>
	 *                             &lt;element name="CODE_ARTICLE" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="35"/>
	 *                                 &lt;/restriction>
	 *                               &lt;/simpleType>
	 *                             &lt;/element>
	 *                             &lt;element name="LIBELLE_ARTICLE" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="255"/>
	 *                                 &lt;/restriction>
	 *                               &lt;/simpleType>
	 *                             &lt;/element>
	 *                             &lt;element name="MONTANT_HT" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="35"/>
	 *                                 &lt;/restriction>
	 *                               &lt;/simpleType>
	 *                             &lt;/element>
	 *                             &lt;element name="MONTANT_TVA" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="35"/>
	 *                                 &lt;/restriction>
	 *                               &lt;/simpleType>
	 *                             &lt;/element>
	 *                             &lt;element name="MONTANT_TTC" minOccurs="0">
	 *                               &lt;simpleType>
	 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                                   &lt;maxLength value="35"/>
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
	 *         &lt;element name="MONTANT_HT" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="MONTANT_TVA" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="MONTANT_TTC" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="AUTRE_MONTANT" minOccurs="0">
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
	@XmlType(name = "", propOrder =
	{ "referencefacture", "detailfactures", "montantht", "montanttva", "montantttc", "autremontant" })
	public static class FACTURE implements Serializable
	{

		@XmlElement(name = "REFERENCE_FACTURE")
		protected String referencefacture;
		@XmlElement(name = "DETAIL_FACTURES")
		protected PAIEMENT.FACTURE.DETAILFACTURES detailfactures;
		@XmlElement(name = "MONTANT_HT")
		protected String montantht;
		@XmlElement(name = "MONTANT_TVA")
		protected String montanttva;
		@XmlElement(name = "MONTANT_TTC")
		protected String montantttc;
		@XmlElement(name = "AUTRE_MONTANT")
		protected String autremontant;

		/**
		 * Obtient la valeur de la propriété referencefacture.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getREFERENCEFACTURE()
		{
			return referencefacture;
		}

		/**
		 * Définit la valeur de la propriété referencefacture.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setREFERENCEFACTURE(final String value)
		{
			this.referencefacture = value;
		}

		/**
		 * Obtient la valeur de la propriété detailfactures.
		 *
		 * @return possible object is {@link PAIEMENT.FACTURE.DETAILFACTURES }
		 *
		 */
		public PAIEMENT.FACTURE.DETAILFACTURES getDETAILFACTURES()
		{
			return detailfactures;
		}

		/**
		 * Définit la valeur de la propriété detailfactures.
		 *
		 * @param value
		 *           allowed object is {@link PAIEMENT.FACTURE.DETAILFACTURES }
		 *
		 */
		public void setDETAILFACTURES(final PAIEMENT.FACTURE.DETAILFACTURES value)
		{
			this.detailfactures = value;
		}

		/**
		 * Obtient la valeur de la propriété montantht.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getMONTANTHT()
		{
			return montantht;
		}

		/**
		 * Définit la valeur de la propriété montantht.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setMONTANTHT(final String value)
		{
			this.montantht = value;
		}

		/**
		 * Obtient la valeur de la propriété montanttva.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getMONTANTTVA()
		{
			return montanttva;
		}

		/**
		 * Définit la valeur de la propriété montanttva.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setMONTANTTVA(final String value)
		{
			this.montanttva = value;
		}

		/**
		 * Obtient la valeur de la propriété montantttc.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getMONTANTTTC()
		{
			return montantttc;
		}

		/**
		 * Définit la valeur de la propriété montantttc.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setMONTANTTTC(final String value)
		{
			this.montantttc = value;
		}

		/**
		 * Obtient la valeur de la propriété autremontant.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getAUTREMONTANT()
		{
			return autremontant;
		}

		/**
		 * Définit la valeur de la propriété autremontant.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setAUTREMONTANT(final String value)
		{
			this.autremontant = value;
		}


		/**
		 * <p>
		 * Classe Java pour anonymous complex type.
		 *
		 * <p>
		 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
		 *
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence minOccurs="0">
		 *         &lt;element name="DETAIL_FACTURE" maxOccurs="unbounded" minOccurs="0">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence minOccurs="0">
		 *                   &lt;element name="NUMERO_LIGNE" minOccurs="0">
		 *                     &lt;simpleType>
		 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *                         &lt;maxLength value="255"/>
		 *                       &lt;/restriction>
		 *                     &lt;/simpleType>
		 *                   &lt;/element>
		 *                   &lt;element name="CODE_ARTICLE" minOccurs="0">
		 *                     &lt;simpleType>
		 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *                         &lt;maxLength value="35"/>
		 *                       &lt;/restriction>
		 *                     &lt;/simpleType>
		 *                   &lt;/element>
		 *                   &lt;element name="LIBELLE_ARTICLE" minOccurs="0">
		 *                     &lt;simpleType>
		 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *                         &lt;maxLength value="255"/>
		 *                       &lt;/restriction>
		 *                     &lt;/simpleType>
		 *                   &lt;/element>
		 *                   &lt;element name="MONTANT_HT" minOccurs="0">
		 *                     &lt;simpleType>
		 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *                         &lt;maxLength value="35"/>
		 *                       &lt;/restriction>
		 *                     &lt;/simpleType>
		 *                   &lt;/element>
		 *                   &lt;element name="MONTANT_TVA" minOccurs="0">
		 *                     &lt;simpleType>
		 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *                         &lt;maxLength value="35"/>
		 *                       &lt;/restriction>
		 *                     &lt;/simpleType>
		 *                   &lt;/element>
		 *                   &lt;element name="MONTANT_TTC" minOccurs="0">
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
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 *
		 *
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder =
		{ "detailfacture" })
		public static class DETAILFACTURES implements Serializable
		{

			@XmlElement(name = "DETAIL_FACTURE")
			protected List<PAIEMENT.FACTURE.DETAILFACTURES.DETAILFACTURE> detailfacture;

			/**
			 * Gets the value of the detailfacture property.
			 *
			 * <p>
			 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you
			 * make to the returned list will be present inside the JAXB object. This is why there is not a
			 * <CODE>set</CODE> method for the detailfacture property.
			 *
			 * <p>
			 * For example, to add a new item, do as follows:
			 *
			 * <pre>
			 * getDETAILFACTURE().add(newItem);
			 * </pre>
			 *
			 *
			 * <p>
			 * Objects of the following type(s) are allowed in the list
			 * {@link PAIEMENT.FACTURE.DETAILFACTURES.DETAILFACTURE }
			 * 
			 * @return
			 *
			 *
			 */
			public List<PAIEMENT.FACTURE.DETAILFACTURES.DETAILFACTURE> getDETAILFACTURE()
			{
				if (detailfacture == null)
				{
					detailfacture = new ArrayList<PAIEMENT.FACTURE.DETAILFACTURES.DETAILFACTURE>();
				}
				return this.detailfacture;
			}


			/**
			 * <p>
			 * Classe Java pour anonymous complex type.
			 *
			 * <p>
			 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
			 *
			 * <pre>
			 * &lt;complexType>
			 *   &lt;complexContent>
			 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       &lt;sequence minOccurs="0">
			 *         &lt;element name="NUMERO_LIGNE" minOccurs="0">
			 *           &lt;simpleType>
			 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
			 *               &lt;maxLength value="255"/>
			 *             &lt;/restriction>
			 *           &lt;/simpleType>
			 *         &lt;/element>
			 *         &lt;element name="CODE_ARTICLE" minOccurs="0">
			 *           &lt;simpleType>
			 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
			 *               &lt;maxLength value="35"/>
			 *             &lt;/restriction>
			 *           &lt;/simpleType>
			 *         &lt;/element>
			 *         &lt;element name="LIBELLE_ARTICLE" minOccurs="0">
			 *           &lt;simpleType>
			 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
			 *               &lt;maxLength value="255"/>
			 *             &lt;/restriction>
			 *           &lt;/simpleType>
			 *         &lt;/element>
			 *         &lt;element name="MONTANT_HT" minOccurs="0">
			 *           &lt;simpleType>
			 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
			 *               &lt;maxLength value="35"/>
			 *             &lt;/restriction>
			 *           &lt;/simpleType>
			 *         &lt;/element>
			 *         &lt;element name="MONTANT_TVA" minOccurs="0">
			 *           &lt;simpleType>
			 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
			 *               &lt;maxLength value="35"/>
			 *             &lt;/restriction>
			 *           &lt;/simpleType>
			 *         &lt;/element>
			 *         &lt;element name="MONTANT_TTC" minOccurs="0">
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
			@XmlType(name = "", propOrder =
			{ "numeroligne", "codearticle", "libellearticle", "montantht", "montanttva", "montantttc" })
			public static class DETAILFACTURE implements Serializable
			{

				@XmlElement(name = "NUMERO_LIGNE")
				protected String numeroligne;
				@XmlElement(name = "CODE_ARTICLE")
				protected String codearticle;
				@XmlElement(name = "LIBELLE_ARTICLE")
				protected String libellearticle;
				@XmlElement(name = "MONTANT_HT")
				protected String montantht;
				@XmlElement(name = "MONTANT_TVA")
				protected String montanttva;
				@XmlElement(name = "MONTANT_TTC")
				protected String montantttc;

				/**
				 * Obtient la valeur de la propriété numeroligne.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getNUMEROLIGNE()
				{
					return numeroligne;
				}

				/**
				 * Définit la valeur de la propriété numeroligne.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setNUMEROLIGNE(final String value)
				{
					this.numeroligne = value;
				}

				/**
				 * Obtient la valeur de la propriété codearticle.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getCODEARTICLE()
				{
					return codearticle;
				}

				/**
				 * Définit la valeur de la propriété codearticle.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setCODEARTICLE(final String value)
				{
					this.codearticle = value;
				}

				/**
				 * Obtient la valeur de la propriété libellearticle.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getLIBELLEARTICLE()
				{
					return libellearticle;
				}

				/**
				 * Définit la valeur de la propriété libellearticle.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setLIBELLEARTICLE(final String value)
				{
					this.libellearticle = value;
				}

				/**
				 * Obtient la valeur de la propriété montantht.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getMONTANTHT()
				{
					return montantht;
				}

				/**
				 * Définit la valeur de la propriété montantht.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setMONTANTHT(final String value)
				{
					this.montantht = value;
				}

				/**
				 * Obtient la valeur de la propriété montanttva.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getMONTANTTVA()
				{
					return montanttva;
				}

				/**
				 * Définit la valeur de la propriété montanttva.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setMONTANTTVA(final String value)
				{
					this.montanttva = value;
				}

				/**
				 * Obtient la valeur de la propriété montantttc.
				 *
				 * @return possible object is {@link String }
				 *
				 */
				public String getMONTANTTTC()
				{
					return montantttc;
				}

				/**
				 * Définit la valeur de la propriété montantttc.
				 *
				 * @param value
				 *           allowed object is {@link String }
				 *
				 */
				public void setMONTANTTTC(final String value)
				{
					this.montantttc = value;
				}

			}

		}

	}


	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 *
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="NUMERO_ROLE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="IMPUTATION" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="ARTICLE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="NUMERO_QUITTANCE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="NATURE_RECETTE_RUBRIQUE" minOccurs="0">
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
	@XmlType(name = "", propOrder =
	{ "numerorole", "imputation", "article", "numeroquittance", "naturerecetterubrique" })
	public static class FICHERECETTE implements Serializable
	{

		@XmlElement(name = "NUMERO_ROLE")
		protected String numerorole;
		@XmlElement(name = "IMPUTATION")
		protected String imputation;
		@XmlElement(name = "ARTICLE")
		protected String article;
		@XmlElement(name = "NUMERO_QUITTANCE")
		protected String numeroquittance;
		@XmlElement(name = "NATURE_RECETTE_RUBRIQUE")
		protected String naturerecetterubrique;

		/**
		 * Obtient la valeur de la propriété numerorole.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNUMEROROLE()
		{
			return numerorole;
		}

		/**
		 * Définit la valeur de la propriété numerorole.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNUMEROROLE(final String value)
		{
			this.numerorole = value;
		}

		/**
		 * Obtient la valeur de la propriété imputation.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getIMPUTATION()
		{
			return imputation;
		}

		/**
		 * Définit la valeur de la propriété imputation.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setIMPUTATION(final String value)
		{
			this.imputation = value;
		}

		/**
		 * Obtient la valeur de la propriété article.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getARTICLE()
		{
			return article;
		}

		/**
		 * Définit la valeur de la propriété article.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setARTICLE(final String value)
		{
			this.article = value;
		}

		/**
		 * Obtient la valeur de la propriété numeroquittance.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNUMEROQUITTANCE()
		{
			return numeroquittance;
		}

		/**
		 * Définit la valeur de la propriété numeroquittance.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNUMEROQUITTANCE(final String value)
		{
			this.numeroquittance = value;
		}

		/**
		 * Obtient la valeur de la propriété naturerecetterubrique.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNATURERECETTERUBRIQUE()
		{
			return naturerecetterubrique;
		}

		/**
		 * Définit la valeur de la propriété naturerecetterubrique.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNATURERECETTERUBRIQUE(final String value)
		{
			this.naturerecetterubrique = value;
		}

	}


	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 *
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="NUMERO_CONTRIBUABLE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="RAISON_SOCIALE" minOccurs="0">
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
	@XmlType(name = "", propOrder =
	{ "numerocontribuable", "raisonsociale" })
	public static class PARTIEVERSANTE implements Serializable
	{

		@XmlElement(name = "NUMERO_CONTRIBUABLE")
		protected String numerocontribuable;
		@XmlElement(name = "RAISON_SOCIALE")
		protected String raisonsociale;

		/**
		 * Obtient la valeur de la propriété numerocontribuable.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNUMEROCONTRIBUABLE()
		{
			return numerocontribuable;
		}

		/**
		 * Définit la valeur de la propriété numerocontribuable.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNUMEROCONTRIBUABLE(final String value)
		{
			this.numerocontribuable = value;
		}

		/**
		 * Obtient la valeur de la propriété raisonsociale.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getRAISONSOCIALE()
		{
			return raisonsociale;
		}

		/**
		 * Définit la valeur de la propriété raisonsociale.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setRAISONSOCIALE(final String value)
		{
			this.raisonsociale = value;
		}

	}


	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 *
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="NOM" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="100"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="QUALITE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="100"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="LIEU" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="100"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="DATE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="50"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="SOCIETE" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="100"/>
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
	@XmlType(name = "", propOrder =
	{ "nom", "qualite", "lieu", "date", "societe" })
	public static class SIGNATAIRE implements Serializable
	{

		@XmlElement(name = "NOM")
		protected String nom;
		@XmlElement(name = "QUALITE")
		protected String qualite;
		@XmlElement(name = "LIEU")
		protected String lieu;
		@XmlElement(name = "DATE")
		protected String date;
		@XmlElement(name = "SOCIETE")
		protected String societe;

		/**
		 * Obtient la valeur de la propriété nom.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getNOM()
		{
			return nom;
		}

		/**
		 * Définit la valeur de la propriété nom.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setNOM(final String value)
		{
			this.nom = value;
		}

		/**
		 * Obtient la valeur de la propriété qualite.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getQUALITE()
		{
			return qualite;
		}

		/**
		 * Définit la valeur de la propriété qualite.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setQUALITE(final String value)
		{
			this.qualite = value;
		}

		/**
		 * Obtient la valeur de la propriété lieu.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getLIEU()
		{
			return lieu;
		}

		/**
		 * Définit la valeur de la propriété lieu.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setLIEU(final String value)
		{
			this.lieu = value;
		}

		/**
		 * Obtient la valeur de la propriété date.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getDATE()
		{
			return date;
		}

		/**
		 * Définit la valeur de la propriété date.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setDATE(final String value)
		{
			this.date = value;
		}

		/**
		 * Obtient la valeur de la propriété societe.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getSOCIETE()
		{
			return societe;
		}

		/**
		 * Définit la valeur de la propriété societe.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setSOCIETE(final String value)
		{
			this.societe = value;
		}

	}

}
