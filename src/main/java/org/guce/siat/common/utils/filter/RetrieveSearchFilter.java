package org.guce.siat.common.utils.filter;

import java.util.Date;

import org.guce.siat.common.model.FileType;


/**
 * The Class RetrieveSearchFilter.
 */
public class RetrieveSearchFilter
{

	/** The from date. */
	private Date fromDate;

	/** The to date. */
	private Date toDate;

	/** The file type. */
	private FileType fileType;

	/**
	 * Gets the from date.
	 *
	 * @return the fromDate
	 */
	public Date getFromDate()
	{
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate
	 *           the fromDate to set
	 */
	public void setFromDate(final Date fromDate)
	{
		this.fromDate = fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the toDate
	 */
	public Date getToDate()
	{
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate
	 *           the toDate to set
	 */
	public void setToDate(final Date toDate)
	{
		this.toDate = toDate;
	}

	/**
	 * Gets the file type.
	 *
	 * @return the fileType
	 */
	public FileType getFileType()
	{
		return fileType;
	}

	/**
	 * Sets the file type.
	 *
	 * @param fileType
	 *           the fileType to set
	 */
	public void setFileType(final FileType fileType)
	{
		this.fileType = fileType;
	}



}
