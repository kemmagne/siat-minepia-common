package org.guce.siat.common.utils.enums;


/**
 * The Enum ValidationType.
 */
public enum ValidationType
{

	/** The content has document type. */
	CONTENT_HAS_DOCUMENT_TYPE("Validation_contentHasDocumentType"),

	/** The content has guce number. */
	CONTENT_HAS_GUCE_NUMBER("Validation_contentHasGuceNumber"),

	/** The content has num message. */
	CONTENT_HAS_NUM_MESSAGE("Validation_contentHasNumMessage"),

	/** The content has siat number. */
	CONTENT_HAS_SIAT_NUMBER("Validation_contentHasSiatNumber"),

	/** The content has code decision. */
	CONTENT_HAS_CODE_DECISION("Validation_contentHasCodeDecision"),

	/** The correspondence file fileitems. */
	CORRESPONDENCE_FILE_FILEITEMS("Validation_correspondenceFileAndFileItems"),

	/** The validate flow. */
	VALIDATE_FLOW("Validation_validateFlow"),

	/** The validate last flow. */
	VALIDATE_LAST_FLOW("Validation_validateLastFlow"),

	/** The cancel request unauthorized. */
	CANCEL_REQUEST_UNAUTHORIZED("Validation_cancelRequestUnauthorized");


	/** The code. */
	private String code;


	/**
	 * Instantiates a new service item type.
	 *
	 * @param code
	 *           the code
	 */
	private ValidationType(final String code)
	{
		this.code = code;
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
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}

}
