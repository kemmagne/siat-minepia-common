package org.guce.siat.common.utils.enums;

/**
 * The Enum BureauType.
 */
public enum BureauType
{
	/** The bureau central. */
	BUREAU_CENTRAL("Bureau central"),

	/** The bureau regional. */
	BUREAU_REGIONAL("Bureau régional");

	/** The code. */
	private String label;

	/**
	 * Instantiates a new bureau type.
	 *
	 * @param label
	 *           the label
	 */
	private BureauType(final String label)
	{
		this.label = label;
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
