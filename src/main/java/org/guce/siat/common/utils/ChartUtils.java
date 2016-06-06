package org.guce.siat.common.utils;

/**
 * The Class ChartUtils.
 */
public class ChartUtils
{

	/** The name. */
	private String name;

	/** The y. */
	private Integer y;

	/**
	 * Instantiates a new chart utils.
	 *
	 * @param name
	 *           the name
	 * @param y
	 *           the y
	 */
	public ChartUtils(final String name, final Integer y)
	{
		this.name = name;
		this.y = y;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *           the new name
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public Integer getY()
	{
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y
	 *           the new y
	 */
	public void setY(final Integer y)
	{
		this.y = y;
	}

}
