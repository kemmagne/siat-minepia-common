package org.guce.siat.common.utils.enums;

/**
 * The Enum AttachmentType.
 */
public enum AttachmentType
{

	/** The facture. */
	FACTURE("FACTURE"),

	/** The pt. */
	POLICE_TECHNIQUE("POLICE TECHNIQUE"),

	/** The ft. */
	FICHE_TECHNIQUE("FICHE TECHNIQUE"),

	/** The clv. */
	CERTIFICAT_LIBRE_VENTE("CERTIFICAT DE LIBRE VENTE"),

	/** The autres. */
	AUTRES("AUTRES"),

	/** The dci. */
	DECLARATION_CONDITIONS_IMPORT("DECLARATION DES CONDITIONS D'IMPORT"),

	/** The ccd. */
	CERTIFICAT_CESSION_DOUANE("CERTIFICAT DE CESSION POUR DOUANE");

	/** The label. */
	private String label;


	/**
	 * Instantiates a new attachment type.
	 *
	 * @param label
	 *           the label
	 */
	private AttachmentType(final String label)
	{
		this.label = label;
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

}
