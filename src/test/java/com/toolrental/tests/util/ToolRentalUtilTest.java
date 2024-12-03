package com.toolrental.tests.util;

import com.toolrental.util.DateUtils;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ToolRentalUtilTest {

    @Test
    public void testUtils() {
        assertFalse(DateUtils.isWeekend(DayOfWeek.WEDNESDAY));
        assertTrue(DateUtils.isWeekend(DayOfWeek.SATURDAY));

        assertTrue(DateUtils.isHoliday(LocalDate.of(2024, 7, 4))); //4th of July
        assertFalse(DateUtils.isHoliday(LocalDate.of(2024, 7, 5)));
        assertTrue(DateUtils.isHoliday(LocalDate.of(2024, 9, 2))); //labor day
        assertFalse(DateUtils.isHoliday(LocalDate.of(2024, 9, 3)));
    }

}
