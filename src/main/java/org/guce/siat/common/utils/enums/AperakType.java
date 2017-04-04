package org.guce.siat.common.utils.enums;

/**
 * The Enum AperakType.
 */
public enum AperakType
{

	/** The aperak d. */
	APERAK_D("APERAK_D", "D"),

	/** The aperak j. */
	APERAK_J("APERAK_J", "J"),

	/** The aperak c. */
	APERAK_C("APERAK_C", "C"),

	/** The aperak k. */
	APERAK_K("APERAK_K", "K"),


	/** The aperak f. */
	APERAK_F("APERAK_F", "F");


	/** The code. */
	private String code;

	/** The char code. */
	private String charCode;


	/**
	 * Instantiates a new aperak type.
	 *
	 * @param code
	 *           the code
	 * @param charCode
	 *           the char code
	 */
	private AperakType(final String code, final String charCode)
	{
		this.code = code;
		this.charCode = charCode;
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
	 *           the new code
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}

	/**
	 * Gets the char code.
	 *
	 * @return the char code
	 */
	public String getCharCode()
	{
		return charCode;
	}

	/**
	 * Sets the char code.
	 *
	 * @param charCode
	 *           the new char code
	 */
	public void setCharCode(final String charCode)
	{
		this.charCode = charCode;
	}



}
