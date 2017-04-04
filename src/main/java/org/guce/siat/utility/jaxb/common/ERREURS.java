package org.guce.siat.utility.jaxb.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.guce.siat.utility.jaxb.aperak.DOCUMENT;


/**
 * The Class ERREURS.
 */
@SuppressWarnings(
{ "serial", "javadoc" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "erreur" })
public class ERREURS implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = -8756783857866876685L;
	/** The erreur. */
	@XmlElement(name = "ERREUR")
	protected ERREURS.ERREUR erreur;

	/**
	 * Obtient la valeur de la propriété erreur.
	 *
	 * @return possible object is {@link DOCUMENT.ERREURS.ERREUR }
	 *
	 */
	public ERREURS.ERREUR getERREUR()
	{
		return erreur;
	}

	/**
	 * Définit la valeur de la propriété erreur.
	 *
	 * @param value
	 *           allowed object is {@link ERREURS.ERREUR }
	 *
	 */
	public void setERREUR(final ERREURS.ERREUR value)
	{
		this.erreur = value;
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
	 *         &lt;element name="CODE_ERREUR" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="5"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="LIBELLE_ERREUR" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="50"/>
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
	@XmlType(name = "", propOrder =
	{ "codeerreur", "libelleerreur", "referencedonnee" })
	public static class ERREUR implements Serializable
	{

		/** The codeerreur. */
		@XmlElement(name = "CODE_ERREUR")
		protected String codeerreur;

		/** The libelleerreur. */
		@XmlElement(name = "LIBELLE_ERREUR")
		protected String libelleerreur;

		/** The referencedonnee. */
		@XmlElement(name = "REFERENCE_DONNEE")
		protected String referencedonnee;

		/**
		 * Obtient la valeur de la propriété codeerreur.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getCODEERREUR()
		{
			return codeerreur;
		}

		/**
		 * Définit la valeur de la propriété codeerreur.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setCODEERREUR(final String value)
		{
			this.codeerreur = value;
		}

		/**
		 * Obtient la valeur de la propriété libelleerreur.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getLIBELLEERREUR()
		{
			return libelleerreur;
		}

		/**
		 * Définit la valeur de la propriété libelleerreur.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setLIBELLEERREUR(final String value)
		{
			this.libelleerreur = value;
		}

		/**
		 * Obtient la valeur de la propriété referencedonnee.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getREFERENCEDONNEE()
		{
			return referencedonnee;
		}

		/**
		 * Définit la valeur de la propriété referencedonnee.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setREFERENCEDONNEE(final String value)
		{
			this.referencedonnee = value;
		}

	}

}
