package com.rental;

import com.rental.model.RentalAgreement;
import com.rental.service.RentalService;

import java.time.LocalDate;

public class SmokeTests {
    public static void main(String[] args) {
        RentalService s = new RentalService();
        // These should run without throwing and print key values:
        RentalAgreement t2 = s.checkout("LADW", 3, 10, LocalDate.of(2020,7,2));
        RentalAgreement t3 = s.checkout("CHNS", 5, 25, LocalDate.of(2015,7,2));
        RentalAgreement t4 = s.checkout("JAKD", 6, 0, LocalDate.of(2015,9,3));
        RentalAgreement t5 = s.checkout("JAKR", 9, 0, LocalDate.of(2015,7,2));
        RentalAgreement t6 = s.checkout("JAKR", 4, 50, LocalDate.of(2020,7,2));
        System.out.println("-- Test 2 --"); t2.print();
        System.out.println("-- Test 3 --"); t3.print();
        System.out.println("-- Test 4 --"); t4.print();
        System.out.println("-- Test 5 --"); t5.print();
        System.out.println("-- Test 6 --"); t6.print();
    }
}
