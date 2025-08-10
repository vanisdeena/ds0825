package com.rental.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class HolidayService {

    private HolidayService() {}

    /** Observed Independence Day: July 4; if Sat -> Fri (3rd), if Sun -> Mon (5th). */
    public static LocalDate observedIndependenceDay(int year) {
        LocalDate july4 = LocalDate.of(year, 7, 4);
        DayOfWeek dow = july4.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY) return july4.minusDays(1);
        if (dow == DayOfWeek.SUNDAY) return july4.plusDays(1);
        return july4;
    }

    /** Labor Day: first Monday in September. */
    public static LocalDate laborDay(int year) {
        LocalDate d = LocalDate.of(year, 9, 1);
        int add = (DayOfWeek.MONDAY.getValue() - d.getDayOfWeek().getValue() + 7) % 7;
        return d.plusDays(add);
    }

    public static boolean isHoliday(LocalDate d) {
        return d.equals(observedIndependenceDay(d.getYear())) || d.equals(laborDay(d.getYear()));
    }
}
