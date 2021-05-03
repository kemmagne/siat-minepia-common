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
 * Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette
 * classe.
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
 *                   &lt;element name="TYPE_FACTURE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="OBJET" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DATE_FACTURATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DATE_ECHEANCE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="MENTION_SPECIALE" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="10"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NUMERO_EXPEDITION" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="35"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="DOCUMENT_REFERENCE" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="NUMERO_DOCUMENT">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="35"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="DATE_DOCUMENT">
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
 *                         &lt;maxLength value="50"/>
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
 *                   &lt;element name="BANQUE" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CODE" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="100"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="LIBELLE" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="100"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="AGENCE" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CODE" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="100"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="LIBELLE" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;maxLength value="100"/>
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
 *                         &lt;maxLength value="250"/>
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
 *                                   &lt;maxLength value="15"/>
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
 *                                   &lt;maxLength value="15"/>
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
 *         &lt;element name="REPARTITIONS" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="REPARTITION" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CODE_BENIF" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="35"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="TYPE_DOSSIER" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="50"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="MONTANT" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *                             &lt;element name="MONTANT_STRING" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "facture",
    "signataire",
    "encaissement",
    "ficherecette",
    "partieversante",
    "chargeur",
    "beneficiaire",
    "repartitions"
})
public class PAIEMENT implements Serializable {

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
    @XmlElement(name = "REPARTITIONS")
    protected PAIEMENT.REPARTITIONS repartitions;

    /**
     * Obtient la valeur de la propri�t� facture.
     *
     * @return possible object is {@link DOCUMENT.CONTENT.PAIEMENT.FACTURE }
     *
     */
    public PAIEMENT.FACTURE getFACTURE() {
        return facture;
    }

    /**
     * D�finit la valeur de la propri�t� facture.
     *
     * @param value allowed object is {@link DOCUMENT.CONTENT.PAIEMENT.FACTURE }
     *
     */
    public void setFACTURE(PAIEMENT.FACTURE value) {
        this.facture = value;
    }

    /**
     * Obtient la valeur de la propri�t� signataire.
     *
     * @return possible object is {@link DOCUMENT.CONTENT.PAIEMENT.SIGNATAIRE }
     *
     */
    public PAIEMENT.SIGNATAIRE getSIGNATAIRE() {
        return signataire;
    }

    /**
     * D�finit la valeur de la propri�t� signataire.
     *
     * @param value allowed object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.SIGNATAIRE }
     *
     */
    public void setSIGNATAIRE(PAIEMENT.SIGNATAIRE value) {
        this.signataire = value;
    }

    /**
     * Obtient la valeur de la propri�t� encaissement.
     *
     * @return possible object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT }
     *
     */
    public PAIEMENT.ENCAISSEMENT getENCAISSEMENT() {
        return encaissement;
    }

    /**
     * D�finit la valeur de la propri�t� encaissement.
     *
     * @param value allowed object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT }
     *
     */
    public void setENCAISSEMENT(PAIEMENT.ENCAISSEMENT value) {
        this.encaissement = value;
    }

    /**
     * Obtient la valeur de la propri�t� ficherecette.
     *
     * @return possible object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.FICHERECETTE }
     *
     */
    public PAIEMENT.FICHERECETTE getFICHERECETTE() {
        return ficherecette;
    }

    /**
     * D�finit la valeur de la propri�t� ficherecette.
     *
     * @param value allowed object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.FICHERECETTE }
     *
     */
    public void setFICHERECETTE(PAIEMENT.FICHERECETTE value) {
        this.ficherecette = value;
    }

    /**
     * Obtient la valeur de la propri�t� partieversante.
     *
     * @return possible object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE }
     *
     */
    public PAIEMENT.PARTIEVERSANTE getPARTIEVERSANTE() {
        return partieversante;
    }

    /**
     * D�finit la valeur de la propri�t� partieversante.
     *
     * @param value allowed object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE }
     *
     */
    public void setPARTIEVERSANTE(PAIEMENT.PARTIEVERSANTE value) {
        this.partieversante = value;
    }

    /**
     * Obtient la valeur de la propri�t� chargeur.
     *
     * @return possible object is {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR }
     *
     */
    public PAIEMENT.CHARGEUR getCHARGEUR() {
        return chargeur;
    }

    /**
     * D�finit la valeur de la propri�t� chargeur.
     *
     * @param value allowed object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR }
     *
     */
    public void setCHARGEUR(PAIEMENT.CHARGEUR value) {
        this.chargeur = value;
    }

    /**
     * Obtient la valeur de la propri�t� beneficiaire.
     *
     * @return possible object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.BENEFICIAIRE }
     *
     */
    public PAIEMENT.BENEFICIAIRE getBENEFICIAIRE() {
        return beneficiaire;
    }

    /**
     * D�finit la valeur de la propri�t� beneficiaire.
     *
     * @param value allowed object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.BENEFICIAIRE }
     *
     */
    public void setBENEFICIAIRE(PAIEMENT.BENEFICIAIRE value) {
        this.beneficiaire = value;
    }

    /**
     * Obtient la valeur de la propri�t� repartitions.
     *
     * @return possible object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.REPARTITIONS }
     *
     */
    public PAIEMENT.REPARTITIONS getREPARTITIONS() {
        return repartitions;
    }

