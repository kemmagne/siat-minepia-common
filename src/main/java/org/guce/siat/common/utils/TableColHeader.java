package org.guce.siat.common.utils;

/**
 * The Class TableColHeader.
 */
public class TableColHeader
{

	/** The label fr. */
	private String labelFr;

	/** The label en. */
	private String labelEn;

	/**
	 * Instantiates a new table col header.
	 */
	public TableColHeader()
	{
	}

	/**
	 * Instantiates a new table col header.
	 *
	 * @param labelFr
	 *           the label fr
	 * @param labelEn
	 *           the label en
	 */
	public TableColHeader(final String labelFr, final String labelEn)
	{
		this.labelFr = labelFr;
		this.labelEn = labelEn;
	}

	/**
	 * Gets the label fr.
	 *
	 * @return the label fr
	 */
	public String getLabelFr()
	{
		return labelFr;
	}

	/**
	 * Sets the label fr.
	 *
	 * @param labelFr
	 *           the new label fr
	 */
	public void setLabelFr(final String labelFr)
	{
		this.labelFr = labelFr;
	}

	/**
	 * Gets the label en.
	 *
	 * @return the label en
	 */
	public String getLabelEn()
	{
		return labelEn;
	}

	/**
	 * Sets the label en.
	 *
	 * @param labelEn
	 *           the new label en
	 */
	public void setLabelEn(final String labelEn)
	{
		this.labelEn = labelEn;
	}
}
