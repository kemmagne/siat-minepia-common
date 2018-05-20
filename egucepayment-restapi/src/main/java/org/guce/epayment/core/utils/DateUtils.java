package org.guce.epayment.core.utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 *
 * @author tadzotsa
 */
public interface DateUtils {

    static final String DATE_TIME_PATTERN_FR = "dd/MM/yyyy HH:mm".intern();

    static final String DATE_PATTERN_FR = "dd/MM/yyyy".intern();

    static final String TIME_PATTERN_FR = "HH:mm".intern();

    static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    static LocalDateTime resetTime(LocalDateTime dateTime) {

        dateTime = dateTime.withHour(Constants.ZERO);
        dateTime = dateTime.withMinute(Constants.ZERO);
        dateTime = dateTime.withSecond(Constants.ZERO);
        dateTime = dateTime.withNano(Constants.ZERO);

        return dateTime;
    }

    static LocalDateTime getSittingDate(final LocalTime minTime, final LocalTime maxTime) {

        final LocalDateTime now = LocalDateTime.now();
        LocalDateTime sittingDate = LocalDateTime.now();

        sittingDate = sittingDate.withSecond(Constants.ZERO);
        sittingDate = sittingDate.withNano(Constants.ZERO);

        final boolean apresJournee = now.getHour() > maxTime.getHour()
                || (now.getHour() == maxTime.getHour() && now.getMinute() > maxTime.getMinute());
        final boolean avantJournee = now.getHour() < minTime.getHour()
                || (now.getHour() == minTime.getHour() && now.getMinute() <= minTime.getMinute());

        final DayOfWeek dayOfWeek = now.getDayOfWeek();

        switch (dayOfWeek) {

            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:

                if (avantJournee || apresJournee) {

                    sittingDate = sittingDate.withHour(minTime.getHour());
                    sittingDate = sittingDate.withMinute(minTime.getMinute());

                    if (apresJournee) {
                        sittingDate = sittingDate.plusDays(Constants.ONE);
                    }
                }
                break;

            case FRIDAY:

                if (avantJournee) {

                    sittingDate = sittingDate.withHour(minTime.getHour());
                    sittingDate = sittingDate.withMinute(minTime.getMinute());
                } else if (apresJournee) {
                    sittingDate = goToNextWeek(sittingDate, minTime.getHour(), minTime.getMinute());
                }
                break;

            case SATURDAY:
            case SUNDAY:
                sittingDate = goToNextWeek(sittingDate, minTime.getHour(), minTime.getMinute());
        }

        return sittingDate;
    }

    static LocalDateTime goToNextWeek(LocalDateTime dt, int minHeure, int minMinute) {

        dt = dt.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        dt = dt.withHour(minHeure);
        dt = dt.withMinute(minMinute);

        return dt;
    }

}
