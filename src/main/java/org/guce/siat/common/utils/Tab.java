package org.guce.siat.common.utils;

import java.util.List;
import java.util.Map;


/**
 * The Class Tab.
 */
public class Tab
{
	/** The label fr. */
	private String labelFr;

	/** The label en. */
	private String labelEn;

	/** The Code. */
	private String code;

	/** The fils. */
	private Boolean fils;

	/** The details. */
	private Boolean details;

	/** The detail. */
	private Map<Integer, String> detail;

	/** The table. */
	private List<Map<Integer, String>> table;

	/** The table col header. */
	private List<TableColHeader> tableColHeader;

	/** The col header. */
	private List<TableColHeader> colHeader;

	/** The repetable. */
	private List<String> repetable;

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
	/*
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
	 * Gets the table.
	 *
	 * @return the table
	 */
	public List<Map<Integer, String>> getTable()
	{
		return table;
	}

	/**
	 * Sets the table.
	 *
	 * @param table
	 *           the table
	 */
	public void setTable(final List<Map<Integer, String>> table)
	{
		this.table = table;
	}

	/**
	 * Gets the table col header.
	 *
	 * @return the table col header
	 */
	public List<TableColHeader> getTableColHeader()
	{
		return tableColHeader;
	}

	/**
	 * Sets the table col header.
	 *
	 * @param tableColHeader
	 *           the new table col header
	 */
	public void setTableColHeader(final List<TableColHeader> tableColHeader)
	{
		this.tableColHeader = tableColHeader;
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
	 * Gets the fils.
	 *
	 * @return the fils
	 */
	public Boolean getFils()
	{
		return fils;
	}

	/**
	 * Sets the fils.
	 *
	 * @param fils
	 *           the new fils
	 */
	public void setFils(final Boolean fils)
	{
		this.fils = fils;
	}

	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public Boolean getDetails()
	{
		return details;
	}

	/**
	 * Sets the details.
	 *
	 * @param details
	 *           the new details
	 */
	public void setDetails(final Boolean details)
	{
		this.details = details;
	}

	/**
	 * Gets the detail.
	 *
	 * @return the detail
	 */
	public Map<Integer, String> getDetail()
	{
		return detail;
	}

	/**
	 * Sets the detail.
	 *
	 * @param detail
	 *           the detail
	 */
	public void setDetail(final Map<Integer, String> detail)
	{
		this.detail = detail;
	}

	/**
	 * Gets the col header.
	 *
	 * @return the col header
	 */
	public List<TableColHeader> getColHeader()
	{
		return colHeader;
	}

	/**
	 * Sets the col header.
	 *
	 * @param colHeader
	 *           the new col header
	 */
	public void setColHeader(final List<TableColHeader> colHeader)
	{
		this.colHeader = colHeader;
	}

	/**
	 * Gets the repetable.
	 *
	 * @return the repetable
	 */
	public List<String> getRepetable()
	{
		return repetable;
	}

	/**
	 * Sets the repetable.
	 *
	 * @param repetable
	 *           the new repetable
	 */
	public void setRepetable(final List<String> repetable)
	{
		this.repetable = repetable;
	}


}
