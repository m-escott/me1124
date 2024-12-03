package com.toolrental.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Utility class providing methods to determine if a given date falls on a weekend or a recognized holiday.
 * <p>
 * This class includes methods to check if a particular {@link java.time.DayOfWeek} represents a weekend day,
 * as well as to identify if a specific {@link java.time.LocalDate} is a recognized holiday such as Independence Day
 * or Labor Day in the United States.
 * <p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class DateUtils {
    /**
     * Determines if the given {@link java.time.DayOfWeek} represents a weekend day (Saturday or Sunday).
     *
     * @param day the {@link java.time.DayOfWeek} to check
     * @return {@code true} if the day is Saturday or Sunday, {@code false} otherwise
     */
    public static boolean isWeekend(DayOfWeek day) {
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    /**
     * Determines if the provided {@link java.time.LocalDate} is a recognized holiday.
     * <p>
     * The method checks two holidays:
     * <ul>
     *     <li>Independence Day (July 4th) — adjusted if it falls on a Saturday or Sunday.</li>
     *     <li>Labor Day (first Monday of September).</li>
     * </ul>
     * <p>
     *
     * @param date the {@link java.time.LocalDate} to check
     * @return {@code true} if the date is either Independence Day or Labor Day, {@code false} otherwise
     */
    public static boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) independenceDay = independenceDay.minusDays(1);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) independenceDay = independenceDay.plusDays(1);

        LocalDate laborDay = LocalDate.of(year, Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}
