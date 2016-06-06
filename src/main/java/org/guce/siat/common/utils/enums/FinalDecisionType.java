package org.guce.siat.common.utils.enums;

/**
 * The Enum FinalDecisionType.
 */
public enum FinalDecisionType
{

	/** The cct. */
	CCT("Certificat Controle Technique"),

	/** The ap. */
	AP("Autorisation préalable d'enlèvement"),

	/** The rd. */
	RD("Refoulement/destriction"),

	/** The rejet. */
	REJET("Rejet"),

	/** The annulation. */
	ANNULATION("Annulation"),
	
	/** The co. */
	CO("Autorisation certificat d'origine"),
	
	/** The cc. */
	CC("Autorisation Cacao Café");

	/** The type. */
	private final String type;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Instantiates a new final decision type.
	 *
	 * @param type
	 *           the type
	 */
	private FinalDecisionType(final String type)
	{
		this.type = type;
	}

}
