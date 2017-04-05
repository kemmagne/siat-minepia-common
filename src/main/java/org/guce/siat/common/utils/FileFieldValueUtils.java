/*
 *
 */
package org.guce.siat.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.guce.siat.common.service.ApplicationPropretiesService;
import org.guce.siat.common.utils.ebms.EbmsUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FileFieldValueUtils.
 */
public final class FileFieldValueUtils {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileFieldValueUtils.class);

    /**
     * The application propreties service.
     */
    public static ApplicationPropretiesService applicationPropretiesService;

    /**
     * Instantiates a new file field value utils.
     */
    private FileFieldValueUtils() {
    }

    /**
     * Format date pattern.
     *
     * @param date the date
     * @return the string
     */
    public static String formatDatePattern(final String date) {
        final StringBuilder builder = new StringBuilder();

        if (date != null && date.length() == 8) {
            builder.append(date.substring(6, 8)).append("/").append(date.substring(4, 6)).append("/").append(date.substring(0, 4));
            return builder.toString();
        }
        return "-";
    }
    /**
     * Format date pattern.
     *
     * @param date the date
     * @return the string
     */
    public static String guceFormatDatePattern(final String date) {
        final StringBuilder builder = new StringBuilder();

        if (date != null && date.length() == 8) {
            builder.append(date.substring(0, 2)).append("/").append(date.substring(2, 4)).append("/").append(date.substring(4, 8));
            return builder.toString();
        }
        return "-";
    }

    /**
     * Format date signataire pattern.
     *
     * @param dateUTC the date utc
     * @return the string
     */
    public static String formatDateSignatairePattern(final String dateUTC) {

        Date signatureDate;
        final SimpleDateFormat signataureFormat = new SimpleDateFormat(DateUtils.FRENCH_DATE);

        try {
            signatureDate = EbmsUtility.UTC2Date(dateUTC);

            if (signatureDate != null) {
                return signataureFormat.format(signatureDate);
            }
        } catch (final Exception e) {

            LOG.error("Error to parse date UTC: " + e.getMessage());
            return "-";
        }
        return "-";

    }

    /**
     * Adds the value repetable.
     *
     * @param values the values
     * @param columns the columns
     * @param fks the fks
     * @return the string
     */
    public static String addValueRepetable(final List<String> values, final List<String> columns, final List<String> fks) {
        final StringBuilder analyseValuesBuilder = new StringBuilder();
        final String col = applicationPropretiesService.getColumnSeparator();
        final String row = applicationPropretiesService.getRowSeparator();
        final String fk = applicationPropretiesService.getRepeatableSeparator();
        for (int i = 0; i < columns.size(); i++) {
            analyseValuesBuilder.append(columns.get(i));
            if (i != columns.size() - 1) {
                analyseValuesBuilder.append(col);
            }
            if (i == columns.size() - 1 && fks != null) {
                analyseValuesBuilder.append(col);
                analyseValuesBuilder.append(fk);
            }

        }
        /**
         * * S'il existe des bloc repétable niveau 1 **
         */
        if (fks != null) {
            for (int i = 0; i < fks.size(); i++) {
                analyseValuesBuilder.append(fks.get(i));
                if (i != fks.size() - 1) {
                    analyseValuesBuilder.append(col);
                }
            }
        }

        int j = 1;
        analyseValuesBuilder.append(row);
        for (int i = 0; i < values.size(); i++) {

            analyseValuesBuilder.append(values.get(i));
            if (j == columns.size()) {
                analyseValuesBuilder.append(row);
                j = 1;
            } else {
                analyseValuesBuilder.append(col);
                j++;
            }

        }

        return analyseValuesBuilder.toString();
    }

	/**
	 * Adds the value repetable custom.
	 *
	 * @param values
	 *           the values
	 * @param columns
	 *           the columns
	 * @param fks
	 *           the fks
	 * @return the string
	 */
    public static String addValueRepetableCustom(final List<String> values, final List<String> columns, final List<String> fks) {
        final StringBuilder analyseValuesBuilder = new StringBuilder();
        final String col = applicationPropretiesService.getCustomColumnSeparator();
        final String row = applicationPropretiesService.getCustomRowSeparator();
        final String fk = applicationPropretiesService.getRepeatableSeparator();
        for (int i = 0; i < columns.size(); i++) {
            analyseValuesBuilder.append(columns.get(i));
            if (i != columns.size() - 1) {
                analyseValuesBuilder.append(col);
            }
            if (i == columns.size() - 1 && fks != null) {
                analyseValuesBuilder.append(col);
                analyseValuesBuilder.append(fk);
            }

        }
        /**
         * * S'il existe des bloc repétable niveau 1 **
         */
        if (fks != null) {
            for (int i = 0; i < fks.size(); i++) {
                analyseValuesBuilder.append(fks.get(i));
                if (i != fks.size() - 1) {
                    analyseValuesBuilder.append(col);
                }
            }
        }

        int j = 1;
        analyseValuesBuilder.append(row);
        for (int i = 0; i < values.size(); i++) {

            analyseValuesBuilder.append(values.get(i));
            if (j == columns.size()) {
                analyseValuesBuilder.append(row);
                j = 1;
            } else {
                analyseValuesBuilder.append(col);
                j++;
            }

        }

        return analyseValuesBuilder.toString();
    }

}
