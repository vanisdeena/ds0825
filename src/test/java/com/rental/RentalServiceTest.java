package com.rental;

import com.rental.exception.InvalidDiscountException;
import com.rental.exception.InvalidRentalDaysException;
import com.rental.model.RentalAgreement;
import com.rental.service.RentalService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RentalServiceTest {

    private final RentalService service = new RentalService();

    @Test
    public void test1_invalidDiscount() {
        assertThrows(InvalidDiscountException.class, () ->
                service.checkout("JAKR", 5, 101, LocalDate.of(2015,9,3)));
    }

    @Test
    public void test2_ladder_case() {
        RentalAgreement a = service.checkout("LADW", 3, 10, LocalDate.of(2020,7,2));
        assertEquals("LADW", a.getToolCode());
        assertEquals("Ladder", a.getToolType());
        assertEquals("Werner", a.getToolBrand());
        assertEquals(3, a.getRentalDays());
        assertEquals(LocalDate.of(2020,7,5), a.getDueDate());
        assertEquals(2, a.getChargeDays()); // weekend charges apply, holiday does not
        assertEquals("3.98", a.getPreDiscountCharge().toPlainString());
        assertEquals("0.40", a.getDiscountAmount().toPlainString());
        assertEquals("3.58", a.getFinalCharge().toPlainString());
    }

    @Test
    public void test3_chainsaw_case() {
        RentalAgreement a = service.checkout("CHNS", 5, 25, LocalDate.of(2015,7,2));
        assertEquals(LocalDate.of(2015,7,7), a.getDueDate());
        assertEquals(3, a.getChargeDays()); // 7/3 weekday charge, 7/4 observed holiday charge, 7/5 weekend no charge, 7/6 weekday charge
        assertEquals("4.47", a.getPreDiscountCharge().toPlainString());
        assertEquals("1.12", a.getDiscountAmount().toPlainString());
        assertEquals("3.35", a.getFinalCharge().toPlainString());
    }

    @Test
    public void test4_jackhammer_case_d() {
        RentalAgreement a = service.checkout("JAKD", 6, 0, LocalDate.of(2015,9,3));
        assertEquals(LocalDate.of(2015,9,9), a.getDueDate());
        assertEquals(3, a.getChargeDays()); // weekend no charge, Labor Day no charge
        assertEquals("8.97", a.getPreDiscountCharge().toPlainString());
        assertEquals("0.00", a.getDiscountAmount().toPlainString());
        assertEquals("8.97", a.getFinalCharge().toPlainString());
    }

    @Test
    public void test5_jackhammer_case_r() {
        RentalAgreement a = service.checkout("JAKR", 9, 0, LocalDate.of(2015,7,2));
        assertEquals(LocalDate.of(2015,7,11), a.getDueDate());
        assertEquals(5, a.getChargeDays()); // only weekdays charged
        assertEquals("14.95", a.getPreDiscountCharge().toPlainString());
        assertEquals("0.00", a.getDiscountAmount().toPlainString());
        assertEquals("14.95", a.getFinalCharge().toPlainString());
    }

    @Test
    public void test6_jackhammer_halfOff() {
        RentalAgreement a = service.checkout("JAKR", 4, 50, LocalDate.of(2020,7,2));
        assertEquals(LocalDate.of(2020,7,6), a.getDueDate());
        assertEquals(1, a.getChargeDays()); // 7/3 weekday, 7/4 observed holiday no charge for jackhammer, weekend no charge
        assertEquals("2.99", a.getPreDiscountCharge().toPlainString());
        assertEquals("1.50", a.getDiscountAmount().toPlainString());
        assertEquals("1.49", a.getFinalCharge().toPlainString());
    }

    @Test
    public void rentalDaysValidation() {
        assertThrows(InvalidRentalDaysException.class, () ->
                service.checkout("LADW", 0, 10, LocalDate.of(2020,7,2)));
    }
}
