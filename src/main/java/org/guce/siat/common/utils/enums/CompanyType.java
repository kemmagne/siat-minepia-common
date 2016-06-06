package org.guce.siat.common.utils.enums;

/**
 * The Enum ServiceItemType.
 */
public enum CompanyType
{

	/** The Deaclarant : Importateur/exportateur. */
	DECLARANT("Operator"),

	/** The Destinataire. */
	DESTINATAIRE("Receiver"),

	/** The transitaire. */
	TRANSITAIRE("TransitAgent");

	/** The code. */
	private String code;


	/**
	 * Instantiates a new service item type.
	 *
	 * @param code
	 *           the code
	 */
	private CompanyType(final String code)
	{
		this.code = code;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}

}
