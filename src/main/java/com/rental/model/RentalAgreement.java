package com.rental.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final BigDecimal dailyRentalCharge;
    private final int chargeDays;
    private final BigDecimal preDiscountCharge;
    private final int discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MM/dd/yy");
    private static final NumberFormat CURRENCY_FMT = NumberFormat.getCurrencyInstance(Locale.US);
    private static final NumberFormat PERCENT_FMT = NumberFormat.getPercentInstance(Locale.US);

    static {
        PERCENT_FMT.setMaximumFractionDigits(0);
        PERCENT_FMT.setMinimumFractionDigits(0);
    }

    public RentalAgreement(
            String toolCode,
            String toolType,
            String toolBrand,
            int rentalDays,
            LocalDate checkoutDate,
            LocalDate dueDate,
            BigDecimal dailyRentalCharge,
            int chargeDays,
            BigDecimal preDiscountCharge,
            int discountPercent,
            BigDecimal discountAmount,
            BigDecimal finalCharge
    ) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public String getToolCode() { return toolCode; }
    public String getToolType() { return toolType; }
    public String getToolBrand() { return toolBrand; }
    public int getRentalDays() { return rentalDays; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getDueDate() { return dueDate; }
    public BigDecimal getDailyRentalCharge() { return dailyRentalCharge; }
    public int getChargeDays() { return chargeDays; }
    public BigDecimal getPreDiscountCharge() { return preDiscountCharge; }
    public int getDiscountPercent() { return discountPercent; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public BigDecimal getFinalCharge() { return finalCharge; }

    public String formatDate(LocalDate d) { return d.format(DATE_FMT); }
    public String formatCurrency(BigDecimal amt) { return CURRENCY_FMT.format(amt); }
    public String formatPercent(int pct) { return pct + "%"; }

    /** Print the rental agreement to stdout matching the required formatting. */
    public void print() {
        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Tool brand: " + toolBrand);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + formatDate(checkoutDate));
        System.out.println("Due date: " + formatDate(dueDate));
        System.out.println("Daily rental charge: " + formatCurrency(dailyRentalCharge));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + formatCurrency(preDiscountCharge));
        System.out.println("Discount percent: " + formatPercent(discountPercent));
        System.out.println("Discount amount: " + formatCurrency(discountAmount));
        System.out.println("Final charge: " + formatCurrency(finalCharge));
    }
}
