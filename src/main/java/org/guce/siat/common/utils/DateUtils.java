package org.guce.siat.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * The Class DateUtils.
 */
public final class DateUtils {

    /**
     * The Constant CONST_DURATION_OF_SECOND.
     */
    public static final long CONST_DURATION_OF_SECOND = 1000;

    /**
     * The Constant CONST_DURATION_OF_MINUTE.
     */
    public static final long CONST_DURATION_OF_MINUTE = CONST_DURATION_OF_SECOND * Constants.SIXTY;

    /**
     * The Constant CONST_DURATION_OF_HOURE.
     */
    public static final long CONST_DURATION_OF_HOURE = CONST_DURATION_OF_MINUTE * Constants.SIXTY;

    /**
     * The Constant CONST_DURATION_OF_DAY.
     */
    public static final long CONST_DURATION_OF_DAY = CONST_DURATION_OF_HOURE * 24;

    /**
     * The Constant PATTERN_DD_MM_YYYY.
     */
    public static final String PATTERN_DD_MM_YYYY = "yyyy-MM-dd";

    /**
     * The Constant PATTERN_YYYY_MM_DD_HH_MM_SS.
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * The Constant PATTERN_YYYY_MM_DD_HH_MM fr.
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_FR = "dd/MM/yyyy HH:mm";

    /**
     * The Constant PATTERN_YYYY_MM_DD_HH_MM_SS fr.
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_FR = "dd/MM/yyyy HH:mm:ss";

    /**
     * The Constant PATTERN_YYYY_MM_DD_HH_MM_SS_Z.
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_Z = "yyyy-MM-dd HH:mm:ss z";

    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_XXX = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_SSS_XXX = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * The Constant PATTERN_YYYY_MM_DD_HH24_MI_SS.
     */
    public static final String PATTERN_YYYY_MM_DD_HH24_MI_SS = "YYYY-MM-DD HH24:MI:SS";

    /**
     * The Constant PATTERN_YYYY_MM_DD_HH.
     */
    public static final String PATTERN_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

    /**
     * The Constant PATTERN_YYYY_MM_DD.
     */
    public static final String PATTERN_YYYY_MM_DD = "YYYY-MM-DD";

    /**
     * The Constant FRENCH_DATE.
     */
    public static final String FRENCH_DATE = "dd/MM/yyyy";

    /**
     * The Constant FRENCH_DATE_TIME.
     */
    public static final String FRENCH_DATE_TIME = "dd/MM/yyyy HH:mm";

    /**
     * The Constant GUCE_DATE.
     */
    public static final String GUCE_DATE = "yyyyMMdd";

    /**
     * The Constant HOURE_ONLY.
     */
    public static final String HOURE_ONLY = "HH:mm";

    /**
     * The Constant PATTERN_DD_MM_YY_HH_MM_SS.
     */
    public static final String PATTERN_DD_MM_YY_HH_MM_SS = "dd-MM-yy HH:mm:ss";

    /**
     * The Constant PATTERN_ddMMyyyy.
     */
    public static final String PATTERN_DDMMYYYY = "dd/MM/yyyy";

    /**
     * Instantiates a new date utils.
     */
    private DateUtils() {
    }

    /**
     * Gets the list annee.
     *
     * @return the list annee
     */
    public static List<Integer> getListAnnee() {
        final List<Integer> listAnnee = new ArrayList<>();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        final long anneeCourante = calendar.get(Calendar.YEAR);
        long annee = Constants.NINETEEN_SIXTY_TWO;

        while (annee < anneeCourante + Constants.FOUR) {
            listAnnee.add((int) annee);
            annee++;
        }
        return listAnnee;
    }

    /**
     * Gets the elapsed time.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @return the elapsed time
     */
    public static String getElapsedTime(final Date startTime, final Date endTime) {
        final long difference = Math.abs(endTime.getTime() - startTime.getTime());

        final long numberOfHoure = difference / CONST_DURATION_OF_HOURE;
        final long numberOfMinute = difference / CONST_DURATION_OF_MINUTE;
        final long restOfMinute = numberOfMinute % Constants.SIXTY;
        final long numberOfSecond = difference / CONST_DURATION_OF_SECOND;
        final long restOfSecond = numberOfSecond % Constants.SIXTY;

        return "(" + numberOfHoure + "h:" + restOfMinute + "m:" + restOfSecond + "s) - " + numberOfHoure + " heure(s) et "
                + restOfMinute + " minute(s) et " + restOfSecond + " seconde(s).";
    }

    /**
     * Format simple date.
     *
     * @param date the date
     * @return the string
     */
    public static String formatSimpleDate(final Date date) {
        final SimpleDateFormat simpleFormat = new SimpleDateFormat(PATTERN_DD_MM_YYYY);
        return simpleFormat.format(date);
    }

    /**
     * Format simple date.
     *
     * @param pattern the pattern
     * @param date the date
     * @return the string
     */
    public static String formatSimpleDate(final String pattern, final Date date) {
        final SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
        return simpleFormat.format(date);
    }

    /**
     * Format simple date from object.
     *
     * @param date the date
     * @return the string
     */
    public static String formatSimpleDateFromObject(final Object date) {
        if (date != null) {
            return new SimpleDateFormat(PATTERN_DD_MM_YYYY).format(date);
        }
        return null;
    }

    /**
     * Format simple date from object.
     *
     * @param pattern the pattern
     * @param date the date
     * @return the string
     */
    public static String formatSimpleDateFromObject(final String pattern, final Object date) {
        if (date != null) {
            return new SimpleDateFormat(pattern).format(date);
        }
        return null;
    }

