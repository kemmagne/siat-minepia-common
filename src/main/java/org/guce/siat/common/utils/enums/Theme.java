package org.guce.siat.common.utils.enums;

/**
 * The Enum Theme.
 */
public enum Theme
{
	/** The blue sky. */
	BLUE_SKY("color-blue-sky", "Blue Sky"),

	/** The blue. */
	BLUE("color-blue", "Blue"),

	/** The brown. */
	BROWN("color-brown", "Brown"),

	/** The green. */
	GREEN("color-green", "Green"),

	/** The purple. */
	PURPLE("color-purple", "Purple"),

	/** The yellow. */
	YELLOW("color-yellow", "Yellow");

	/** The code. */
	private final String code;

	/** The label. */
	private final String label;


	/**
	 * Instantiates a new themes.
	 *
	 * @param code
	 *           the code
	 * @param label
	 *           the label
	 */
	private Theme(final String code, final String label)
	{
		this.code = code.intern();
		this.label = label.intern();
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

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

}
