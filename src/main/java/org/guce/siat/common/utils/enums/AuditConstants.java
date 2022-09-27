package org.guce.siat.common.utils.enums;

/**
 * The Enum AuditConstants.
 */
public enum AuditConstants {
    /**
     * The save.
     */
    SAVE("SAVE"),
    /**
     * The update.
     */
    UPDATE("UPDATE"),
    /**
     * The delete.
     */
    DELETE("DELETE"),
    /**
     * The bad credentials.
     */
    BAD_CREDENTIALS("BAD_CREDENTIALS"),
    /**
     * The password reset audit operation type
     */
    PASSWORD_RESET("PASSWORD_RESET"),
    /**
     * The product type agent association operation type
     */
    PRODUCT_TYPE_AGENT_ASSOCIATION("PRODUCT_TYPE_AGENT_ASSOCIATION");

    /**
     * The code.
     */
    private final String code;

    /**
     * Instantiates a new persistence action.
     *
     * @param code the code
     */
    private AuditConstants(final String code) {
        this.code = code.intern();
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }
}
