package com.rental.model;

import java.math.BigDecimal;

public enum ToolType {
    LADDER("Ladder", new BigDecimal("1.99"), true, true, false),
    CHAINSAW("Chainsaw", new BigDecimal("1.49"), true, false, true),
    JACKHAMMER("Jackhammer", new BigDecimal("2.99"), true, false, false);

    private final String displayName;
    private final BigDecimal dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    ToolType(String displayName, BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.displayName = displayName;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getDisplayName() { return displayName; }
    public BigDecimal getDailyCharge() { return dailyCharge; }
    public boolean isWeekdayCharge() { return weekdayCharge; }
    public boolean isWeekendCharge() { return weekendCharge; }
    public boolean isHolidayCharge() { return holidayCharge; }
}
