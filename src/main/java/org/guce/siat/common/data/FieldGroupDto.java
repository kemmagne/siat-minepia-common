package org.guce.siat.common.data;

import java.util.List;

import org.guce.siat.common.utils.Tab;




/**
 * The Class FieldGroupDto.
 *
 * @param <T>
 *           the generic type
 */
public class FieldGroupDto<T>
{

	/** The label fr. */
	private String labelFr;

	/** The label en. */
	private String labelEn;

	/** The field values. */
	private List<T> fieldValues;

	private List<Tab> tabs;

	/**
	 * Gets the tabs.
	 *
	 * @return the tabs
	 */
	public List<Tab> getTabs()
	{
		return tabs;
	}

	/**
	 * Sets the tabs.
	 *
	 * @param tabs
	 *           the new tabs
	 */
	public void setTabs(final List<Tab> tabs)
	{
		this.tabs = tabs;
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

	/**
	 * Gets the field values.
	 *
	 * @return the field values
	 */
	public List<T> getFieldValues()
	{
		return fieldValues;
	}

	/**
	 * Sets the field values.
	 *
	 * @param fieldValues
	 *           the new field values
	 */
	public void setFieldValues(final List<T> fieldValues)
	{
		this.fieldValues = fieldValues;
	}
}
