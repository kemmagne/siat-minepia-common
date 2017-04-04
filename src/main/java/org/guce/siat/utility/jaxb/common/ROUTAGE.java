package org.guce.siat.utility.jaxb.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Class ROUTAGE.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "emetteur", "destinataire" })
public class ROUTAGE implements Serializable
{

	/** The emetteur. */
	@XmlElement(name = "EMETTEUR", required = true)
	protected String emetteur;
	
	/** The destinataire. */
	@XmlElement(name = "DESTINATAIRE", required = true)
	protected String destinataire;

	/**
	 * Obtient la valeur de la propriété emetteur.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEMETTEUR()
	{
		return emetteur;
	}

	/**
	 * Définit la valeur de la propriété emetteur.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setEMETTEUR(final String value)
	{
		this.emetteur = value;
	}

	/**
	 * Obtient la valeur de la propriété destinataire.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDESTINATAIRE()
	{
		return destinataire;
	}

	/**
	 * Définit la valeur de la propriété destinataire.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setDESTINATAIRE(final String value)
	{
		this.destinataire = value;
	}

}
