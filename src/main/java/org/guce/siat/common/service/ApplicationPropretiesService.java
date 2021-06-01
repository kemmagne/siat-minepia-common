package org.guce.siat.common.service;

/**
 * The Interface ApplicationPropretiesService.
 */
public interface ApplicationPropretiesService {

    /**
     * Gets the column separator.
     *
     * @return the column separator
     */
    String getColumnSeparator();

    /**
     * Gets the column separator.
     *
     * @return the custom column separator
     */
    String getCustomColumnSeparator();

    /**
     * Gets the row separator.
     *
     * @return the row separator
     */
    String getRowSeparator();

    /**
     * Gets the row separator.
     *
     * @return the custom row separator
     */
    String getCustomRowSeparator();

    /**
     * Gets the app version.
     *
     * @return the app version
     */
    String getAppVersion();

    /**
     * Gets the app env.
     *
     * @return the app env
     */
    String getAppEnv();

    /**
     * Gets the attachement folder.
     *
     * @return the attachement folder
     */
    String getAttachementFolder();

    /**
     * Gets the repeatable separator.
     *
     * @return the repeatable separator
     */
    String getRepeatableSeparator();

    /**
     * Gets the max retry time number.
     *
     * @return the max retry time number
     */
    String getMaxRetryTimeNumber();

    /**
     * Gets the number of minutes must be exceeded between two send retry.
     *
     * @return the number of minutes must be exceeded between two send retry
     */
    String getNumberMinuteExceedBetweenTwoRetry();
}
