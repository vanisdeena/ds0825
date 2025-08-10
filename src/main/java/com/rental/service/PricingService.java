package com.rental.service;

import com.rental.model.ToolType;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class PricingService {
    private PricingService() {}

    public static boolean isWeekend(LocalDate d) {
        DayOfWeek w = d.getDayOfWeek();
        return (w == DayOfWeek.SATURDAY || w == DayOfWeek.SUNDAY);
    }

    /** Whether the given day is chargeable for a tool type, considering holiday/weekend rules. */
    public static boolean isChargeable(ToolType type, LocalDate d) {
        if (HolidayService.isHoliday(d)) return type.isHolidayCharge();
        if (isWeekend(d)) return type.isWeekendCharge();
        return type.isWeekdayCharge();
    }

    /** Count chargeable days from the day after checkout through the due date inclusive. */
    public static int countChargeDays(LocalDate checkoutDate, int rentalDays, ToolType type) {
        int charge = 0;
        LocalDate cur = checkoutDate.plusDays(1);
        for (int i = 0; i < rentalDays; i++) {
            if (isChargeable(type, cur)) charge++;
            cur = cur.plusDays(1);
        }
        return charge;
    }
}
