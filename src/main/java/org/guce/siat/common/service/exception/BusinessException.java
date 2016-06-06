package org.guce.siat.common.service.exception;

/**
 * Exception levée par la couche métier. Elle encapsule toutes les exceptions qui peuvent être levées par les couches
 * inférieures dont les exception de type métier
 */
public class BusinessException extends RuntimeException
{

	/** Id de la version pour la sérialisation. */
	private static final long serialVersionUID = -344127810894845362L;

	/** sévérité de l'exception. */
	private final BusinessExceptionSeverity severity;

	/**
	 * Contruit une nouvelle exception métier avec une cause et un message.
	 *
	 * @param msg
	 *           Message
	 * @param exception
	 *           Cause de l'exception
	 * @param severity
	 *           sévérité
	 */
	public BusinessException(final String msg, final Throwable exception, final BusinessExceptionSeverity severity)
	{
		super(msg, exception);
		this.severity = severity;
	}

	/**
	 * Contruit une nouvelle exception métier avec une cause.
	 *
	 * @param exception
	 *           Cause de l'exception
	 * @param severity
	 *           sévérité
	 */
	public BusinessException(final Throwable exception, final BusinessExceptionSeverity severity)
	{
		super(exception);
		this.severity = severity;
	}

	/**
	 * Contruit une nouvelle exception métier avec un message.
	 *
	 * @param msg
	 *           Message
	 * @param severity
	 *           sévérité
	 */
	public BusinessException(final String msg, final BusinessExceptionSeverity severity)
	{
		super(msg);
		this.severity = severity;
	}

	/**
	 * Gets the severity.
	 *
	 * @return the severity
	 */
	public BusinessExceptionSeverity getSeverity()
	{
		return severity;
	}

}
