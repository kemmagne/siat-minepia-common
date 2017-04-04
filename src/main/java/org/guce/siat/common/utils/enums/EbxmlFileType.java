package org.guce.siat.common.utils.enums;

/**
 * The Enum EbxmlFileType.
 */
public enum EbxmlFileType
{

	/** The F l_ c t_01. */
	APERAK("Aperak"),

	/** The F l_ c t_02. */
	STANDARD("Standard");


	/** The label. */
	private final String label;

	/**
	 * Instantiates a new flow code.
	 *
	 * @param label
	 *           the label
	 */
	private EbxmlFileType(final String label)
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


}
