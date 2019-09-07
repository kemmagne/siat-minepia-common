package org.guce.siat.common.utils.enums;

/**
 * The Enum AuthoritiesConstants.
 */
public enum AuthorityConstants
{

	//Authorities of type 'ADMINISTRATION'
	/** The root. */
	ROOT("ROOT", "ROOT"),

	/** The admin ministere. */
	ADMIN_MINISTERE("ADMIN_MIN", "Administrateur Ministère"),

	/** The admin organisme. */
	ADMIN_ORGANISME("ADMIN_ORG", "Administrateur Organisme"),


	//Authorities of type 'SUPERVISION'

	/** The superviseur. */
	SUPERVISEUR("SUPER", "Superviseur"),

	/** The consulter. */
	CONSULTER("CONS", "Consulter"),

	//Authorities of type 'COTATION'
	/** The AGEN t_ cotatio n_1. */
	AGENT_COTATION_1("AC1", "Agent de Cotation 1"),

	/** The AGEN t_ cotatio n_2. */
	AGENT_COTATION_2("AC2", "Agent de Cotation 2"),

	/** The AGEN t_ cotatio n_3. */
	AGENT_COTATION_3("AC3", "Agent de Cotation 3"),

	/** The AGEN t_ cotatio n_4. */
	AGENT_COTATION_4("AC4", "Agent de Cotation 4"),

	/** The AGEN t_ cotatio n_5. */
	AGENT_COTATION_5("AC5", "Agent de Cotation 5"),

	/** The AGEN t_ cotatio n_6. */
	AGENT_COTATION_6("AC6", "Agent de Cotation 6"),


	//Authorities of type 'DECISION'
	/** The agent recevabilite. */
	AGENT_RECEVABILITE("AR", "Agent de Recevabilité"),

	/** The inspecteur. */
	INSPECTEUR("INS", "Inspecteur"),

	/** The controleur. */
	CONTROLEUR("CONT", "Contrôleur"),

	/** The laboratoire. */
	LABORATOIRE("LAB", "Laboratoire"),

	/** The societe traitement. */
	SOCIETE_TRAITEMENT("TREAT", "Société de Traitement"),

	/** The signataire. */
	SIGNATAIRE("SIGN", "Signataire"),

	/** The responsable traitement. */
	RESPONSABLE_TRAITEMENT("RT", "Responsable de Traitement"),

	/** The importateur. */
	IMPORTATEUR("IMP", "Importateur"),

	/** The douane. */
	DOUANE("DOUANE", "Douane"),
        
        STATISTIQUE("STAT", "Statistiques"),
        
	/** The consignataire navire. */
	CONSIGNATAIRE_NAVIRE("CONS_NAV", "Consignataire Navire");



	/** The code. */
	private final String code;

	/** The label. */
	private final String label;

	/**
	 * Instantiates a new authorities constants.
	 *
	 * @param code
	 *           the code
	 * @param label
	 *           the label
	 */
	private AuthorityConstants(final String code, final String label)
	{
		this.code = code.intern();
		this.label = label.intern();
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
