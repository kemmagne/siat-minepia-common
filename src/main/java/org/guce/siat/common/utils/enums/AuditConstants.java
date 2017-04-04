package org.guce.siat.common.utils.enums;


/**
 * The Enum AuditConstants.
 */
public enum AuditConstants
{
	/** The save. */
	SAVE("SAVE"),

	/** The update. */
	UPDATE("UPDATE"),

	/** The delete. */
	DELETE("DELETE"),

	/** The bad credentials. */
	BAD_CREDENTIALS("BAD_CREDENTIALS");

	/** The code. */
	private final String code;


	/**
	 * Instantiates a new persistence action.
	 *
	 * @param code
	 *           the code
	 */
	private AuditConstants(final String code)
	{
		this.code = code.intern();
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return this.code;
	}
}
