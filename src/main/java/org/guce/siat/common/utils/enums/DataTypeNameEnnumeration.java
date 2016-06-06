package org.guce.siat.common.utils.enums;

/**
 * The Enum LocaleValues.
 */
public enum DataTypeNameEnnumeration
{

	/** The observation. */
	OBSERVATION("Observation"),

	/** The date inspection demandee date. */
	DATE_INSPECTION_DEMANDEE_DATE("Date inspection demandée: Date"),

	/** The date inspection demandee heure. */
	DATE_INSPECTION_DEMANDEE_HEURE("Date inspection demandée: Heure"),

	/** The societe traitement sohaitee code. */
	SOCIETE_TRAITEMENT_SOHAITEE_CODE("Société de traitement sohaitée: Code"),

	/** The societe traitement sohaitee libelle. */
	SOCIETE_TRAITEMENT_SOHAITEE_LIBELLE("Société de traitement sohaitée: Libellé");



	/** The code. */
	private final String code;

	/**
	 * Instantiates a new locale values.
	 *
	 * @param code
	 *           the code
	 */
	private DataTypeNameEnnumeration(final String code)
	{
		this.code = code.intern();
	}


	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return this.code;
	}
}
