package org.guce.siat.common.utils.ebms;

/**
 * UtilitiesException represents all kinds of exception occuring in the utility functions.
 */
public class UtilitiesException extends GenericException
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8560869380787114168L;

	/**
	 * Creates a new instance of UtilitiesException.
	 */
	public UtilitiesException()
	{
	}

	/**
	 * Creates a new instance of UtilitiesException.
	 *
	 * @param message
	 *           the error message.
	 */
	public UtilitiesException(final String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance of UtilitiesException.
	 *
	 * @param cause
	 *           the cause of this exception.
	 */
	public UtilitiesException(final Throwable cause)
	{
		super(cause);
	}

	/**
	 * Creates a new instance of UtilitiesException.
	 *
	 * @param message
	 *           the error message.
	 * @param cause
	 *           the cause of this exception.
	 */
	public UtilitiesException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
