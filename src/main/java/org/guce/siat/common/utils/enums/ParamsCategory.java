package org.guce.siat.common.utils.enums;

/**
 * The Enum ParamsCategory.
 */
public enum ParamsCategory {

    /**
     * The gr.
     */
    GR("Risk_Management"),
    /**
     * The gn.
     */
    GN("General");

    /**
     * The code.
     */
    private final String code;

    /**
     * Instantiates a new params category.
     *
     * @param code the code
     */
    private ParamsCategory(final String code) {
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
