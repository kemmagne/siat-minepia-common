package org.guce.siat.common.utils.filter;

import java.util.Date;


/**
 * The Class AuditFilter.
 */
public class AuditFilter
{

	/** The begin date. */
	private Date beginDate;

	/** The end date. */
	private Date endDate;

	/**
	 * Instantiates a new audit filter.
	 */
	public AuditFilter()
	{
	}

	/**
	 * Gets the begin date.
	 *
	 * @return the begin date
	 */
	public Date getBeginDate()
	{
		return beginDate;
	}

	/**
	 * Sets the begin date.
	 *
	 * @param beginDate
	 *           the new begin date
	 */
	public void setBeginDate(final Date beginDate)
	{
		this.beginDate = beginDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *           the new end date
	 */
	public void setEndDate(final Date endDate)
	{
		this.endDate = endDate;
	}

}
