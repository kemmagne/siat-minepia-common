package org.guce.siat.common.utils.enums;

/**
 * The Enum PositionType.
 */
public enum PositionType {

    /**
     * The administrateur.
     */
    ADMINISTRATEUR("ADMINISTRATEUR", "Administrateur", "Administrator"),
    /**
     * The ministre.
     */
    MINISTRE("MINISTRE", "Ministre", "Minister"),
    /**
     * The secretaire general.
     */
    SECRETAIRE_GENERAL("SECRETAIRE_GENERAL", "Secrétaire Général", "General Secretary"),
    /**
     * The directeur.
     */
    DIRECTEUR("DIRECTEUR", "Directeur", "Manager"),
    /**
     * The sous directeur.
     */
    SOUS_DIRECTEUR("SOUS_DIRECTEUR", "Sous Directeur", "Assistant Director"),
    /**
     * The chef service.
     */
    CHEF_SERVICE("CHEF_SERVICE", "Chef Service", "Head Service"),
    /**
     * The chef bureau.
     */
    CHEF_BUREAU("CHEF_BUREAU", "Chef Bureau", "Head Office"),
    /**
     * The chef secteur.
     */
    CHEF_SECTEUR("CHEF_SECTEUR", "Chef Secteur", "Head Sector"),
    /**
     * The agent.
     */
    AGENT("AGENT", "Agent", "Agent"),
    /**
     * The superviseur.
     */
    SUPERVISEUR("SUPERVISEUR", "Superviseur", "Supervisor"),
    STATISTIQUE("STATISTIQUE", "Statistique", "Statistic"),
    /**
     * The observateur.
     */
    OBSERVATEUR("OBSERVATEUR", "Observateur", "Observer");

    /**
     * The code.
     */
    private final String code;

    /**
     * The label fr.
     */
    private final String labelFr;

    /**
     * The label en.
     */
    private final String labelEn;

    /**
     * Instantiates a new position type.
     *
     * @param code the code
     * @param labelFr the label fr
     * @param labelEn the label en
     */
    private PositionType(final String code, final String labelFr, final String labelEn) {
        this.code = code.intern();
        this.labelFr = labelFr.intern();
        this.labelEn = labelEn.intern();
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the label fr.
     *
     * @return the labelFr
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * Gets the label en.
     *
     * @return the labelEn
     */
    public String getLabelEn() {
        return labelEn;
    }

}
