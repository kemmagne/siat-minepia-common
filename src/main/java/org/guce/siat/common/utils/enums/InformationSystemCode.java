package org.guce.siat.common.utils.enums;



/**
 * The Enum InformationSystemCode.
 */
public enum InformationSystemCode
{

	/** The ct. */
	CT("Contrôle technique"),

	/** The cct. */
	CCT("Certificat de contrôle technique"),

	/** The ap. */
	AP("Authorisation Préalable"),

	/** The co. */
	CO("Certificat d'origine"),

	/** The am. */
	AM("Affaires Maritime"),

	/** The ft. */
	FT("Frets Terrestres"),

	/** The sf. */
	SF("Suivi Financiers"),

	/** The cc. */
	CC("Cacao Café"),

	/** Bultin de qualité CC suite d'activation de la décision par article **/
	BQ("Bultin de qualité CC");

	/** The label. */
	private String label;


	/**
	 * Instantiates a new information system code.
	 *
	 * @param label
	 *           the label
	 */
	private InformationSystemCode(final String label)
	{
		this.label = label.intern();

	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *           the new label
	 */
	public void setLabel(final String label)
	{
		this.label = label;
	}

}
