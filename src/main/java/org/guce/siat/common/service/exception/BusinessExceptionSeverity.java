package org.guce.siat.common.service.exception;

/**
 * Type de sévérité de l'exception BusinessException
 *
 */
public enum BusinessExceptionSeverity
{
	/**
	 * Sévérité avertissement de l'exception
	 */
	SEVERITY_WARN,
	/**
	 * Sévérité information de l'exception
	 */
	SEVERITY_INFO,
	/**
	 * Sévérité erreur de l'exception
	 */
	SEVERITY_ERROR,
	/**
	 * Sévérité erreur fatale de l'exception
	 */
	SEVERITY_FATAL;

}
