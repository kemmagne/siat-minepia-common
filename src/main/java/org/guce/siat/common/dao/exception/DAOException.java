package org.guce.siat.common.dao.exception;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;


/**
 * Exception levée par la couche d'accès aux données. Elle encapsule toutes les exceptions qui peuvent être levées par
 * les couches inférieures dont les exception de type SQLException
 */
public class DAOException extends RuntimeException
{

	/**
	 * Id de la version pour la sérialisation
	 */
	private static final long serialVersionUID = -344127810894845362L;

	/** Code erreur */
	private final String errorCode;

	/** Message d'erreur */
	private final String errorMessage;

	/**
	 * Contruit une nouvelle exception DAO avec une cause et un message.
	 *
	 * @param exception
	 *           Cause de l'exception
	 * @param msg
	 *           Message
	 */

	public DAOException(final String msg, final Throwable exception)
	{
		super(msg, exception);
		errorCode = null;
		errorMessage = null;
	}

	/**
	 * Contruit une nouvelle exception DAO avec une cause.
	 *
	 * @param exception
	 *           Cause de l'exception
	 */
	public DAOException(final Throwable exception)
	{
		super(exception);
		if (exception.getCause() instanceof SQLException)
		{
			final SQLException sqlException = (SQLException) exception.getCause();
			final String[] cause = DAOException.splitErrorCodeAndMessage(sqlException);
			if (cause.length > 1)
			{
				this.errorCode = cause[0].trim();
				this.errorMessage = cause[1].trim();
			}
			else
			{
				errorCode = null;
				errorMessage = null;
			}
		}
		else
		{
			errorCode = null;
			errorMessage = null;
		}
	}

	/**
	 * Contruit une nouvelle exception DAO avec un message
	 *
	 * @param msg
	 *           Message
	 */
	public DAOException(final String msg)
	{
		super(msg);
		errorCode = null;
		errorMessage = null;
	}

	/**
	 * Code erreur renvoyé par la base de données
	 *
	 * @return Code erreur renvoyée par la base de données
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * Message d'erreur renvoyé par la base de données
	 *
	 * @return Message d'erreur renvoyé par la base de données
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * Décompose le message renvoyé par la base de données
	 *
	 * @param sqlException
	 *           Exception levée par la base de données
	 * @return Message d'erreur décomposé
	 */
	public static String[] splitErrorCodeAndMessage(final SQLException sqlException)
	{
		final String[] messages = sqlException.getMessage().split("\\|");
		String[] cause = new String[]
		{ StringUtils.EMPTY, StringUtils.EMPTY };
		if (messages.length > 0)
		{
			cause = messages[0].split(":");
		}
		return cause;
	}

}
