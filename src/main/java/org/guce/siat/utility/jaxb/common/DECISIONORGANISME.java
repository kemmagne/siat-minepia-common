package org.guce.siat.utility.jaxb.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Class DECISIONORGANISME.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "code", "libelle", "observation" })
public class DECISIONORGANISME implements Serializable
{

	/** The code. */
	@XmlElement(name = "CODE")
	protected String code;
	
	/** The libelle. */
	@XmlElement(name = "LIBELLE")
	protected String libelle;
	
	/** The observation. */
	@XmlElement(name = "OBSERVATION")
	protected String observation;

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

	/**
	 * Obtient la valeur de la propriété observation.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getOBSERVATION()
	{
		return observation;
	}

	/**
	 * Définit la valeur de la propriété observation.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setOBSERVATION(final String value)
	{
		this.observation = value;
	}

}
