package org.guce.siat.common.utils.enums;


/**
 * The Enum AdministrationType.
 */
public enum AdministrationType
{

	/** The ministry. */
	MINISTRY("MINISTRY", "Ministère", "Ministry"),

	/** The organism. */
	ORGANISM("ORGANISM", "Organisme", "Organism"),

	/** The sub department. */
	SUB_DEPARTMENT("SUB_DEPARTMENT", "Sous Direction", "SubDepartment"),

	/** The service. */
	SERVICE("SERVICE", "Service", "Service"),

	/** The bureau. */
	BUREAU("BUREAU", "Bureau", "Bureau"),

	/** The laboratory. */
	LABORATORY("LABORATORY", "Laboratoire", "Laboratory"),

	/** The treatment company. */
	TREATMENT_COMPANY("TREATMENT_COMPANY", "Société de traitement", "TreatmentCompany");


	/** The code. */
	private final String code;

	/** The label. */
	private final String label;

	/** The class name. */
	private final String className;


	/**
	 * Instantiates a new administration type.
	 *
	 * @param code
	 *           the code
	 * @param label
	 *           the label
	 */
	private AdministrationType(final String code, final String label, final String className)
	{
		this.code = code.intern();
		this.label = label.intern();
		this.className = className.intern();
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel()
	{
		return this.label;
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
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassName()
	{
		return className;
	}



}