    /**
     * D�finit la valeur de la propri�t� repartitions.
     *
     * @param value allowed object is
             *     {@link DOCUMENT.CONTENT.PAIEMENT.REPARTITIONS }
     *
     */
    public void setREPARTITIONS(PAIEMENT.REPARTITIONS value) {
        this.repartitions = value;
    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
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
    @XmlType(name = "", propOrder = {
        "code",
        "libelle"
    })
    public static class BENEFICIAIRE implements Serializable {

        @XmlElement(name = "CODE")
        protected String code;
        @XmlElement(name = "LIBELLE")
        protected String libelle;

        /**
         * Obtient la valeur de la propri�t� code.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCODE() {
            return code;
        }

        /**
         * D�finit la valeur de la propri�t� code.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setCODE(String value) {
            this.code = value;
        }

        /**
         * Obtient la valeur de la propri�t� libelle.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLIBELLE() {
            return libelle;
        }

        /**
         * D�finit la valeur de la propri�t� libelle.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setLIBELLE(String value) {
            this.libelle = value;
        }

    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
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
     *                         &lt;maxLength value="15"/>
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
    @XmlType(name = "", propOrder = {
        "numerocontribuable",
        "raisonsociale",
        "adresse",
        "telephonefixe",
        "telephonemobile",
        "fax"
    })
    public static class CHARGEUR implements Serializable {

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
         * Obtient la valeur de la propri�t� numerocontribuable.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNUMEROCONTRIBUABLE() {
            return numerocontribuable;
        }

        /**
         * D�finit la valeur de la propri�t� numerocontribuable.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNUMEROCONTRIBUABLE(String value) {
            this.numerocontribuable = value;
        }

        /**
         * Obtient la valeur de la propri�t� raisonsociale.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRAISONSOCIALE() {
            return raisonsociale;
        }

        /**
         * D�finit la valeur de la propri�t� raisonsociale.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setRAISONSOCIALE(String value) {
            this.raisonsociale = value;
        }

        /**
         * Obtient la valeur de la propri�t� adresse.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.ADRESSE }
         *
         */
        public PAIEMENT.CHARGEUR.ADRESSE getADRESSE() {
            return adresse;
        }

        /**
         * D�finit la valeur de la propri�t� adresse.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.ADRESSE }
         *
         */
        public void setADRESSE(PAIEMENT.CHARGEUR.ADRESSE value) {
            this.adresse = value;
        }

        /**
         * Obtient la valeur de la propri�t� telephonefixe.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.TELEPHONEFIXE }
         *
         */
        public PAIEMENT.CHARGEUR.TELEPHONEFIXE getTELEPHONEFIXE() {
            return telephonefixe;
        }

        /**
         * D�finit la valeur de la propri�t� telephonefixe.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.TELEPHONEFIXE }
         *
         */
        public void setTELEPHONEFIXE(PAIEMENT.CHARGEUR.TELEPHONEFIXE value) {
            this.telephonefixe = value;
        }

        /**
         * Obtient la valeur de la propri�t� telephonemobile.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.TELEPHONEMOBILE }
         *
         */
        public PAIEMENT.CHARGEUR.TELEPHONEMOBILE getTELEPHONEMOBILE() {
            return telephonemobile;
        }

        /**
         * D�finit la valeur de la propri�t� telephonemobile.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.TELEPHONEMOBILE }
         *
         */
        public void setTELEPHONEMOBILE(PAIEMENT.CHARGEUR.TELEPHONEMOBILE value) {
            this.telephonemobile = value;
        }

        /**
         * Obtient la valeur de la propri�t� fax.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.FAX }
         *
         */
        public PAIEMENT.CHARGEUR.FAX getFAX() {
            return fax;
        }

        /**
         * D�finit la valeur de la propri�t� fax.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.FAX }
         *
         */
        public void setFAX(PAIEMENT.CHARGEUR.FAX value) {
            this.fax = value;
        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
         *               &lt;maxLength value="15"/>
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
        @XmlType(name = "", propOrder = {
            "adresse1",
            "adresse2",
            "bp",
            "paysadresse",
            "ville",
            "email",
            "siteweb"
        })
        public static class ADRESSE implements Serializable {

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
             * Obtient la valeur de la propri�t� adresse1.
             *
             * @return possible object is {@link String }
             *
             */
            public String getADRESSE1() {
                return adresse1;
            }

            /**
             * D�finit la valeur de la propri�t� adresse1.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setADRESSE1(String value) {
                this.adresse1 = value;
            }

            /**
             * Obtient la valeur de la propri�t� adresse2.
             *
             * @return possible object is {@link String }
             *
             */
            public String getADRESSE2() {
                return adresse2;
            }

            /**
             * D�finit la valeur de la propri�t� adresse2.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setADRESSE2(String value) {
                this.adresse2 = value;
            }

            /**
             * Obtient la valeur de la propri�t� bp.
             *
             * @return possible object is {@link String }
             *
             */
            public String getBP() {
                return bp;
            }

            /**
             * D�finit la valeur de la propri�t� bp.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setBP(String value) {
                this.bp = value;
            }

            /**
             * Obtient la valeur de la propri�t� paysadresse.
             *
             * @return possible object is
                     *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE }
             *
             */
            public PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE getPAYSADRESSE() {
                return paysadresse;
            }

            /**
             * D�finit la valeur de la propri�t� paysadresse.
             *
             * @param value allowed object is
                     *     {@link DOCUMENT.CONTENT.PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE }
             *
             */
            public void setPAYSADRESSE(PAIEMENT.CHARGEUR.ADRESSE.PAYSADRESSE value) {
                this.paysadresse = value;
            }

            /**
             * Obtient la valeur de la propri�t� ville.
             *
             * @return possible object is {@link String }
             *
             */
            public String getVILLE() {
                return ville;
            }

            /**
             * D�finit la valeur de la propri�t� ville.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setVILLE(String value) {
                this.ville = value;
            }

            /**
             * Obtient la valeur de la propri�t� email.
             *
             * @return possible object is {@link String }
             *
             */
            public String getEMAIL() {
                return email;
            }

            /**
             * D�finit la valeur de la propri�t� email.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setEMAIL(String value) {
                this.email = value;
            }

            /**
             * Obtient la valeur de la propri�t� siteweb.
             *
             * @return possible object is {@link String }
             *
             */
            public String getSITEWEB() {
                return siteweb;
            }

            /**
             * D�finit la valeur de la propri�t� siteweb.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setSITEWEB(String value) {
                this.siteweb = value;
            }

            /**
             * <p>
             * Classe Java pour anonymous complex type.
             *
             * <p>
             * Le fragment de sch�ma suivant indique le contenu attendu figurant
             * dans cette classe.
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
            @XmlType(name = "", propOrder = {
                "codepays",
                "nompays"
            })
            public static class PAYSADRESSE implements Serializable {

                @XmlElement(name = "CODE_PAYS")
                protected String codepays;
                @XmlElement(name = "NOM_PAYS")
                protected String nompays;

                /**
                 * Obtient la valeur de la propri�t� codepays.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getCODEPAYS() {
                    return codepays;
                }

                /**
                 * D�finit la valeur de la propri�t� codepays.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setCODEPAYS(String value) {
                    this.codepays = value;
                }

                /**
                 * Obtient la valeur de la propri�t� nompays.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getNOMPAYS() {
                    return nompays;
                }

                /**
                 * D�finit la valeur de la propri�t� nompays.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setNOMPAYS(String value) {
                    this.nompays = value;
                }

            }

        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
        @XmlType(name = "", propOrder = {
            "indicatifpays",
            "numero"
        })
        public static class FAX implements Serializable {

            @XmlElement(name = "INDICATIF_PAYS")
            protected String indicatifpays;
            @XmlElement(name = "NUMERO")
            protected String numero;

            /**
             * Obtient la valeur de la propri�t� indicatifpays.
             *
             * @return possible object is {@link String }
             *
             */
            public String getINDICATIFPAYS() {
                return indicatifpays;
            }

            /**
             * D�finit la valeur de la propri�t� indicatifpays.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setINDICATIFPAYS(String value) {
                this.indicatifpays = value;
            }

            /**
             * Obtient la valeur de la propri�t� numero.
             *
             * @return possible object is {@link String }
             *
             */
            public String getNUMERO() {
                return numero;
            }

            /**
             * D�finit la valeur de la propri�t� numero.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setNUMERO(String value) {
                this.numero = value;
            }

        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
        @XmlType(name = "", propOrder = {
            "indicatifpays",
            "numero"
        })
        public static class TELEPHONEFIXE implements Serializable {

            @XmlElement(name = "INDICATIF_PAYS")
            protected String indicatifpays;
            @XmlElement(name = "NUMERO")
            protected String numero;

            /**
             * Obtient la valeur de la propri�t� indicatifpays.
             *
             * @return possible object is {@link String }
             *
             */
            public String getINDICATIFPAYS() {
                return indicatifpays;
            }

            /**
             * D�finit la valeur de la propri�t� indicatifpays.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setINDICATIFPAYS(String value) {
                this.indicatifpays = value;
            }

            /**
             * Obtient la valeur de la propri�t� numero.
             *
             * @return possible object is {@link String }
             *
             */
            public String getNUMERO() {
                return numero;
            }

            /**
             * D�finit la valeur de la propri�t� numero.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setNUMERO(String value) {
                this.numero = value;
            }

        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
        @XmlType(name = "", propOrder = {
            "indicatifpays",
            "numero"
        })
        public static class TELEPHONEMOBILE implements Serializable {

            @XmlElement(name = "INDICATIF_PAYS")
            protected String indicatifpays;
            @XmlElement(name = "NUMERO")
            protected String numero;

            /**
             * Obtient la valeur de la propri�t� indicatifpays.
             *
             * @return possible object is {@link String }
             *
             */
            public String getINDICATIFPAYS() {
                return indicatifpays;
            }

            /**
             * D�finit la valeur de la propri�t� indicatifpays.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setINDICATIFPAYS(String value) {
                this.indicatifpays = value;
            }

            /**
             * Obtient la valeur de la propri�t� numero.
             *
             * @return possible object is {@link String }
             *
             */
            public String getNUMERO() {
                return numero;
            }

            /**
             * D�finit la valeur de la propri�t� numero.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setNUMERO(String value) {
                this.numero = value;
            }

        }

    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
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
     *               &lt;maxLength value="50"/>
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
     *         &lt;element name="BANQUE" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CODE" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="100"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="LIBELLE" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="100"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="AGENCE" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CODE" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="100"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="LIBELLE" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;maxLength value="100"/>
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
        "numerorecu",
        "nature",
        "dateencaissement",
        "montant",
        "observations",
        "canalencaissement",
        "banque"
    })
    public static class ENCAISSEMENT implements Serializable {

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
        @XmlElement(name = "BANQUE")
        protected PAIEMENT.ENCAISSEMENT.BANQUE banque;

        /**
         * Obtient la valeur de la propri�t� numerorecu.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNUMERORECU() {
            return numerorecu;
        }

        /**
         * D�finit la valeur de la propri�t� numerorecu.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNUMERORECU(String value) {
            this.numerorecu = value;
        }

        /**
         * Obtient la valeur de la propri�t� nature.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNATURE() {
            return nature;
        }

        /**
         * D�finit la valeur de la propri�t� nature.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNATURE(String value) {
            this.nature = value;
        }

        /**
         * Obtient la valeur de la propri�t� dateencaissement.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDATEENCAISSEMENT() {
            return dateencaissement;
        }

        /**
         * D�finit la valeur de la propri�t� dateencaissement.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setDATEENCAISSEMENT(String value) {
            this.dateencaissement = value;
        }

        /**
         * Obtient la valeur de la propri�t� montant.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMONTANT() {
            return montant;
        }

        /**
         * D�finit la valeur de la propri�t� montant.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setMONTANT(String value) {
            this.montant = value;
        }

        /**
         * Obtient la valeur de la propri�t� observations.
         *
         * @return possible object is {@link String }
         *
         */
        public String getOBSERVATIONS() {
            return observations;
        }

        /**
         * D�finit la valeur de la propri�t� observations.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setOBSERVATIONS(String value) {
            this.observations = value;
        }

        /**
         * Obtient la valeur de la propri�t� canalencaissement.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCANALENCAISSEMENT() {
            return canalencaissement;
        }

        /**
         * D�finit la valeur de la propri�t� canalencaissement.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setCANALENCAISSEMENT(String value) {
            this.canalencaissement = value;
        }

        /**
         * Obtient la valeur de la propri�t� banque.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT.BANQUE }
         *
         */
        public PAIEMENT.ENCAISSEMENT.BANQUE getBANQUE() {
            return banque;
        }

        /**
         * D�finit la valeur de la propri�t� banque.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT.BANQUE }
         *
         */
        public void setBANQUE(PAIEMENT.ENCAISSEMENT.BANQUE value) {
            this.banque = value;
        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODE" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="100"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="LIBELLE" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="100"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="AGENCE" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="CODE" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;maxLength value="100"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="LIBELLE" minOccurs="0">
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
            "code",
            "libelle",
            "agence"
        })
        public static class BANQUE implements Serializable {

            @XmlElement(name = "CODE")
            protected String code;
            @XmlElement(name = "LIBELLE")
            protected String libelle;
            @XmlElement(name = "AGENCE")
            protected PAIEMENT.ENCAISSEMENT.BANQUE.AGENCE agence;

            /**
             * Obtient la valeur de la propri�t� code.
             *
             * @return possible object is {@link String }
             *
             */
            public String getCODE() {
                return code;
            }

            /**
             * D�finit la valeur de la propri�t� code.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setCODE(String value) {
                this.code = value;
            }

            /**
             * Obtient la valeur de la propri�t� libelle.
             *
             * @return possible object is {@link String }
             *
             */
            public String getLIBELLE() {
                return libelle;
            }

            /**
             * D�finit la valeur de la propri�t� libelle.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setLIBELLE(String value) {
                this.libelle = value;
            }

            /**
             * Obtient la valeur de la propri�t� agence.
             *
             * @return possible object is
                     *     {@link DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT.BANQUE.AGENCE }
             *
             */
            public PAIEMENT.ENCAISSEMENT.BANQUE.AGENCE getAGENCE() {
                return agence;
            }

            /**
             * D�finit la valeur de la propri�t� agence.
             *
             * @param value allowed object is
                     *     {@link DOCUMENT.CONTENT.PAIEMENT.ENCAISSEMENT.BANQUE.AGENCE }
             *
             */
            public void setAGENCE(PAIEMENT.ENCAISSEMENT.BANQUE.AGENCE value) {
                this.agence = value;
            }

            /**
             * <p>
             * Classe Java pour anonymous complex type.
             *
             * <p>
             * Le fragment de sch�ma suivant indique le contenu attendu figurant
             * dans cette classe.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="CODE" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;maxLength value="100"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="LIBELLE" minOccurs="0">
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
            @XmlType(name = "", propOrder = {
                "code",
                "libelle"
            })
            public static class AGENCE implements Serializable {

                @XmlElement(name = "CODE")
                protected String code;
                @XmlElement(name = "LIBELLE")
                protected String libelle;

                /**
                 * Obtient la valeur de la propri�t� code.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getCODE() {
                    return code;
                }

                /**
                 * D�finit la valeur de la propri�t� code.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setCODE(String value) {
                    this.code = value;
                }

                /**
                 * Obtient la valeur de la propri�t� libelle.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getLIBELLE() {
                    return libelle;
                }

                /**
                 * D�finit la valeur de la propri�t� libelle.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setLIBELLE(String value) {
                    this.libelle = value;
                }

            }

        }

    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
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
     *         &lt;element name="TYPE_FACTURE" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="OBJET" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="DATE_FACTURATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DATE_ECHEANCE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="MENTION_SPECIALE" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="10"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="NUMERO_EXPEDITION" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="35"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="DOCUMENT_REFERENCE" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="NUMERO_DOCUMENT">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="35"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="DATE_DOCUMENT">
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
    @XmlType(name = "", propOrder = {
        "referencefacture",
        "detailfactures",
        "montantht",
        "montanttva",
        "montantttc",
        "autremontant",
        "typefacture",
        "objet",
        "datefacturation",
        "dateecheance",
        "mentionspeciale",
        "numeroexpedition",
        "documentreference"
    })
    public static class FACTURE implements Serializable {

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
        @XmlElement(name = "TYPE_FACTURE")
        protected String typefacture;
        @XmlElement(name = "OBJET")
        protected String objet;
        @XmlElement(name = "DATE_FACTURATION")
        protected String datefacturation;
        @XmlElement(name = "DATE_ECHEANCE")
        protected String dateecheance;
        @XmlElement(name = "MENTION_SPECIALE")
        protected String mentionspeciale;
        @XmlElement(name = "NUMERO_EXPEDITION")
        protected String numeroexpedition;
        @XmlElement(name = "DOCUMENT_REFERENCE")
        protected PAIEMENT.FACTURE.DOCUMENTREFERENCE documentreference;

        /**
         * Obtient la valeur de la propri�t� referencefacture.
         *
         * @return possible object is {@link String }
         *
         */
        public String getREFERENCEFACTURE() {
            return referencefacture;
        }

        /**
         * D�finit la valeur de la propri�t� referencefacture.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setREFERENCEFACTURE(String value) {
            this.referencefacture = value;
        }

        /**
         * Obtient la valeur de la propri�t� detailfactures.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.FACTURE.DETAILFACTURES }
         *
         */
        public PAIEMENT.FACTURE.DETAILFACTURES getDETAILFACTURES() {
            return detailfactures;
        }

        /**
         * D�finit la valeur de la propri�t� detailfactures.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.FACTURE.DETAILFACTURES }
         *
         */
        public void setDETAILFACTURES(PAIEMENT.FACTURE.DETAILFACTURES value) {
            this.detailfactures = value;
        }

        /**
         * Obtient la valeur de la propri�t� montantht.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMONTANTHT() {
            return montantht;
        }

        /**
         * D�finit la valeur de la propri�t� montantht.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setMONTANTHT(String value) {
            this.montantht = value;
        }

        /**
         * Obtient la valeur de la propri�t� montanttva.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMONTANTTVA() {
            return montanttva;
        }

        /**
         * D�finit la valeur de la propri�t� montanttva.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setMONTANTTVA(String value) {
            this.montanttva = value;
        }

        /**
         * Obtient la valeur de la propri�t� montantttc.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMONTANTTTC() {
            return montantttc;
        }

        /**
         * D�finit la valeur de la propri�t� montantttc.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setMONTANTTTC(String value) {
            this.montantttc = value;
        }

        /**
         * Obtient la valeur de la propri�t� autremontant.
         *
         * @return possible object is {@link String }
         *
         */
        public String getAUTREMONTANT() {
            return autremontant;
        }

        /**
         * D�finit la valeur de la propri�t� autremontant.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setAUTREMONTANT(String value) {
            this.autremontant = value;
        }

        /**
         * Obtient la valeur de la propri�t� typefacture.
         *
         * @return possible object is {@link String }
         *
         */
        public String getTYPEFACTURE() {
            return typefacture;
        }

        /**
         * D�finit la valeur de la propri�t� typefacture.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setTYPEFACTURE(String value) {
            this.typefacture = value;
        }

        /**
         * Obtient la valeur de la propri�t� objet.
         *
         * @return possible object is {@link String }
         *
         */
        public String getOBJET() {
            return objet;
        }

        /**
         * D�finit la valeur de la propri�t� objet.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setOBJET(String value) {
            this.objet = value;
        }

        /**
         * Obtient la valeur de la propri�t� datefacturation.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDATEFACTURATION() {
            return datefacturation;
        }

        /**
         * D�finit la valeur de la propri�t� datefacturation.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setDATEFACTURATION(String value) {
            this.datefacturation = value;
        }

        /**
         * Obtient la valeur de la propri�t� dateecheance.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDATEECHEANCE() {
            return dateecheance;
        }

        /**
         * D�finit la valeur de la propri�t� dateecheance.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setDATEECHEANCE(String value) {
            this.dateecheance = value;
        }

        /**
         * Obtient la valeur de la propri�t� mentionspeciale.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMENTIONSPECIALE() {
            return mentionspeciale;
        }

        /**
         * D�finit la valeur de la propri�t� mentionspeciale.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setMENTIONSPECIALE(String value) {
            this.mentionspeciale = value;
        }

        /**
         * Obtient la valeur de la propri�t� numeroexpedition.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNUMEROEXPEDITION() {
            return numeroexpedition;
        }

        /**
         * D�finit la valeur de la propri�t� numeroexpedition.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNUMEROEXPEDITION(String value) {
            this.numeroexpedition = value;
        }

        /**
         * Obtient la valeur de la propri�t� documentreference.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.FACTURE.DOCUMENTREFERENCE }
         *
         */
        public PAIEMENT.FACTURE.DOCUMENTREFERENCE getDOCUMENTREFERENCE() {
            return documentreference;
        }

        /**
         * D�finit la valeur de la propri�t� documentreference.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.FACTURE.DOCUMENTREFERENCE }
         *
         */
        public void setDOCUMENTREFERENCE(PAIEMENT.FACTURE.DOCUMENTREFERENCE value) {
            this.documentreference = value;
        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
        @XmlType(name = "", propOrder = {
            "detailfacture"
        })
        public static class DETAILFACTURES implements Serializable {

            @XmlElement(name = "DETAIL_FACTURE")
            protected List<PAIEMENT.FACTURE.DETAILFACTURES.DETAILFACTURE> detailfacture;

            /**
             * Gets the value of the detailfacture property.
             *
             * <p>
             * This accessor method returns a reference to the live list, not a
             * snapshot. Therefore any modification you make to the returned
             * list will be present inside the JAXB object. This is why there is
             * not a <CODE>set</CODE> method for the detailfacture property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getDETAILFACTURE().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
                     * {@link DOCUMENT.CONTENT.PAIEMENT.FACTURE.DETAILFACTURES.DETAILFACTURE }
             *
             *
             */
            public List<PAIEMENT.FACTURE.DETAILFACTURES.DETAILFACTURE> getDETAILFACTURE() {
                if (detailfacture == null) {
                    detailfacture = new ArrayList<>();
                }
                return this.detailfacture;
            }

            /**
             * <p>
             * Classe Java pour anonymous complex type.
             *
             * <p>
             * Le fragment de sch�ma suivant indique le contenu attendu figurant
             * dans cette classe.
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
            @XmlType(name = "", propOrder = {
                "numeroligne",
                "codearticle",
                "libellearticle",
                "montantht",
                "montanttva",
                "montantttc"
            })
            public static class DETAILFACTURE implements Serializable {

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
                 * Obtient la valeur de la propri�t� numeroligne.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getNUMEROLIGNE() {
                    return numeroligne;
                }

                /**
                 * D�finit la valeur de la propri�t� numeroligne.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setNUMEROLIGNE(String value) {
                    this.numeroligne = value;
                }

                /**
                 * Obtient la valeur de la propri�t� codearticle.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getCODEARTICLE() {
                    return codearticle;
                }

                /**
                 * D�finit la valeur de la propri�t� codearticle.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setCODEARTICLE(String value) {
                    this.codearticle = value;
                }

                /**
                 * Obtient la valeur de la propri�t� libellearticle.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getLIBELLEARTICLE() {
                    return libellearticle;
                }

                /**
                 * D�finit la valeur de la propri�t� libellearticle.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setLIBELLEARTICLE(String value) {
                    this.libellearticle = value;
                }

                /**
                 * Obtient la valeur de la propri�t� montantht.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getMONTANTHT() {
                    return montantht;
                }

                /**
                 * D�finit la valeur de la propri�t� montantht.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setMONTANTHT(String value) {
                    this.montantht = value;
                }

                /**
                 * Obtient la valeur de la propri�t� montanttva.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getMONTANTTVA() {
                    return montanttva;
                }

                /**
                 * D�finit la valeur de la propri�t� montanttva.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setMONTANTTVA(String value) {
                    this.montanttva = value;
                }

                /**
                 * Obtient la valeur de la propri�t� montantttc.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getMONTANTTTC() {
                    return montantttc;
                }

                /**
                 * D�finit la valeur de la propri�t� montantttc.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setMONTANTTTC(String value) {
                    this.montantttc = value;
                }

            }

        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="NUMERO_DOCUMENT">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="35"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="DATE_DOCUMENT">
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
            "numerodocument",
            "datedocument"
        })
        public static class DOCUMENTREFERENCE implements Serializable {

            @XmlElement(name = "NUMERO_DOCUMENT", required = true)
            protected String numerodocument;
            @XmlElement(name = "DATE_DOCUMENT", required = true)
            protected String datedocument;

            /**
             * Obtient la valeur de la propri�t� numerodocument.
             *
             * @return possible object is {@link String }
             *
             */
            public String getNUMERODOCUMENT() {
                return numerodocument;
            }

            /**
             * D�finit la valeur de la propri�t� numerodocument.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setNUMERODOCUMENT(String value) {
                this.numerodocument = value;
            }

            /**
             * Obtient la valeur de la propri�t� datedocument.
             *
             * @return possible object is {@link String }
             *
             */
            public String getDATEDOCUMENT() {
                return datedocument;
            }

            /**
             * D�finit la valeur de la propri�t� datedocument.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setDATEDOCUMENT(String value) {
                this.datedocument = value;
            }

        }

    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
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
    @XmlType(name = "", propOrder = {
        "numerorole",
        "imputation",
        "article",
        "numeroquittance",
        "naturerecetterubrique"
    })
    public static class FICHERECETTE implements Serializable {

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
         * Obtient la valeur de la propri�t� numerorole.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNUMEROROLE() {
            return numerorole;
        }

        /**
         * D�finit la valeur de la propri�t� numerorole.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNUMEROROLE(String value) {
            this.numerorole = value;
        }

        /**
         * Obtient la valeur de la propri�t� imputation.
         *
         * @return possible object is {@link String }
         *
         */
        public String getIMPUTATION() {
            return imputation;
        }

        /**
         * D�finit la valeur de la propri�t� imputation.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setIMPUTATION(String value) {
            this.imputation = value;
        }

        /**
         * Obtient la valeur de la propri�t� article.
         *
         * @return possible object is {@link String }
         *
         */
        public String getARTICLE() {
            return article;
        }

        /**
         * D�finit la valeur de la propri�t� article.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setARTICLE(String value) {
            this.article = value;
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
         * Obtient la valeur de la propri�t� naturerecetterubrique.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNATURERECETTERUBRIQUE() {
            return naturerecetterubrique;
        }

        /**
         * D�finit la valeur de la propri�t� naturerecetterubrique.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNATURERECETTERUBRIQUE(String value) {
            this.naturerecetterubrique = value;
        }

    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
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
     *                         &lt;maxLength value="15"/>
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
    @XmlType(name = "", propOrder = {
        "numerocontribuable",
        "raisonsociale",
        "adresse",
        "telephonefixe",
        "telephonemobile",
        "fax"
    })
    public static class PARTIEVERSANTE implements Serializable {

        @XmlElement(name = "NUMERO_CONTRIBUABLE")
        protected String numerocontribuable;
        @XmlElement(name = "RAISON_SOCIALE")
        protected String raisonsociale;
        @XmlElement(name = "ADRESSE")
        protected PAIEMENT.PARTIEVERSANTE.ADRESSE adresse;
        @XmlElement(name = "TELEPHONE_FIXE")
        protected PAIEMENT.PARTIEVERSANTE.TELEPHONEFIXE telephonefixe;
        @XmlElement(name = "TELEPHONE_MOBILE")
        protected PAIEMENT.PARTIEVERSANTE.TELEPHONEMOBILE telephonemobile;
        @XmlElement(name = "FAX")
        protected PAIEMENT.PARTIEVERSANTE.FAX fax;

        /**
         * Obtient la valeur de la propri�t� numerocontribuable.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNUMEROCONTRIBUABLE() {
            return numerocontribuable;
        }

        /**
         * D�finit la valeur de la propri�t� numerocontribuable.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNUMEROCONTRIBUABLE(String value) {
            this.numerocontribuable = value;
        }

        /**
         * Obtient la valeur de la propri�t� raisonsociale.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRAISONSOCIALE() {
            return raisonsociale;
        }

        /**
         * D�finit la valeur de la propri�t� raisonsociale.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setRAISONSOCIALE(String value) {
            this.raisonsociale = value;
        }

        /**
         * Obtient la valeur de la propri�t� adresse.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.ADRESSE }
         *
         */
        public PAIEMENT.PARTIEVERSANTE.ADRESSE getADRESSE() {
            return adresse;
        }

        /**
         * D�finit la valeur de la propri�t� adresse.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.ADRESSE }
         *
         */
        public void setADRESSE(PAIEMENT.PARTIEVERSANTE.ADRESSE value) {
            this.adresse = value;
        }

        /**
         * Obtient la valeur de la propri�t� telephonefixe.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.TELEPHONEFIXE }
         *
         */
        public PAIEMENT.PARTIEVERSANTE.TELEPHONEFIXE getTELEPHONEFIXE() {
            return telephonefixe;
        }

        /**
         * D�finit la valeur de la propri�t� telephonefixe.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.TELEPHONEFIXE }
         *
         */
        public void setTELEPHONEFIXE(PAIEMENT.PARTIEVERSANTE.TELEPHONEFIXE value) {
            this.telephonefixe = value;
        }

        /**
         * Obtient la valeur de la propri�t� telephonemobile.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.TELEPHONEMOBILE }
         *
         */
        public PAIEMENT.PARTIEVERSANTE.TELEPHONEMOBILE getTELEPHONEMOBILE() {
            return telephonemobile;
        }

        /**
         * D�finit la valeur de la propri�t� telephonemobile.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.TELEPHONEMOBILE }
         *
         */
        public void setTELEPHONEMOBILE(PAIEMENT.PARTIEVERSANTE.TELEPHONEMOBILE value) {
            this.telephonemobile = value;
        }

        /**
         * Obtient la valeur de la propri�t� fax.
         *
         * @return possible object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.FAX }
         *
         */
        public PAIEMENT.PARTIEVERSANTE.FAX getFAX() {
            return fax;
        }

        /**
         * D�finit la valeur de la propri�t� fax.
         *
         * @param value allowed object is
                 *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.FAX }
         *
         */
        public void setFAX(PAIEMENT.PARTIEVERSANTE.FAX value) {
            this.fax = value;
        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
         *               &lt;maxLength value="15"/>
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
        @XmlType(name = "", propOrder = {
            "adresse1",
            "adresse2",
            "bp",
            "paysadresse",
            "ville",
            "email",
            "siteweb"
        })
        public static class ADRESSE implements Serializable {

            @XmlElement(name = "ADRESSE1")
            protected String adresse1;
            @XmlElement(name = "ADRESSE2")
            protected String adresse2;
            @XmlElement(name = "BP")
            protected String bp;
            @XmlElement(name = "PAYS_ADRESSE")
            protected PAIEMENT.PARTIEVERSANTE.ADRESSE.PAYSADRESSE paysadresse;
            @XmlElement(name = "VILLE")
            protected String ville;
            @XmlElement(name = "EMAIL")
            protected String email;
            @XmlElement(name = "SITE_WEB")
            protected String siteweb;

            /**
             * Obtient la valeur de la propri�t� adresse1.
             *
             * @return possible object is {@link String }
             *
             */
            public String getADRESSE1() {
                return adresse1;
            }

            /**
             * D�finit la valeur de la propri�t� adresse1.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setADRESSE1(String value) {
                this.adresse1 = value;
            }

            /**
             * Obtient la valeur de la propri�t� adresse2.
             *
             * @return possible object is {@link String }
             *
             */
            public String getADRESSE2() {
                return adresse2;
            }

            /**
             * D�finit la valeur de la propri�t� adresse2.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setADRESSE2(String value) {
                this.adresse2 = value;
            }

            /**
             * Obtient la valeur de la propri�t� bp.
             *
             * @return possible object is {@link String }
             *
             */
            public String getBP() {
                return bp;
            }

            /**
             * D�finit la valeur de la propri�t� bp.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setBP(String value) {
                this.bp = value;
            }

            /**
             * Obtient la valeur de la propri�t� paysadresse.
             *
             * @return possible object is
                     *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.ADRESSE.PAYSADRESSE }
             *
             */
            public PAIEMENT.PARTIEVERSANTE.ADRESSE.PAYSADRESSE getPAYSADRESSE() {
                return paysadresse;
            }

            /**
             * D�finit la valeur de la propri�t� paysadresse.
             *
             * @param value allowed object is
                     *     {@link DOCUMENT.CONTENT.PAIEMENT.PARTIEVERSANTE.ADRESSE.PAYSADRESSE }
             *
             */
            public void setPAYSADRESSE(PAIEMENT.PARTIEVERSANTE.ADRESSE.PAYSADRESSE value) {
                this.paysadresse = value;
            }

            /**
             * Obtient la valeur de la propri�t� ville.
             *
             * @return possible object is {@link String }
             *
             */
            public String getVILLE() {
                return ville;
            }

            /**
             * D�finit la valeur de la propri�t� ville.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setVILLE(String value) {
                this.ville = value;
            }

            /**
             * Obtient la valeur de la propri�t� email.
             *
             * @return possible object is {@link String }
             *
             */
            public String getEMAIL() {
                return email;
            }

            /**
             * D�finit la valeur de la propri�t� email.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setEMAIL(String value) {
                this.email = value;
            }

            /**
             * Obtient la valeur de la propri�t� siteweb.
             *
             * @return possible object is {@link String }
             *
             */
            public String getSITEWEB() {
                return siteweb;
            }

            /**
             * D�finit la valeur de la propri�t� siteweb.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setSITEWEB(String value) {
                this.siteweb = value;
            }

            /**
             * <p>
             * Classe Java pour anonymous complex type.
             *
             * <p>
             * Le fragment de sch�ma suivant indique le contenu attendu figurant
             * dans cette classe.
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
            @XmlType(name = "", propOrder = {
                "codepays",
                "nompays"
            })
            public static class PAYSADRESSE implements Serializable {

                @XmlElement(name = "CODE_PAYS")
                protected String codepays;
                @XmlElement(name = "NOM_PAYS")
                protected String nompays;

                /**
                 * Obtient la valeur de la propri�t� codepays.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getCODEPAYS() {
                    return codepays;
                }

                /**
                 * D�finit la valeur de la propri�t� codepays.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setCODEPAYS(String value) {
                    this.codepays = value;
                }

                /**
                 * Obtient la valeur de la propri�t� nompays.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getNOMPAYS() {
                    return nompays;
                }

                /**
                 * D�finit la valeur de la propri�t� nompays.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setNOMPAYS(String value) {
                    this.nompays = value;
                }

            }

        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
        @XmlType(name = "", propOrder = {
            "indicatifpays",
            "numero"
        })
        public static class FAX implements Serializable {

            @XmlElement(name = "INDICATIF_PAYS")
            protected String indicatifpays;
            @XmlElement(name = "NUMERO")
            protected String numero;

            /**
             * Obtient la valeur de la propri�t� indicatifpays.
             *
             * @return possible object is {@link String }
             *
             */
            public String getINDICATIFPAYS() {
                return indicatifpays;
            }

            /**
             * D�finit la valeur de la propri�t� indicatifpays.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setINDICATIFPAYS(String value) {
                this.indicatifpays = value;
            }

            /**
             * Obtient la valeur de la propri�t� numero.
             *
             * @return possible object is {@link String }
             *
             */
            public String getNUMERO() {
                return numero;
            }

            /**
             * D�finit la valeur de la propri�t� numero.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setNUMERO(String value) {
                this.numero = value;
            }

        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
        @XmlType(name = "", propOrder = {
            "indicatifpays",
            "numero"
        })
        public static class TELEPHONEFIXE implements Serializable {

            @XmlElement(name = "INDICATIF_PAYS")
            protected String indicatifpays;
            @XmlElement(name = "NUMERO")
            protected String numero;

            /**
             * Obtient la valeur de la propri�t� indicatifpays.
             *
             * @return possible object is {@link String }
             *
             */
            public String getINDICATIFPAYS() {
                return indicatifpays;
            }

            /**
             * D�finit la valeur de la propri�t� indicatifpays.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setINDICATIFPAYS(String value) {
                this.indicatifpays = value;
            }

            /**
             * Obtient la valeur de la propri�t� numero.
             *
             * @return possible object is {@link String }
             *
             */
            public String getNUMERO() {
                return numero;
            }

            /**
             * D�finit la valeur de la propri�t� numero.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setNUMERO(String value) {
                this.numero = value;
            }

        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
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
        @XmlType(name = "", propOrder = {
            "indicatifpays",
            "numero"
        })
        public static class TELEPHONEMOBILE implements Serializable {

            @XmlElement(name = "INDICATIF_PAYS")
            protected String indicatifpays;
            @XmlElement(name = "NUMERO")
            protected String numero;

            /**
             * Obtient la valeur de la propri�t� indicatifpays.
             *
             * @return possible object is {@link String }
             *
             */
            public String getINDICATIFPAYS() {
                return indicatifpays;
            }

            /**
             * D�finit la valeur de la propri�t� indicatifpays.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setINDICATIFPAYS(String value) {
                this.indicatifpays = value;
            }

            /**
             * Obtient la valeur de la propri�t� numero.
             *
             * @return possible object is {@link String }
             *
             */
            public String getNUMERO() {
                return numero;
            }

            /**
             * D�finit la valeur de la propri�t� numero.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setNUMERO(String value) {
                this.numero = value;
            }

        }

    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="REPARTITION" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CODE_BENIF" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="35"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="TYPE_DOSSIER" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="50"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="MONTANT" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
     *                   &lt;element name="MONTANT_STRING" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "repartition"
    })
    public static class REPARTITIONS implements Serializable {

        @XmlElement(name = "REPARTITION")
        protected List<PAIEMENT.REPARTITIONS.REPARTITION> repartition;

        /**
         * Gets the value of the repartition property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the repartition property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getREPARTITION().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
                 * {@link DOCUMENT.CONTENT.PAIEMENT.REPARTITIONS.REPARTITION }
         *
         *
         */
        public List<PAIEMENT.REPARTITIONS.REPARTITION> getREPARTITION() {
            if (repartition == null) {
                repartition = new ArrayList<>();
            }
            return this.repartition;
        }

        /**
         * <p>
         * Classe Java pour anonymous complex type.
         *
         * <p>
         * Le fragment de sch�ma suivant indique le contenu attendu figurant
         * dans cette classe.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CODE_BENIF" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="35"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="TYPE_DOSSIER" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="50"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="MONTANT" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
         *         &lt;element name="MONTANT_STRING" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "codebenif",
            "typedossier",
            "montant",
            "montantstring"
        })
        public static class REPARTITION implements Serializable {

            @XmlElement(name = "CODE_BENIF")
            protected String codebenif;
            @XmlElement(name = "TYPE_DOSSIER")
            protected String typedossier;
            @XmlElement(name = "MONTANT")
            protected Float montant;
            @XmlElement(name = "MONTANT_STRING")
            protected String montantstring;

            /**
             * Obtient la valeur de la propri�t� codebenif.
             *
             * @return possible object is {@link String }
             *
             */
            public String getCODEBENIF() {
                return codebenif;
            }

            /**
             * D�finit la valeur de la propri�t� codebenif.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setCODEBENIF(String value) {
                this.codebenif = value;
            }

            /**
             * Obtient la valeur de la propri�t� typedossier.
             *
             * @return possible object is {@link String }
             *
             */
            public String getTYPEDOSSIER() {
                return typedossier;
            }

            /**
             * D�finit la valeur de la propri�t� typedossier.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setTYPEDOSSIER(String value) {
                this.typedossier = value;
            }

            /**
             * Obtient la valeur de la propri�t� montant.
             *
             * @return possible object is {@link Float }
             *
             */
            public Float getMONTANT() {
                return montant;
            }

            /**
             * D�finit la valeur de la propri�t� montant.
             *
             * @param value allowed object is {@link Float }
             *
             */
            public void setMONTANT(Float value) {
                this.montant = value;
            }

            /**
             * Obtient la valeur de la propri�t� montantstring.
             *
             * @return possible object is {@link String }
             *
             */
            public String getMONTANTSTRING() {
                return montantstring;
            }

            /**
             * D�finit la valeur de la propri�t� montantstring.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setMONTANTSTRING(String value) {
                this.montantstring = value;
            }

        }

    }

    /**
     * <p>
     * Classe Java pour anonymous complex type.
     *
     * <p>
     * Le fragment de sch�ma suivant indique le contenu attendu figurant dans
     * cette classe.
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
    @XmlType(name = "", propOrder = {
        "nom",
        "qualite",
        "lieu",
        "date",
        "societe"
    })
    public static class SIGNATAIRE implements Serializable {

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
         * Obtient la valeur de la propri�t� nom.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNOM() {
            return nom;
        }

        /**
         * D�finit la valeur de la propri�t� nom.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setNOM(String value) {
            this.nom = value;
        }

        /**
         * Obtient la valeur de la propri�t� qualite.
         *
         * @return possible object is {@link String }
         *
         */
        public String getQUALITE() {
            return qualite;
        }

        /**
         * D�finit la valeur de la propri�t� qualite.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setQUALITE(String value) {
            this.qualite = value;
        }

        /**
         * Obtient la valeur de la propri�t� lieu.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLIEU() {
            return lieu;
        }

        /**
         * D�finit la valeur de la propri�t� lieu.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setLIEU(String value) {
            this.lieu = value;
        }

        /**
         * Obtient la valeur de la propri�t� date.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDATE() {
            return date;
        }

        /**
         * D�finit la valeur de la propri�t� date.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setDATE(String value) {
            this.date = value;
        }

        /**
         * Obtient la valeur de la propri�t� societe.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSOCIETE() {
            return societe;
        }

        /**
         * D�finit la valeur de la propri�t� societe.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setSOCIETE(String value) {
            this.societe = value;
        }

    }

}
