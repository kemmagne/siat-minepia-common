package org.guce.siat.common.utils.enums;

/**
 * The Enum ServiceItemType.
 */
public enum ServiceItemType
{

	/** The native. */
	NATIVE("N"),

	/** The subfamily. */
	SUBFAMILY("S");

	/** The code. */
	private String code;


	/**
	 * Instantiates a new service item type.
	 *
	 * @param code
	 *           the code
	 */
	private ServiceItemType(final String code)
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
