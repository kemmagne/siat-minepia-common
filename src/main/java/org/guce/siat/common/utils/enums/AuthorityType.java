package org.guce.siat.common.utils.enums;

/**
 * The Enum AuthorityType.
 */
public enum AuthorityType {

    /**
     * The cotation.
     */
    COTATION("Cotation"),
    /**
     * The decision.
     */
    DECISION("Decision"),
    /**
     * The administration.
     */
    ADMINISTRATION("Administration"),
    STATISTIQUE("Statistique"),
    /**
     * The supervision.
     */
    SUPERVISION("Supervision");

    /**
     * The label.
     */
    private final String label;

    /**
     * Instantiates a new authority type.
     *
     * @param label the label
     */
    private AuthorityType(final String label) {
        this.label = label.intern();
    }

    /**
     * Gets the label.
     *
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

}
