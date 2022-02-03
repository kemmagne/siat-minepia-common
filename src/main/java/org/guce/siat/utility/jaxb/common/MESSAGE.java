package org.guce.siat.utility.jaxb.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Class MESSAGE.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "typemessage", "ephytomessage" ,  "dateemission", "etat", "numeromessage", "numeromessageorigine", "dateemissionmsgorigine" })
public class MESSAGE implements Serializable
{

	/** The typemessage. */
	@XmlElement(name = "TYPE_MESSAGE", required = true)
	protected String typemessage;

        @XmlElement(name = "EPHYTO_MESSAGE", required = true)
        protected String ephytomessage;
        
	/** The dateemission. */
	@XmlElement(name = "DATE_EMISSION", required = true)
	protected String dateemission;

	/** The etat. */
	@XmlElement(name = "ETAT", required = true)
	protected String etat;

	/** The numeromessage. */
	@XmlElement(name = "NUMERO_MESSAGE", required = true)
	protected String numeromessage;

	/** The numromessageorigine. */
	@XmlElement(name = "NUMERO_MESSAGE_ORIGINE", required = true)
	protected String numeromessageorigine;

	/** The dateemissionmsgorigine. */
	@XmlElement(name = "DATE_EMISSION_MSG_ORIGINE", required = true)
	protected String dateemissionmsgorigine;

	/**
	 * Obtient la valeur de la propriété typemessage.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTYPEMESSAGE()
	{
		return typemessage;
	}

	/**
	 * Définit la valeur de la propriété typemessage.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setTYPEMESSAGE(final String value)
	{
		this.typemessage = value;
	}

	/**
	 * Obtient la valeur de la propriété dateemission.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDATEEMISSION()
	{
		return dateemission;
	}

	/**
	 * Définit la valeur de la propriété dateemission.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setDATEEMISSION(final String value)
	{
		this.dateemission = value;
	}

	/**
	 * Obtient la valeur de la propriété etat.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getETAT()
	{
		return etat;
	}

	/**
	 * Définit la valeur de la propriété etat.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setETAT(final String value)
	{
		this.etat = value;
	}

	/**
	 * Obtient la valeur de la propriété numeromessage.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNUMEROMESSAGE()
	{
		return numeromessage;
	}

	/**
	 * Définit la valeur de la propriété numeromessage.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setNUMEROMESSAGE(final String value)
	{
		this.numeromessage = value;
	}

	/**
	 * Obtient la valeur de la propriété numromessageorigine.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNUMEROMESSAGEORIGINE()
	{
		return numeromessageorigine;
	}

	/**
	 * Définit la valeur de la propriété numromessageorigine.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setNUMEROMESSAGEORIGINE(final String value)
	{
		this.numeromessageorigine = value;
	}

	/**
	 * Obtient la valeur de la propriété dateemissionmsgorigine.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDATEEMISSIONMSGORIGINE()
	{
		return dateemissionmsgorigine;
	}

	/**
	 * Définit la valeur de la propriété dateemissionmsgorigine.
	 *
	 * @param value
	 *           allowed object is {@link String }
	 *
	 */
	public void setDATEEMISSIONMSGORIGINE(final String value)
	{
		this.dateemissionmsgorigine = value;
	}

        
        /**
         * Obtient la valeur de la propriété ephytomessage.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEPHYTOMESSAGE() {
            return ephytomessage;
        }

        /**
         * Définit la valeur de la propriété ephytomessage.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEPHYTOMESSAGE(String value) {
            this.ephytomessage = value;
        }

}