    /**
     * Format simple date for oracle.
     *
     * @param date the date
     * @return the string
     */
    public static String formatSimpleDateForOracle(final Date date) {
        final SimpleDateFormat simpleFormat = new SimpleDateFormat(PATTERN_YYYY_MM_DD_HH_MM_SS);
        return simpleFormat.format(date);
    }

    public static Date parse(String date, String pattern) {
        final DateFormat simpleFormat = new SimpleDateFormat(pattern);

        try {
            return simpleFormat.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Gets the tomorrow.
     *
     * @param date the date
     * @return the tomorrow
     */
    public static Date getTomorrow(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * Gets the pattern yyyy mm dd hh mm ss.
     *
     * @return the pattern yyyy mm dd hh mm ss
     */
    public static String getPatternYyyyMmDdHhMmSs() {
        return PATTERN_YYYY_MM_DD_HH_MM_SS;
    }

    /**
     * Gets the difference indays.
     *
     * @param timestamp1 the timestamp1
     * @param timestamp2 the timestamp2
     * @return the difference indays
     */
    public static int getDifferenceIndays(final long timestamp1, final long timestamp2) {
        final int seconds = Constants.SIXTY;
        final int minutes = Constants.SIXTY;
        final int hours = Constants.TWENTYFOUR;
        final int millies = Constants.THOUSAND;
        long timestamp1Temp1 = timestamp1;
        long timestemp2Temp2 = timestamp2;
        long temp;
        if (timestamp1Temp1 < timestemp2Temp2) {
            temp = timestamp1Temp1;
            timestamp1Temp1 = timestemp2Temp2;
            timestemp2Temp2 = temp;
        }
        final Calendar startDate = Calendar.getInstance(TimeZone.getDefault());
        final Calendar endDate = Calendar.getInstance(TimeZone.getDefault());
        endDate.setTimeInMillis(timestamp1Temp1);
        startDate.setTimeInMillis(timestemp2Temp2);
        if ((timestamp1Temp1 - timestemp2Temp2) < 1 * hours * minutes * seconds * millies) {
            final int day1 = endDate.get(Calendar.DAY_OF_MONTH);
            final int day2 = startDate.get(Calendar.DAY_OF_MONTH);
            return day1 == day2 ? 0 : 1;
        }
        int diffDays = 0;
        startDate.add(Calendar.DAY_OF_MONTH, diffDays);
        while (startDate.before(endDate)) {
            startDate.add(Calendar.DAY_OF_MONTH, 1);
            diffDays++;
        }
        return diffDays;
    }

    /**
     * Adds the.
     *
     * @param date the date
     * @param calendarField the calendar field
     * @param amount the amount
     * @return the date
     */
    public static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * Adds the days.
     *
     * @param date the date
     * @param amount the amount
     * @return the date
     */
    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * Adds the hours.
     *
     * @param date the date
     * @param amount the amount
     * @return the date
     */
    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * Adds the minutes.
     *
     * @param date the date
     * @param amount the amount
     * @return the date
     */
    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * Adds the weeks.
     *
     * @param date the date
     * @param amount the amount
     * @return the date
     */
    public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * Sets the hour.
     *
     * @param date the date
     * @param hour the hour
     * @return the date
     */
    public static Date setHour(final Date date, final int hour) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * Sets the hour and minutes.
     *
     * @param date the date
     * @param hour the hour
     * @param minutes the minutes
     * @return the date
     */
    public static Date setHourAndMinutes(final Date date, final int hour, final int minutes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Checks if is same day.
     *
     * @param cal1 the cal1
     * @param cal2 the cal2
     * @return true, if is same day
     */
    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException();
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Checks if is same day.
     *
     * @param date1 the date1
     * @param date2 the date2
     * @return true, if is same day
     */
    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException();
        }
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * Day of week.
     *
     * @param date the date
     * @return the int
     */
    public static int dayOfWeek(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Gets the day of year.
     *
     * @param date the date
     * @return the day of year
     */
    public static int getDayOfYear(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Gets the year.
     *
     * @param date the date
     * @return the year
     */
    public static int getYear(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * Gets the hour from date.
     *
     * @param date the date
     * @return the hour from date
     */
    public static Short getHourFromDate(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (short) c.get(Calendar.HOUR);
    }

    /**
     * Gets the minutes from date.
     *
     * @param date the date
     * @return the minutes from date
     */
    public static Integer getMinutesFromDate(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * Gets the date between dates.
     *
     * @param d1 the d1
     * @param d2 the d2
     * @return the date between dates
     */
    public static Date getDateBetweenDates(final Date d1, final Date d2) {
        if (d1 != null && d2 != null) {
            if (d2.after(d1)) {
                final long delta = (Math.abs(d2.getTime() - d1.getTime())) / (CONST_DURATION_OF_DAY);
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(d1);
                calendar.add(Calendar.DAY_OF_YEAR, Math.round(delta / Constants.TWO));
                return calendar.getTime();
            } else {
                return d2;
            }
        } else {
            return new Date();
        }
    }

    /**
     * Gets the current year start date.
     *
     * @return the current year start date
     */
    public static Date getCurrentYearStartDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
    }

    /**
     * Gets the current year end date.
     *
     * @return the current year end date
     */
    public static Date getCurrentYearEndDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 11); // 11 = december
        calendar.set(Calendar.DAY_OF_MONTH, 31); // new years eve

        return calendar.getTime();
    }

}
