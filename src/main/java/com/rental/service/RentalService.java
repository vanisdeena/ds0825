package com.rental.service;

import com.rental.ToolCatalog;
import com.rental.exception.InvalidDiscountException;
import com.rental.exception.InvalidRentalDaysException;
import com.rental.model.RentalAgreement;
import com.rental.model.Tool;
import com.rental.model.ToolType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalService {

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) throw new InvalidRentalDaysException(rentalDays);
        if (discountPercent < 0 || discountPercent > 100) throw new InvalidDiscountException(discountPercent);

        Tool tool = ToolCatalog.get(toolCode);
        ToolType type = tool.getType();
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);

        int chargeDays = PricingService.countChargeDays(checkoutDate, rentalDays, type);

        BigDecimal daily = type.getDailyCharge();
        BigDecimal preDiscount = daily.multiply(BigDecimal.valueOf(chargeDays)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountAmt = preDiscount
                .multiply(BigDecimal.valueOf(discountPercent))
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscount.subtract(discountAmt).setScale(2, RoundingMode.HALF_UP);

        return new RentalAgreement(
                tool.getCode(),
                type.getDisplayName(),
                tool.getBrand(),
                rentalDays,
                checkoutDate,
                dueDate,
                daily,
                chargeDays,
                preDiscount,
                discountPercent,
                discountAmt,
                finalCharge
        );
    }
}
