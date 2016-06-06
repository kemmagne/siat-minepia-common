package org.guce.siat.utility.jaxb.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Class REFERENCEDOSSIER.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "numerodossier", "numerodemande", "service", "referenceguce", "referencesiat", "datecreation", "si" })
public class REFERENCEDOSSIER implements Serializable
{

	/** The numerodossier. */
	@XmlElement(name = "NUMERO_DOSSIER", required = true)
	protected String numerodossier;

	/** The numerodemande. */
	@XmlElement(name = "NUMERO_DEMANDE", required = true)
	protected String numerodemande;

	/** The service. */
	@XmlElement(name = "SERVICE", required = true)
	protected String service;

	/** The referenceguce. */
	@XmlElement(name = "REFERENCE_GUCE", required = true)
	protected String referenceguce;

	/** The referencesiat. */
	@XmlElement(name = "REFERENCE_SIAT", required = true)
	protected String referencesiat;

	/** The datecreation. */
	@XmlElement(name = "DATE_CREATION", required = true)
	protected String datecreation;

	/** The si. */
	@XmlElement(name = "SI", required = true)
	protected String si;


	/**
	 * Gets the si.
	 *
	 * @return the si
	 */
	public String getSI()
	{
		return si;
	}

	/**
	 * Sets the si.
	 *
	 * @param si
	 *           the new si
	 */
	public void setSI(final String si)
	{
		this.si = si;
	}

	/**
	 * Obtient la valeur de la propriété numerodossier.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNUMERODOSSIER()
	{
		return numerodossier;
	}

	/**
	 * Définit la valeur de la propriété numerodossier.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setNUMERODOSSIER(final String value)
	{
		this.numerodossier = value;
	}

	/**
	 * Obtient la valeur de la propriété numerodemande.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNUMERODEMANDE()
	{
		return numerodemande;
	}

	/**
	 * Définit la valeur de la propriété numerodemande.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setNUMERODEMANDE(final String value)
	{
		this.numerodemande = value;
	}

	/**
	 * Obtient la valeur de la propriété service.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSERVICE()
	{
		return service;
	}

	/**
	 * Définit la valeur de la propriété service.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setSERVICE(final String value)
	{
		this.service = value;
	}

	/**
	 * Obtient la valeur de la propriété referenceguce.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getREFERENCEGUCE()
	{
		return referenceguce;
	}

	/**
	 * Définit la valeur de la propriété referenceguce.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setREFERENCEGUCE(final String value)
	{
		this.referenceguce = value;
	}

	/**
	 * Obtient la valeur de la propriété referencesiat.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getREFERENCESIAT()
	{
		return referencesiat;
	}

	/**
	 * Définit la valeur de la propriété referencesiat.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setREFERENCESIAT(final String value)
	{
		this.referencesiat = value;
	}

	/**
	 * Obtient la valeur de la propriété datecreation.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDATECREATION()
	{
		return datecreation;
	}

	/**
	 * Définit la valeur de la propriété datecreation.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setDATECREATION(final String value)
	{
		this.datecreation = value;
	}

}
