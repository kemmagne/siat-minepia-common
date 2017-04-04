package org.guce.siat.utility.jaxb.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.guce.siat.utility.jaxb.aperak.DOCUMENT;


/**
 * The Class PIECESJOINTES.
 */
@SuppressWarnings(
{ "serial", "javadoc" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "piecejointe" })
public class PIECESJOINTES implements Serializable
{

	/** The piecejointe. */
	@XmlElement(name = "PIECE_JOINTE")
	protected List<PIECEJOINTE> piecejointe;

	/**
	 * Gets the value of the piecejointe property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
	 * the piecejointe property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getPIECEJOINTE().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link DOCUMENT.CONTENT.PIECESJOINTES.PIECEJOINTE }
	 *
	 * @return the piecejointe
	 */
	public List<PIECEJOINTE> getPIECEJOINTE()
	{
		if (piecejointe == null)
		{
			piecejointe = new ArrayList<PIECEJOINTE>();
		}
		return this.piecejointe;
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
	 *         &lt;element name="TYPE_PJ" minOccurs="0">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;maxLength value="35"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="LIBELLE_PJ" minOccurs="0">
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
	{ "typepj", "libellepj" })
	public static class PIECEJOINTE implements Serializable
	{

		/** The typepj. */
		@XmlElement(name = "TYPE_PJ")
		protected String typepj;

		/** The libellepj. */
		@XmlElement(name = "LIBELLE_PJ")
		protected String libellepj;


		public PIECEJOINTE()
		{
		}

		public PIECEJOINTE(final String typepj, final String libellepj)
		{
			this.typepj = typepj;
			this.libellepj = libellepj;
		}

		/**
		 * Obtient la valeur de la propriété typepj.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getTYPEPJ()
		{
			return typepj;
		}

		/**
		 * Définit la valeur de la propriété typepj.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setTYPEPJ(final String value)
		{
			this.typepj = value;
		}

		/**
		 * Obtient la valeur de la propriété libellepj.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getLIBELLEPJ()
		{
			return libellepj;
		}

		/**
		 * Définit la valeur de la propriété libellepj.
		 *
		 * @param value
		 *           allowed object is {@link String }
		 *
		 */
		public void setLIBELLEPJ(final String value)
		{
			this.libellepj = value;
		}

	}

}
