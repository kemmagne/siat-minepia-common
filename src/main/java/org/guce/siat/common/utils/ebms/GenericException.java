package org.guce.siat.common.utils.ebms;

/**
 * GenericException represents all kinds of non-runtime exception in the application.
 */
public class GenericException extends Exception
{

	/**
	 *
	 */
	private static final long serialVersionUID = 4309686492524988483L;

	/**
	 * Creates a new instance of GenericException.
	 */
	public GenericException()
	{
		super();
	}

	/**
	 * Creates a new instance of GenericException.
	 *
	 * @param message
	 *           the error message.
	 */
	public GenericException(final String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance of GenericException.
	 *
	 * @param cause
	 *           the cause of this exception.
	 */
	public GenericException(final Throwable cause)
	{
		super(cause);
	}

	/**
	 * Creates a new instance of GenericException.
	 *
	 * @param message
	 *           the error message.
	 * @param cause
	 *           the cause of this exception.
	 */
	public GenericException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Returns a string representation of this exception.
	 *
	 * @return a string representation of this exception.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return StringUtilities.toString(this);
	}
}
