package com.rental;

import com.rental.model.RentalAgreement;
import com.rental.service.RentalService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        // Demo: LADW, 3 days, 10% discount, checkout 07/02/20
        RentalAgreement agreement = service.checkout("LADW", 3, 10, LocalDate.of(2020,7,2));
        agreement.print();
    }
}
